%Extensao do predicato Contrato: Id,IdAd, IdAda, TipoDeContrato, TipoDeProcedimento, Descrição, Valor, Prazo, Local, Data -> {V,F,D}
% ------- Conhecimento Perfeito Positivo
contrato(1,600018709,502381973, "Aquisicao de servicos", "Ajuste Direto", "Aquisicao de servicos de acesso da Base de Dados Juridicos", 4000, 195, "Lisboa", data(19,03,2020)).
contrato(2,506696464,809589087, "Aquisicao de serviços", "Ajuste Direto", "Reparacao e Conservacao de Escolas - Fornecimento de aluminos", 2800, 30, "Vila Flor", data(16,02,2020)).
contrato(3,506901173,515204463, "Aquisicao de servicos", "Ajuste Direto", "Obras de Demolicao e Reposicao da Legalidade De Anexo", 4694.60, 25, "Braga", data(11,03,2020)).
contrato(4,502011378,980474710, "Aquisicao de bens moveis", "Concurso Publico", "Aquisicao de um microscopio 2Photon", 320000,105, "Braga", data(31,03,2020)).
contrato(5,680047360,500853975, "Aquisicao de bens moveis", "Concurso Publico", "Aquisicao de cafe por lotes para os anos de 2020 e 2021", 157275, 353, "Braga", data(13,01,2020)).
contrato(6,501413197,504099388, "Locacao de bens moveis", "Concurso Publico", "Aquisicao de nos de computacao (servidores) para cloud interna", 210000, 30, "Porto", data(16,03,2020)).
contrato(7,600084779,502443855, "Aquisicao de servicos", "Consulta Previa", "Aquisicao de servicos de desenvolvimento aplicacional dos sistemas de informacao da Autoridade Tributaria e Aduaneira", 201400, 210, data(19,02,2020)).
contrato(8,600006638,504654748, "Aquisicao de bens moveis", "Consulta Previa", "Aquisicao de 2 equipamentos multifuncionais", 10980, 30, data(12,03,2020)).


% ------- Conhecimento Perfeito Negativo
-contrato(9,501128840,502605731, "Aquisicao de servicos", "Ajuste Direto", "Prestacao de Servicos de Informatica para a Gestao dos Clientes", 4800.50, 365, data(17,03,2020)).

% ------- Conhecimento Imperfeito Incerto
%Não se sabe o prazo do contrato
contrato(10,506346773, 503504564, "Locacao de bens moveis", "Concurso Publico", "Fornecimento de energia eletrica em BTN e MT para as instalacoes da GESAMB", 1780.22, prazo_desconhecido, data(18,01,2019)).

% ------- Conhecimento Imperfeito Impreciso
%Não se sabe se o valor do contrato é 500€ ou 4700€
excecao(contrato(11,600086992, 503188620, "Locacao de bens moveis", "Concurso Publico", "Contratacao de 2 veiculos eletricos em regime de aluguer operacional de veiculos", 5000, 140, "Portugal", data(16,01,2020))).
excecao(contrato(11,600086992, 503188620, "Locacao de bens moveis", "Concurso Publico", "Contratacao de 2 veiculos eletricos em regime de aluguer operacional de veiculos", 4700, 140, "Portugal", data(16,01,2020))).

% ------- Conhecimento Imperfeito Interdito

%É impossivel saber o valor do contrato
contrato(12,508481287,508592909, "Aquisicao de bens moveis", "Consulta Previa", "Aquisicao de seringas", valor_interdito, 352, data(14,01,2020)).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%Extensao do predicato adjudicante: IdAd, Nome, NIF, Morada -> {V,F,D}

