
//  CG-FORMAS-PRIMARIAS

#include <stdio.h>
#include <math.h>
#include <csignal>
#include <string>
#include <stdlib.h>
#include <fstream>
#include <iostream>
#include <vector>
#include "geometry.h"

using namespace std;

#ifndef CG_bezierPatch_h
#define CG_bezierPatch_h

void readPFile(string pFile);

void bezierPatch(string pFile, int tessellation, FILE *f);

#endif