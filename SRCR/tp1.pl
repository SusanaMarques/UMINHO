%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag(discontiguous_warnings,off).
:- set_prolog_flag(single_var_warnings,off).
%:- set_prolog_flag(unknown,fail).

:- op(900,xfy,'::').
:- dynamic adjudicante/4.
:- dynamic adjudicataria/4.
:- dynamic contrato/10.
:- dynamic idAdjud/1.
:- dynamic idAdjudicata/1.
:- dynamic excecao/1.
:-dynamic data/3.
:-dynamic (-)/1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Carregar predicados do ficheiro no qual é guardado o estado

:- include('bc.pl').
:- include('aux.pl').
:- include('evinvolucao.pl').
:- include('invariantes.pl').

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Pressupostos Mundo Fechado
%--------------------------------- - - - - - - - - - -  -  -  -  -   -

-contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT) :-
    nao(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT)),
    nao(excecao(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT))).

-adjudicante(IdAd,Nome,Nif,Morada) :-
    nao(adjudicante(IdAd,Nome,Nif,Morada)),
    nao(excecao(adjudicante(IdAd,Nome,Nif,Morada))).

-adjudicataria(IdAd,Nome,Nif,Morada) :-
    nao(adjudicataria(IdAd,Nome,Nif,Morada)),
    nao(excecao(adjudicataria(IdAd,Nome,Nif,Morada))).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Sistema de Inferencia
%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% Extensao do meta-predicado demo: Questao,Resposta -> {"verdadeiro","falso","desconhecido"}
% capaz de responder a uma única questão
demo(Q,verdadeiro) :- Q.
demo(Q,falso) :- -Q.
demo(Q,desconhecido) :- nao(Q), nao(-Q).

% Extensao do meta-predicado demoList: [Questao],[Resposta] -> {V,F,D}
% capaz de responder a várias questões em simultâneo
demoList([Q],[R]):- demo(Q,R).
demoList([Q|Qs],[R|Rs]) :- demo(Q,R), demoList(Qs,Rs).

% Extensao do meta-predicado query: [Questao], Resposta -> {V,F,D}
% capaz de fazer a conjunção e/ou disjunção de uma lista de questões
query([],verdadeiro).
query([Q],R) :- demo(Q,R).
query([Q1,'E'|Qs],R) :- demo(Q1,R1), query(Qs,R2), conjuncao(R1,R2,R).
query([Q1,'OU'|Qs],R) :- demo(Q1,R1), query(Qs,R2), disjuncao(R1,R2,R).


% Extensao do predicado conjuncao: X,Y -> {V,F,D}
conjuncao(verdadeiro,verdadeiro,verdadeiro).
conjuncao(verdadeiro,desconhecido,desconhecido).
conjuncao(desconhecido,verdadeiro,desconhecido).
conjuncao(desconhecido,desconhecido,desconhecido).
conjuncao(falso,_,falso).
conjuncao(_,falso,falso).

% Extensao do predicado disjuncao: X,Y -> {V,F,D}
disjuncao(verdadeiro,_,verdadeiro).
disjuncao(_,verdadeiro,verdadeiro).
disjuncao(falso,falso,falso).
disjuncao(falso,desconhecido,desconhecido).
disjuncao(desconhecido,falso,desconhecido).
disjuncao(desconhecido,desconhecido,desconhecido).


%--------------------------------------------------------------------
