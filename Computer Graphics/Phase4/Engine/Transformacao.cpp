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


void Transformacoes::use_VBO() {
    int i;
    int p = 0;
    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_NORMAL_ARRAY);
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);

    v = (float*) malloc(sizeof(float) * verts.size()*3);
    n = (float*) malloc(sizeof(float) * normal.size()*3);
    textu = (float*) malloc(sizeof(float) * textura.size()*2);

    for(i=0; i < verts.size(); i++) {
        v[p] = verts[i].getX();
        v[p + 1] = verts[i].getY();
        v[p + 2] = verts[i].getZ();
        p += 3;
    }

    p = 0;

    for(i=0; i < normal.size(); i++){
        n[p] = normal[i].getX();
        n[p+1] = normal[i].getY();
        n[p+2] = normal[i].getZ();
        p+=3;
    }

    p = 0;
    for(i=0; i < textura.size(); i++){
        textu[p] = textura[i].getX();
        textu[p+1]  = textura[i].getY();
        p+=2;
    }

    p_tam = verts.size() * 3;
    n_tam = normal.size() * 3;
    tex_tam = textura.size() * 2;

    glGenBuffers(3,buffer);
    glBindBuffer(GL_ARRAY_BUFFER, buffer[0]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float) * p_tam, v, GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER,buffer[1]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float) * n_tam, n, GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER, buffer[2]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float) * tex_tam, textu, GL_STATIC_DRAW);

    free(v);
    free(n);
    free(textu);
    newText();
    }


void Transformacoes::newText() {
    std::string path = "../../../Fase4/texturas/" + text;
    ilGenImages(1, &tt);
    ilBindImage(tt);
    ilLoadImage((ILstring) path.c_str());
    width =  (unsigned int) ilGetInteger(IL_IMAGE_WIDTH);
    height = (unsigned int) ilGetInteger(IL_IMAGE_HEIGHT);
    ilConvertImage(IL_RGBA, IL_UNSIGNED_BYTE);
    data = ilGetData();
    glGenTextures(1, &texID);
    glBindTexture(GL_TEXTURE_2D, texID);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
    gluBuild2DMipmaps(GL_TEXTURE_2D, GL_RGBA, width, height,
                      GL_RGBA, GL_UNSIGNED_BYTE, data);
    //glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);

}

void Transformacoes::desenha() {
    glBindBuffer(GL_ARRAY_BUFFER, buffer[0]);
    glVertexPointer(3 , GL_FLOAT, 0 , 0);
    if(normal.size() != 0){
        glBindBuffer(GL_ARRAY_BUFFER, buffer[1]);
        glNormalPointer(GL_FLOAT, 0 , 0);
    }
    if(textura.size() != 0){
        glBindBuffer(GL_ARRAY_BUFFER, buffer[2]);
        glTexCoordPointer(2 , GL_FLOAT, 0 , 0); 
	}
    glDrawArrays(GL_TRIANGLES,0, p_tam);
}


//Camera

Camera::Camera(){
    posicao = Vert(0.0f,30.0f,500.0f);
    front = Vert(0.0f,0.0f,0.1f);
    up = Vert(0.0f,1.0,0.0f);
}

Vert cross(Vert a, Vert b){
    Vert r = Vert();
    r.setX(a.getY()*b.getZ() - a.getZ()*b.getY());
    r.setY(a.getZ()*b.getX() - a.getX()*b.getZ());
    r.setZ(a.getX()*b.getY() - a.getY()*b.getX());
    return r;
}

Vert normalize(Vert v){
    Vert r = Vert();
    float n = sqrt(pow(v.getX(),2)+pow(v.getY(),2)+pow(v.getZ(),2));
    r.setX(v.getX()/n);
    r.setY(v.getY()/n);
    r.setZ(v.getZ()/n);
    return r;
}



float radians(float a){
    return a*(M_PI/180);
}

void Camera::posicaoForward(float speed){
    Vert r = Vert();
    r.setX(this->posicao.getX()+(speed * normalize(this->front).getX()));
    r.setY(this->posicao.getY()+(speed * normalize(this->front).getY()));
    r.setZ(this->posicao.getZ()+(speed * normalize(this->front).getZ()));
    setposicao(r);
}

void Camera::posicaoBackward(float speed){
    Vert r = Vert();
    r.setX(this->posicao.getX()-(speed * normalize(this->front).getX()));
    r.setY(this->posicao.getY()-(speed * normalize(this->front).getY()));
    r.setZ(this->posicao.getZ()-(speed * normalize(this->front).getZ()));
    setposicao(r);
}

void Camera::posicaoLeft(float speed){
    Vert r = Vert();
    Vert aux =normalize(cross(this->front, this->up));
    r.setX(this->posicao.getX()-(aux.getX()* speed));
    r.setY(this->posicao.getY()-(aux.getY()* speed));
    r.setZ(this->posicao.getZ()-(aux.getZ()* speed));
    setposicao(r);
}

void Camera::posicaoRight(float speed){
    Vert r = Vert();
    Vert aux =normalize(cross(this->front, this->up));
    r.setX(this->posicao.getX()+aux.getX()* speed);
    r.setY(this->posicao.getY()+aux.getY()* speed);
    r.setZ(this->posicao.getZ()+aux.getZ()* speed);
    setposicao(r);
}



void Camera::lookdirection(float yaw, float pitch) {
    Vert direction = Vert();
    direction.setX(cos(radians(yaw)) * cos(radians(pitch)));
    direction.setY(sin(radians(pitch)));
    direction.setZ(sin(radians(yaw)) * cos(radians(pitch)));
    setfront(direction);
}



