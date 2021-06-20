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
extern int globalPlanetCounter = 0;
float planetCoord[50] = {0};
float planetRot[50] = {0};

void renderScene(void){
    int i = 0, j = 0;
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



    //ciclo que contém todas as transformações percorrendo o vector
    for(i = 0; i < transformacoes.size(); i++) {
        // guardar estado inicial da matriz
        // uma vez que vai haver alterações na matriz devido a transformações geométricas
        glPushMatrix();

        //para obter uma transformação:
        Transformacao transform = transformacoes[i].getTransformacao();

        //usamos as funções do glut com
        //os dados respetivos das rotações, translaçoes e escalas
        glRotatef(transform.getRotacao().getAngle(), transform.getRotacao().getX(), transform.getRotacao().getY(),
                  transform.getRotacao().getZ());
        glTranslatef(transform.getTrans().getX(), transform.getTrans().getY(), transform.getTrans().getZ());
        glScalef(transform.getEscala().getX(), transform.getEscala().getY(), transform.getEscala().getZ());

        //limpamos o vetor de vertices anteriores desenhados
        vertexes.clear();

        //criamos o vetor de vertices com os triangulos provenientes da transformaçao
        vertexes = transformacoes[i].getVerts();

        glBegin(GL_TRIANGLES);

       // cor da transformaçao 
        glColor3f(transform.getCor().getR()/255,transform.getCor().getG()/255,transform.getCor().getB()/255);

        //percorre-se o vetor de vertices para desenhar os triangulos
        for (j = 0; j < vertexes.size(); j++)
            glVertex3f(vertexes[j].getX(), vertexes[j].getY(), vertexes[j].getZ());

        glEnd();

        //estado da matriz reposto
        glPopMatrix();
    }

    //End of frame
    glutSwapBuffers();
}

