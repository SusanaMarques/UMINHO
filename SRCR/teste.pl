%yes
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).

evolucao(-(adjudicante(21,nome,123456789,morada), negativo)).

evolucao(-contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020)), negativo).

evolucao(-(adjudicataria(21,nome,123456789,morada), negativo)).

%no
evolucao(contrato(20,123456789,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).


evolucao(adjudicante(21,nome,123456789,morada)).
involucao(adjudicante(21,nome,123456789,morada)).

evolucao(adjudicataria(21,nome,987654321,morada)).
involucao(adjudicataria(21,nome,987654321,morada)).


% ------------------------------- Insere conhecimento imperfeito incerto 
%yes
evolucao(contrato(31,600018709,502381973,desconhecido,"Ajuste Direto","desc",340,25,"local",data(1,1,2020)), incerto, tipocontrato).
involucao(contrato(31,600018709,502381973,idk,"Ajuste Direto","desc",340,25,"local",data(1,1,2020)),incerto, tipocontrato).
%no
evolucao(contrato(15,123456789,502381973,idk,"Ajuste Direto","desc",340,25,"local",data(1,1,2020)), incerto, tipocontrato). 

%yes
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)), incerto, tipoprocedimento).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)),incerto, tipoprocedimento).

%yes
evolucao(contrato(32,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),incerto, descricao).
involucao(contrato(32,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), incerto, descricao).

%yes
evolucao(contrato(33,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,-1,25,local,data(1,1,2020)),incerto, valor).
involucao(contrato(33,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,-1,25,local,data(1,1,2020)), incerto, valor).

%%prazos
% yes
evolucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,-1,local,data(1,1,2020)),incerto, prazo).
involucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,-1,local,data(1,1,2020)), incerto, prazo).

evolucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,25,local,data(1,1,2020)),incerto, prazo).
involucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,25,local,data(1,1,2020)),incerto, prazo).

%yes
evolucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), incerto, local).
involucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),  incerto, local).

%%data
evolucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), incerto, data).
involucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), incerto, data).

%% ---------- adjudicante & adjudicataria

evolucao(adjudicante(20,desconhecido,987654321,morada), incerto, nome).
involucao(adjudicante(20,desconhecido,987654321,morada), incerto, nome).

evolucao(adjudicante(21,nome,123456789,desconhecido), incerto, morada).
involucao(adjudicante(21,nome,123456789,desconhecido), incerto, morada).

% ------------------------------- Insere conhecimento imperfeito impreciso 
% Id,IdAd, IdAda, TipoDeContrato, TipoDeProcedimento, Descrição, Valor, Prazo, Local, Data -> {V,F,D}
%nao é suposto funcionar
evolucao(contrato(40,600018709,502381973,idk,"Ajuste Direto","desc",340,25,"local",data(1,1,2020)), impreciso, tipocontrato).


%yes
evolucao(contrato(45,600018709,502381973,idk,"Consulta Previa",desc,340,25,local,data(1,1,2020)),impreciso, tipocontrato).

evolucao(contrato(35,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), impreciso, tipoprocedimento).
involucao(contrato(35,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),impreciso, tipoprocedimento).


evolucao(contrato(36,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),impreciso, descricao).
involucao(contrato(36,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), impreciso, descricao).


%%prazos
evolucao(contrato(37,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,0,25,local,data(1,1,2020)),impreciso, valor).
involucao(contrato(37,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,0,25,local,data(1,1,2020)), impreciso, valor).

evolucao(contrato(38,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,0,local,data(1,1,2020)),impreciso, prazo).
involucao(contrato(38,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,0,local,data(1,1,2020)), impreciso, prazo).


evolucao(contrato(39,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), impreciso, local).
involucao(contrato(39,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),  impreciso, local).


%%data
evolucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), impreciso, data).
involucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), impreciso, data).

%no 
evolucao(contrato(40,123456789,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), impreciso, data).


%% ---------- adjudicante & adjudicataria

evolucao(adjudicante(20,impreciso,987654321,morada), impreciso, nome).
involucao(adjudicante(20,impreciso,987654321,morada), impreciso, nome).

%%no
involucao(adjudicante(21,wtv,123456789,morada), impreciso, morada).

evolucao(adjudicataria(22,impreciso,987654321,morada), impreciso, nome).
involucao(adjudicataria(22,impreciso,987654321,morada), impreciso, nome).

% ------------------------------- Insere conhecimento imperfeito interdito 
% Id,IdAd, IdAda, TipoDeContrato, TipoDeProcedimento, Descrição, Valor, Prazo, Local, Data -> {V,F,D}
evolucao(contrato(40,600018709,502381973,idk,"Ajuste Direto","desc",340,25,"local",data(1,1,2020)), interdito, tipocontrato).
involucao(contrato(40,600018709,502381973,idk,"Ajuste Direto","desc",340,25,"local",data(1,1,2020)),interdito, tipocontrato).


