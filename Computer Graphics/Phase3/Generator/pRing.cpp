//
//  CG-FORMAS-PRIMARIAS
//

#include "pRing.h"

#define _USE_MATH_DEFINES
#include <math.h>

void pRing(float radius_int, float radius_out, int slices, int stacks, FILE* f){


	float ang_slice = (360*M_PI)/(slices*180);

	for(float i = 0; i < 2*M_PI; i += ang_slice){

		fprintf(f, "%f %f %f \n", sin(i)*radius_int, 0.0, cos(i)*radius_int);
		fprintf(f, "%f %f %f \n", sin(i)*radius_out, 0.0, cos(i)*radius_out);
		fprintf(f, "%f %f %f \n", sin(i+ang_slice)*radius_int, 0.0, cos(i+ang_slice)*radius_int);

		fprintf(f, "%f %f %f \n", sin(i)*radius_out, 0.0, cos(i)*radius_out);
		fprintf(f, "%f %f %f \n", sin(i+ang_slice)*radius_out, 0.0, cos(i+ang_slice)*radius_out);
		fprintf(f, "%f %f %f \n", sin(i+ang_slice)*radius_int, 0.0, cos(i+ang_slice)*radius_int);

		fprintf(f, "%f %f %f \n", sin(i)*radius_out, 0.0, cos(i)*radius_out);
		fprintf(f, "%f %f %f \n", sin(i)*radius_int, 0.0, cos(i)*radius_int);
		fprintf(f, "%f %f %f \n", sin(i+ang_slice)*radius_int, 0.0, cos(i+ang_slice)*radius_int);

		fprintf(f, "%f %f %f \n", sin(i+ang_slice)*radius_out, 0.0, cos(i+ang_slice)*radius_out);
		fprintf(f, "%f %f %f \n", sin(i)*radius_out, 0.0, cos(i)*radius_out);
		fprintf(f, "%f %f %f \n", sin(i+ang_slice)*radius_int, 0.0, cos(i+ang_slice)*radius_int);
		
	}
}
