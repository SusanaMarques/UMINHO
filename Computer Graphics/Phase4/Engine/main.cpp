#include "headers/Transformacao.h"
#include <csignal>
#include <string>


//#pragma clang diagnostic push
//#pragma clang diagnostic ignored "-Wdeprecated-declarations"

using namespace std;
using namespace tinyxml2;

//Camera
Camera camera = Camera();
int width = 1000, height=1000;
float lastX = width/2, lastY=height/2;
bool frstMouse = true;
float yaw = -90.0f;
float pitch = 0.0f;
float cameraSpeed = 2.0f; //velocidade do movimento
float sensibilidade = 0.2f;  //sensibilidade do rato ao movimento


float xx = 0.0f, yy = 0.0f, zz = 0.0f; // coordenadas para a camara
int draw = GL_LINE; //maneira de desenho: linhas, pontos, preenchido
vector<Vert> vertexes; //vertices 
vector<Transformacoes> transformacoes;
vector<Vert> normal;
vector<Vert> texturaz;

//inicializa luz
float luzX, luzY, luzZ;
string luzz;
float isPoint = 0;


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
        sprintf(title, "CG_Fase4 | %lf FPS", fps);
        glutSetWindowTitle(title);
    }
}



void renderScene(void){
    int i = 0, j = 0;
  
    // clear buffers
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // set the camera
    glLoadIdentity();
    gluLookAt(camera.posicao.getX(),camera.posicao.getY(),camera.posicao.getZ(),
              camera.posicao.getX()+camera.front.getX(),camera.posicao.getY()+camera.front.getY(),camera.posicao.getZ()+camera.front.getZ(),
              camera.up.getX(),camera.up.getY(),camera.up.getZ());


    //daw instructions
    glPolygonMode(GL_FRONT_AND_BACK,draw);


    float deriv[3];
    float res[3];
    //ciclo que contém todas as transformações percorrendo o vector
    for(size_t i = 0; i < transformacoes.size(); i++) {
        // guardar estado inicial da matriz
        // uma vez que vai haver alterações na matriz devido a transformações geométricas
        glPushMatrix();

        Transformacoes trf = transformacoes[i];
        //para obter uma transformação:
        Transformacao transform = transformacoes[i].getTransformacao();


        //sol
        //fonte luminosa
        if(i==0){
            GLfloat pos[4] = { luzX, luzY, luzZ, isPoint};
            GLfloat amb[3] = { 0.0, 0.0, 0.0 };
            GLfloat diff[4] = { 1.0f, 1.0f, 1.0f, 1.0f };
            GLfloat matt[3] = { 5, 5, 5 };

            glMaterialf(GL_FRONT, GL_SHININESS, 50);
            glLightfv(GL_LIGHT0, GL_POSITION, pos); // posição da luz
            glLightfv(GL_LIGHT0, GL_AMBIENT, amb); // luz ambiente
            glLightfv(GL_LIGHT0, GL_DIFFUSE, diff); // luz difusa

            glMaterialfv(GL_FRONT_AND_BACK, GL_EMISSION, matt);
        }
        else {
            GLfloat matt[3] = { 0, 0, 0 };
            glMaterialfv(GL_FRONT_AND_BACK, GL_EMISSION, matt);
        }

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
                     //glColor3f(transform.getCor().getR()/255,transform.getCor().getG()/255,transform.getCor().getB()/255);

                    
                    if(subg[j].getText().compare("") != 0) {
                        glBindTexture(GL_TEXTURE_2D, subg[j].getTexID());
                        glEnable(GL_LIGHTING);
                    }
                    subg[j].desenha();
                    glDisable(GL_LIGHTING);
                    glBindTexture(GL_TEXTURE_2D, 0);
                     //estado da matriz reposto
 
                    glPopMatrix();
                }
            }
            Cor cor = transform.getCor();
            if (!cor.semCor())
                glColor3f(cor.getR()/255, cor.getG()/255, cor.getB()/255);


        //estado da matriz reposto
        if(trf.getText().compare("") != 0) {
                glBindTexture(GL_TEXTURE_2D, trf.getTexID());
                glEnable(GL_LIGHTING);
            }
            trf.desenha();
            glDisable(GL_LIGHTING);
            glBindTexture(GL_TEXTURE_2D, 0);

        glPopMatrix();
    }

  


    framerate();
    //End of frame
    glutSwapBuffers();
}

void use_VBO_total(){

    glPolygonMode(GL_FRONT, GL_FILL);
    glEnable(GL_NORMALIZE);
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_LIGHT0);
    glEnable(GL_TEXTURE_2D);
    glShadeModel (GL_SMOOTH);

    for(size_t i = 0; i < transformacoes.size(); i++){
        transformacoes[i].use_VBO();

        if(transformacoes[i].getSubgrupo().size() > 0){
            vector<Transformacoes> s = transformacoes[i].getSubgrupo();

            for(size_t j = 0; j < s.size(); j++) {
                s[j].use_VBO();
            }
            transformacoes[i].setSubgrupo(s);
        }
    }
}

