use trabalhoaibm;

-- inserção dimensão dim_destino
insert into dim_destino(id_destino,descricao)
select distinct ID_DESTINATION,DESC_DESTINATION from urgency_episodes_new;

select * from dim_destino order by id_destino;

-- inserção dimensão dim_distrito
insert into dim_distrito(descricao)
select distinct DISTRICT from urgency_episodes_new;

select * from dim_distrito order by id_distrito;

-- inserção na dim_color
insert into dim_color(id_color, descricao)
select distinct ID_COLOR, DESC_COLOR from urgency_episodes_new;

select * from dim_color;

-- inserção dimensão dim_genero
insert into dim_genero(descricao)
select distinct SEX from urgency_episodes_new;

select * from dim_genero;

-- inserção dimensão dim_medicamento
insert ignore into dim_medicamento(id_medicamento,descricao)
select distinct COD_DRUG, DESC_DRUG from urgency_prescriptions;

select * from dim_medicamento;

-- inserção dim_data
insert into dim_data (data)
select distinct DATE_OF_BIRTH from urgency_episodes_new;

insert ignore into dim_data (data)
select distinct DT_ADMITION_URG from urgency_episodes_new;

insert ignore into dim_data (data)
select distinct DT_ADMITION_TRAIGE from urgency_episodes_new;

insert ignore into dim_data (data)
select distinct DT_DIAGNOSIS from urgency_episodes_new;

insert ignore into dim_data (data)
select distinct DT_DISCHARGE from urgency_episodes_new;

insert ignore into dim_data (data)
select distinct DT_PRESCRIPTION from urgency_prescriptions;

insert ignore into dim_data (data)
select distinct DT_PRESCRIPTION from urgency_procedures;

insert ignore into dim_data (data)
select distinct DT_BEGIN from urgency_procedures;

select * from dim_data order by id_data;

-- inserção na dimensão dim_causas_externas -- done
insert into dim_causas_externas (id_causas_externas, descricao)
select distinct ID_EXT_CAUSE, DESC_EXTERNAL_CAUSE  from urgency_episodes_new;

select * from dim_causas_externas;

-- inserção na dimensão dim_razao_alta --done
insert into dim_razao_alta (id_razao_alta, descricao)
select distinct ID_REASON, DESC_REASON from urgency_episodes_new;

select * from dim_razao_alta;

-- inserção na dimensão dim_intevenção --done
insert ignore into dim_intervencao(id_intervencao, descricao)
select distinct ID_INTERVENTION, DESC_INTERVENTION from urgency_procedures;

select * from dim_intervencao;

-- inserção na dimensão dim_n_exames -- done
insert into dim_n_exames(id_n_exames)
select distinct NUM_EXAM from urgency_exams;

select * from dim_n_exames;

-- inserção na dim_desct_exames
insert into dim_desct_exames(descricao)
select distinct a1.DESC_EXAM  from urgency_exams a1;
    
select * from dim_desct_exames;

-- insercao na dimensão dim_triagem
insert into dim_triagem(id_data_triagem, id_prof_triagem, pain_scale, id_color)
select distinct a1.id_data, a2.ID_PROF_TRIAGE, a2.PAIN_SCALE, a3.id_color
	from urgency_episodes_new a2
    left join dim_data a1 on a1.data = a2.DT_ADMITION_TRAIGE
    left join dim_color a3 on a3.id_color = a2.ID_COLOR;
    
select * from dim_triagem;

-- inserção na dimensão dim_prescricao
insert ignore into dim_prescricao(id_prescricao, id_prof_prescreve, id_data)
select distinct a1.COD_PRESCRIPTION, a1.ID_PROF_PRESCRIPTION, a2.id_data
	from urgency_prescriptions a1
    left join dim_data a2 on a2.data = a1.DT_PRESCRIPTION;

select * from dim_prescricao order by id_prescricao;


ALTER TABLE dim_procedimento
MODIFY COLUMN id_procedimento int auto_increment;

-- inserção na dimensão dim_procedimento
insert into dim_procedimento (id_data, especificacao, id_intervencao, cancelamento, id_presc_procedimento)
select distinct a1.id_data, a2.NOTE, a3.id_intervencao, a2.ID_PROFESSIONAL_CANCEL, a2.ID_PRESCRIPTION
	from urgency_procedures a2
    left join dim_data a1 on a1.data = a2.DT_BEGIN
    left join dim_intervencao a3 on a3.descricao = a2.DESC_INTERVENTION;

select * from dim_procedimento;


-- FAZER PROCEDURE DEPOIS DE POVOAR ISTO
-- inserção na dimensão dim_presc_procedimento
insert into dim_presc_procedimento (id_presc_procedimento, id_profissional, id_data)
select distinct a1.ID_PRESCRIPTION, a1.ID_PROFESSIONAL, a2.id_data
	from urgency_procedures a1
    left join dim_data a2 on a2.data = a1.DT_PRESCRIPTION;

select * from dim_presc_procedimento;

-- inserção na dimensão dim_nivel1
insert into dim_nivel1(codigo, descricao)
select distinct level_1_code, level_1_desc from icd9_hierarchy;

insert into dim_nivel1(codigo, descricao) values ('NA','NA');

select * from dim_nivel1;

