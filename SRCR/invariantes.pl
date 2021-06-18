%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariantes Estruturais e Referenciais
%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% Invariante que garante que não existe conhecimento perfeito positivo repetido
+contrato(A,B,C,D,E,F,G,H,I,J)		:: (solucoes(A,contrato(A,B,C,D,E,F,G,H,I,J),L),comprimento(L,1)).
+adjudicante(A,B,C,D) 				:: (solucoes(A,adjudicante(A,B,C,D),L),comprimento(L,1)).
+adjudicataria(A,B,C,D) 			:: (solucoes(A,adjudicataria(A,B,C,D),L),comprimento(L,1)).

% Invariante que garante que não existe conhecimento perfeito negativo repetido
+(-contrato(A,B,C,D,E,F,G,H,I,J)) 	:: (solucoes(A,-contrato(A,B,C,D,E,F,G,H,I,J),L),comprimento(L,1)).
+(-adjudicante(A,B,C,D)) 			:: (solucoes(A,-adjudicante(A,B,C,D),L),comprimento(L,1)).
+(-adjudicataria(A,B,C,D)) 			:: (solucoes(A,-adjudicataria(A,B,C,D),L),comprimento(L,1)).

% Invariante que garante que não existe conhecimento imperfeito generico repetido
+imperfeito(CII) 						:: (solucoes(CII, excecao(CII), R),comprimento(R, 1)).
% Invariante que garante que não existe conhecimento imperfeito incerto repetido
+incerto(CII) 						:: (solucoes(CII, excecao(CII), R),comprimento(R, 1)).
% Invariante que garante que não existe conhecimento imperfeito impreciso repetido
+impreciso(CII) 					:: (solucoes(CII, excecao(CII), R),comprimento(R, 1)).
% Invariante que garante que não existe conhecimento imperfeito interdito repetido
+interdito(CII) 					:: (solucoes(CII, excecao(CII), R),comprimento(R, 1)).
% Invariante que garante que não existem excecoes repetidas
+(excecao(E)) :: (solucoes(E, excecao(E), R), comprimento(R, 1)).

% Invariante que não permite adicionar conhecimento perfeito positivo que contradiz conhecimento perfeito negativo
+contrato(A,B,C,D,E,F,G,H,I,J)		:: nao((solucoes(A,-contrato(A,B,C,D,E,F,G,H,I,J),L),comprimento(L,1))).
+adjudicante(A,B,C,D) 				:: nao((solucoes(A,-adjudicante(A,B,C,D),L),comprimento(L,1))).
+adjudicataria(A,B,C,D) 			:: nao((solucoes(A,-adjudicataria(A,B,C,D),L),comprimento(L,1))).

% Invariante que não permite adicionar conhecimento perfeito negativo que contradiz conhecimento perfeito positivo
+(-contrato(A,B,C,D,E,F,G,H,I,J)) 	:: nao((solucoes(A,contrato(A,B,C,D,E,F,G,H,I,J),L),comprimento(L,1))).
+(-adjudicante(A,B,C,D)) 			:: nao((solucoes(A,adjudicante(A,B,C,D),L),comprimento(L,1))).
+(-adjudicataria(A,B,C,D)) 			:: nao((solucoes(A,adjudicataria(A,B,C,D),L),comprimento(L,1))).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariantes Estruturais e Referenciais: Contrato

%Garantir que conhecimento perfeito não é imperfeito
+contrato(IdC,_,_,_,_,_,_,_,_,_) :: (solucoes(IdC, excecao(contrato(IdC,_,_,_,_,_,_,_,_,_)),R),comprimento(R,0)).

%Garantir que não há 2 contratos com ids iguais
+contrato(IdC,_,_,_,_,_,_,_,_,_) :: (solucoes(IdC, contrato(IdC,_,_,_,_,_,_,_,_,_) ,R), comprimento(R,1)).
+incerto(contrato(IdC,_,_,_,_,_,_,_,_,_)) :: (solucoes(IdC, contrato(IdC,_,_,_,_,_,_,_,_,_) ,R), comprimento(R,1)).
+interdito(contrato(IdC,_,_,_,_,_,_,_,_,_)) :: (solucoes(IdC, contrato(IdC,_,_,_,_,_,_,_,_,_) ,R), comprimento(R,1)).

