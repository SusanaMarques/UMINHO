-- converter os ids dos profissionais para 1 significando que o procedimento foi cancelado
DELIMITER //
CREATE PROCEDURE cancelado()
BEGIN
	UPDATE dim_procedimento
	SET cancelamento = 1
	WHERE dim_procedimento.cancelamento != 0;
    
    UPDATE urgency_procedures
	SET ID_PROFESSIONAL_CANCEL = 1
	WHERE urgency_procedures.ID_PROFESSIONAL_CANCEL != 0;
END //
DELIMITER ;

call cancelado;