// funçao que permite que ocorra uma transformaçao
Transformacao FazTransformacao(Translacao translac, Escala escal, Rotacao rotat, Cor c, Transformacao transform){

    Transformacao pt;

    //na translaçao iremos obter a soma das coordenadas do objeto Transformacao (coordenadas de translaçao) com as coordenadas do vetor(translac) 
    translac.setX(translac.getX() + transform.getTrans().getX());
    translac.setY(translac.getY() + transform.getTrans().getY());
    translac.setZ(translac.getZ() +  transform.getTrans().getZ());

    //multiplica-se os valores 
    escal.setX(escal.getX() * transform.getEscala().getX());
    escal.setY(escal.getY() * transform.getEscala().getY());
    escal.setZ(escal.getZ() * transform.getEscala().getZ());
    rotat.setAngle(rotat.getAngle() + transform.getRotacao().getAngle());
    
    //somar o valor da rotação ao antigo
    rotat.setX(rotat.getX() + transform.getRotacao().getX());
    rotat.setY(rotat.getY() + transform.getRotacao().getY());
    rotat.setZ(rotat.getZ() + transform.getRotacao().getZ());
    
    //herda a cor do nível anterior
    c.setR(c.getR());
    c.setG(c.getG());
    c.setB(c.getB());

    pt = Transformacao(translac,rotat,escal,c);

    return pt;

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

void Parse(XMLElement *group , Transformacao transf){

    Transformacao trf;
    Translacao trl;
    Escala esc;
    Rotacao rot;
    Cor cor;

    float transX = 0.0, transY = 0.0, transZ = 0.0, ang = 0.0, esX = 0.0, esY=0.0,esZ=0.0, rotX=0.0, rotY=0.0, rotZ=0.0;
    float cr=1, cg=1, cb=1;



    if(strcmp(group->FirstChildElement()->Value(),"group")==0){
        group = group->FirstChildElement();

    }

    XMLElement* transfor = group->FirstChildElement();
    XMLAttribute* at;
    XMLAttribute* as;
    XMLAttribute* ar;
    XMLAttribute* ac;
    
    //parser do grupo
    for(transfor; (strcmp(transfor->Value(),"models")!=0); transfor = transfor->NextSiblingElement()){
        if(strcmp(transfor->Value(), "translate")==0) {

           at = const_cast<XMLAttribute *>(transfor->FirstAttribute());
            transX = stof(transfor->Attribute("X"));
            transY = stof(transfor->Attribute("Y"));
            transZ = stof(transfor->Attribute("Z"));

            trl = Translacao(transX,transY,transZ);

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

            ar = const_cast<XMLAttribute *>(transfor->FirstAttribute());
            ang = stof(transfor->Attribute("angle"));

            rotX = stof(transfor->Attribute("X"));

            rotY = stof(transfor->Attribute("Y"));

            rotZ = stof(transfor->Attribute("Z"));
            rot =Rotacao(ang,rotX,rotY,rotZ);
        }
        if(strcmp(transfor->Value(), "color")==0){

            ac= const_cast<XMLAttribute *>(transfor->FirstAttribute());

            cr= stof(transfor->Attribute("R"));

            cg = stof(transfor->Attribute("G"));

            cb = stof(transfor->Attribute("B"));

            cor = Cor(cr,cg,cb);


        }
    }
    //transforma aqui o objeto
    trf= FazTransformacao(trl,esc,rot,cor,transf);


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

        //Para recolher as coordenadas de todas as esferas
        if(strcmp(models->Attribute("fich"), "sphere.3d") == 0 && ((rotX==0||rotY==0)||(rotX==0||rotZ==0)||(rotY==0||rotZ==0)) && trf.getTrans().getY()==0){
            planetCoord[globalPlanetCounter * 3 + 0] = trf.getTrans().getX();
            planetCoord[globalPlanetCounter * 3 + 1] = trf.getTrans().getY();
            planetCoord[globalPlanetCounter * 3 + 2] = trf.getTrans().getZ();

            planetRot[globalPlanetCounter * 4 + 0] = ang;
            planetRot[globalPlanetCounter * 4 + 1] = rotX;
            planetRot[globalPlanetCounter * 4 + 2] = rotY;
            planetRot[globalPlanetCounter * 4 + 3] = rotZ;

            globalPlanetCounter++;
        }
        //coloca a transformaçao no vetor de transformaçoes
       transformacoes.push_back(tran);
    }
        // faz parse dos outros grupos dentro do grupo
    if(group->FirstChildElement("group")){
            Parse(group->FirstChildElement("group"),trf);
    }

        //faz parse dos outros grupos ao lado do grupo
    if(group->NextSiblingElement("group")){
        Parse(group->NextSiblingElement("group"),transf);

    }
}
//lê ficheiro xml

void readXMLfile(string fich) {
    XMLDocument doc;
    XMLElement *root;


    if (!(doc.LoadFile(fich.c_str()))) {
            XMLElement * scene = doc.FirstChildElement("scene");
            XMLElement * group = scene-> FirstChildElement("group");

            Transformacao tr = Transformacao();
            //alteração da escala inicial com objetivo de diminuir
            Escala escal = Escala(1,1,1);
            tr.setEscala(escal);
            //parser do ficheiro xml para fazer o group com a transformação
            Parse(group,tr);
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
        case 48 ... 57:
            angleY=0.0;

            if(planetRot[(key-48)*4+2] != 0){
                angleY=planetRot[(key-48)*4+0];
            }

            xx = sin(angleY/180 * M_PI + M_PI/2)*planetCoord[(key-48)*3+0];
            px = xx;
            lx = xx+25;
            yy = 0;
            py = yy;
            ly=yy+25;
            zz = cos(angleY/180 * M_PI + M_PI/2)*planetCoord[(key-48)*3+0];
            pz=zz;
            lz=zz+25;
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
    glutInitWindowSize(800,800);
    glutCreateWindow("CG_Fase2");

// put callback registration here
    glutDisplayFunc( renderScene );
    glutReshapeFunc( reshape );
    glutSpecialFunc(keyboardspecial);
    glutKeyboardFunc(keyboard);
// OpenGL settings
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);

// enter GLUT's main loop
    glutMainLoop();

    return 1;

}


