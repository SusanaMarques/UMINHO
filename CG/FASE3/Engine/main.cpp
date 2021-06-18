#include "headers/Transformacao.h"
#include <csignal>
#include <string>


//#pragma clang diagnostic push
//#pragma clang diagnostic ignored "-Wdeprecated-declarations"

using namespace std;
using namespace tinyxml2;

float lx = 160.0f, ly = 160.0f, lz = 160.0f; //coordenadas para o lookAt
float xx = 0.0f, yy = 0.0f, zz = 0.0f; // coordenadas para a camara
float px = 0.0f, py = 0.0f, pz = 0.0f;
float angleY = 0.0f, angleX = 0.0f, angleZ = 0.0f; //para rotação 
int draw = GL_LINE; //maneira de desenho: linhas, pontos, preenchido
vector<Vert> vertexes; //vertices 
vector<Transformacoes> transformacoes;

//VBO
int buffercount=0;
GLuint *buffer;
vector <float> vertexB;
int *vertexCountArray;

//framerate
float frames=0;
float tempoBase=0;

void framerate() {
    char title[50];
    frames++;
    float tempo = glutGet(GLUT_ELAPSED_TIME);
    if (tempo - tempoBase > 1000) {
        float fps = frames * 1000.0 / (tempo - tempoBase);
        tempoBase = tempo;
        frames = 0;
        sprintf(title, "CG_Fase3 | %lf FPS", fps);
        glutSetWindowTitle(title);
    }
}

void drawSolarSystemModel() {

    int bufferIndex=0;

    for (size_t i = 0; i < transformacoes.size(); i++) {
        Transformacao transform = transformacoes[i].getTransformacao();

        //satélites
        if (transformacoes[i].getSubgrupo().size() != 0) {
            vector<Transformacoes> subg = transformacoes[i].getSubgrupo();
            for (size_t j = 0; j < subg.size(); j++) {
                Transformacao subtransf = subg[j].getTransformacao();

                Translacao t = subtransf.getTrans();

                //limpamos o vetor de vertices anteriores desenhados
                vertexes.clear();

                //criamos o vetor de vertices com os triangulos provenientes da transformaçao
                vertexes = subg[j].getVerts();

                vertexB.clear();
                //percorre-se o vetor de vertices para desenhar os triangulos
                for (int j = 0; j < vertexes.size(); j++) {
                    vertexB.push_back(vertexes[j].getX());
                    vertexB.push_back(vertexes[j].getY());
                    vertexB.push_back(vertexes[j].getZ());
                }
                vertexCountArray[bufferIndex]=vertexB.size();
                glBindBuffer(GL_ARRAY_BUFFER, buffer[bufferIndex]);
                glBufferData(GL_ARRAY_BUFFER, vertexB.size() * sizeof(float), vertexB.data(), GL_STATIC_DRAW);
                bufferIndex++;


            }
        }

        //limpamos o vetor de vertices anteriores desenhados
        vertexes.clear();

//criamos o vetor de vertices com os triangulos provenientes da transformaçao
        vertexes = transformacoes[i].getVerts();

        vertexB.clear();
//percorre-se o vetor de vertices para desenhar os triangulos
        for (int j = 0; j < vertexes.size(); j++) {
            vertexB.push_back(vertexes[j].getX());
            vertexB.push_back(vertexes[j].getY());
            vertexB.push_back(vertexes[j].getZ());
        }
        vertexCountArray[bufferIndex]=vertexB.size();
        glBindBuffer(GL_ARRAY_BUFFER, buffer[bufferIndex]);
        glBufferData(GL_ARRAY_BUFFER, vertexB.size() * sizeof(float), vertexB.data(), GL_STATIC_DRAW);
        bufferIndex++;

    }
}


