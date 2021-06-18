//
//  CG-FORMAS-PRIMARIAS
//

#include "sphere.h"

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

    for(int s=0; s<stacks; s++){

        rodela_atual = radius - (s*altura_rodela);
        proxima_rodela = radius -(s+1)*altura_rodela;
        angulo=0;

        for(int i=0; i<slices; i++, angulo=i*ang_slices){
            if(s==0){ //topo
                beta = getBeta(proxima_rodela,radius);
                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta,radius),proxima_rodela,esfericaZ(angulo,beta,radius));
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),proxima_rodela,esfericaZ(angulo+ang_slices,beta,radius));
                fprintf(f,"%f %f %f\n",0.0f, rodela_atual, 0.0f);
            }
            if(s==stacks-1){ //base
                beta = getBeta(rodela_atual,radius);
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),rodela_atual,esfericaZ(angulo+ang_slices,beta,radius));
                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta,radius),rodela_atual,esfericaZ(angulo,beta,radius));
                fprintf(f,"%f %f %f\n",0.0f, proxima_rodela, 0.0f);
            }else{ //faces/lateral
                beta = getBeta(rodela_atual,radius);
                beta2 = getBeta(proxima_rodela,radius);

                //stack atual com a inferior
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),rodela_atual,esfericaZ(angulo+ang_slices,beta,radius));
                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta,radius),rodela_atual,esfericaZ(angulo,beta,radius));
                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta2,radius),proxima_rodela,esfericaZ(angulo,beta2,radius));

                //stack inferior com stack atual
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta,radius),rodela_atual,esfericaZ(angulo+ang_slices,beta,radius));
                fprintf(f,"%f %f %f\n",esfericaX(angulo,beta2,radius),proxima_rodela,esfericaZ(angulo,beta2,radius));
                fprintf(f,"%f %f %f\n",esfericaX(angulo+ang_slices,beta2,radius),proxima_rodela,esfericaZ(angulo+ang_slices,beta2,radius));
            }
        }
    }
}
