package com.company;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_HALF_UP;


public class Probability {


    public BigDecimal prob1(int X, int Y) {

        BigDecimal[] A = new BigDecimal[13];
        BigDecimal[] E = new BigDecimal[13];
        A[0] = BigDecimal.valueOf(0.0360);
        E[0] = BigDecimal.valueOf(0.0212);
        A[1] = BigDecimal.valueOf(0.0620);
        E[1] = BigDecimal.valueOf(0.0768);
        A[2] = BigDecimal.valueOf(0.1024);
        E[2] = BigDecimal.valueOf(0.1508);
        A[3] = BigDecimal.valueOf(0.1384);
        E[3] = BigDecimal.valueOf(0.1976);
        A[4] = BigDecimal.valueOf(0.1444);
        E[4] = BigDecimal.valueOf(0.1948);
        A[5] = BigDecimal.valueOf(0.1152);
        E[5] = BigDecimal.valueOf(0.1528);
        A[6] = BigDecimal.valueOf(0.0992);
        E[6] = BigDecimal.valueOf(0.1044);
        A[7] = BigDecimal.valueOf(0.0868);
        E[7] = BigDecimal.valueOf(0.0592);
        A[8] = BigDecimal.valueOf(0.0760);
        E[8] = BigDecimal.valueOf(0.0228);
        A[9] = BigDecimal.valueOf(0.0584);
        E[9] = BigDecimal.valueOf(0.0112);
        A[10] = BigDecimal.valueOf(0.0488);
        E[10] = BigDecimal.valueOf(0.0048);
        A[11] = BigDecimal.valueOf(0.0256);
        E[11] = BigDecimal.valueOf(0.0020);
        A[12] = BigDecimal.valueOf(0.0068);
        E[12] = BigDecimal.valueOf(0.0016);

        BigDecimal res = BigDecimal.valueOf(0.0);

        //Casos clientes satisfeitos e insatisfeitos
        int s = X;
        while (s <= 12 && Y != 12) {
            res = res.add(A[s].multiply(E[Y]));
            s++;
        }

        int sd = X;
        int sf = Y;
        while ((sd > 0) && (sf > 0)) {
            res = res.add(E[sf - 1].multiply(A[sd - 1]));
            sd--;
            sf--;
        }


        //caso particular probabilidade P(X12)
        if (Y == 12 && X > 1) {
            int ss = X;
            int g = 0;
            int sss = X;
            int f = sss;
            while (ss > 1) {
                sss = f;
                int w = Y - 1;
                while (sss > 1) {
                    res = res.add(A[g].multiply(E[w]));
                    sss--;
                    w--;
                }
                f--;
                ss--;
                g++;
            }
        }
        //caso particular probabilidade P(X12)
        int p = 0;
        while (Y == 12 && p <= 12) {
            res = res.add(A[p].multiply(E[Y]));
            p++;
        }


        return res;

    }

    public BigDecimal prob2(int X, int Y) {

        BigDecimal[] A = new BigDecimal[13];
        BigDecimal[] E = new BigDecimal[13];
        A[0] = BigDecimal.valueOf(0.0316);
        E[0] = BigDecimal.valueOf(0.0144);
        A[1] = BigDecimal.valueOf(0.0984);
        E[1] = BigDecimal.valueOf(0.0516);
        A[2] = BigDecimal.valueOf(0.1764);
        E[2] = BigDecimal.valueOf(0.1320);
        A[3] = BigDecimal.valueOf(0.2144);
        E[3] = BigDecimal.valueOf(0.1776);
        A[4] = BigDecimal.valueOf(0.1936);
        E[4] = BigDecimal.valueOf(0.2028);
        A[5] = BigDecimal.valueOf(0.1384);
        E[5] = BigDecimal.valueOf(0.1520);
        A[6] = BigDecimal.valueOf(0.0820);
        E[6] = BigDecimal.valueOf(0.1184);
        A[7] = BigDecimal.valueOf(0.0352);
        E[7] = BigDecimal.valueOf(0.0740);
        A[8] = BigDecimal.valueOf(0.0188);
        E[8] = BigDecimal.valueOf(0.0396);
        A[9] = BigDecimal.valueOf(0.0084);
        E[9] = BigDecimal.valueOf(0.0236);
        A[10] = BigDecimal.valueOf(0.0024);
        E[10] = BigDecimal.valueOf(0.0092);
        A[11] = BigDecimal.valueOf(0.0004);
        E[11] = BigDecimal.valueOf(0.0028);
        A[12] = BigDecimal.valueOf(0.0000);
        E[12] = BigDecimal.valueOf(0.0020);

        BigDecimal res = BigDecimal.valueOf(0.0);

        //Casos clientes satisfeitos e insatisfeitos
        int s = X;
        while (s <= 12 && Y != 12) {
            res = res.add(A[s].multiply(E[Y]));
            s++;
        }

        int sd = X;
        int sf = Y;
        while ((sd > 0) && (sf > 0)) {
            res = res.add(E[sf - 1].multiply(A[sd - 1]));
            sd--;
            sf--;
        }


        //caso particular probabilidade P(X12)
        if (Y == 12 && X > 1) {
            int ss = X;
            int g = 0;
            int sss = X;
            int f = sss;
            while (ss > 1) {
                sss = f;
                int w = Y - 1;
                while (sss > 1) {
                    res = res.add(A[g].multiply(E[w]));
                    sss--;
                    w--;
                }
                f--;
                ss--;
                g++;
            }
        }
        //caso particular probabilidade P(X12)
        int p = 0;
        while (Y == 12 && p <= 12) {
            res = res.add(A[p].multiply(E[Y]));
            p++;
        }


        return res;

    }

