#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include "sphere.h"
#include "box.h"
#include "cone.h"
#include "plane.h"
#include "pRing.h"

int main(int argc, char *argv[]){
	int i1,i2;
	float p1,p2,p3;
	char* op=argv[1];
	FILE* f=NULL;

	if(argc<2)
		printf("ERRO!! Nenhuma 'tag' de desenho detectada!\nTem as seguintes opções:\n\t-> sphere\n\t-> cone\n\t-> plane\n\t-> box\n");
	else
		if(strcmp(op,"sphere")==0){
			if(argc==6){

				if(sscanf(argv[2],"%f",&p1)&&sscanf(argv[3],"%d",&i1)&&sscanf(argv[4],"%d",&i2)){
					f=fopen(argv[5],"w");
					sphere(p1,i1,i2,f);
					fclose(f);

				}else
					printf("ERRO!! Parametros não estão correctos!\nEx: sphere [raio] [camadas] [fatias] [output]\n");
			}
			else
				printf("ERRO!! Número de argumentos errado\nEx: sphere [raio] [camadas] [fatias] [output]\n");
		}else
					if(strcmp(op,"plane")==0){
						if(argc==5){

							if(sscanf(argv[2],"%f",&p1)&&sscanf(argv[3],"%f",&p2)){
								f=fopen(argv[4],"w");
								plane(p1,p2,f);
								fclose(f);

							}else
								printf("ERRO!! Parametros não estão correctos!\nEx: plane [Lado x] [Lado z] [output]\n");
						}
						else
							printf("ERRO!! Número de argumentos errado\nEx: plane [Lado x] [Lado y] [output]\n");
					}
					else
						if(strcmp(op,"box")==0){
							if(argc==8){

								if(sscanf(argv[2],"%f",&p1)&&sscanf(argv[3],"%f",&p2)&&sscanf(argv[4],"%f",&p3)&&sscanf(argv[5],"%d",&i1)&&sscanf(argv[6],"%d",&i2)){
									f=fopen(argv[7],"w");
									box(p1,p2,p3,i1,i2,f);
									fclose(f);

								}else
									printf("ERRO!! Parametros não estão correctos!\nEx: box [altura] [largura] [comprimento] [divisoes horizontais] [divisoes verticais] [output]\n");
							}
							else
								printf("ERRO!! Número de argumentos errado\nEx: box [altura] [largura] [comprimento] [divisoes horizontais] [divisoes verticais] [output]\n");
						}
						else
							if(strcmp(op,"cone")==0){
								if(argc==7){

									if(sscanf(argv[2],"%f",&p1) && sscanf(argv[3],"%f",&p2) && sscanf(argv[4],"%d",&i1) && sscanf(argv[5],"%d",&i2)){
										f=fopen(argv[6],"w");
										cone(p1,p2,i1,i2,f);
										fclose(f);

									}else
										printf("ERRO!! Parametros não estão correctos!\nEx: cone [raio_base] [altura] [fatias] [camadas] [output]\n");
								}
								else
									printf("ERRO!! Número de argumentos errado\nEx: cone [raio_base] [altura] [fatias] [camadas] [output]\n");
							}
							else
								if(strcmp(op,"pRing")==0){
									if(argc==7){

										if(sscanf(argv[2],"%f",&p1) && sscanf(argv[3],"%f",&p2) && sscanf(argv[4],"%d",&i1) && sscanf(argv[5],"%d",&i2)){
											f=fopen(argv[6],"w");
											pRing(p1,p2,i1,i2,f);
											fclose(f);

										}else
											printf("ERRO!! Parametros não estão correctos!\nEx: pRing [raio_interior] [raio_exterior] [fatias] [camadas] [output]\n");
									}
									else
										printf("ERRO!! Número de argumentos errado\nEx: pRing [raio_interior] [raio_exterior] [fatias] [camadas] [output]\n");
								}
              else
								printf("ERRO!! Nenhuma 'tag' de desenho detectada!\nTem as seguintes opções:\n\t-> sphere\n\t-> cone\n\t-> plane\n\t-> box\n");
                }