evolucao(contrato(23,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)), interdito, tipoprocedimento).
involucao(contrato(23,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)),interdito, tipoprocedimento).

evolucao(contrato(24,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),interdito, descricao).
involucao(contrato(24,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), interdito, descricao).


evolucao(contrato(25,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,-1,25,local,data(1,1,2020)),interdito, valor).
involucao(contrato(25,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,-1,25,local,data(1,1,2020)),interdito, valor).

%%prazos
evolucao(contrato(25,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,-1,local,data(1,1,2020)),interdito, prazo).
involucao(contrato(25,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,20,-1,local,data(1,1,2020)), interdito, prazo).


evolucao(contrato(26,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)), interdito, local).
involucao(contrato(26,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),  interdito, local).

%%data
evolucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), interdito, data).
involucao(contrato(40,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(-1,-1,-1)), interdito, data).

%% ---------- adjudicante

%yes
evolucao(adjudicante(20,desconhecido,987654321,morada), interdito, nome).
involucao(adjudicante(20,desconhecido,987654321,morada), interdito, nome).
%yes
evolucao(adjudicataria(21,nome,123456789,desconhecido), interdito, morada).
involucao(adjudicataria(21,nome,123456789,desconhecido), interdito, morada).
%no
evolucao(adjudicante(20,wtv,600018709,morada), interdito, nome).


%% -----------invariantes

%%garantir que cada contrato só tem 1 dos 3 tipos de procedimentos possíveis

%%conhecimento perfeito: YES
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(21,600018709,502381973, "Aquisicao","Consulta Previa","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(21,600018709,502381973, "Aquisicao","Consulta Previa","desc", 4000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(22,600018709,502381973, "Aquisicao","Concurso Publico","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(22,600018709,502381973, "Aquisicao","Concurso Publico","desc", 4000, 195, "Lisboa", data(19,03,2020))).

%%conhecimento perfeito: NO
evolucao(contrato(20,600018709,502381973, "Aquisicao",idk,"desc", 4000, 195, "Lisboa", data(19,03,2020))).

%%conhecimento incerto :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)),incerto, tipoprocedimento).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)),incerto, tipoprocedimento).



%%conhecimento interdito :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)),interdito, tipoprocedimento).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,340,25,local,data(1,1,2020)),interdito, tipoprocedimento).




%Garantir que o valor do contrato por ajuste direto é igual ou inferior a 5000 euros

%%conhecimento perfeito: YES
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).

%%conhecimento perfeito: NO
evolucao(contrato(21,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 6000, 195, "Lisboa", data(19,03,2020))).

%%conhecimento incerto :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,34000,25,local,data(1,1,2020)),incerto, tipoprocedimento).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,34000,25,local,data(1,1,2020)),incerto, tipoprocedimento).

%%conhecimento incerto :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,idk,25,local,data(1,1,2020)),incerto, valor).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,idk,25,local,data(1,1,2020)),incerto, valor).


%%conhecimento impreciso :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),impreciso, valor).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),impreciso, valor).


%%conhecimento interdito :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,34000,25,local,data(1,1,2020)),interdito, tipoprocedimento).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos",idk,desc,34000,25,local,data(1,1,2020)),interdito, tipoprocedimento).


%%conhecimento interdito :YES
evolucao(contrato(30,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),interdito, valor).
involucao(contrato(30,600018709,502381973,"Aquisicao de servicos","Ajuste Direto",desc,340,25,local,data(1,1,2020)),interdito, valor).






%Garantir que um contrato por ajuste direto é um dos segintes: Contrato de aquisição ou locação de bens móveis ou aquisição de serviços

%%conhecimento perfeito: YES
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(21,600018709,502381973, "Locacao de bens moveis","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(21,600018709,502381973, "Locacao de bens moveis","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(22,600018709,502381973, "Aquisicao de servicos","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(22,600018709,502381973, "Aquisicao de servicos","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).

%%conhecimento perfeito: NO
evolucao(contrato(20,600018709,502381973, idk,"Ajuste Direto","desc", 40, 195, "Lisboa", data(19,03,2020))).

%%conhecimento incerto :YES
evolucao(contrato(30,600018709,502381973,idk,idk,desc,340,25,local,data(1,1,2020)),incerto, tipoprocedimento).
involucao(contrato(30,600018709,502381973,idk,idk,desc,340,25,local,data(1,1,2020)),incerto, tipoprocedimento).


%%conhecimento interdito :YES
evolucao(contrato(30,600018709,502381973,idk,idk,desc,340,25,local,data(1,1,2020)),interdito, tipoprocedimento).
involucao(contrato(30,600018709,502381973,idk,idk,desc,340,25,local,data(1,1,2020)),interdito, tipoprocedimento).






%%Prazo de vigencia ate 1 ano
%%conhecimento perfeito: YES
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
involucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).

