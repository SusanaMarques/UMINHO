#include "headers/Transformacao.h"

//vertice
Vert::Vert() {
    x=0;
    y=0;
    z=0;
}

Vert::Vert(float a, float b, float c){
    x=a;
    y=b;
    z=c;
}


//cor
Cor::Cor() {
    red = 1.0;
    green = 1.0;
    blue = 1.0;
}

Cor::Cor(float r, float g, float b) {
    red = r;
    green = g;
    blue = b;
}

//escala
Escala::Escala(){
    x_eixo = 0.0;
    y_eixo = 0.0;
    z_eixo = 0.0;
}

Escala::Escala(float x, float y, float z) {
    x_eixo = x;
    y_eixo = y;
    z_eixo = z;
};

//rotaçao
Rotacao::Rotacao() {
    angle = 0.0;
    x_eixo = 0.0;
    y_eixo = 0.0;
    z_eixo = 0.0;
}

Rotacao::Rotacao(float angle, float x, float y, float z) {
    this->angle = angle;
    x_eixo = x;
    y_eixo = y;
    z_eixo = z;
}

//translacao
Translacao::Translacao(){
    x_eixo = 0.0;
    y_eixo = 0.0;
    z_eixo = 0.0;
}

Translacao::Translacao(float x, float y, float z) {
    x_eixo = x;
    y_eixo = y;
    z_eixo = z;
};

//transformacao
Transformacao::Transformacao(){
    trans = Translacao();
    rotacao = Rotacao();
    escala = Escala();
    cor = Cor();
}
Transformacao::Transformacao(Translacao trans, Rotacao rotacao, Escala escala, Cor cor){
    this->trans = trans;
    this->rotacao = rotacao;
    this->escala = escala;
    this->cor = cor;
}

Transformacoes::Transformacoes() {
    //para perceber se está a ler uma esfera um anel... (sphere.3d)
    tipo = "";
    t = Transformacao();
    verts = std::vector<Vert>();
}

Transformacoes::Transformacoes(std::string tipo, Transformacao t, std::vector<Vert> verts ) {
    this->tipo = tipo;
    this->t = t;
    this->verts = verts;
}
