package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {


 public static void main(String[] args) throws IOException {

  Probability p = new Probability();

  //NAO TRANSFERIR
  //matrizes nao transferir 13*13 :

  BigDecimal[][] matrix1 = p.matriz_naotransfere_filial1();
  BigDecimal[][] matrix2 = p.matriz_naotransfere_filial2();
   //p.print2D(matrix1);
   //p.print2D(matrix2);

  //169
  //P1   ->169

  BigDecimal[][][][] P1 = p.calculaProduto(matrix1, matrix2);






  //TRANFERE 1 CARRO DA FILIAL 1 PARA 2
  //matrizes de tranferir 1 carro da filial 1 para 2 13*13

  BigDecimal[][] matrix1carro_f1 = p.matriz_transfere1_from_filial(matrix1);
  BigDecimal[][] matrix1carro_f2 = p.matriz_recebe1_to_filial(matrix2);

  //P2   ->169
  BigDecimal[][][][] P2 = p.calculaProduto1(matrix1carro_f1, matrix1carro_f2);


  //TRANFERE 2 CARROS DA FILIAL 1 PARA 2
  //matrizes de tranferir 2 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix2carro_f1 = p.matriz_transfere2_from_filial(matrix1);
  BigDecimal[][] matrix2carro_f2 = p.matriz_recebe2_to_filial(matrix2);

  //P3   ->169

  BigDecimal[][][][] P3 = p.calculaProduto2(matrix2carro_f1, matrix2carro_f2);


  //TRANFERE 3 CARROS DA FILIAL 1 PARA 2
  //matrizes de tranferir 3 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix3carro_f1 = p.matriz_transfere3_from_filial(matrix1);
  BigDecimal[][] matrix3carro_f2 = p.matriz_recebe3_to_filial(matrix2);

  //P4   ->169

  BigDecimal[][][][] P4 = p.calculaProduto3(matrix3carro_f1, matrix3carro_f2);


  //outras 3 decisoes: filiais ao contrários

  //TRANFERE 1 CARRO DA FILIAL 2 PARA 1
  //matrizes de tranferir 1 carro da filial 2 para 1 13*13

  BigDecimal[][] matrix1carro_f22 = p.matriz_transfere1_from_filial(matrix2);
  BigDecimal[][] matrix1carro_f11 = p.matriz_recebe1_to_filial(matrix1);


  //P5   ->169

  BigDecimal[][][][] P5 = p.calculaProduto1(matrix1carro_f22, matrix1carro_f11);


  //TRANFERE 2 CARROS DA FILIAL 2 PARA 1
  //matrizes de tranferir 2 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix2carro_f22 = p.matriz_transfere2_from_filial(matrix2);
  BigDecimal[][] matrix2carro_f11 = p.matriz_recebe2_to_filial(matrix1);

  //P6   ->169

  BigDecimal[][][][] P6 = p.calculaProduto2(matrix2carro_f22, matrix2carro_f11);


  //TRANFERE 3 CARROS DA FILIAL 2 PARA 1
  //matrizes de tranferir 3 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix3carro_f22 = p.matriz_transfere3_from_filial(matrix2);
  BigDecimal[][] matrix3carro_f11 = p.matriz_recebe3_to_filial(matrix1);

  //P7   ->169

  BigDecimal[][][][] P7 = p.calculaProduto3(matrix3carro_f22, matrix3carro_f11);


  //CUSTOS


  //NAO TRANSFERIR
  //matrizes nao transferir de custos 13*13 :

  BigDecimal[][] matrix11 = p.matriz_naotransfere_filial1_custos();
  BigDecimal[][] matrix22 = p.matriz_naotransfere_filial2_custos();



  //C1   ->169

  BigDecimal[][][][] C1 = p.calculaProduto(matrix11, matrix22);


  //TRANFERE 1 CARRO DA FILIAL 1 PARA 2
  //matrizes de tranferir 1 carro da filial 1 para 2 13*13

  BigDecimal[][] matrix1carro_f11_custo = p.matriz_transfere1_from_filial_custos(matrix11);
  BigDecimal[][] matrix1carro_f22_custo = p.matriz_recebe1_to_filial_custos(matrix22);


  //C2   ->169

  BigDecimal[][][][] C2 = p.calculaProduto1a(matrix1carro_f11_custo, matrix1carro_f22_custo);


  //TRANFERE 2 CARROS DA FILIAL 1 PARA 2
  //matrizes de tranferir 2 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix2carro_f11_custo = p.matriz_transfere2_from_filial_custos(matrix11);
  BigDecimal[][] matrix2carro_f22_custo = p.matriz_recebe2_to_filial_custos(matrix22);

  //C3   ->169

  BigDecimal[][][][] C3 = p.calculaProduto2a(matrix2carro_f11_custo, matrix2carro_f22_custo);


  //TRANFERE 3 CARROS DA FILIAL 1 PARA 2
  //matrizes de tranferir 3 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix3carro_f11_custo = p.matriz_transfere3_from_filial_custos(matrix11);
  BigDecimal[][] matrix3carro_f22_custo = p.matriz_recebe3_to_filial_custos(matrix22);

  //C4   ->169

  BigDecimal[][][][] C4 = p.calculaProduto3a(matrix3carro_f11_custo, matrix3carro_f22_custo);

  //outras 3 decisoes: filiais ao contrários

  //TRANFERE 1 CARRO DA FILIAL 2 PARA 1
  //matrizes de tranferir 1 carro da filial 2 para 1 13*13

  BigDecimal[][] matrix1carro_f222 = p.matriz_transfere1_from_filial_custos(matrix22);
  BigDecimal[][] matrix1carro_f111 = p.matriz_recebe1_to_filial_custos(matrix11);

  //C5   ->169


  BigDecimal[][][][] C5 = p.calculaProduto1a(matrix1carro_f222, matrix1carro_f111);


  //TRANFERE 2 CARROS DA FILIAL 2 PARA 1
  //matrizes de tranferir 2 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix2carro_f222 = p.matriz_transfere2_from_filial_custos(matrix22);
  BigDecimal[][] matrix2carro_f111 = p.matriz_recebe2_to_filial_custos(matrix11);

  //C6   ->169

  BigDecimal[][][][] C6 = p.calculaProduto2a(matrix2carro_f222, matrix2carro_f111);


  //TRANFERE 3 CARROS DA FILIAL 2 PARA 1
  //matrizes de tranferir 3 carros da filial 1 para 2 13*13

  BigDecimal[][] matrix3carro_f222 = p.matriz_transfere3_from_filial_custos(matrix22);
  BigDecimal[][] matrix3carro_f111 = p.matriz_recebe3_to_filial_custos(matrix11);

  //C7   ->169

  BigDecimal[][][][] C7 = p.calculaProduto3a(matrix3carro_f222, matrix3carro_f111);

  //QN=PN . RN

  //todas as matrizes BigDecimal[] são calculadas pela transposta,
  // ou seja F[N] é a transposta de Fn, QN é a transposta de Qn...


  BigDecimal[] Q1 = p.calculaQn(P1, C1);
  BigDecimal[] Q2 = p.calculaQn(P2, C2);
  BigDecimal[] Q3 = p.calculaQn(P3, C3);
  BigDecimal[] Q4 = p.calculaQn(P4, C4);
  BigDecimal[] Q5 = p.calculaQn(P5, C5);
  BigDecimal[] Q6 = p.calculaQn(P6, C6);
  BigDecimal[] Q7 = p.calculaQn(P7, C7);
  

  BigDecimal[][] F = new BigDecimal[169][1];
  BigDecimal[][] D = new BigDecimal[169][1];


  F[0] = p.calculaF0();

  //Algoritmo de iteração do valor

   for (int N = 1; N > 0; N++) {


    BigDecimal[] P1_FN = p.calculaPn_Fn(P1, F[N - 1]);
    BigDecimal[] P2_FN = p.calculaPn_Fn(P2, F[N - 1]);
    BigDecimal[] P3_FN = p.calculaPn_Fn(P3, F[N - 1]);
    BigDecimal[] P4_FN = p.calculaPn_Fn(P4, F[N - 1]);
    BigDecimal[] P5_FN = p.calculaPn_Fn(P5, F[N - 1]);
    BigDecimal[] P6_FN = p.calculaPn_Fn(P6, F[N - 1]);
    BigDecimal[] P7_FN = p.calculaPn_Fn(P7, F[N - 1]);

    BigDecimal[] Q1_P1_FN = p.calculaQn_Pn_Fn(Q1, P1_FN);
    BigDecimal[] Q2_P2_FN = p.calculaQn_Pn_Fn(Q2, P2_FN);
    BigDecimal[] Q3_P3_FN = p.calculaQn_Pn_Fn(Q3, P3_FN);
    BigDecimal[] Q4_P4_FN = p.calculaQn_Pn_Fn(Q4, P4_FN);
    BigDecimal[] Q5_P5_FN = p.calculaQn_Pn_Fn(Q5, P5_FN);
    BigDecimal[] Q6_P6_FN = p.calculaQn_Pn_Fn(Q6, P6_FN);
    BigDecimal[] Q7_P7_FN = p.calculaQn_Pn_Fn(Q7, P7_FN);


    F[N] = p.calculaFn(Q1_P1_FN, Q2_P2_FN, Q3_P3_FN, Q4_P4_FN, Q5_P5_FN, Q6_P6_FN, Q7_P7_FN);

    D[N] = p.calculaDn(F[N], F[N - 1]);


    //min- calculo do minimo do vetor D[N];
    BigDecimal min = BigDecimal.valueOf(10000000);
    for(int i=0;i<169;i++){
       if(D[N][i].compareTo(min)<=0) min=D[N][i];
    }

    //max
    BigDecimal max = BigDecimal.valueOf(0);
    for(int i=0;i<169;i++){
     if(D[N][i].compareTo(max)>=0) max=D[N][i];
    }

    BigDecimal media = min.add(max).divide(BigDecimal.valueOf(2),12,RoundingMode.CEILING);
/*
    //pára o algoritmo de iteração
    if(max.subtract(min).divide(media,12, RoundingMode.CEILING).compareTo(BigDecimal.valueOf(0.01))<=0) {
     System.out.println("Minimo: "+min+" Máximo: "+max);
     System.out.println("Número de interações: "+N); int k=0; int j=0;
     for(int i=0;i<169;i++){
      if(F[N][i]==Q1_P1_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Não transferir");
      if(F[N][i]==Q2_P2_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Transferir 1 automóvel da filial 1 para 2");
      if(F[N][i]==Q3_P3_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Transferir 2 automóvel da filial 1 para 2");
      if(F[N][i]==Q4_P4_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Transferir 3 automóvel da filial 1 para 2");
      if(F[N][i]==Q5_P5_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Transferir 1 automóvel da filial 2 para 1");
      if(F[N][i]==Q6_P6_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Transferir 2 automóvel da filial 2 para 1");
      if(F[N][i]==Q7_P7_FN[i]) System.out.println("Estado: (" +k+","+j+")"+" : "+"Transferir 3 automóvel da filial 2 para 1");
      j++; if(j==13) {k++; j=0;}
      }

     break;

    }
*/
   }



 }

}









