DROP PROCEDURE IF EXISTS criar_atleta;
DELIMITER $$
CREATE PROCEDURE criar_atleta(IN idAtleta INT, IN nome VARCHAR(100), IN dataNascimento DATE, 
                              IN sexo ENUM('F', 'M'), IN morada INT, IN idMorada INT, IN endereco VARCHAR(100), 
                              IN localidade VARCHAR(100), IN idContacto INT, IN telefone INT, in email VARCHAR(100))
BEGIN 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    START TRANSACTION;
   -- criar morada caso não exista
   SET @morada := 0;
   SELECT @morada := m.idMorada from Morada as m where m.idMorada = idMorada;
   IF(@morada = 0) 
   THEN INSERT INTO Morada(idMorada, endereco, localidade) VALUE (idMorada, endereco, localidade);
   SELECT @morada := m.idMorada from Morada as m where m.idMorada = idMorada;
   END IF;
   -- criar atleta
   INSERT INTO Atleta(idAtleta,nome, dataNascimento, sexo, morada)
   VALUES (idAtleta,nome, dataNascimento, sexo, @morada);
   -- criar contacto
   INSERT INTO Contacto(idContacto,telefone, email, idAtleta)
   VALUES (idContacto,telefone, email, idAtleta);
   COMMIT; 
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS inserir_alteta_prova;
DELIMITER $$
CREATE PROCEDURE inserir_alteta_prova(IN idAtleta INT, IN idProva INT, IN data DATE)
BEGIN 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    START TRANSACTION;
   -- criar relação atleta prova
   INSERT INTO Atleta_Prova(idAtleta,idProva,data)
   VALUES (idAtleta,idProva,data);
   COMMIT; 
END $$
DELIMITER ;

###################################################

DROP PROCEDURE IF EXISTS criar_medico;
DELIMITER $$
CREATE PROCEDURE criar_medico(IN idMedico INT, IN nome VARCHAR(100), IN dataInicioServico DATE,
							  IN dataNascimento DATE, IN especialidade VARCHAR(100), IN idChefe INT,
                              IN idClinica INT, IN morada INT)
BEGIN 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    START TRANSACTION;
   -- criar morada caso não exista
   SET @morada := 0;
   SELECT @morada := m.idMorada from Morada as m where m.idMorada = morada;
   IF(@morada = 0) 
   THEN INSERT INTO Morada(idMorada, endereco, localidade) VALUE (morada, endereco, localidade);
   SELECT @morada := m.idMorada from Morada as m where  m.idMorada = morada;
   END IF;
   -- criar medico
   INSERT INTO Medico(idMedico, nome, dataInicioservico, dataNascimento, especialidade, idChefe, idClinica, morada)
   VALUES (idMedico, nome, dataInicioservico, dataNascimento, especialidade, idChefe, idClinica, morada);
  
   COMMIT; 
END $$
DELIMITER ;

###################################################

DROP PROCEDURE IF EXISTS agendamento;
DELIMITER $$
CREATE PROCEDURE agendamento(IN idTesteClinico INT, IN designacao VARCHAR(100), IN dataHora DATETIME,
                             IN preco DECIMAL(6,2), IN resultado VARCHAR(100), IN idMedico INT, IN idAtleta INT)
BEGIN 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    START TRANSACTION;
   -- criar agendamento
   INSERT INTO testeClinico(idTesteClinico, designacao, dataHora, preco, resultado, idMedico, idAtleta)
   VALUES(idTesteClinico, designacao, dataHora, preco, null, idMedico, idAtleta);
   COMMIT; 
END $$
DELIMITER ;

###################################################

DROP PROCEDURE IF EXISTS criar_prova;
DELIMITER $$
CREATE PROCEDURE criar_prova(IN idProva INT, IN designacao VARCHAR(100), IN modalidade VARCHAR(100),
                             IN categoria VARCHAR(100), IN designacao ENUM('Dopping', 'Eletrocardiograma',
                             'Ecocardiograma','Raio X', 'Prova de Esforço'))
BEGIN 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    START TRANSACTION;
   -- criar prova
   INSERT INTO prova(idProva, designacao, modalidade, categoria)
   VALUES(idProva, designacao, modalidade, categoria);
   -- criar tabela de testes obrigatórios para a prova
   INSERT INTO prova_tipoteste(idProva, designacao)
   values(idProva, designacao);
   COMMIT; 
END $$
DELIMITER ;