//le ficheiro .3d
void lerFicheiro(string ficheiro){
	string line;
    string delimiter = " ";
    int pos;
    float xx,yy,zz;
    Vert v ;
    Vert n ;
    Vert t ;
    string novo;
    int ii=0;

    ifstream file(ficheiro);

    if(file.is_open()){

        while(getline (file,line)) {
            if(ii%3==0){

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

        if((ii%3==1)) {
           
            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            xx = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            n.setX(xx);

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            yy = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            n.setY(yy);

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            zz = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            n.setZ(zz);
            //insere os vertices lidos no array de vertices
            normal.push_back(n);

        }
        if((ii%3==2)) {
           
            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            xx = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            t.setX(xx);

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            yy = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            t.setY(yy);

            pos = line.find(delimiter);
            novo = line.substr(0, pos);
            zz = atof(novo.c_str());
            line.erase(0, pos + delimiter.length());
            t.setZ(0);
            //insere os vertices lidos no array de vertices
            texturaz.push_back(t);

        }
        ii=ii+1;
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
    for(; (strcmp(transfor->Value(),"models")!=0); transfor = transfor->NextSiblingElement()){
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

        if (models->Attribute("text")) tran.setText(models->Attribute("text"));
   

        //lê sphere.3d (ou outro tipo de ficheiro) e coloca os vertices no vector vertexes
        lerFicheiro(tran.getTipo());
        //coloca os vertices no objeto tran
        tran.setVerts(vertexes);
        vertexes.clear();
        tran.setNormal(normal);
        normal.clear();
        tran.setTextura(texturaz);
        texturaz.clear();

        //coloca a transformaçao no objeto trans
        tran.setTrans(trf);

        

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
             XMLElement * luzes = scene-> FirstChildElement("lights");
            XMLElement * luz = luzes-> FirstChildElement("light");
            luzz = luz -> Attribute("luz");
            if(luz->Attribute("luz") && !luzz.compare(luz->Attribute("luz")))
                isPoint = 1;

            luzX = atof(luz->Attribute("posX"));
            luzY = atof(luz->Attribute("posY"));
            luzZ = atof(luz->Attribute("posZ"));

            Transformacao tr = Transformacao();
            Escala escal = Escala(1,1,1);
            tr.setEscala(escal);
            //parser do ficheiro xml para fazer o group com a transformação
            Parse(group,tr,"I");

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

    width = w;
    height = h;

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
            camera.posicaoLeft(cameraSpeed);
            break;
        case 'd':
        case 'D':
            camera.posicaoRight(cameraSpeed);
            break;
        case 'w':
        case 'W':
            camera.posicaoForward(cameraSpeed);
            break;
        case 's':
        case 'S':
            camera.posicaoBackward(cameraSpeed);
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
            camera.posicao.setY(camera.posicao.getY()+cameraSpeed);
            break;
        case GLUT_KEY_DOWN:
            camera.posicao.setY(camera.posicao.getY()-cameraSpeed);
            break;
    }
    glutPostRedisplay();
}


void mouseMotion(int x, int y) {
    if (frstMouse) {
        lastX = (float) x;
        lastY = (float) y;
        frstMouse = false;
    }

    float offsetX = (float) x - lastX;
    float offsetY = lastY - (float) y;  //coordenadas começam de baixo para cima

    lastX = (float) x;
    lastY = (float) y;



    if(x>=0 && x<50){
        offsetX=-5;
    }
    if(y>=0 && y<50){
        offsetY=-5;
    }

    if(x<=width && x>width-50){
        offsetX=+5;
    }
    if(y<=height && y>height-50){
        offsetY=+5;
    }

    offsetX *= sensibilidade;
    offsetY *= sensibilidade;

    yaw += offsetX;
    pitch += offsetY;


    if (pitch > 89.0f)
        pitch = 89.0;
    if (pitch < -89.0f)
        pitch = -89.0f;

    camera.lookdirection(yaw, pitch);
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
    glutCreateWindow("CG_Fase4");

// put callback registration here
    glutDisplayFunc( renderScene );
    glutReshapeFunc( reshape );
    glutIdleFunc (renderScene);
    glutSpecialFunc(keyboardspecial);
    glutKeyboardFunc(keyboard);
    glutWarpPointer(width/2 , height/2);
    glutSetCursor(GLUT_CURSOR_NONE);
    glutPassiveMotionFunc(mouseMotion);

    // init GLEW
#ifndef __APPLE__
    glewInit();
#endif
    ilInit();
    ilEnable(IL_ORIGIN_SET);
    ilOriginFunc(IL_ORIGIN_LOWER_LEFT);

// OpenGL settings
    use_VBO_total();

    tempoBase=glutGet(GLUT_ELAPSED_TIME);
// enter GLUT's main loop
    glutMainLoop();

    return 1;

}
#pragma clang diagnostic pop
