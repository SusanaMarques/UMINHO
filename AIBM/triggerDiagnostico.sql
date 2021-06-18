DELIMITER //
DROP TRIGGER IF EXISTS povoarDiagnostico //
CREATE TRIGGER povoarDiagnostico
BEFORE INSERT
ON dim_diagnostico FOR EACH ROW
BEGIN
	if  ( exists (select level_5_code from icd9_hierarchy where  new.codigo_diagnostico = level_5_code )) = 1 then
        set new.id_nivel5 = (select id_nivel5 from dim_nivel5 where codigo = (select level_5_code from icd9_hierarchy where new.codigo_diagnostico = level_5_code limit 1));
        set new.id_nivel4 = (select id_nivel4 from dim_nivel5 where codigo = (select level_5_code from icd9_hierarchy where new.codigo_diagnostico = level_5_code limit 1));
        set new.id_nivel3 = (select id_nivel3 from dim_nivel4 where id_nivel4 = new.id_nivel4);
        set new.id_nivel2 = (select id_nivel2 from dim_nivel3 where id_nivel3 = new.id_nivel3);
        set new.id_nivel1 = (select id_nivel1 from dim_nivel2 where id_nivel2 = new.id_nivel2);
	elseif (select exists (select level_4_code from icd9_hierarchy where new.codigo_diagnostico = level_4_code)) = 1  then
		set new.id_nivel5 = (select id_nivel5 from dim_nivel5 where codigo='NA' limit 1);
		set new.id_nivel4 = (select id_nivel4 from dim_nivel4 where codigo = (select level_4_code from icd9_hierarchy where new.codigo_diagnostico = level_4_code limit 1));
		set new.id_nivel3 = (select id_nivel3 from dim_nivel4 where id_nivel4 = new.id_nivel4);
        set new.id_nivel2 = (select id_nivel2 from dim_nivel3 where id_nivel3 = new.id_nivel3);
        set new.id_nivel1 = (select id_nivel1 from dim_nivel2 where id_nivel2 = new.id_nivel2);
	elseif (select exists(select level_3_code from icd9_hierarchy where new.codigo_diagnostico = level_3_code)) = 1  then
		set new.id_nivel5 = (select id_nivel5 from dim_nivel5 where codigo='NA' limit 1);
		set new.id_nivel4 = (select id_nivel4 from dim_nivel4 where codigo='NA' limit 1);
		set new.id_nivel3 = (select id_nivel3 from dim_nivel3 where codigo = (select level_3_code from icd9_hierarchy where new.codigo_diagnostico = level_3_code limit 1));
        set new.id_nivel2 = (select id_nivel2 from dim_nivel3 where id_nivel3 = new.id_nivel3);
        set new.id_nivel1 = (select id_nivel1 from dim_nivel2 where id_nivel2 = new.id_nivel2);
	elseif (select exists(select level_2_code from icd9_hierarchy where new.codigo_diagnostico = level_2_code)) = 1  then
		set new.id_nivel5 = (select id_nivel5 from dim_nivel5 where codigo='NA' limit 1);
		set new.id_nivel4 = (select id_nivel4 from dim_nivel4 where codigo='NA' limit 1);
		set new.id_nivel3 = (select id_nivel3 from dim_nivel3 where codigo='NA' limit 1);
        set new.id_nivel2 = (select id_nivel2 from dim_nivel2 where codigo = (select level_2_code from icd9_hierarchy where new.codigo_diagnostico = level_2_code limit 1));
        set new.id_nivel1 = (select id_nivel1 from dim_nivel2 where id_nivel2 = new.id_nivel2);
	elseif not(new.codigo_diagnostico like 'E%') and not(new.codigo_diagnostico like 'V%') then
		set new.id_nivel5 = (select id_nivel5 from dim_nivel5 where codigo='NA' limit 1);
		set new.id_nivel4 = (select id_nivel4 from dim_nivel4 where codigo='NA' limit 1);
		set new.id_nivel3 = (select id_nivel3 from dim_nivel3 where codigo='NA' limit 1);
        set new.id_nivel2 = (select id_nivel2 from dim_nivel2 where codigo =  (select level_2_code from icd9_hierarchy where new.codigo_diagnostico >= CAST(substr(level_2_code,1,3) AS UNSIGNED) and new.codigo_diagnostico <= CAST(substr(level_2_code,4,3) AS unsigned)  limit 1));
        set new.id_nivel1 = (select id_nivel1 from dim_nivel2 where id_nivel2 = new.id_nivel2);
	else
		set new.id_nivel5 = (select id_nivel5 from dim_nivel5 where codigo='NA' limit 1);
		set new.id_nivel4 = (select id_nivel4 from dim_nivel4 where codigo='NA' limit 1);
		set new.id_nivel3 = (select id_nivel3 from dim_nivel3 where codigo='NA' limit 1);
        set new.id_nivel2 = (select id_nivel2 from dim_nivel2 where codigo='NA' limit 1);
        set new.id_nivel1 = (select id_nivel1 from dim_nivel1 where codigo='NA' limit 1);
	end if;
END;//
DELIMITER ;

