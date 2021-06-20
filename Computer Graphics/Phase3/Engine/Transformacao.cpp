#include "headers/Transformacao.h"

//////////////////vertice
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


/////////////////cor
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

bool Cor::semCor(){
	return (red==0 && green ==0 && blue==0);
}

//////////////escala
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

bool Escala::semEscala(){
    return (x_eixo == 0 && y_eixo == 0 && z_eixo == 0);
}
///////////////rotaçao
Rotacao::Rotacao() {
    tempo = 0.0; // tempo para a curva
    angle = 0.0;
    x_eixo = 0.0;
    y_eixo = 0.0;
    z_eixo = 0.0;
}

Rotacao::Rotacao(float time,float angle, float x, float y, float z) {
    tempo = time;
    angle = angle;
    x_eixo = x;
    y_eixo = y;
    z_eixo = z;
}

bool Rotacao::semRotacao(){
    return (tempo == 0.0 && angle == 0.0 && x_eixo == 0.0 && y_eixo == 0.0 && z_eixo == 0.0);
}

/////////////////translacao/////////////////////////
Translacao::Translacao(){
    x_eixo = 0.0;
    y_eixo = 0.0;
    z_eixo = 0.0;
    tamanho =0.0; 
    tempo = 0.0;
}

Translacao :: Translacao(float x, float y, float z, float t, int num, std::vector<Vert> transl) {
    x_eixo = x;
    y_eixo = y;
    z_eixo = z;
    tempo = t;
    tamanho = num;
    ve = transl;
}


void Translacao::getCatmullRomPoint(float t, int* pos, float* res, float* deriv, std::vector<Vert> ve) {

float auxR[4], auxD[4];
    float quad, cubo;
    int i1, i2, i3, i4;
    Vert p0, p1, p2, p3;

    quad = t*t;
    cubo = t*t*t;
    res[0] = res[1] = res[2] = 0;

    float m[4][4] = {{-0.5f, 1.5f,  -1.5f, 0.5f},
                     {1.0f,  -2.5f, 2.0f,    -0.5f},
                     {-0.5f, 0.0f,    0.5f,  0.0f},
                     {0.0f,    1.0f,    0.0f,    0.0f}};

    auxR[0] = cubo * m[0][0] + quad * m[1][0] + t * m[2][0] + 1 * m[3][0];
    auxR[1] = cubo * m[0][1] + quad * m[1][1] + t * m[2][1] + 1 * m[3][1];
    auxR[2] = cubo * m[0][2] + quad * m[1][2] + t * m[2][2] + 1 * m[3][2];
    auxR[3] = cubo * m[0][3] + quad * m[1][3] + t * m[2][3] + 1 * m[3][3];

    auxD[0] = 3*quad*m[0][0] + 2*t*m[1][0] + m[2][0];
    auxD[1] = 3*quad*m[0][1] + 2*t*m[1][1] + m[2][1];
    auxD[2] = 3*quad*m[0][2] + 2*t*m[1][2] + m[2][2];
    auxD[3] = 3*quad*m[0][3] + 2*t*m[1][3] + m[2][3];


    i1 = pos[0];
    i2 = pos[1];
    i3 = pos[2];
    i4 = pos[3];

    p0 = ve[i1];
    p1 = ve[i2];
    p2 = ve[i3];
    p3 = ve[i4];

    res[0] = auxR[0] * p0.getX() + auxR[1] * p1.getX() + auxR[2] * p2.getX() + auxR[3] * p3.getX();
    res[1] = auxR[0] * p0.getY() + auxR[1] * p1.getY() + auxR[2] * p2.getY() + auxR[3] * p3.getY();
    res[2] = auxR[0] * p0.getZ() + auxR[1] * p1.getZ() + auxR[2] * p2.getZ() + auxR[3] * p3.getZ();

    deriv[0] = auxD[0] * p0.getX() + auxD[1]*p1.getX() + auxD[2]*p2.getX() + auxD[3]*p3.getX();
    deriv[1] = auxD[0] * p0.getY() + auxD[1]*p1.getY() + auxD[2]*p2.getY() + auxD[3]*p3.getY();
    deriv[2] = auxD[0] * p0.getZ() + auxD[1]*p1.getZ() + auxD[2]*p2.getZ() + auxD[3]*p3.getZ();
}


void Translacao::getGlobalCatmullRomPoint(float gt, float* res, float* deriv, std::vector<Vert> ve){
    float t;
    int size, i;
    int index[4];


    size = ve.size();
    t = size * gt; //the real global t
    i = floor(t); //arredonda
    t = t - i; //where within the segment

    index[0] = (i + size-1) % size;
    index[1] = (index[0] + 1) %size;
    index[2] = (index[1] + 1) %size;
    index[3] = (index[2] + 1) %size;

    getCatmullRomPoint(t, index,res, deriv, ve);
}


//desenhar a curva de catmull
void Translacao::renderCatmullRomCurve( std::vector<Vert> vert, float r, float g, float b) {
    int i;
    float npts[3];
    int x = (int) vert.size();
    glBegin(GL_LINE_LOOP);

    for(i=0; i < x ; i ++) {
        npts[0] = vert.at(i).getX();
        npts[1] = vert.at(i).getY();
        npts[2] = vert.at(i).getZ();
        glColor3f(r,g,b);
        glVertex3fv(npts);
    }

    glEnd();
}

std::vector<Vert> Translacao::curve() {
    float res[3];
    float deriv[3];
    float t;

    for (t = 0; t < 1; t += 0.01) {

        getGlobalCatmullRomPoint(t, res, deriv, ve);
        Vert p = Vert(res[0], res[1], res[2]);
        curvas.push_back(p);
    }
    return curvas;
}


    bool Translacao::semTranslacao() {
    return (tempo == 0.0 && ve.empty());
}

///////////////////transformacao
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

bool Transformacao::semTransformacao() {

    return (trans.semTranslacao() && rotacao.semRotacao() && escala.semEscala() && cor.semCor());
}

/////////////////////transformacoes

Transformacoes::Transformacoes() {
    //para perceber se está a ler uma esfera um anel... (sphere.3d)
    tipo = "";
    t = Transformacao();
    verts = std::vector<Vert>();
    subgrupo = std::vector<Transformacoes>();
    pos=0;
}

Transformacoes::Transformacoes(std::string tipo, Transformacao t, std::vector<Vert> verts, std::vector<Transformacoes> sub ) {
    this->tipo = tipo;
    this->t = t;
    this->verts = verts;
    this->subgrupo = sub;
}
