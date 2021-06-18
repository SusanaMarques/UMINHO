-- função que atribui a uma data a estação do ano correspondente 
DELIMITER //
DROP FUNCTION IF EXISTS Estacao //
CREATE FUNCTION `Estacao` ( data datetime )
RETURNS varchar(100)
READS SQL DATA
DETERMINISTIC
BEGIN

   DECLARE estacao varchar(100);

   IF data = '9999-01-01 00:00:00' THEN
	  SET estacao = 'NA'; 
   ELSEIF DATE_FORMAT(data, '%m-%d') >= '03-20' and DATE_FORMAT(data, '%m-%d') < '06-21' THEN
      SET estacao = 'Primavera';

   ELSEIF DATE_FORMAT(data, '%m-%d') >= '06-21' and DATE_FORMAT(data, '%m-%d') < '09-22'  THEN
      SET estacao = 'Verao';

   ELSEIF DATE_FORMAT(data, '%m-%d') >= '09-22' and DATE_FORMAT(data, '%m-%d') < '12-21' THEN
      SET estacao = 'Outono';

   ELSE
      SET estacao = 'Inverno';

   END IF;

   RETURN estacao;
END; //
DELIMITER ;

-- função que verifica se a uma data corresponde a um feriado 
DELIMITER //
DROP FUNCTION IF EXISTS Feriado //
CREATE FUNCTION `Feriado` ( data datetime )
RETURNS varchar(100)
READS SQL DATA
DETERMINISTIC
BEGIN

   DECLARE feriado varchar(100);

   IF data = '9999-01-01 00:00:00' THEN
      SET feriado = 'NA'; 
   ELSEIF DATE_FORMAT(data, '%m-%d') ='01-01' THEN
      SET feriado = 'Dia de Ano-Novo';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '04-25' THEN
      SET feriado = 'Dia da Liberdade';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '05-01' THEN
      SET feriado = 'Dia do Trabalhador';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '06-10' THEN
      SET feriado = 'Dia de Portugal';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '10-05' THEN
      SET feriado = 'Dia da Implementação da República';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '11-01' THEN
      SET feriado = 'Dia de Todos os Santos';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '12-01' THEN
      SET feriado = 'Restauracão da Independência';

   ELSEIF DATE_FORMAT(data, '%m-%d') = '12-25' THEN
      SET feriado = 'NATAL';

   ELSE 
      SET feriado = 'NA';
	END IF;

   RETURN feriado;
END; //
DELIMITER ;