% Garantir que contratos com ids diferentes têm diferente informacao
+contrato(IdC,A,B,C,D,E,F,G,H,Data) :: (solucoes(IdC, contrato(_,A,B,C,D,E,F,G,H,Data) ,R) ,comprimento(R,1)).
+incerto(contrato(IdC,A,B,C,D,E,F,G,H,Data)) :: (solucoes(IdC, contrato(_,A,B,C,D,E,F,G,H,Data) ,R) ,comprimento(R,1)).
+interdito(contrato(IdC,A,B,C,D,E,F,G,H,Data)) :: (solucoes(IdC, contrato(_,A,B,C,D,E,F,G,H,Data) ,R) ,comprimento(R,1)).


% Garantir que cada contrato só tem 1 dos 3 tipos de procedimentos possiveis
+contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT) :: (contrato(IdC,IdAd,IdAda,TContrato,"Ajuste Direto",D,V,P,L,DT);
															     contrato(IdC,IdAd,IdAda,TContrato,"Consulta Previa",D,V,P,L,DT);
                                                     		     contrato(IdC,IdAd,IdAda,TContrato,"Concurso Publico",D,V,P,L,DT)).

+incerto(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT)) :: (excecao(contrato(IdC,IdAd,IdAda,TContrato,desconhecido,D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Ajuste Direto",D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Consulta Previa",D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Concurso Publico",D,V,P,L,DT))).

+impreciso(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT)) :: (excecao(contrato(IdC,IdAd,IdAda,TContrato,"Ajuste Direto",D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Consulta Previa",D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Concurso Publico",D,V,P,L,DT))).

+interdito(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,D,V,P,L,DT)) :: (excecao(contrato(IdC,IdAd,IdAda,TContrato,procedimento_interdito,D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Ajuste Direto",D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Consulta Previa",D,V,P,L,DT));
																			excecao(contrato(IdC,IdAd,IdAda,TContrato,"Concurso Publico",D,V,P,L,DT))).

%Garantir que o valor do contrato por ajuste direto é igual ou inferior a 5000 euros
+contrato(IdC,_,_,_,"Ajuste Direto",_,V,_,_,_) :: (V =< 5000).
+incerto(contrato(IdC,_,_,_,"Ajuste Direto",_,V,_,_,_)) :: (excecao(contrato(IdC,_,_,_,"Ajuste Direto",_,desconhecido,_,_,_)) ; (V =< 5000)).
+impreciso(contrato(IdC,_,_,_,"Ajuste Direto",_,V,_,_,_)) :: ((intervalo(V)) ; (excecao(contrato(IdC,_,_,_,"Ajuste Direto",_,V,_,_,_)) , V =< 5000)).
+interdito(contrato(IdC,_,_,_,"Ajuste Direto",_,V,_,_,_)) :: (excecao(contrato(IdC,_,_,_,"Ajuste Direto",_,valor_interdito,_,_,_)) ; V =< 5000).

%Garantir que um contrato por ajuste direto é um dos segintes: Contrato de aquisição ou locação de bens móveis ou aquisição de serviços
+contrato(IdC,_,_,TContrato,"Ajuste Direto",_,_,_,_,_) :: (contrato(IdC,_,_,"Aquisicao","Ajuste Direto",_,_,_,_,_);
													  	   contrato(IdC,_,_,"Locacao de bens moveis","Ajuste Direto",_,_,_,_,_);
   													       contrato(IdC,_,_,"Aquisicao de servicos","Ajuste Direto",_,_,_,_,_)).

+incerto(contrato(IdC,_,_,TContrato,"Ajuste Direto",_,_,_,_,_)) :: (excecao(contrato(IdC,_,_,"Aquisicao","Ajuste Direto",_,_,_,_,_));
														   			excecao(contrato(IdC,_,_,desconhecido,"Ajuste Direto",_,_,_,_,_));
													  	  			excecao(contrato(IdC,_,_,"Locacao de bens moveis","Ajuste Direto",_,_,_,_,_));
   													      			excecao(contrato(IdC,_,_,"Aquisicao de servicos","Ajuste Direto",_,_,_,_,_))).

+impreciso(contrato(IdC,_,_,TContrato,"Ajuste Direto",_,_,_,_,_)) :: (excecao(contrato(IdC,_,_,"Aquisicao","Ajuste Direto",_,_,_,_,_));
																	  excecao(contrato(IdC,_,_,"Locacao de bens moveis","Ajuste Direto",_,_,_,_,_));
																	  excecao(contrato(IdC,_,_,"Aquisicao de servicos","Ajuste Direto",_,_,_,_,_))).

+interdito(contrato(IdC,_,_,TContrato,"Ajuste Direto",_,_,_,_,_)) :: (excecao(contrato(IdC,_,_,"Aquisicao","Ajuste Direto",_,_,_,_,_));
																	  excecao(contrato(IdC,_,_,tipo_interdito,"Ajuste Direto",_,_,_,_,_));
																	  excecao(contrato(IdC,_,_,"Locacao de bens moveis","Ajuste Direto",_,_,_,_,_));
																	  excecao(contrato(IdC,_,_,"Aquisicao de servicos","Ajuste Direto",_,_,_,_,_))).														

% Prazo de vigencia ate 1 ano
+contrato(IdC,_,_,_,"Ajuste Direto",_,_,Prazo,_,_) :: Prazo =< 365.
+incerto(contrato(IdC,_,_,_,"Ajuste Direto",_,_,Prazo,_,_)) 	:: (excecao(contrato(IdC,_,_,_,"Ajuste Direto",_,_,desconhecido,_,_)) ; Prazo =< 365).
+impreciso(contrato(IdC,_,_,_,"Ajuste Direto",_,_,Prazo,_,_))	:: (intervalo(Prazo) ; Prazo =< 365).
+interdito(contrato(IdC,_,_,_,"Ajuste Direto",_,_,Prazo,_,_))	:: (excecao(contrato(IdC,_,_,_,"Ajuste Direto",_,_,prazo_interdito,_,_)) ; Prazo =< 365).						

%Regra dos 3 anos válida para todos os contratos
+contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,Descricao,Val,Prazo,Local,Data) :: (solucoes(Vl, (contrato(_,IdAd,IdAda,_,_,_,Vl,_,_,Dt),
																											nao(nulointerdito(Vl)),
																											nao(anoImperfeito(Dt)),
																											nao(contrato(_,IdAd,IdAda,_,_,_,Vl,_,_,desconhecido)),
																											menos3Anos(Dt,Data)
																									),L),
                                                                                       sumVals([-Val|L],Ret),
                                                                                       Ret<75000).

