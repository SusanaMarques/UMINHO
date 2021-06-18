#include "bezierPatch.h"

// "Global" Values

int nrPatches = 0;
int nrVertices = 0;
std::vector<std::vector<int>> patches;
std::vector<Vec3f> vertices;

// Function that reads and collects info from .patch file

void readPFile(string pFile)
{

    string line;
    string newl;
    string delimiter1 = "\n";
    string delimiter2 = ", ";

    int pos;
    float xx, yy, zz;

    ifstream file(pFile);

    if (file.is_open())
    {

        // 1. Capture Number of Patches

        if (getline(file, line))
        {

            pos = line.find(delimiter1);
            newl = line.substr(0, pos);
            nrPatches = atof(newl.c_str());
            line.erase(0, pos + delimiter1.length());
        }

        // 2. Capture Patch Representation (16 Points)

        for (int i = 0; i < nrPatches; i++)
        {

            std::vector<int> ni;

            if (getline(file, line))
            {

                // 2.a. First Fifteen

                for (int x = 0; x < 15; x++)
                {

                    pos = line.find(delimiter2);
                    newl = line.substr(0, pos);
                    int aux = atof(newl.c_str());
                    line.erase(0, pos + delimiter2.length());
                    ni.push_back(aux);
                }

                // 2.b. Last

                pos = line.find(delimiter1);
                newl = line.substr(0, pos);
                int aux = atof(newl.c_str());
                line.erase(0, pos + delimiter1.length());
                ni.push_back(aux);
            }

            // 2.c. Push Patch Line

            patches.push_back(ni);
        }

        // 3. Capture Number of Control Points

        if (getline(file, line))
        {

            pos = line.find(delimiter1);
            newl = line.substr(0, pos);
            nrVertices = atof(newl.c_str());
            line.erase(0, pos + delimiter1.length());
        }

        // 4. Capture Control Point's Coordinates

        for (int i = 0; i < nrVertices; i++)
        {

            if (getline(file, line))
            {

                // 4.a. Capture x

                pos = line.find(delimiter2);
                newl = line.substr(0, pos);
                xx = atof(newl.c_str());
                line.erase(0, pos + delimiter2.length());

                // 4.b. Capture y

                pos = line.find(delimiter2);
                newl = line.substr(0, pos);
                yy = atof(newl.c_str());
                line.erase(0, pos + delimiter2.length());

                // 4.c. Capture z

                pos = line.find(delimiter1);
                newl = line.substr(0, pos);
                zz = atof(newl.c_str());
                line.erase(0, pos + delimiter1.length());

                // 4.d. Create a "Point"
                Vec3f v;
                v = Vec3f(xx, yy, zz);
                //v = {xx, yy, zz};

                // 4.e. Push Point

                vertices.push_back(v);
            }
        }

        file.close();

        cout << "File: " << pFile << " finished" << endl;
    }
    else
    {

        //DO NOTHING
    }
}

void debugDisMess()
{

    printf("XXX --- TESTX --- XXX\n");
    printf("---------------------\n");
    printf("Num of Patches: %d\n", nrPatches);
    printf("---------------------\n");
    printf("Num of Vertices: %d\n", nrVertices);
    printf("---------------------\n");
    printf("PATCHES\n");

    for (int i = 0; i < nrPatches; i++)
    {
        printf("PATCH %d: ", i);
        for (int j = 0; j < 16; j++)
        {
            printf("%d ", patches[i][j]);
        }
        printf("\n");
    }

    printf("---------------------\n");
    printf("VERTICES\n");

    for (int i = 0; i < nrVertices; i++)
    {
        Vec3f aux = vertices[i];
        cout << "V: " << aux << endl;
    }
}

//Equation

Vec3f calcBezierCurve(const Vec3f *P, float a)
{

    float k1 = (1 - a) * (1 - a) * (1 - a);
    float k2 = 3 * a * (1 - a) * (1 - a);
    float k3 = 3 * a * a * (1 - a);
    float k4 = a * a * a;

    return P[0] * k1 + P[1] * k2 + P[2] * k3 + P[3] * k4;
}

Vec3f calcBezierPatch(const Vec3f *controlPoints, float u, float v)
{

    // Line U -> The patch can be interpreted has a Matrix u * v
    Vec3f uCurve[4];

    // Cycles 4 control points
    for (int i = 0; i < 4; ++i)
    {

        // First calc for line u
        uCurve[i] = calcBezierCurve(&controlPoints[4 * i], u);
    }

    //Then calc for line v
    return calcBezierCurve(uCurve, v);
}

// "Main" Function - Generates .3d file

void bezierPatch(string pFile, int tessellation, FILE *f)
{

    readPFile(pFile);

    debugDisMess();

    // Stores "Bezier Points" we calculate
    Vec3f *BezierPoints = new Vec3f[(tessellation + 1) * (tessellation + 1)];

    // Stores control points (coordinates) from this patch
    Vec3f controlPoints[16];

    // Cycles through every patch (we are going to generate model patch by patch)
    for (int i = 0; i < nrPatches; i++)
    {

        // Set this patch control points coordinates
        for (int j = 0; j < 16; j++)
        {

            controlPoints[j].x = vertices[patches[i][j]].x;
            controlPoints[j].y = vertices[patches[i][j]].y;
            controlPoints[j].z = vertices[patches[i][j]].z;
        }

        // calc every point according to tessellation
        for (int j = 0, z = 0; j <= tessellation; j++)
        {

            for (int m = 0; m <= tessellation; m++, z++)
            {

                BezierPoints[z] = calcBezierPatch(controlPoints, m / (float)tessellation, j / (float)tessellation);
            }
        }

        // print every face to file
        for (int j = 0, z = 0; j < tessellation; j++)
        {

            for (int m = 0; m < tessellation; m++, z++)
            {

                int va = (tessellation + 1) * j + m;
                int vb = (tessellation + 1) * (j + 1) + m;
                int vc = (tessellation + 1) * (j + 1) + m + 1;
                int vd = (tessellation + 1) * j + m + 1;

                fprintf(f, "%f %f %f\n", BezierPoints[vc].x, BezierPoints[vc].y, BezierPoints[vc].z);
                fprintf(f, "%f %f %f\n", BezierPoints[vb].x, BezierPoints[vb].y, BezierPoints[vb].z);
                fprintf(f, "%f %f %f\n", BezierPoints[va].x, BezierPoints[va].y, BezierPoints[va].z);
                fprintf(f, "%f %f %f\n", BezierPoints[vd].x, BezierPoints[vd].y, BezierPoints[vd].z);
                fprintf(f, "%f %f %f\n", BezierPoints[vc].x, BezierPoints[vc].y, BezierPoints[vc].z);
                fprintf(f, "%f %f %f\n", BezierPoints[va].x, BezierPoints[va].y, BezierPoints[va].z);
            }
        }
    }
}