-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ClinicaAltaPerformance
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ClinicaAltaPerformance
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ClinicaAltaPerformance` DEFAULT CHARACTER SET utf8 ;
USE `ClinicaAltaPerformance` ;

-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Morada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Morada` (
  `idMorada` INT NOT NULL AUTO_INCREMENT,
  `endereço` VARCHAR(100) NOT NULL,
  `localidade` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idMorada`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Atleta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Atleta` (
  `idAtleta` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `dataNascimento` DATE NOT NULL,
  `sexo` VARCHAR(5) NOT NULL,
  `morada` INT NOT NULL,
  PRIMARY KEY (`idAtleta`),
  INDEX `fk_Atleta_Morada_idx` (`morada` ASC) VISIBLE,
  CONSTRAINT `fk_Atleta_Morada`
    FOREIGN KEY (`morada`)
    REFERENCES `ClinicaAltaPerformance`.`Morada` (`idMorada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Prova`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Prova` (
  `idProva` INT NOT NULL AUTO_INCREMENT,
  `designacao` VARCHAR(100) NOT NULL,
  `modalidade` VARCHAR(100) NOT NULL,
  `categoria` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idProva`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Atleta_Prova`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Atleta_Prova` (
  `id_Atleta` INT NOT NULL,
  `id_Prova` INT NOT NULL,
  `data` DATE NOT NULL,
  INDEX `fk_Atleta_Prova_Modalidade_prova_idx` (`id_Prova` ASC) VISIBLE,
  PRIMARY KEY (`id_Prova`, `id_Atleta`),
  CONSTRAINT `fk_Atleta_Prova_Modalidade_atleta`
    FOREIGN KEY (`id_Atleta`)
    REFERENCES `ClinicaAltaPerformance`.`Atleta` (`idAtleta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Atleta_Prova_Modalidade_prova`
    FOREIGN KEY (`id_Prova`)
    REFERENCES `ClinicaAltaPerformance`.`Prova` (`idProva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Clinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Clinica` (
  `idClinica` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `morada` INT NOT NULL,
  PRIMARY KEY (`idClinica`),
  INDEX `fk_Clinica_Morada1_idx` (`morada` ASC) VISIBLE,
  CONSTRAINT `fk_Clinica_Morada1`
    FOREIGN KEY (`morada`)
    REFERENCES `ClinicaAltaPerformance`.`Morada` (`idMorada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Medico` (
  `idMedico` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `dataInicioServico` DATE NOT NULL,
  `dataNascimento` DATE NOT NULL,
  `especialidade` VARCHAR(100) NOT NULL,
  `idChefe` INT NULL,
  `idClinica` INT NOT NULL,
  `morada` INT NOT NULL,
  PRIMARY KEY (`idMedico`),
  INDEX `fk_Tecnico_Hospital_idx` (`idClinica` ASC) VISIBLE,
  INDEX `fk_Medico_Chefe_idx` (`idChefe` ASC) VISIBLE,
  INDEX `fk_Medico_Morada1_idx` (`morada` ASC) VISIBLE,
  CONSTRAINT `fk_Medico_Chefe`
    FOREIGN KEY (`idChefe`)
    REFERENCES `ClinicaAltaPerformance`.`Medico` (`idMedico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tecnico_Hospital`
    FOREIGN KEY (`idClinica`)
    REFERENCES `ClinicaAltaPerformance`.`Clinica` (`idClinica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Medico_Morada1`
    FOREIGN KEY (`morada`)
    REFERENCES `ClinicaAltaPerformance`.`Morada` (`idMorada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`TesteClinico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`TesteClinico` (
  `idTesteClinico` INT NOT NULL AUTO_INCREMENT,
  `designacao` VARCHAR(100) NOT NULL,
  `dataHora` DATETIME NOT NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `resultado` VARCHAR(100) NULL,
  `idMedico` INT NOT NULL,
  `idAtleta` INT NOT NULL,
  PRIMARY KEY (`idTesteClinico`),
  INDEX `fk_TesteClinico_Tecnico1_idx` (`idMedico` ASC) VISIBLE,
  INDEX `fk_TesteClinico_Atleta_idx` (`idAtleta` ASC) VISIBLE,
  CONSTRAINT `fk_TesteClinico_Tecnico1`
    FOREIGN KEY (`idMedico`)
    REFERENCES `ClinicaAltaPerformance`.`Medico` (`idMedico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TesteClinico_Atleta`
    FOREIGN KEY (`idAtleta`)
    REFERENCES `ClinicaAltaPerformance`.`Atleta` (`idAtleta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Prova_TipoTeste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Prova_TipoTeste` (
  `idProva` INT NOT NULL,
  `designacao` VARCHAR(100) NOT NULL,
  CONSTRAINT `fk_Prova_Teste_Prova`
    FOREIGN KEY (`idProva`)
    REFERENCES `ClinicaAltaPerformance`.`Prova` (`idProva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClinicaAltaPerformance`.`Contacto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ClinicaAltaPerformance`.`Contacto` (
  `idContacto` INT NOT NULL AUTO_INCREMENT,
  `telefone` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `idAtleta` INT NOT NULL,
  PRIMARY KEY (`idContacto`, `idAtleta`),
  INDEX `fk_Contacto_Atleta1_idx` (`idAtleta` ASC) VISIBLE,
  CONSTRAINT `fk_Contacto_Atleta1`
    FOREIGN KEY (`idAtleta`)
    REFERENCES `ClinicaAltaPerformance`.`Atleta` (`idAtleta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
