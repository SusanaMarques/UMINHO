Use ClinicaAltaPerformance;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'Admin';
CREATE USER 'medico'@'localhost' IDENTIFIED BY 'Medico';
CREATE USER 'rececionista'@'localhost' IDENTIFIED BY 'Rececionista';

GRANT SELECT, UPDATE, INSERT, DELETE ON * TO 'admin'@'localhost';

GRANT EXECUTE ON PROCEDURE testes_antes_prova TO 'admin'@'localhost';
GRANT EXECUTE ON PROCEDURE dopping_positivo TO 'admin'@'localhost';
GRANT EXECUTE ON PROCEDURE pacientes_testes  TO 'admin'@'localhost';
GRANT EXECUTE ON PROCEDURE nr_exames_medico TO 'admin'@'localhost';
GRANT EXECUTE ON PROCEDURE agendamentos_exame_clinica TO 'admin'@'localhost';


GRANT EXECUTE ON PROCEDURE testes_antes_prova TO 'medico'@'localhost';
GRANT EXECUTE ON PROCEDURE dopping_positivo TO 'medico'@'localhost';
GRANT EXECUTE ON PROCEDURE pacientes_testes  TO 'medico'@'localhost';
GRANT EXECUTE ON PROCEDURE agendamentos_exame_clinica TO 'medico'@'localhost';

GRANT EXECUTE ON PROCEDURE testes_antes_prova TO 'rececionista'@'localhost';
GRANT EXECUTE ON PROCEDURE pacientes_testes  TO 'rececionista'@'localhost';
GRANT EXECUTE ON PROCEDURE agendamentos_exame_clinica TO 'rececionista'@'localhost';

GRANT SELECT ON view_agendamento TO 'admin'@'localhost';
GRANT SELECT ON view_agendamento TO 'medico'@'localhost';
GRANT SELECT ON view_agendamento TO 'rececionista'@'localhost';
GRANT SELECT ON view_dados_pacientes TO 'admin'@'localhost';
GRANT SELECT ON view_dados_pacientes TO 'medico'@'localhost';
GRANT SELECT ON view_dados_pacientes TO 'rececionista'@'localhost';
GRANT SELECT ON view_medicos_especialidades TO 'admin'@'localhost';
GRANT SELECT ON view_medicos_especialidades TO 'rececionista'@'localhost';
GRANT SELECT ON view_exames_realizados TO 'admin'@'localhost';
GRANT SELECT ON view_exames_realizados TO 'medico'@'localhost';
GRANT SELECT ON view_exames_realizados TO 'rececionista'@'localhost';


-- DROP USER 'admin'@'localhost';
-- DROP USER 'medico'@'localhost';
-- DROP USER 'rececionista'@'localhost';
-- FLUSH PRIVILEGES; 