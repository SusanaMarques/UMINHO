#include <math.h>
#include "tinyxml2.h"
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <fstream>
#include <string>
#include <iostream>
#include <vector>
#include <string>

#ifdef __APPLE__
#define GL_SILENCE_DEPRECATION
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

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
};


class Rotacao {
    float angle, x_eixo, y_eixo, z_eixo;

public:
    Rotacao();
    Rotacao(float angle, float x, float y, float z);
    float getAngle(){ return angle;}
    float getX(){ return x_eixo; }
    float getY(){ return y_eixo; }
    float getZ(){ return z_eixo; }
    void setAngle(float a){ angle = a; }
    void setX(float x){ x_eixo = x;}
    void setY(float y){ y_eixo = y;}
    void setZ(float z){ z_eixo = z;}
};

class Translacao{
    float x_eixo, y_eixo, z_eixo;

public:
    Translacao();
    Translacao(float x, float y, float z);
    float getX(){ return x_eixo; }
    float getY(){ return y_eixo; }
    float getZ(){ return z_eixo; }
    void setX(float x){ x_eixo = x;}
    void setY(float y){ y_eixo = y;}
    void setZ(float z){ z_eixo = z;}
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
};


class Transformacoes{
    std::string tipo;
    Transformacao t;
    std::vector<Vert> verts;

public:
    Transformacoes();
    Transformacoes(std::string tipo, Transformacao t, std::vector<Vert> vert);
    std::string getTipo(){ return tipo; }
    Transformacao getTransformacao(){ return t; }
    std::vector<Vert> getVerts(){ return verts; }
    void setTipo(std::string t){ tipo = t; }
    void setTrans(Transformacao trans){ t = trans;}
    void setVerts(std::vector<Vert> v){ verts = v;}
};


