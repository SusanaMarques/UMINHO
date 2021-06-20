//
//  CG-FORMAS-PRIMARIAS
//
//

#include "cone.h"

#define _USE_MATH_DEFINES
#include <math.h>


void cone(float raio, float altura, int fatias, int camadas, FILE* f) {

	float volumeI = (M_PI * (raio * raio) * altura) / 3;
	//cout << "volumeI = " << volumeI << "\n";
	//float cor = 1 / (float(fatias));
	float ix0 = 0.0, iz0 = 0.0, ix1 = 0.0, iz1 = 0.0;
	float px0 = 0.0, pz0 = 0.0, px1 = 0.0, pz1 = 0.0;
	float slice_angle = (2 * M_PI) / float(fatias);
	float stack_num = altura / float(camadas);
	//cout << "slice_angle = " << slice_angle << "\n";
	float stck0 = 0.0, raio0 = raio, raio1 = 0.0;

	// GL_POLYGON, GL_LINE_LOOP
	for (int j = 0; j <= camadas; j++) {

		float stck1 = j * stack_num;
		//cout << "altura stck1 = " << stck1 << "\n";

		float raio1 = (raio - (raio / camadas) * j); 
		//cout << "raio1 = " << raio1 << "\n";

		ix0 = raio0 * sin(0);
		iz0 = raio0 * cos(0);
		ix1 = raio1 * sin(0);
		iz1 = raio1 * cos(0);


		for (int i = 1; i <= fatias; i++) {

			float angl = slice_angle * i;

			px0 = raio0 * sin(angl);
			pz0 = raio0 * cos(angl);
			px1 = raio1 * sin(angl);
			pz1 = raio1 * cos(angl);

			//base
			fprintf(f, "%f %f %f\n", 0.0f, stck0, 0.0f);
			fprintf(f, "%f %f %f\n", px0, stck0, pz0);
			fprintf(f, "%f %f %f\n", ix0, stck0, iz0);
			

			//topo
			fprintf(f, "%f %f %f\n", 0.0f, stck1, 0.0f);
			fprintf(f, "%f %f %f\n", ix1, stck1, iz1);
			fprintf(f, "%f %f %f\n", px1, stck1, pz1);

			//lado trig1
			fprintf(f, "%f %f %f\n", ix1, stck1, iz1);
			fprintf(f, "%f %f %f\n", ix0, stck0, iz0);
			fprintf(f, "%f %f %f\n", px0, stck0, pz0);

			//lado trig2
			fprintf(f, "%f %f %f\n", px1, stck1, pz1);
			fprintf(f, "%f %f %f\n", ix1, stck1, iz1);
			fprintf(f, "%f %f %f\n", px0, stck0, pz0);


			ix0 = px0;
			iz0 = pz0;
			ix1 = px1;
			iz1 = pz1;

		}
		//cor += j * 0.01;
		stck0 = stck1;
		raio0 = raio1;
	}

}

