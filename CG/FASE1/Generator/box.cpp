//
//  CG-FORMAS-PRIMARIAS
//
//
#include "box.h"

#define _USE_MATH_DEFINES
#include <stdio.h>
#include <math.h>



void box(float altura, float largura, float comprimento, int div_horiz, int div_vert, FILE* f) {

    float z_horizontal_step = altura / (float) div_horiz;
    float z_vertical_step = largura / (float) div_vert;

    float x_horizontal_step = altura / (float) div_horiz;
    float x_vertical_step = comprimento / (float) div_vert;

    float y_horizontal_step = largura / (float) div_horiz;
    float y_vertical_step = comprimento / (float) div_vert;

    //z == 0 && z == lado_z
    for(int c = 0; c < div_vert; c++) {
        for(int l = 0; l < div_horiz; l++) {

            //z == lado_z
            fprintf(f,"%f %f %f\n", c*z_vertical_step, l*z_horizontal_step, comprimento);
            fprintf(f,"%f %f %f\n", (c+1)*z_vertical_step, (l+1)*z_horizontal_step, comprimento);
            fprintf(f,"%f %f %f\n", c*z_vertical_step, (l+1)*z_horizontal_step, comprimento);

            fprintf(f,"%f %f %f\n", c*z_vertical_step, l*z_horizontal_step, comprimento);
            fprintf(f,"%f %f %f\n", (c+1)*z_vertical_step, l*z_horizontal_step, comprimento);
            fprintf(f,"%f %f %f\n", (c+1)*z_vertical_step, (l+1)*z_horizontal_step, comprimento);

            //z == 0
            fprintf(f,"%f %f %f\n", c*z_vertical_step, (l+1)*z_horizontal_step, 0.0f);
            fprintf(f,"%f %f %f\n", (c+1)*z_vertical_step, (l+1)*z_horizontal_step, 0.0f);
            fprintf(f,"%f %f %f\n", c*z_vertical_step, l*z_horizontal_step, 0.0f);

            fprintf(f,"%f %f %f\n", (c+1)*z_vertical_step, (l+1)*z_horizontal_step, 0.0f);
            fprintf(f,"%f %f %f\n", (c+1)*z_vertical_step, l*z_horizontal_step, 0.0f);
            fprintf(f,"%f %f %f\n", c*z_vertical_step, l*z_horizontal_step, 0.0f);





            //x == lado_x
            fprintf(f,"%f %f %f\n", largura, (l+1)*x_horizontal_step, c*x_vertical_step);
            fprintf(f,"%f %f %f\n", largura, (l+1)*x_horizontal_step, (c+1)*x_vertical_step);
            fprintf(f,"%f %f %f\n", largura, l*x_horizontal_step, c*x_vertical_step);

            fprintf(f,"%f %f %f\n", largura, (l+1)*x_horizontal_step, (c+1)*x_vertical_step);
            fprintf(f,"%f %f %f\n", largura, l*x_horizontal_step, (c+1)*x_vertical_step);
            fprintf(f,"%f %f %f\n", largura, l*x_horizontal_step, c*x_vertical_step);

            //x == 0
            fprintf(f,"%f %f %f\n", 0.0f, l*x_horizontal_step, c*x_vertical_step);
            fprintf(f,"%f %f %f\n", 0.0f, (l+1)*x_horizontal_step, (c+1)*x_vertical_step);
            fprintf(f,"%f %f %f\n", 0.0f, (l+1)*x_horizontal_step, c*x_vertical_step);

            fprintf(f,"%f %f %f\n", 0.0f, l*x_horizontal_step, c*x_vertical_step);
            fprintf(f,"%f %f %f\n", 0.0f, l*x_horizontal_step, (c+1)*x_vertical_step);
            fprintf(f,"%f %f %f\n", 0.0f, (l+1)*x_horizontal_step, (c+1)*x_vertical_step);




            //y == altura
            fprintf(f,"%f %f %f\n", l*y_horizontal_step, altura, c*y_vertical_step);
            fprintf(f,"%f %f %f\n", (l+1)*y_horizontal_step, altura, (c+1)*y_vertical_step);
            fprintf(f,"%f %f %f\n", (l+1)*y_horizontal_step, altura, c*y_vertical_step);

            fprintf(f,"%f %f %f\n", l*y_horizontal_step, altura, c*y_vertical_step);
            fprintf(f,"%f %f %f\n", l*y_horizontal_step, altura, (c+1)*y_vertical_step);
            fprintf(f,"%f %f %f\n", (l+1)*y_horizontal_step, altura, (c+1)*y_vertical_step);
            
            //y == 0
            fprintf(f,"%f %f %f\n", (l+1)*y_horizontal_step, 0.0f, c*y_vertical_step);
            fprintf(f,"%f %f %f\n", (l+1)*y_horizontal_step, 0.0f, (c+1)*y_vertical_step);
            fprintf(f,"%f %f %f\n", l*y_horizontal_step, 0.0f, c*y_vertical_step);

            fprintf(f,"%f %f %f\n", (l+1)*y_horizontal_step, 0.0f, (c+1)*y_vertical_step);
            fprintf(f,"%f %f %f\n", l*y_horizontal_step, 0.0f, (c+1)*y_vertical_step);
            fprintf(f,"%f %f %f\n", l*y_horizontal_step, 0.0f, c*y_vertical_step);

        }
    }
    
}
