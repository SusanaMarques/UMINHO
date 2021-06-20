//
//  CG-FORMAS-PRIMARIAS
//
//

#include "plane.h"

void plane( float ladox, float ladoz, FILE* f){


  float x,y,z=0;

  //divisao dos lados para ficar centrado na origem

  x = ladox/2;
  z=ladoz/2;

    fprintf(f, "%f %f %f \n", -x, y, -z); 
		fprintf(f, "%f %f %f \n", -x, y, z);
		fprintf(f, "%f %f %f \n", x, y, z);  
	
		fprintf(f, "%f %f %f \n", x, y, z);
		fprintf(f, "%f %f %f \n", x, y, -z);
		fprintf(f, "%f %f %f \n", -x, y, -z); 

		fprintf(f, "%f %f %f \n", -x, y, -z); 
  	fprintf(f, "%f %f %f \n", x, y, z);
  	fprintf(f, "%f %f %f \n", -x, y, z);

    fprintf(f, "%f %f %f \n", x, y, z);
    fprintf(f, "%f %f %f \n", -x, y, -z); 
    fprintf(f, "%f %f %f \n", x, y, -z);

}