-- inserção na dimensão dim_nivel2
insert into dim_nivel2(codigo, descricao, id_nivel1)
select distinct a1.level_2_code, a1.level_2_desc, a2.id_nivel1
	from icd9_hierarchy a1
    left join dim_nivel1 a2 on a2.codigo = level_1_code and a2.descricao = level_1_desc;
    
insert into dim_nivel2(codigo, descricao, id_nivel1) values ('NA','NA', (select id_nivel1 from dim_nivel1 where codigo='NA' limit 1));

select * from dim_nivel2;

-- inserção na dimensão dim_nivel3
insert into dim_nivel3(codigo, descricao, id_nivel2)
select distinct a1.level_3_code, a1.level_3_desc, a2.id_nivel2
	from icd9_hierarchy a1
    left join dim_nivel2 a2 on a2.codigo = level_2_code and a2.descricao = level_2_desc;

select * from dim_nivel3;

-- inserção na dimensão dim_nivel4
insert into dim_nivel4(codigo, descricao, id_nivel3)
select distinct a1.level_4_code, a1.level_4_desc, a2.id_nivel3
	from icd9_hierarchy a1
    left join dim_nivel3 a2 on a2.codigo = level_3_code and a2.descricao = level_3_desc;

select * from dim_nivel4;


-- inserção na dimensão dim_nivel5
insert into dim_nivel5(codigo, descricao, id_nivel4)
select distinct a1.level_5_code, a1.level_5_desc, a2.id_nivel4
	from icd9_hierarchy a1
    left join dim_nivel4 a2 on a2.codigo = level_4_code and a2.descricao = level_4_desc;
    

select * from dim_nivel5;
select count(distinct codigo_diagnostico) from dim_diagnostico;
-- inserção na dimensão dim_diagnostico
insert into dim_diagnostico(codigo_diagnostico, descricao, id_prof_diagnostico, id_data_diagnostico)
select distinct a1.COD_DIAGNOSIS, a1.DIAGNOSIS, a1.ID_PROF_DIAGNOSIS, a2.id_data
	from urgency_episodes_new a1
    left join dim_data a2 on a2.data = a1.DT_DIAGNOSIS;
    
select * from dim_nivel2 where codigo = '001-009';
select * from dim_diagnostico;

-- insere na tabela dim_estacao as estacoes do ano
insert into dim_estacao(id_estacao,estacao) values (1,"Inverno");
insert into dim_estacao(id_estacao,estacao) values (2,"Primavera");
insert into dim_estacao(id_estacao,estacao) values (3,"Verao");
insert into dim_estacao(id_estacao,estacao) values (4,"Outono");
insert into dim_estacao(id_estacao,estacao) values (5,"NA");

-- insere na tabela dim_feriado
insert into dim_feriado(id_feriado,feriado) values (1,"Dia de Ano-Novo");
insert into dim_feriado(id_feriado,feriado) values (2,"Dia da Liberdade");
insert into dim_feriado(id_feriado,feriado) values (3,"Dia do Trabalhador");
insert into dim_feriado(id_feriado,feriado) values (4,"Dia de Portugal");
insert into dim_feriado(id_feriado,feriado) values (5,"Dia da Implementação da República");
insert into dim_feriado(id_feriado,feriado) values (6,"Dia de Todos os Santos");
insert into dim_feriado(id_feriado,feriado) values (7,"Restauração da Independência");
insert into dim_feriado(id_feriado,feriado) values (8,"Natal");
insert into dim_feriado(id_feriado,feriado) values (9,"NA");

-- inserção na tabela de factos
insert into facts_episodio (urg_episodio, id_causas_externas, id_data_admissao, id_data_nascimento, id_distrito, id_genero,id_prof_admissao, id_triagem, id_destino, id_data_alta, id_razao_alta)
select distinct a1.URG_EPISODE, a2.id_causas_externas, a3.id_data, a4.id_data, a5.id_distrito, a6.id_genero, a1.ID_PROF_ADMITION, a7.id_triagem, a8.id_destino, a9.id_data, a10.id_razao_alta
		from urgency_episodes_new a1
		inner join dim_causas_externas a2 on a2.descricao = a1.DESC_EXTERNAL_CAUSE
		inner join dim_data a3 on a3.data = a1.DT_ADMITION_URG
		inner join dim_data a4 on a4.data = a1.DATE_OF_BIRTH
		inner join dim_distrito a5 on a5.descricao = a1.DISTRICT
		inner join dim_genero a6 on a6.descricao = a1.SEX
		inner join dim_triagem a7 on a7.id_prof_triagem = a1.ID_PROF_TRIAGE and a7.pain_scale = a1.PAIN_SCALE
		inner join dim_destino a8 on a8.descricao = a1.DESC_DESTINATION
		inner join dim_data a9 on a9.data = a1.DT_DISCHARGE
		inner join dim_razao_alta a10 on a10.descricao = a1.DESC_REASON
        group by a1.URG_EPISODE;

select count(*) from facts_episodio;

select * from dim_desct_exames_has_dim_n_exames;
select * from dim_desct_exames_has_facts_episodio;
select * from dim_prescricao_has_facts_episodio;
select * from dim_prescricao_has_dim_medicamento;
select * from dim_prescricao_has_facts_episodio;
select * from facts_episodio_has_dim_procedimento;