void renderScene(void){
    int i = 0, j = 0;
    int bufferIndex = 0;
    // clear buffers
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // set the camera
    glLoadIdentity();
    // lx, ly e lz alterados sempre q começa
    gluLookAt(lx,ly,lz,
              px,py,pz,
              0.0f,1.0f,0.0f);

    //daw instructions
    glPolygonMode(GL_FRONT_AND_BACK,draw);
    //yy nunca vai ser alterado
    glTranslatef(xx, yy, zz);  // para enquadrar com um planeta quando selecionado
    glRotatef(angleY, 0.0, 1.0, 0.0);
    glRotatef(angleX, 1.0, 0.0, 0.0);
    glRotatef(angleZ, 0.0, 0.0, 1.0);
    glTranslatef(-xx, -yy, -zz);

    float deriv[3];
    float res[3];
    //ciclo que contém todas as transformações percorrendo o vector
    for(size_t i = 0; i < transformacoes.size(); i++) {
        // guardar estado inicial da matriz
        // uma vez que vai haver alterações na matriz devido a transformações geométricas
        glPushMatrix();

        //para obter uma transformação:
        Transformacao transform = transformacoes[i].getTransformacao();

	//planetas
	if(!transform.semTransformacao()){

	    Translacao trl = transform.getTrans();
        if (!trl.semTranslacao()) {
            if (trl.getTamanho() > 0 && trl.getTempo() > 0) {
                float t = glutGet(GLUT_ELAPSED_TIME) % (int) (trl.getTempo() * 1000);
                float tempo = t / (trl.getTempo() * 1000.0);
                vector<Vert> curva = trl.curve();
                trl.renderCatmullRomCurve(curva, transform.getCor().getR()/255, transform.getCor().getG()/255, transform.getCor().getB()/255);
                trl.getGlobalCatmullRomPoint(tempo, res, deriv, trl.getVert());
                glTranslatef(res[0], res[1], res[2]);
            }
        }

	Rotacao rot = transform.getRotacao();

            if (!rot.semRotacao() && rot.getTempo() != 0) {
                float t = glutGet(GLUT_ELAPSED_TIME) % (int) (rot.getTempo() * 1000);


                float tempo = (t * 360.0) / (rot.getTempo() * 1000.0);

                glRotatef(tempo, rot.getX(), rot.getY(), rot.getZ());
            }

        Escala esc = transform.getEscala();
            if (!esc.semEscala())
                glScalef(esc.getX(), esc.getY(), esc.getZ());


        }

//satélites
  	if(transformacoes[i].getSubgrupo().size() != 0) {
                vector<Transformacoes> subg = transformacoes[i].getSubgrupo();
                for (size_t j = 0; j < subg.size(); j++) {
                    glPushMatrix();
                    Transformacao subtransf = subg[j].getTransformacao();

                    Translacao t = subtransf.getTrans();

                    if(t.getTempo() != 0){
                        float te = glutGet(GLUT_ELAPSED_TIME) % (int) (t.getTempo() * 1000);
                        float tempo = te / (t.getTempo() * 1000.0);
                        vector<Vert> subcurva= t.curve();
                        t.renderCatmullRomCurve(subcurva, subtransf.getCor().getR()/255, subtransf.getCor().getG()/255, subtransf.getCor().getB()/255);
                        t.getGlobalCatmullRomPoint(tempo, res, deriv, t.getVert());
                        glTranslatef(res[0], res[1], res[2]);

                    }else{
                        glTranslatef(t.getX(),t.getY(),t.getZ());
                    }

                    Rotacao subrot = subtransf.getRotacao();
                        if (subrot.getTempo() != 0) {
                            float r = glutGet(GLUT_ELAPSED_TIME) % (int) (subrot.getTempo() * 1000);
                            float tempo = (r * 360) / (subrot.getTempo() * 1000);
                            glRotatef(tempo, subrot.getX(), subrot.getY(), subrot.getZ());

                        }

                    Escala subesc = subtransf.getEscala();
                    glScalef(subesc.getX(), subesc.getY(), subesc.getZ());

                    Cor cor = subtransf.getCor();
                    if (!cor.semCor())
                        glColor3f(cor.getR()/255, cor.getG()/255, cor.getB()/255);

                    // cor da transformaçao
                    glColor3f(transform.getCor().getR()/255,transform.getCor().getG()/255,transform.getCor().getB()/255);

                    glBindBuffer(GL_ARRAY_BUFFER, buffer[bufferIndex]);
                    glVertexPointer(3, GL_FLOAT, 0, 0);
                    glDrawArrays(GL_TRIANGLES, 0, vertexCountArray[bufferIndex]*3);
                    bufferIndex++;

                     //estado da matriz reposto
 
                    glPopMatrix();
                }
            }
            Cor cor = transform.getCor();
            if (!cor.semCor())
                glColor3f(cor.getR()/255, cor.getG()/255, cor.getB()/255);


       //limpamos o vetor de vertices anteriores desenhados
        vertexes.clear();

        //criamos o vetor de vertices com os triangulos provenientes da transformaçao
        vertexes = transformacoes[i].getVerts();

       // cor da transformaçao 
        glColor3f(transform.getCor().getR()/255,transform.getCor().getG()/255,transform.getCor().getB()/255);

        glBindBuffer(GL_ARRAY_BUFFER, buffer[bufferIndex]);
        glVertexPointer(3, GL_FLOAT, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, vertexCountArray[bufferIndex]*3);
        bufferIndex++;

        //estado da matriz reposto
        glPopMatrix();
    }

    framerate();
    //End of frame
    glutSwapBuffers();
}


