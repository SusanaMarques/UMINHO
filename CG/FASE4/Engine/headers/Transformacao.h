#ifdef __APPLE__
#define GL_SILENCE_DEPRECATION
#include <GLUT/glut.h>
#include "/usr/local/Cellar/devil/1.8.0_2/include/il/il.h"
#else
#include <GL/glew.h>
#include <GL/glut.h>
#include <IL/il.h>
#include <IL/ilu.h>
#include <IL/ilut.h>
#endif

#include <math.h>
#include "tinyxml2.h"
#include <math.h>
#include <stdlib.h>
#include <fstream>
#include <string>
#include <iostream>
#include <vector>

#include <string>

class Vert {
    float x;
    float y;
    float z;


public:
    Vert();
    Vert(float, float, float);
    float getX() { return x; }
    float getY() { return y; }
    float getZ() { return z; }
    void setX( float a) {  x = a; }
    void setY( float a) {  y = a; }
    void setZ( float a) {  z = a; }

};

class Cor{
    float red;
    float green;
    float blue;

public:
    Cor();
    Cor(float r, float g, float b);
    float getR(){ return red; }
    float getG(){ return green; }
    float getB(){ return blue; }
    void setR(float r){ red = r; }
    void setG(float g){ green = g; }
    void setB(float b){ blue = b; }
    bool semCor();
};

class Escala{
    float x_eixo, y_eixo, z_eixo;

public:
    Escala();
    Escala(float x, float y, float z);
    float getX(){ return x_eixo; }
    float getY(){ return y_eixo; }
    float getZ(){ return z_eixo; }
    void setX(float x){ x_eixo = x;}
    void setY(float y){ y_eixo = y;}
    void setZ(float z){ z_eixo = z;}
    bool semEscala();
};


class Rotacao {
    float tempo,angle, x_eixo, y_eixo, z_eixo;

public:
    Rotacao();
    Rotacao(float tempo, float angle, float x, float y, float z);
    float getTempo(){ return tempo;}
    float getAngle(){ return angle;}
    float getX(){ return x_eixo; }
    float getY(){ return y_eixo; }
    float getZ(){ return z_eixo; }
    void setTempo(float a){ tempo = a; }
    void setAngle(float a){ angle = a; }
    void setX(float x){ x_eixo = x;}
    void setY(float y){ y_eixo = y;}
    void setZ(float z){ z_eixo = z;}
    bool semRotacao();
};

class Translacao{
    float x_eixo, y_eixo, z_eixo;
    float tempo;
    int tamanho;
    std::vector<Vert> ve;
    std::vector<Vert> curvas;

public:
    Translacao();
    Translacao(float x, float y, float z, float ti, int s, std::vector<Vert> t);
    float getX(){ return x_eixo; }
    float getY(){ return y_eixo; }
    float getZ(){ return z_eixo; }
    float getTempo(){ return tempo; }
    int getTamanho(){ return tamanho; }
    std::vector<Vert> getVert(){ return ve; }
    std::vector<Vert> getCurvas(){ return curvas; }

    void setX(float x){ x_eixo = x;}
    void setY(float y){ y_eixo = y;}
    void setZ(float z){ z_eixo = z;}
    void setTempo(float x){ tempo = x;}
    void setTamanho(float y){ tamanho = y;}
    void setVert(std::vector<Vert> t){ ve = t;}
    void setCurvas(std::vector<Vert> c){ curvas = c;}
    void getCatmullRomPoint(float t, int* pos,float *res, float* deriv, std::vector<Vert> ve);
    void getGlobalCatmullRomPoint(float t, float* res, float* deriv, std::vector<Vert> ve);
    void renderCatmullRomCurve( std::vector<Vert> vert, float r, float g, float b);
    std::vector<Vert> curve();
    bool semTranslacao();
};


class Transformacao {
    Translacao trans;
    Rotacao rotacao;
    Escala escala;
    Cor cor;


public:
    Transformacao();
    Transformacao(Translacao trans, Rotacao rotacao, Escala escala, Cor cor);
    Translacao getTrans() { return trans; }
    Rotacao getRotacao() { return rotacao; }
    Escala getEscala(){ return escala;}
    Cor getCor(){ return cor; }
    void setTrans(Translacao t){ trans = t; }
    void setRotacao(Rotacao r){ rotacao = r; }
    void setEscala(Escala esc){ escala = esc; }
    void setCor(Cor c){ cor = c; }
    bool semTransformacao();
};


class Transformacoes{
    std::string tipo;
    Transformacao t;
    std::vector<Vert> verts;
    std::vector<Transformacoes> subgrupo;
    int pos;
    std::string text =" ";
    std::vector<Vert> normal;
    std::vector<Vert> textura;

    //VBO
    GLuint buffer[3];
    float *v, *n, *textu;
    float p_tam, n_tam, tex_tam;

    //Textura:
    unsigned int tt, width, height;
    unsigned int texID;
    unsigned char *data;

public:
    Transformacoes();
    Transformacoes(std::string tipo, Transformacao t, std::vector<Vert> vert, std::vector<Transformacoes> sub);
    std::string getTipo(){ return tipo; }
    std::vector<Transformacoes> getSubgrupo(){ return subgrupo; }
    Transformacao getTransformacao(){ return t; }
    void setSubgrupo(std::vector<Transformacoes> sub){ subgrupo = sub; }
    std::vector<Vert> getVerts(){ return verts; }
    void use_VBO();
    void desenha();
    void setTipo(std::string t){ tipo = t; }
    void setTrans(Transformacao trans){ t = trans;}
    void setVerts(std::vector<Vert> v){ verts = v;}
    void push_child(Transformacoes t){subgrupo.push_back(t);}
    std::string getText() {return text; }
    unsigned int getTexID() { return texID; }
    void setText (std::string t) {text =t;}
    void setNormal(std::vector<Vert> n) {normal =n;}
    std::vector<Vert> getTextura(){ return textura; }
    std::vector<Vert> getNormal(){ return normal; }
    void setTextura(std::vector<Vert> tex) {textura=tex;}
    void newText();

};

class Camera {
public:
    Camera();
    Vert posicao;
    Vert front;
    Vert up;

    void setposicao(Vert a) {
        posicao.setX(a.getX());
        posicao.setY(a.getY());
        posicao.setZ(a.getZ());
    }

    void setfront(Vert a){
        front.setX(a.getX());
        front.setY(a.getY());
        front.setZ(a.getZ());
    }

    void setup(Vert a){
        up.setX(a.getX());
        up.setY(a.getY());
        up.setZ(a.getZ());
    }

    void posicaoForward(float speed);
    void posicaoBackward(float speed);
    void posicaoLeft(float speed);
    void posicaoRight(float speed);
    void lookdirection(float a, float b);
};