+incerto(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,Descricao,Val,Prazo,Local,Data)) ::(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,Descricao,desconhecido,Prazo,Local,Data);
																							anoImperfeito(Data);
																					 (solucoes(Vl, (contrato(_,IdAd,IdAda,_,_,_,Vl,_,_,Dt),
																											nao(nulointerdito(Vl)),
																											nao(anoImperfeito(Dt)),
																											nao(contrato(_,IdAd,IdAda,_,_,_,desconhecido,_,_,Dt)),
																											menos3Anos(Dt,Data)),L),
                                                                                       sumVals([-Val|L],Ret),
                                                                                       Ret<75000)).

+interdito(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,Descricao,Val,Prazo,Local,Data)) ::(contrato(IdC,IdAd,IdAda,TContrato,TProcedimento,Descricao,valor_interdito,Prazo,Local,Data);
																								anoImperfeito(Data);
																					 (solucoes(Vl, (contrato(_,IdAd,IdAda,_,_,_,Vl,_,_,Dt),
																											nao(nulointerdito(Vl)),
																											nao(anoImperfeito(Dt)),
																											nao(contrato(_,IdAd,IdAda,_,_,_,desconhecido,_,_,Dt)) ,
																											menos3Anos(Dt,Data)),L),
                                                                                       sumVals([-Val|L],Ret),
                                                                                       Ret<75000)).

% Garantir que o valor de cada contrato é válido (>= 0) para conhecimento perfeito positivo
+contrato(IdC,_,_,_,_,_,V,_,_,_) :: valorValido(V).
+incerto(contrato(IdC,_,_,_,_,_,V,_,_,_)) :: (contrato(IdC,_,_,_,_,_,desconhecido,_,_,_) ;valorValido(V)).
+impreciso(contrato(IdC,_,_,_,_,_,V,_,_,_)) :: (contrato(IdC,_,_,_,_,_,valor_interdito,_,_,_) ;valorValido(V)).
+interdito(contrato(IdC,_,_,_,_,_,V,_,_,_)) :: (contrato(IdC,_,_,_,_,_,valor_interdito,_,_,_) ;valorValido(V)).

% Garantir que o valor de cada contrato é válido (>= 0) para conhecimento perfeito negativo
+(-contrato(IdC,_,_,_,_,_,V,_,_,_)) :: valorValido(V).

