Use ClinicaAltaPerformance;


### Interrogações do utilizador ###

##1
DROP PROCEDURE IF EXISTS testes_antes_prova
DELIMITER $$
CREATE PROCEDURE testes_antes_prova (IN testes_atleta VARCHAR(100))
BEGIN
SELECT t.designacao as Designação, t.dataHora as "Data e Hora" FROM TesteClinico t
INNER JOIN Atleta_Prova ap ON ap.data > t.dataHora
WHERE ap.id_Atleta = t.idAtleta AND id_Atleta = 1
ORDER BY t.dataHora;
END $$
DELIMITER ;

##2 
DROP PROCEDURE IF EXISTS dopping_positivo
DELIMITER $$
CREATE PROCEDURE dopping_positivo (IN nome_atleta VARCHAR(100))
BEGIN
SELECT DISTINCT a.nome
FROM(SELECT ap.id_Atleta 
	 FROM Atleta_Prova ap
     INNER JOIN Prova p ON p.idProva = ap.id_Prova WHERE p.modalidade = "velocidade") as partial_table
INNER JOIN TesteClinico t ON t.idAtleta = partial_table.id_Atleta
INNER JOIN Atleta a ON a.idAtleta = partial_table.id_Atleta
WHERE designacao = "dopping" AND resultado = "positivo" ;
END $$
DELIMITER ;


##3
DROP PROCEDURE IF EXISTS pacientes_testes 
DELIMITER $$
CREATE PROCEDURE pacientes_testes (IN nome_atleta VARCHAR(100))
BEGIN
SELECT a.nome as Nome FROM Atleta a
INNER JOIN TesteClinico t ON t.idAtleta = a.idAtleta
WHERE t.designacao = "Analise_sanguinea";
END $$
DELIMITER ;

##4 
DROP PROCEDURE IF EXISTS nr_exames_medico
DELIMITER $$
CREATE PROCEDURE  nr_exames_medico (IN nome_medico VARCHAR(100))
SELECT count(*), sum(preco) as Faturado FROM TesteClinico t 
WHERE t.idMedico = (SELECT m.idMedico FROM Medico m where m.nome = "Tiago Ferreira") 
AND YEAR(t.dataHora) = 2019;
END $$
DELIMITER ;


##5

DROP PROCEDURE IF EXISTS agendamentos_exame_clinica
DELIMITER $$
CREATE PROCEDURE  agendamentos_exame_clinica (IN designcao_testeclinico VARCHAR(100))
SELECT t.designacao as Designação, t.dataHora as "Data e Hora", a.nome as Nome FROM TesteClinico t 
INNER JOIN Medico m ON m.idMedico = t.idMedico
INNER JOIN Atleta a ON a.idAtleta =  t.idAtleta
WHERE t.designacao = "Eletrocardiograma" AND t.dataHora > CURRENT_TIME() AND m.idClinica = 1;
END $$
DELIMITER ;