//le ficheiro .3d
void lerFicheiro(string ficheiro){
	string line;
    string delimiter = " ";
    int pos;
    float xx,yy,zz;
    Vert v ;
    string novo;

    ifstream file(ficheiro);

    if(file.is_open()){

        while(getline (file,line)) {

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            xx = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            v.setX(xx);

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            yy = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            v.setY(yy);

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            zz = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            v.setZ(zz);
            //insere os vertices lidos no array de vertices
            vertexes.push_back(v);

        }

        file.close();
        cout << "Ficheiro: " << ficheiro << " lido com sucesso" << endl;


        }
        else {
    //DO NOTHING
   //     cout << "ERRO AO LER FICHEIRO" << endl;
    }
}


void Parse(XMLElement *group , Transformacao transf, string p){

    Transformacao trf;
    Translacao trl;
    Escala esc;
    Rotacao rot;
    Cor cor;

    float transX = 0.0, transY = 0.0, transZ = 0.0, ang = 0.0, esX = 0.0, esY=0.0,esZ=0.0, rotX=0.0, rotY=0.0, rotZ=0.0;
    float cr=1, cg=1, cb=1; float tempo=0;



    if(strcmp(group->FirstChildElement()->Value(),"group")==0){
        group = group->FirstChildElement();

    }

    XMLElement* transfor = group->FirstChildElement();
    XMLAttribute* at;
    XMLAttribute* as;
    XMLAttribute* ar;
    XMLAttribute* ac;
    XMLAttribute* pt;
   
    //parser do grupo
    for(transfor; (strcmp(transfor->Value(),"models")!=0); transfor = transfor->NextSiblingElement()){
        if(strcmp(transfor->Value(), "translate")==0) {

          at = const_cast<XMLAttribute *>(transfor->FirstAttribute());
            if(strcmp(at->Name(),"X")==0) transX = stof(transfor->Attribute("X"));
            else transX = 0;
            if(strcmp(at->Name(),"Y")==0) transY = stof(transfor->Attribute("Y"));
            else transY=0;
            if(strcmp(at->Name(),"Z")==0) transZ = stof(transfor->Attribute("Z"));
            else transZ=0;


            if(transfor->Attribute("time")) tempo = stof(transfor->Attribute("time"));
            else tempo = 0;

            vector<Vert> trp;

                for(XMLElement* pt = transfor->FirstChildElement("point"); pt; pt = pt->NextSiblingElement("point")){


                if(pt->Attribute("X")) xx = stof(pt->Attribute("X"));
                else xx = 0;
                if(pt->Attribute("Y")) yy = stof(pt->Attribute("Y"));
                else yy=0;
                if(pt->Attribute("Z")) zz = stof(pt->Attribute("Z"));
                else zz=0;

                Vert ptt = Vert(xx,yy,zz);

                trp.push_back(ptt);
                printf("processei point\n");

	        }
            //conta trajétória
            //if(trp.size()>0) buffercount++;

		    trl = Translacao(transX, transY, transZ, tempo, trp.size(), trp);
        }
        if(strcmp(transfor->Value(), "scale")==0){

            as = const_cast<XMLAttribute *>(transfor->FirstAttribute());
            esX = stof(transfor->Attribute("X"));
            esY = stof(transfor->Attribute("Y"));
            esZ = stof(transfor->Attribute("Z"));

            esc.setX(esX);
            esc.setY(esY);
            esc.setZ(esZ);
        }
        if(strcmp(transfor->Value(), "rotate")==0){

          
	    if(transfor->Attribute("time")) tempo = stof(transfor->Attribute("time"));
            else tempo = 0;

            if(transfor->Attribute("angle")) ang = stof(transfor->Attribute("angle"));
            else ang = 0;

            rotX = stof(transfor->Attribute("X"));

            rotY = stof(transfor->Attribute("Y"));

            rotZ = stof(transfor->Attribute("Z"));
            rot =Rotacao(tempo,ang,rotX,rotY,rotZ);
        }
        if(strcmp(transfor->Value(), "color")==0){


            cr= stof(transfor->Attribute("R"));

            cg = stof(transfor->Attribute("G"));

            cb = stof(transfor->Attribute("B"));

            cor = Cor(cr,cg,cb);


        }
    }


    esc.setX(esc.getX() * transf.getEscala().getX());
    esc.setY(esc.getY() * transf.getEscala().getY());
    esc.setZ(esc.getZ() * transf.getEscala().getZ());

    //transforma aqui o objeto
    trf = Transformacao(trl,rot,esc,cor);

    for(XMLElement* models = group->FirstChildElement("models")->FirstChildElement("model"); models; models = models -> NextSiblingElement("model")){
        //cria um objeto Transformaçoes
        Transformacoes tran;

        tran.setTipo(models->Attribute("fich"));
        //lê sphere.3d (ou outro tipo de ficheiro) e coloca os vertices no vector vertexes
        lerFicheiro(tran.getTipo());
        //coloca os vertices no objeto tran
        tran.setVerts(vertexes);
        vertexes.clear();
        //coloca a transformaçao no objeto trans
        tran.setTrans(trf);

        //Conta Planeta
        buffercount++;

        if(p == "I"){
            transformacoes.push_back(tran);
        }
        if(p == "F"){
            int pos = transformacoes.size() - 1;
            transformacoes[pos].push_child(tran);
        }
        if(p == "P"){
            int pos = transformacoes.size() - 1;
            transformacoes[pos].push_child(tran);
        }

    }
        // faz parse dos filhos
    if(group->FirstChildElement("group")){
            Parse(group->FirstChildElement("group"),trf,"F");
    }


    if(group->NextSiblingElement("group") && (p == "F" || p == "P")){
        Parse(group->NextSiblingElement("group"),transf,"P");
    }


        //faz parse nos irmãos
    if(group->NextSiblingElement("group") && p != "F" && p != "P"){
        Parse(group->NextSiblingElement("group"),transf,"I");
    }}