% Garantir que um contrato está associado a um nif existente, quer para uma entidade adjudicante, quer para uma entidade adjudicataria
+contrato(_,Ida,Idada,_,_,_,_,_,_,_) :: ((solucoes(Ida, (adjudicante(_,_,Ida,_);excecao(adjudicante(_,_,Ida,_))), R1), comprimento(R1,S1), S1 >= 1),		
									    (solucoes(Idada, (adjudicataria(_,_,Idada,_);excecao(adjudicante(_,_,Ida,_))), R2), comprimento(R2,S2), S2 >= 1)).

+incerto(contrato(_,Ida,Idada,_,_,_,_,_,_,_)) :: ((solucoes(Ida, (adjudicante(_,_,Ida,_);excecao(adjudicante(_,_,Ida,_))), R1), comprimento(R1,S1), S1 >= 1),		
									    (solucoes(Idada, (adjudicataria(_,_,Idada,_);excecao(adjudicante(_,_,Ida,_))), R2), comprimento(R2,S2), S2 >= 1)).

+impreciso(contrato(_,Ida,Idada,_,_,_,_,_,_,_)) :: ((solucoes(Ida, (adjudicante(_,_,Ida,_);excecao(adjudicante(_,_,Ida,_))), R1), comprimento(R1,S1), S1 >= 1),		
									    (solucoes(Idada, (adjudicataria(_,_,Idada,_);excecao(adjudicante(_,_,Ida,_))), R2), comprimento(R2,S2), S2 >= 1)).
									
+interdito(contrato(_,Ida,Idada,_,_,_,_,_,_,_)) :: ((solucoes(Ida, (adjudicante(_,_,Ida,_);excecao(adjudicante(_,_,Ida,_))), R1), comprimento(R1,S1), S1 >= 1),		
									    (solucoes(Idada, (adjudicataria(_,_,Idada,_);excecao(adjudicante(_,_,Ida,_))), R2), comprimento(R2,S2), S2 >= 1)).

%Data Valida
+contrato(_,_,_,_,_,_,_,_,_,Data):- dataValida(Data).
+incerto(contrato(_,_,_,_,_,_,_,_,_,_,D)):-dataValida(D).
+interdito(contrato(_,_,_,_,_,_,_,_,_,_,D)):-dataValida(D).
+imperfeito(contrato(_,_,_,_,_,_,_,_,_,_,D)):-dataValida(D).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariantes Estruturais e Referenciais: Adjudicante

%Garantir que o id e nif de cada entidade adjudicante é único para conhecimento perfeito positivo
+adjudicante(IdAd,Nome,Nif,Morada) :: (solucoes(IdAd, (adjudicante(IdAd,_,_,_)),R), comprimento(R,1)).									
+adjudicante(IdAd,Nome,Nif,Morada) :: (solucoes(Nif, adjudicante(_,_,Nif,_),R), comprimento(R,1)).

%Garantir que o id e nif de cada entidade adjudicante é único para conhecimento imperfeito incerto
+incerto(adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes(IdAd, (adjudicante(IdAd,_,_,_)),R), comprimento(R,1)).									
+incerto(adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes(Nif, adjudicante(_,_,Nif,_),R), comprimento(R,1)).

%Garantir que o id e nif de cada entidade adjudicante é único para conhecimento imperfeito interdito
+interdito(adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes(IdAd, (adjudicante(IdAd,_,_,_)),R), comprimento(R,1)).									
+interdito(adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes(Nif, adjudicante(_,_,Nif,_),R), comprimento(R,1)).

% Garantir que adjudicantes com ids diferentes têm diferente informação para conhecimento perfeito positivo
+adjudicante(IdAd,Nome,Nif,Morada) :: (solucoes((Nome, Nif,Morada), adjudicante(_,Nome,_,Morada), R), comprimento(R,1)).
+incerto(adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes((Nome, Nif,Morada), adjudicante(_,Nome,_,Morada), R), comprimento(R,1)).
+interdito(adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes((Nome, Nif,Morada), adjudicante(_,Nome,_,Morada), R), comprimento(R,1)).

% Garantir que adjudicantes com ids diferentes têm diferente informação para conhecimento perfeito negativo
+(-adjudicante(IdAd,Nome,Nif,Morada)) :: (solucoes((Nome, Nif,Morada), -adjudicante(_,Nome,Nif,Morada), R), comprimento(R,1)).									

