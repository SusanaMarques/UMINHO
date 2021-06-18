//
//  CG-FORMAS-PRIMARIAS
//

#include "bezierPatch.h"

#define _USE_MATH_DEFINES
#include <math.h>

float getBeta(float height, float radius){ //height-distancia do ponto até ao eixo
    return asin(height/radius);
}

float esfericaX(float alfa, float beta, float radius){
    return radius*cos(beta)*sin(alfa);
}

float esfericaZ(float alfa, float beta, float radius){
    return radius*cos(beta)*cos(alfa);
}


void sphere(float radius, int slices, int stacks, FILE* f){
    float altura_rodela = (2*radius)/stacks;
    float ang_slices = ((360*M_PI)/180)/slices;

    float rodela_atual;  //altura/y da stack atual
    float proxima_rodela; //altura/y da stack a baixo
    float beta;
    float beta2;
    float angulo;
    float tu; //coordenada u da textura
    float tv; //coordenada v da textura

    for(int s=0; s<stacks; s++){

        rodela_atual = radius - (s*altura_rodela);
        proxima_rodela = radius -(s+1)*altura_rodela;
        angulo=0;

        for(int i=0; i<slices; i++, angulo=i*ang_slices){
            if(s==0){ //topo
                beta = getBeta(proxima_rodela,radius);
                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta,radius),proxima_rodela,esfericaZ(angulo,beta,radius));
                Vec3f v1 = Vec3f(esfericaX(angulo, beta, radius), proxima_rodela, esfericaZ(angulo, beta, radius));
                v1.normalize();
                fprintf(f,"%f %f %f\n",v1.x,v1.y,v1.z);
                tu = asin(v1.x) / M_PI + 0.5f;
                tv = asin(v1.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),proxima_rodela,esfericaZ(angulo+ang_slices,beta,radius));
                Vec3f v2 = Vec3f(esfericaX(angulo + ang_slices, beta, radius), proxima_rodela, esfericaZ(angulo + ang_slices, beta, radius));
                v2.normalize();
                fprintf(f, "%f %f %f\n", v2.x, v2.y, v2.z);
                tu = asin(v2.x) / M_PI + 0.5f;
                tv = asin(v2.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",0.0f, rodela_atual, 0.0f);
                Vec3f v3 = Vec3f(0.0f, rodela_atual, 0.0f);
                v3.normalize();
                fprintf(f, "%f %f %f\n", v3.x, v3.y, v3.z);
                tu = asin(v3.x) / M_PI + 0.5f;
                tv = asin(v3.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);
            }
            if(s==stacks-1){ //base
                beta = getBeta(rodela_atual,radius);
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),rodela_atual,esfericaZ(angulo+ang_slices,beta,radius));
                Vec3f v7 = Vec3f(esfericaX(angulo + ang_slices, beta, radius), rodela_atual, esfericaZ(angulo + ang_slices, beta, radius));
                v7.normalize();
                fprintf(f, "%f %f %f\n", v7.x, v7.y, v7.z);
                tu = asin(v7.x) / M_PI + 0.5f;
                tv = asin(v7.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta,radius),rodela_atual,esfericaZ(angulo,beta,radius));
                Vec3f v8 = Vec3f(esfericaX(angulo, beta, radius), rodela_atual, esfericaZ(angulo, beta, radius));
                v8.normalize();
                fprintf(f, "%f %f %f\n", v8.x, v8.y, v8.z);
                tu = asin(v8.x) / M_PI + 0.5f;
                tv = asin(v8.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",0.0f, proxima_rodela, 0.0f);
                Vec3f v9 = Vec3f(0.0f, proxima_rodela, 0.0f);
                v9.normalize();
                fprintf(f, "%f %f %f\n", v9.x, v9.y, v9.z);
                tu = asin(v9.x) / M_PI + 0.5f;
                tv = asin(v9.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

            }else{ //faces/lateral
                beta = getBeta(rodela_atual,radius);
                beta2 = getBeta(proxima_rodela,radius);

                //stack atual com a inferior
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),rodela_atual,esfericaZ(angulo+ang_slices,beta,radius));
                Vec3f v4 = Vec3f(esfericaX(angulo + ang_slices, beta, radius), rodela_atual, esfericaZ(angulo + ang_slices, beta, radius));
                v4.normalize();
                fprintf(f, "%f %f %f\n", v4.x, v4.y, v4.z);
                tu = asin(v4.x) / M_PI + 0.5f;
                tv = asin(v4.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta,radius),rodela_atual,esfericaZ(angulo,beta,radius));
                Vec3f v5 = Vec3f(esfericaX(angulo, beta, radius), rodela_atual, esfericaZ(angulo, beta, radius));
                v5.normalize();
                fprintf(f, "%f %f %f\n", v5.x, v5.y, v5.z);
                tu = asin(v5.x) / M_PI + 0.5f;
                tv = asin(v5.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta2,radius),proxima_rodela,esfericaZ(angulo,beta2,radius));
                Vec3f v6 = Vec3f(esfericaX(angulo, beta2, radius), proxima_rodela, esfericaZ(angulo, beta2, radius));
                v6.normalize();
                fprintf(f, "%f %f %f\n", v6.x, v6.y, v6.z);
                tu = asin(v6.x) / M_PI + 0.5f;
                tv = asin(v6.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);
            

                //stack inferior com stack atual
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),rodela_atual,esfericaZ(angulo+ang_slices,beta,radius));
                Vec3f v10 = Vec3f(esfericaX(angulo + ang_slices, beta, radius), rodela_atual, esfericaZ(angulo + ang_slices, beta, radius));
                v10.normalize();
                fprintf(f, "%f %f %f\n", v10.x, v10.y, v10.z);
                tu = asin(v10.x) / M_PI + 0.5f;
                tv = asin(v10.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta2,radius),proxima_rodela,esfericaZ(angulo,beta2,radius));
                Vec3f v11 = Vec3f(esfericaX(angulo, beta2, radius), proxima_rodela, esfericaZ(angulo, beta2, radius));
                v11.normalize();
                fprintf(f, "%f %f %f\n", v11.x, v11.y, v11.z);
                tu = asin(v11.x) / M_PI + 0.5f;
                tv = asin(v11.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);

                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta2,radius),proxima_rodela,esfericaZ(angulo+ang_slices,beta2,radius));
                Vec3f v12 = Vec3f(esfericaX(angulo + ang_slices, beta2, radius), proxima_rodela, esfericaZ(angulo + ang_slices, beta2, radius));
                v12.normalize();
                fprintf(f, "%f %f %f\n", v12.x, v12.y, v12.z);
                tu = asin(v12.x) / M_PI + 0.5f;
                tv = asin(v12.y) / M_PI + 0.5f;
                fprintf(f,"%f %f\n",tu, tv);
            }
        }
    }
}
