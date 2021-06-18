//
//  CG-FORMAS-PRIMARIAS
//

#include "orbit.h"

#define _USE_MATH_DEFINES
#include <math.h>

void orbit(float radius_int, float radius_out, int slices, FILE* f){

    float ang_slices = ((360*M_PI)/180)/slices;

    for (float i = 0; i < 2*M_PI; i += ang_slices){
			fprintf(f,"%f %f %f \n",sin(i)*radius_int,0.0,cos(i)*radius_int);
			fprintf(f,"%f %f %f \n",sin(i)*radius_out,0.0,cos(i)*radius_out);
			fprintf(f,"%f %f %f \n",sin(i+ang_slices)*radius_int,0.0,cos(i+ang_slices)*radius_int);
			fprintf(f,"%f %f %f \n",sin(i)*radius_out,0.0,cos(i)*radius_out);
			fprintf(f,"%f %f %f \n",sin(i+ang_slices)*radius_out,0.0,cos(i+ang_slices)*radius_out);
			fprintf(f,"%f %f %f \n",sin(i+ang_slices)*radius_int,0.0,cos(i+ang_slices)*radius_int);
			fprintf(f,"%f %f %f \n",sin(i)*radius_out,0.0,cos(i)*radius_out);
			fprintf(f,"%f %f %f \n",sin(i)*radius_int,0.0,cos(i)*radius_int);
			fprintf(f,"%f %f %f \n",sin(i+ang_slices)*radius_int,0.0,cos(i+ang_slices)*radius_int);
			fprintf(f,"%f %f %f \n",sin(i+ang_slices)*radius_out,0.0,cos(i+ang_slices)*radius_out);
			fprintf(f,"%f %f %f \n",sin(i)*radius_out,0.0,cos(i)*radius_out);
			fprintf(f,"%f %f %f \n",sin(i+ang_slices)*radius_int,0.0,cos(i+ang_slices)*radius_int);
		}    
}