% ------- Conhecimento Perfeito Positivo
adjudicante(1, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa").
adjudicante(2, "Municipio de Vila Flor", 506696464, "Vila Flor").
adjudicante(3, "Municipio de Braga", 506901173, "Braga").
adjudicante(5, "Servicos de Accao Social da Universidade do Minho", 680047360, "Braga").
adjudicante(7, "Centro Hospitalar Universitario de Lisboa Norte", 508481287, "Lisboa").
adjudicante(10, "Gestao Ambiental e de Residuos", 506346773, "Evora").
adjudicante(11, "Fundo Ambiental", 600086992, "Portugal").

% ------- Conhecimento Perfeito Negativo
-adjudicante(9, "Municipio de Braganca", 501128840, "Braganca").

% ------- Conhecimento Imperfeito Incerto

%Não se sabe a morada da entidade
adjudicante(8, "Supremo Tribunal Administrativo", 600006638, morada_desconhecida).

% ------- Conhecimento Imperfeito Impreciso

%Não se sabe se a morada da entidade é Braga ou Guimarães
excecao(adjudicante(4, "Universidade do Minho", 502011378, "Braga")).
excecao(adjudicante(4, "Universidade do Minho", 502011378, "Guimaraes")).

% ------- Conhecimento Imperfeito Interdito
%É impossivel saber a nome da entidade
adjudicante(6, nome_interdito, 501413197, "Porto").
excecao(adjudicante(Id,Nome,Nif,Morada)) :- adjudicataria(Id,nome_interdito,Nif,Morada).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%Extensao do predicato adjudicataria: IdAda, Nome, NIF, Morada -> {V,F,D}

% ------- Conhecimento Perfeito Positivo
adjudicataria(1, "Data Juris - Direito e Informatica Lda", 502381973, "Portugal").
adjudicataria(4, "OLYMPUS IBERIA SAU", 980474710, "Portugal").
adjudicataria(6, "Pamafe Informatica Lda", 504099388, "Portugal").
adjudicataria(7, "Accenture Tecnology Solutions - Solucoes Informaticas Integradas", 502443855, "Portugal").
adjudicataria(8, "Beltrao Coelho, Lda", 504654748, "Portugal").
adjudicataria(9, "Batist Medical Portugal Unipessoal Lda", 508592909, "Portugal").
adjudicataria(10, "EDP", 503504564, Portugal).
adjudicataria(11, "SGald Automotive", 503188620, "Portugal").

% ------- Conhecimento Perfeito Negativo
-adjudicataria(13, "CGITI Portugal", 502605731, "Portugal").

% ------- Conhecimento Imperfeito Incerto

%Não se sabe a morada da entidade
adjudicataria(3, "Plateia de Calculos Unipessoal Lda", 515204463, morada_desconhecida).

% ------- Conhecimento Imperfeito Impreciso

%Não se sabe se a morada da entidade é Vila Flor ou Mirandela
excecao(adjudicataria(2, "Carlos Manuel Pires", 809589087, "Vila Flor")).
excecao(adjudicataria(2, "Carlos Manuel Pires", 809589087, "Mirandela")).


% ------- Conhecimento Imperfeito Interdito

%É impossivel saber a morada da entidade
adjudicataria(5, "Manuel Rui Azinhais Nabeiro Lda", 500853975, morada_interdita).

% Representação da representaçao do invariante de exclusividade mutua na base de conhecimento
%+adjudicataria(Id,Nome,Nif,Morada) :: (solucoes((Id,Nome,Nif,Morada), 
%                                       (adjudicataria(5,"Manuel Rui Azinhais Nabeiro Lda", 500853975, morada_interdita),
%                                       nao(nulointerdito(morada_interdita))), R), comprimento(R,0)).




%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Exceções para conhecimento imperfeito incerto
%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% ---- Exceções do predicado Contrato
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,desconhecido,TP,Desc,Val,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,desconhecido,Desc,Val,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,desconhecido,Val,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,desconhecido,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,desconhecido,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,desconhecido,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,desconhecido).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,A))) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(desconhecido,M,A)).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,A))) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,desconhecido,A)).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,A))) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,desconhecido)).

% ---- Exceções do predicado Adjudicante
excecao(adjudicante(Id,Nome,Nif,Morada)) :- adjudicante(Id,desconhecido,Nif,Morada).
excecao(adjudicante(Id,Nome,Nif,Morada)) :- adjudicante(Id,Nome,Nif,desconhecido).


% ---- Exceções do predicado Adjudicataria
excecao(adjudicataria(Id,Nome,Nif,Morada)) :- adjudicante(Id,desconhecido,Nif,Morada).
excecao(adjudicataria(Id,Nome,Nif,Morada)) :- adjudicante(Id,Nome,Nif,desconhecido).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Exceções para conhecimento imperfeito interdito
%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% ---- Exceções do predicado Contrato
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,tc_interdito,TP,Desc,Val,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,tp_interdito,Desc,Val,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,descricao_interdita,Val,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,valor_interdito,P,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,prazo_interdito,Local,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,local_interdito,Data).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data)) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data_interdita).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,A))) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(data_interdita,M,A)).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,A))) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,data_interdita,A)).
excecao(contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,A))) :- contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,data(D,M,data_interdita)).

%Invariante de exclusao mutua do conhecimento interdito e não interdito
+contrato(Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data) :: (solucoes((Id,IdA,IdAda,TC,TP,Desc,Val,P,Local,Data),
                                                         (contrato(Id,_,_,TC,TP,Desc,Val,P,Local,Data), (nulointerdito(TC);
                                                                                                         nulointerdito(P);
                                                                                                         nulointerdito(TP);
                                                                                                         nulointerdito(Desc);
                                                                                                         nulointerdito(Val);
                                                                                                         nulointerdito(Local);
                                                                                                         nulointerdito(Data))),
                                                          R),
                                                        comprimento(R,0)).

% ---- Exceções do predicado Adjudicante
excecao(adjudicante(Id,Nome,Nif,Morada)) :- adjudicante(Id,nome_interdito,Nif,Morada).
excecao(adjudicante(Id,Nome,Nif,Morada)) :- adjudicante(Id,Nome,Nif,morada_interdita).

+adjudicante(Id,Nome,Nif,Morada) :: (solucoes(adjudicante(Id,Nome,Nif,Morada),
                                      (adjudicante(_,Nome,_,Morada), (nulointerdito(Nome); nulointerdito(Morada))),
                                      R),comprimento(R,0)).



% ---- Exceções do predicado Adjudicataria
excecao(adjudicataria(Id,Nome,Nif,Morada)) :- adjudicataria(Id,nome_interdito,Nif,Morada).
excecao(adjudicataria(Id,Nome,Nif,Morada)) :- adjudicataria(Id,Nome,Nif,morada_interdita).

+adjudicataria(Id,Nome,Nif,Morada) :: (solucoes(adjudicataria(Id,Nome,Nif,Morada),
                                               (adjudicataria(_,Nome,_,Morada) , (nulointerdito(Nome) ; nulointerdito(Morada))),R),
                                               comprimento(R,0)).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Flags de conhecimento interdito
nulointerdito(tc_interdito).
nulointerdito(tp_interdito).
nulointerdito(descricao_interdita).
nulointerdito(valor_interdito).
nulointerdito(prazo_interdito).
nulointerdito(local_interdito).
nulointerdito(data_interdita).

nulointerdito(nome_interdito).
nulointerdito(morada_interdita).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Flag de valor representado por um intervalo
intervalo(range).