//lê ficheiro xml

void readXMLfile(string fich) {
    XMLDocument doc;
    XMLElement *root;


    if (!(doc.LoadFile(fich.c_str()))) {
            int n=0;
            XMLElement * scene = doc.FirstChildElement("scene");
            XMLElement * group = scene-> FirstChildElement("group");

            Transformacao tr = Transformacao();
            Escala escal = Escala(1,1,1);
            tr.setEscala(escal);
            //parser do ficheiro xml para fazer o group com a transformação
            Parse(group,tr,"I");

            printf("Numero de transformações depois do parse: %d\n", buffercount);
            buffer = new GLuint [buffercount];
            vertexCountArray = new int [buffercount];
    } else {
      cout << "Ficheiro XML não foi encontrado" << endl;
    }
}


////////////////////////////////////////////////////////
//////////////STAYS THE SAME///////////////////////////
void reshape(int w, int h){
    // Prevent a divide by zero, when window is too short
    // (you cant make a window with zero width).
    if(h == 0)
        h = 1;
    float ratio = w *1.0 / h;
    // Set the projection matrix as current
    glMatrixMode(GL_PROJECTION);
    // Load Identity Matrix
    glLoadIdentity();

    // Set the viewport to be the entire window
    glViewport(0, 0, w, h);

    // Set perspective
    gluPerspective(45.0f ,ratio, 1.0f ,1000.0f);

    // return to the model view matrix mode
    glMatrixMode(GL_MODELVIEW);
}