    //decisoes

    //matriz 13x13 de probabilidades de não transferência de carros-filial1
    public BigDecimal[][] matriz_naotransfere_filial1() {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = prob1(i, j);

            }
        }

        return matrix;
    }

    //matriz 13x13 de probabilidades de não transferência de carros-filial1
    public BigDecimal[][] matriz_naotransfere_filial2() {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = prob2(i, j);

            }
        }

        return matrix;
    }


    public BigDecimal[][][][] calculaProduto(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);

                    }

                }
                i++;
            }
        }
        return result;
    }

    //transforme duas matrizes 13x13 numa  matriz 169x169 de probabilidades numa transferência de 1 carro
    //da filial 1 para a filial 2

    //é diferente da calculaProduto uma vez que temos que lidar com os casos da linha 0 na matriz da filial 1
    // é -100000 e a linha 12 da matriz da filial 2 é -100000
    public BigDecimal[][][][] calculaProduto1(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        if ((h == 0 && n == 12))
                            result[h][n][k][l] = ((a[h][k]).multiply(b[n][l])).negate();
                        else result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);

                    }

                }
            }
        }
        return result;
    }

    //transforme duas matrizes 13x13 numa  matriz 169x169 de contribuiçoes numa transferência de 1 carro
    //da filial 1 para a filial 2

    //é diferente da calculaProduto uma vez que temos que lidar com os casos da linha 0 na matriz da filial 1
    // é 100000 e a linha 12 da matriz da filial 2 é 100000

    public BigDecimal[][][][] calculaProduto1a(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        if (h == 0 || n == 12)

                            result[h][n][k][l] = ((a[h][k]).multiply(b[n][l])).abs();
                        else result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);
                    }
                }

            }
        }

        return result;
    }


    //calcula probabilidades da transferencia de 2 carros

    public BigDecimal[][][][] calculaProduto2(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        if ((h == 0 && n == 12) || (h == 0 && n == 11)
                                || (h == 1 && n == 12) || (h == 1 && n == 11))
                            result[h][n][k][l] = ((a[h][k]).multiply(b[n][l])).negate();
                        else result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);

                    }

                }
                i++;
            }
        }
        return result;
    }

    //calcula contribuiçoes da transferencia de 2 carros

    public BigDecimal[][][][] calculaProduto2a(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        if (h == 0 || n == 12 || h == 1 || n == 11)

                            result[h][n][k][l] = ((a[h][k]).multiply(b[n][l])).abs();
                        else result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);
                    }
                }

            }
        }

        return result;
    }

    //calcula probabilidades da transferencia de 3 carros

    public BigDecimal[][][][] calculaProduto3(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        if ((h == 0 && n == 12) || (h == 0 && n == 11) || (h == 0 && n == 10)
                                || (h == 1 && n == 12) || (h == 1 && n == 11) || (h == 1 && n == 10)
                                || (h == 2 && n == 12) || (h == 2 && n == 11) || (h == 2 && n == 10))
                            result[h][n][k][l] = ((a[h][k]).multiply(b[n][l])).negate();
                        else result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);

                    }

                }
            }
        }

        return result;
    }

    //calcula contribuiçoes da transferencia de 3 carros

    public BigDecimal[][][][] calculaProduto3a(BigDecimal[][] a, BigDecimal[][] b) {

        BigDecimal[][][][] result = new BigDecimal[13][13][13][13];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        if (h == 0 || n == 12 || h == 1 || n == 11 || h == 2 || n == 10)

                            result[h][n][k][l] = ((a[h][k]).multiply(b[n][l])).abs();
                        else result[h][n][k][l] = (a[h][k]).multiply(b[n][l]);
                        s = s.add(result[h][n][k][l]);
                    }
                }
            }
        }

        return result;
    }


    //transferir 1 carro da filial 1 para 2


    public BigDecimal[][] matriz_transfere1_from_filial(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);

                }
                //shift
                else matrix[i][j] = a[i - 1][j];

            }
        }

        return matrix;
    }

    public BigDecimal[][] matriz_recebe1_to_filial(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 12) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);
                }
                //shift
                else matrix[i][j] = a[i + 1][j];

            }
        }

        return matrix;
    }

    //transferir 2 carros da filial 1 para 2

    public BigDecimal[][] matriz_transfere2_from_filial(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);

                } else if (i == 1) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);

                }
                //shift
                else matrix[i][j] = a[i - 1][j];
            }
        }
        return matrix;
    }

    public BigDecimal[][] matriz_recebe2_to_filial(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 12) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);
                } else if (i == 11) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);
                }
                //shift
                else matrix[i][j] = a[i + 2][j];

            }
        }
        return matrix;
    }

    //transferir 3 carros da filial 1 para 2

    public BigDecimal[][] matriz_transfere3_from_filial(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0 || i == 1 || i == 2) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);
                }
                //shift
                else matrix[i][j] = a[i - 3][j];

            }
        }

        return matrix;
    }

    public BigDecimal[][] matriz_recebe3_to_filial(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 12 || i == 11 || i == 10) {
                    matrix[i][j] = BigDecimal.valueOf(-1000000);
                }
                //shift
                else matrix[i][j] = a[i + 3][j];

            }
        }

        return matrix;
    }


    //CUSTOS

    public BigDecimal custo1(int X, int Y) {

        BigDecimal[] A = new BigDecimal[13];
        BigDecimal[] E = new BigDecimal[13];
        A[0] = BigDecimal.valueOf(0.0360);
        E[0] = BigDecimal.valueOf(0.0212);
        A[1] = BigDecimal.valueOf(0.0620);
        E[1] = BigDecimal.valueOf(0.0768);
        A[2] = BigDecimal.valueOf(0.1024);
        E[2] = BigDecimal.valueOf(0.1508);
        A[3] = BigDecimal.valueOf(0.1384);
        E[3] = BigDecimal.valueOf(0.1976);
        A[4] = BigDecimal.valueOf(0.1444);
        E[4] = BigDecimal.valueOf(0.1948);
        A[5] = BigDecimal.valueOf(0.1152);
        E[5] = BigDecimal.valueOf(0.1528);
        A[6] = BigDecimal.valueOf(0.0992);
        E[6] = BigDecimal.valueOf(0.1044);
        A[7] = BigDecimal.valueOf(0.0868);
        E[7] = BigDecimal.valueOf(0.0592);
        A[8] = BigDecimal.valueOf(0.0760);
        E[8] = BigDecimal.valueOf(0.0228);
        A[9] = BigDecimal.valueOf(0.0584);
        E[9] = BigDecimal.valueOf(0.0112);
        A[10] = BigDecimal.valueOf(0.0488);
        E[10] = BigDecimal.valueOf(0.0048);
        A[11] = BigDecimal.valueOf(0.0256);
        E[11] = BigDecimal.valueOf(0.0020);
        A[12] = BigDecimal.valueOf(0.0068);
        E[12] = BigDecimal.valueOf(0.0016);

        BigDecimal res = BigDecimal.valueOf(0.0);

        //Casos clientes satisfeitos e insatisfeitos
        int s = X;
        int ff = X;
        if (Y > 8) res = res.add(BigDecimal.valueOf(-10));
        while (s <= 12 && Y != 12) {
            res = res.add(A[s].multiply(E[Y]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf(ff)));
            s++;
        }


        int sd = X;
        int sf = Y;
        while ((sd > 0) && (sf > 0)) {
            res = res.add(E[sf - 1].multiply(A[sd - 1]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf((sd - 1))));
            sd--;
            sf--;
        }


        //caso particular probabilidade P(X12)
        if (Y == 12 && X > 1) {
            int ss = X;
            int g = 0;
            int sss = X;
            int f = sss;
            while (ss > 1) {
                sss = f;
                int w = Y - 1;
                while (sss > 1) {
                    res = res.add(A[g].multiply(E[w]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf(g)));
                    sss--;
                    w--;
                }
                f--;
                ss--;
                g++;
            }
        }
        //caso particular probabilidade P(X12)
        int p = 0;
        while (Y == 12 && p <= 12) {
            res = res.add(A[p].multiply(E[Y]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf(X)));
            p++;
        }


        return res;

    }


    public BigDecimal custo2(int X, int Y) {

        BigDecimal[] A = new BigDecimal[13];
        BigDecimal[] E = new BigDecimal[13];
        A[0] = BigDecimal.valueOf(0.0316);
        E[0] = BigDecimal.valueOf(0.0144);
        A[1] = BigDecimal.valueOf(0.0984);
        E[1] = BigDecimal.valueOf(0.0516);
        A[2] = BigDecimal.valueOf(0.1764);
        E[2] = BigDecimal.valueOf(0.1320);
        A[3] = BigDecimal.valueOf(0.2144);
        E[3] = BigDecimal.valueOf(0.1776);
        A[4] = BigDecimal.valueOf(0.1936);
        E[4] = BigDecimal.valueOf(0.2028);
        A[5] = BigDecimal.valueOf(0.1384);
        E[5] = BigDecimal.valueOf(0.1520);
        A[6] = BigDecimal.valueOf(0.0820);
        E[6] = BigDecimal.valueOf(0.1184);
        A[7] = BigDecimal.valueOf(0.0352);
        E[7] = BigDecimal.valueOf(0.0740);
        A[8] = BigDecimal.valueOf(0.0188);
        E[8] = BigDecimal.valueOf(0.0396);
        A[9] = BigDecimal.valueOf(0.0084);
        E[9] = BigDecimal.valueOf(0.0236);
        A[10] = BigDecimal.valueOf(0.0024);
        E[10] = BigDecimal.valueOf(0.0092);
        A[11] = BigDecimal.valueOf(0.0004);
        E[11] = BigDecimal.valueOf(0.0028);
        A[12] = BigDecimal.valueOf(0.0000);
        E[12] = BigDecimal.valueOf(0.0020);

        BigDecimal res = BigDecimal.valueOf(0.0);

        //Casos clientes satisfeitos e insatisfeitos
        int s = X;
        int ff = X;
        if (Y > 8) res = res.add(BigDecimal.valueOf(-10));
        while (s <= 12 && Y != 12) {
            res = res.add(A[s].multiply(E[Y]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf(ff)));
            s++;
        }


        int sd = X;
        int sf = Y;
        while ((sd > 0) && (sf > 0)) {
            res = res.add(E[sf - 1].multiply(A[sd - 1]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf((sd - 1))));
            sd--;
            sf--;
        }


        //caso particular probabilidade P(X12)
        if (Y == 12 && X > 1) {
            int ss = X;
            int g = 0;
            int sss = X;
            int f = sss;
            while (ss > 1) {
                sss = f;
                int w = Y - 1;
                while (sss > 1) {
                    res = res.add(A[g].multiply(E[w]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf(g)));
                    sss--;
                    w--;
                }
                f--;
                ss--;
                g++;
            }
        }
        //caso particular probabilidade P(X12)
        int p = 0;
        while (Y == 12 && p <= 12) {
            res = res.add(A[p].multiply(E[Y]).multiply(BigDecimal.valueOf(30)).multiply(BigDecimal.valueOf(X)));
            p++;
        }


        return res;

    }

    public BigDecimal[][] matriz_naotransfere_filial1_custos() {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = custo1(i, j);

            }
        }

        return matrix;
    }

    public BigDecimal[][] matriz_naotransfere_filial2_custos() {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = custo2(i, j);

            }
        }
        return matrix;
    }


    //transferir 1 carro da filial 1 para 2

    public BigDecimal[][] matriz_transfere1_from_filial_custos(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0) {
                    matrix[i][j] = BigDecimal.valueOf(1000000);

                }
                //shift
                else matrix[i][j] = (a[i - 1][j]);

            }
        }

        return matrix;
    }

    public BigDecimal[][] matriz_recebe1_to_filial_custos(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 12) {
                    matrix[i][j] = BigDecimal.valueOf(1000000);
                }
                //shift
                else matrix[i][j] = a[i + 1][j].add(BigDecimal.valueOf(-7));

            }
        }

        return matrix;
    }

    //transferir 2 carros da filial 1 para 2

    public BigDecimal[][] matriz_transfere2_from_filial_custos(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0 || i == 1) {
                    matrix[i][j] = BigDecimal.valueOf(1000000);
                }
                //shift
                else matrix[i][j] = a[i - 2][j];

            }
        }

        return matrix;
    }

    public BigDecimal[][] matriz_recebe2_to_filial_custos(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 12 || i == 11) {
                    matrix[i][j] = BigDecimal.valueOf(1000000);
                }
                //shift
                else matrix[i][j] = a[i + 2][j].add(BigDecimal.valueOf(-14));

            }
        }

        return matrix;
    }

    //transferir 3 carros da filial 1 para 2

    public BigDecimal[][] matriz_transfere3_from_filial_custos(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0 || i == 1 || i == 2) {
                    matrix[i][j] = BigDecimal.valueOf(100000);
                }
                //shift
                else matrix[i][j] = a[i - 3][j];

            }
        }

        return matrix;
    }

    public BigDecimal[][] matriz_recebe3_to_filial_custos(BigDecimal[][] a) {
        int rowLen = 13, colLen = 13;
        BigDecimal[][] matrix = new BigDecimal[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 12 || i == 11 || i == 10) {
                    matrix[i][j] = BigDecimal.valueOf(100000);
                }
                //shift
                else matrix[i][j] = a[i + 3][j].add(BigDecimal.valueOf(-21));

            }
        }

        return matrix;
    }


    public BigDecimal[] calculaQn(BigDecimal[][][][] a, BigDecimal[][][][] b) {

        BigDecimal[] result = new BigDecimal[169];

        int i = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < b.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < b.length; l++) {
                        s = s.add(a[h][n][k][l].multiply(b[h][n][k][l]));

                    }

                }
                result[i++] = s;
            }
        }
        return result;
    }


    public BigDecimal[] calculaF0() {

        BigDecimal[] result = new BigDecimal[169];

        for (int i = 0; i < 169; i++) {
            result[i] = BigDecimal.valueOf(0);
        }
        return result;
    }


    public BigDecimal[] calculaPn_Fn(BigDecimal[][][][] a, BigDecimal[] b) {

        BigDecimal[] result = new BigDecimal[169];

        int i = 0;
        int j = 0;
        for (int h = 0; h < a.length; h++) {
            for (int n = 0; n < a.length; n++) {
                BigDecimal s = BigDecimal.valueOf(0.0);
                j = 0;
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < a.length; l++) {
                        s = s.add(a[h][n][k][l].multiply(b[j++]));
                    }

                }
                result[i++] = s;
            }
        }
        return result;
    }

    public BigDecimal[] calculaQn_Pn_Fn(BigDecimal[] a, BigDecimal[] b) {

        BigDecimal[] result = new BigDecimal[169];

        for (int i = 0; i < 169; i++) {
            result[i] = a[i].add(b[i]);
        }
        return result;
    }

    public BigDecimal[] calculaFn(BigDecimal[] a, BigDecimal[] b, BigDecimal[] c, BigDecimal[] d, BigDecimal[] e, BigDecimal[] f, BigDecimal[] g) {

        BigDecimal[] result = new BigDecimal[169];

        for (int i = 0; i < 169; i++) {
            BigDecimal s = BigDecimal.valueOf(0);
            if (s.compareTo(a[i]) < 0) s = a[i];
            if (s.compareTo(b[i]) < 0) s = b[i];
            if (s.compareTo(c[i]) < 0) s = c[i];
            if (s.compareTo(d[i]) < 0) s = d[i];
            if (s.compareTo(e[i]) < 0) s = e[i];
            if (s.compareTo(f[i]) < 0) s = f[i];
            if (s.compareTo(g[i]) < 0) s = g[i];

            result[i] = s;
        }
        return result;
    }

    public BigDecimal[] calculaDn(BigDecimal[] a, BigDecimal[] b) {

        BigDecimal[] result = new BigDecimal[169];

        for (int i = 0; i < 169; i++) {
            result[i] = a[i].subtract(b[i]);

        }
        return result;
    }





    public static void print2D(BigDecimal mat[][]) {
        // Loop through all rows
        BigDecimal res = BigDecimal.valueOf(0.0);
        int i = 0;
        for (BigDecimal[] row : mat) {
            res = BigDecimal.valueOf(0.0);
            for (BigDecimal x : row) {
                 System.out.printf("%-15s",x  );
                //res = res.add(x);

            }
            System.out.print("\n");


            i++;
        }


    }




}