% Garantir que não é possível remover uma entidade adjudicataria com um contrato 
-adjudicante(_,_,Nif,_) :: (solucoes(Nif, contrato(IdC,Nif,_,_,_,_,_,_,_,_), R),comprimento(R, 0)).
-incerto(adjudicante(_,_,Nif,_)) :: (solucoes(Nif, contrato(IdC,Nif,_,_,_,_,_,_,_,_), R),comprimento(R, 0)).
-interdito(adjudicante(_,_,Nif,_)) :: (solucoes(Nif, contrato(IdC,Nif,_,_,_,_,_,_,_,_), R),comprimento(R, 0)).
													
% Garantir que o nif do adjudicante é válido para conhecimento positivo
+adjudicante(_,_,Nif,_) :: nifValido(Nif).
+(-adjudicante(_,_,Nif,_)) :: nifValido(Nif).
+incerto(adjudicante(Id,_,Nif,_)) :: (adjudicante(Id,_,desconhecido,_) ; nifValido(Nif)).
+interdito(adjudicante(Id,_,Nif,_)) :: (adjudicante(Id,_,nif_interdito,_) ; nifValido(Nif)).

%--------------------------------- - - - - - - - - - -  -  -  -  -   - 
% Invariantes Estruturais e Referenciais: Adjudicataria

%Garantir que o id e nif de cada entidade adjudicataria é único para conhecimento perfeito positivo
+adjudicataria(IdAd,Nome,Nif,Morada) :: (solucoes(IdAd, (adjudicataria(IdAd,_,_,_)),R), comprimento(R,1)).									
+adjudicataria(IdAd,Nome,Nif,Morada) :: (solucoes(Nif, adjudicataria(_,_,Nif,_),R), comprimento(R,1)).

%Garantir que o id e nif de cada entidade adjudicataria é único para conhecimento imperfeito incerto
+incerto(adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes(IdAd, (adjudicataria(IdAd,_,_,_)),R), comprimento(R,1)).									
+incerto(adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes(Nif, adjudicataria(_,_,Nif,_),R), comprimento(R,1)).

%Garantir que o id e nif de cada entidade adjudicataria é único para conhecimento imperfeito interdito
+interdito(adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes(IdAd, (adjudicataria(IdAd,_,_,_)),R), comprimento(R,1)).									
+interdito(adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes(Nif, adjudicataria(_,_,Nif,_),R), comprimento(R,1)).

% Garantir que adjudicatarias com ids diferentes têm diferente informação
+adjudicataria(IdAd,Nome,Nif,Morada) :: (solucoes((Nome, Nif,Morada), adjudicataria(_,Nome,_,Morada), R), comprimento(R,1)).
+incerto(adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes((Nome, Nif,Morada), adjudicataria(_,Nome,_,Morada), R), comprimento(R,1)).
+interdito(adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes((Nome, Nif,Morada), adjudicataria(_,Nome,_,Morada), R), comprimento(R,1)).

% Garantir que adjudicatarias com ids diferentes têm diferente informação para conhecimento perfeito negativo
+(-adjudicataria(IdAd,Nome,Nif,Morada)) :: (solucoes((Nome, Nif,Morada), -adjudicataria(_,Nome,Nif,Morada), R), 
									   comprimento(R,1)).									

% Garantir que não é possível remover uma entidade adjudicataria com um contrato 
-adjudicataria(_,_,Nif,_) :: (solucoes(Nif, contrato(IdC,Nif,_,_,_,_,_,_,_,_), R),comprimento(R, 0)).
-incerto(adjudicataria(_,_,Nif,_)) :: (solucoes(Nif, contrato(IdC,Nif,_,_,_,_,_,_,_,_), R),comprimento(R, 0)).
-interdito(adjudicataria(_,_,Nif,_)) :: (solucoes(Nif, contrato(IdC,Nif,_,_,_,_,_,_,_,_), R),comprimento(R, 0)).
													
% Garantir que o nif do adjudicataria é válido para conhecimento positivo
+adjudicataria(_,_,Nif,_) :: nifValido(Nif).
+(-adjudicataria(_,_,Nif,_)) :: nifValido(Nif).
+incerto(adjudicataria(Id,_,Nif,_)) ::(adjudicataria(Id,_,desconhecido,_) ;nifValido(Nif)).
+interdito(adjudicataria(Id,_,Nif,_)) ::(adjudicataria(Id,_,nif_interdito,_) ;nifValido(Nif)).