%%conhecimento perfeito: NO
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 395, "Lisboa", data(19,03,2020))).

%%conhecimento incerto :YES
evolucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,340,idk,local,data(1,1,2020)),incerto, prazo).
involucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,340,idk,local,data(1,1,2020)),incerto, prazo).


%%conhecimento impreciso :YES
evolucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,340,2,local,data(1,1,2020)),impreciso, prazo).
involucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,340,2,local,data(1,1,2020)),impreciso, prazo).


%%conhecimento interdito :YES
evolucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,340,2,local,data(1,1,2020)),interdito, prazo).
involucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,340,2,local,data(1,1,2020)),interdito, prazo).




%Regra dos 3 anos válida para todos os contratos

%%conhecimento perfeito: YES (o segundo contrato dá yes) -mais do que 3 anos:

evolucao(contrato(20,600018709,502381973, "Aquisicao","Consulta Previa","desc", 760000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(21,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2024))).
--------------------------------------------------------
%%conhecimento perfeito: YES (o segundo contrato dá yes) - menos que 75000:

evolucao(contrato(26,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(27,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(18,03,2020))).


%%conhecimento perfeito: NO (o segundo contrato dá não)
evolucao(contrato(22,600018709,502381973, "Aquisicao","Consulta Previa","desc", 760000, 195, "Lisboa", data(19,03,2020))).
evolucao(contrato(23,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 4000, 195, "Lisboa", data(19,03,2020))).




%%conhecimento incerto :YES

evolucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(idk,idk,idk)),incerto, data).
involucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(idk,idk,idk)),incerto, data).
evolucao(contrato(32,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(idk,2,2020)),incerto, dataDia).
involucao(contrato(32,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(idk,2,2020)),incerto, dataDia).
evolucao(contrato(33,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,idk,2024)),incerto, dataMes).
involucao(contrato(33,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,idk,2024)),incerto, dataMes).
evolucao(contrato(34,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,idk)),incerto, dataAno).
involucao(contrato(34,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,idk)),incerto, dataAno).

%%conhecimento impreciso :YES

evolucao(contrato(31,600018709,502381973,"Aquisicao","Consulta Previa",desc,120040,340,local,data(1,1,2020)),impreciso, data).
involucao(contrato(31,600018709,502381973,"Aquisicao","Consulta Previa",desc,120040,340,local,data(1,1,2020)),impreciso, data).
evolucao(contrato(33,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,2020)),impreciso, dataDia).
involucao(contrato(33,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,2020)),impreciso, dataDia).
evolucao(contrato(34,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,1,2024)),impreciso, dataMes).
involucao(contrato(34,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,1,2024)),impreciso, dataMes).
evolucao(contrato(35,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,0)),impreciso, dataAno).
involucao(contrato(35,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,0)),impreciso, dataAno).


%%conhecimento interdito :YES

evolucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(desconhecido,desconhecido,desconhecido)),interdito, data).
involucao(contrato(31,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(desconhecido,desconhecido,desconhecido)),interdito, data).
evolucao(contrato(32,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(idk,2,2020)),interdito, dataDia).
involucao(contrato(32,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(idk,2,2020)),interdito, dataDia).
evolucao(contrato(33,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,idk,2024)),interdito, dataMes).
involucao(contrato(33,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,idk,2024)),interdito, dataMes).
evolucao(contrato(34,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,idk)),interdito, dataAno).
involucao(contrato(34,600018709,502381973,"Aquisicao","Ajuste Direto",desc,1240,340,local,data(1,2,idk)),interdito, dataAno).


%demo
demo(adjudicante(1, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa"),verdadeiro).
demo(adjudicante(2, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa"),falso).
demo(adjudicante(4, "Universidade do Minho", 502011378, "Braga"), desconhecido).

%demoList
demoList([adjudicante(1, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa"),adjudicante(2, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa")], X).



query([adjudicante(1, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa"), 'E', adjudicante(2, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa")],X ).
query([adjudicante(1, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa"), 'OU', adjudicante(2, "Direcao-Geral do Tribunal de Contas", 600018709, "Lisboa")],X ).


%%
evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 3000, 195, "Lisboa", data(19,03,2020)), impreciso, prazo, 200, 300).

evolucao(contrato(20,600018709,502381973, "Aquisicao","Ajuste Direto","desc", 3000, 195, "Lisboa", data(19,03,2020)), data(26,03,2020)).


evolucao(contrato(25,600018709,502381973, "Aquisicao","Ajuste Direto","desc", idk, 195, "Lisboa", data(19,03,2020)), impreciso, valor, 200, 300).