void keyboard(unsigned char key, int a, int b) {
    switch (key) {
        case 'a':
        case 'A':
            angleY -= 5;
            if (angleY < -360) angleY += 360;
            break;
        case 'd':
        case 'D':
            angleY += 5;
            if (angleY > 360) angleY -= 360;
            break;
        case 'q':
        case 'Q':
            angleX -= 5;
            if (angleX < -360) angleX += 360;
            break;

        case 'e':
        case 'E':
            angleX += 5;
            if (angleX > 360) angleX -= 360;
            break;
        case 'w':
        case 'W':
            angleZ += 5;
            if(angleZ > 360) angleZ += 360;
            break;
        case 's':
        case 'S':
            angleZ -= 5;
           if(angleZ < -360) angleZ -=360;
            break;
        case '+':
            lx -= 5; ly -= 5; lz -= 5;
            break;

        case '-':
            if(lx<435){
              lx += 5, ly += 5, lz += 5;
            }
            break;
        case 'f':
        case 'F':
            draw = GL_FILL;
            break;
        case 'p':
        case 'P':
            draw = GL_POINT;
            break;
        case 'l':
        case 'L':
            draw = GL_LINE;
            break;
        case 27:
            exit(0);
    }
   glutPostRedisplay();
}

void keyboardspecial(int key, int a, int b){
    switch (key){
        case GLUT_KEY_UP:
            zz -=cos(angleY /180 * M_PI);
            xx +=sin(angleY /180 * M_PI);
            break;
        case GLUT_KEY_DOWN:
            zz +=1 * cos(angleY /180 * M_PI);
            xx -=1 * sin(angleY /180 * M_PI);
            break;
        case GLUT_KEY_LEFT:
            xx -=cos(angleY /180 * M_PI);
            zz -=sin(angleY /180 * M_PI);
            break;
        case GLUT_KEY_RIGHT:
            xx +=cos(angleY /180 * M_PI);
            zz +=sin(angleY /180 * M_PI);
            break;
    }
    glutPostRedisplay();
} 
//Menu de Ajuda

void help(){

cout <<"|---------------------> Controlos 3D <----------------------|"<< endl;
    cout <<"|                                                           |"<< endl;
    cout <<"|       * Translação: Seta cima, baixo, esquerda, direita   |"<< endl;
    cout <<"|       * Rotação: w, a, s, d  | W, A, S, D                 |"<< endl;
    cout <<"|       * Zoom: + | -                                       |"<< endl;
    cout <<"|       * Representação do sólido:                          |"<< endl;
    cout <<"|           - por linhas: l | L                             |"<< endl;
    cout <<"|           - por pontos: p | P                             |"<< endl;
    cout <<"|           - preenchido: f | F                             |"<< endl;
    cout <<"|                                                           |"<< endl;
    cout <<"|                                                           |"<< endl;
    cout <<" ------------------------------><---------------------------"<< endl;
}


int main(int argc, char** argv){

	    if (argc > 1){

        if(strcmp(argv[1], "help") == 0) {
          help();
          raise(SIGINT);
        }
         else readXMLfile(argv[1]);
}

//init glut and the window
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DEPTH | GLUT_DOUBLE | GLUT_RGBA);
    glutInitWindowPosition(100,100);
    glutInitWindowSize(1000,1000);
    glutCreateWindow("CG_Fase3");

// put callback registration here
    glutDisplayFunc( renderScene );
    glutReshapeFunc( reshape );
    glutIdleFunc (renderScene);
    glutSpecialFunc(keyboardspecial);
    glutKeyboardFunc(keyboard);

    // init GLEW
#ifndef __APPLE__
    glewInit();
#endif
    glEnableClientState(GL_VERTEX_ARRAY);

    glGenBuffers(buffercount, buffer);
    drawSolarSystemModel();

// OpenGL settings
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);

    tempoBase=glutGet(GLUT_ELAPSED_TIME);
// enter GLUT's main loop
    glutMainLoop();

    return 1;

}
#pragma clang diagnostic pop
