-- trigger que antes de inserir as datas associa as estações das mesmas
DELIMITER //
DROP TRIGGER IF EXISTS estacoes //
CREATE TRIGGER estacoes
BEFORE INSERT
ON dim_data FOR EACH ROW
BEGIN 
	set new.id_estacao = (select id_estacao from dim_estacao where estacao = Estacao(new.data));
END;//
DELIMITER ;

-- trigger que antes de inserir as datas associa as estações das mesmas
DELIMITER //
DROP TRIGGER IF EXISTS feriados //
CREATE TRIGGER feriados
BEFORE INSERT
ON dim_data FOR EACH ROW
BEGIN 
	set new.id_feriado = (select id_feriado from dim_feriado where feriado = Feriado(new.data));
END;//
DELIMITER ;