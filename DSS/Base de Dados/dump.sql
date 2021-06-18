-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: MC
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Administrador`
--

DROP TABLE IF EXISTS `Administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Administrador` (
  `idAdministrador` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`idAdministrador`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
INSERT INTO `Administrador` VALUES (1110,'admin@gmail.com','Admin','password');
/*!40000 ALTER TABLE `Administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoriaMusica`
--

DROP TABLE IF EXISTS `CategoriaMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategoriaMusica` (
  `idMusica` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  KEY `fk_CategoriaMusica_UtilizadorRegistado1_idx` (`idUtilizador`),
  KEY `fk_CategoriaMusica_Musica1` (`idMusica`),
  CONSTRAINT `fk_CategoriaMusica_Musica1` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`),
  CONSTRAINT `fk_CategoriaMusica_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoriaMusica`
--

LOCK TABLES `CategoriaMusica` WRITE;
/*!40000 ALTER TABLE `CategoriaMusica` DISABLE KEYS */;
INSERT INTO `CategoriaMusica` VALUES (1157766532,2222,'Acid Jazz'),(1157766532,2220,'Acid Jazz'),(1157766532,2222,'Acid Jazz'),(1157766532,2220,'Acid Jazz'),(1157766532,2221,'Acid Jazz'),(1157766532,2222,'Acid Jazz');
/*!40000 ALTER TABLE `CategoriaMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoriaVideo`
--

DROP TABLE IF EXISTS `CategoriaVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategoriaVideo` (
  `idVideo` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  KEY `fk_CategoriaVideo_UtilizadorRegistado1_idx` (`idUtilizador`),
  KEY `fk_CategoriaVideo_Video1` (`idVideo`),
  CONSTRAINT `fk_CategoriaVideo_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`),
  CONSTRAINT `fk_CategoriaVideo_Video1` FOREIGN KEY (`idVideo`) REFERENCES `video` (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoriaVideo`
--

LOCK TABLES `CategoriaVideo` WRITE;
/*!40000 ALTER TABLE `CategoriaVideo` DISABLE KEYS */;
/*!40000 ALTER TABLE `CategoriaVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Musica`
--

DROP TABLE IF EXISTS `Musica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Musica` (
  `idMusica` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `duracao` double NOT NULL,
  `formato` varchar(5) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `artista` varchar(45) NOT NULL,
  PRIMARY KEY (`idMusica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Musica`
--

LOCK TABLES `Musica` WRITE;
/*!40000 ALTER TABLE `Musica` DISABLE KEYS */;
INSERT INTO `Musica` VALUES (-1309349282,'Dont Start Now',185662.546875,'mp3','Disco;Pop','Dua Lipa'),(-950332797,'Malibu',203166.296875,'mp3','Pop','Miley Cyrus'),(-936513553,'You Need To Calm Down',210408,'mp3','Pop','Taylor Swift'),(1157766532,'Acid Jazz',365219.65625,'mp3','Acid Jazz','Herr Krank');
/*!40000 ALTER TABLE `Musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlaylistMusica`
--

DROP TABLE IF EXISTS `PlaylistMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PlaylistMusica` (
  `idPlaylist` int(11) NOT NULL,
  `nomePlaylist` varchar(45) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `idMusica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaylistMusica`
--

LOCK TABLES `PlaylistMusica` WRITE;
/*!40000 ALTER TABLE `PlaylistMusica` DISABLE KEYS */;
INSERT INTO `PlaylistMusica` VALUES (300,'Biblioteca Pessoal',2220,-936513553),(300,'Biblioteca Pessoal',2220,1157766532),(301,'Biblioteca Pessoal',2221,-936513553),(301,'Biblioteca Pessoal',2221,-1309349282),(301,'Biblioteca Pessoal',2221,-950332797),(301,'Biblioteca Pessoal',2221,1157766532),(302,'Biblioteca Pessoal',2222,-936513553),(302,'Biblioteca Pessoal',2222,1157766532);
/*!40000 ALTER TABLE `PlaylistMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlaylistVideo`
--

DROP TABLE IF EXISTS `PlaylistVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PlaylistVideo` (
  `idPlaylist` int(11) NOT NULL,
  `nomePlaylist` varchar(45) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `idVideo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaylistVideo`
--

LOCK TABLES `PlaylistVideo` WRITE;
/*!40000 ALTER TABLE `PlaylistVideo` DISABLE KEYS */;
INSERT INTO `PlaylistVideo` VALUES (402,'Biblioteca Pessoal',2222,-994512811),(402,'Biblioteca Pessoal',2222,1375141236),(402,'Biblioteca Pessoal',2222,-1251370356),(401,'Biblioteca Pessoal',2221,-994512811),(401,'Biblioteca Pessoal',2221,1375141236),(401,'Biblioteca Pessoal',2221,-1251370356),(400,'Biblioteca Pessoal',2220,-994512811),(400,'Biblioteca Pessoal',2220,-1547848440),(400,'Biblioteca Pessoal',2220,-1251370356);
/*!40000 ALTER TABLE `PlaylistVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProprietariosMusica`
--

DROP TABLE IF EXISTS `ProprietariosMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProprietariosMusica` (
  `idMusica` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  PRIMARY KEY (`idMusica`,`idUtilizador`),
  KEY `fk_ProprietariosMusica_UtilizadorRegistado1_idx` (`idUtilizador`),
  CONSTRAINT `fk_ProprietariosMusica_Musica1` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`),
  CONSTRAINT `fk_ProprietariosMusica_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProprietariosMusica`
--

LOCK TABLES `ProprietariosMusica` WRITE;
/*!40000 ALTER TABLE `ProprietariosMusica` DISABLE KEYS */;
INSERT INTO `ProprietariosMusica` VALUES (-936513553,2220),(1157766532,2220),(-1309349282,2221),(-950332797,2221),(-936513553,2221),(1157766532,2221),(-936513553,2222),(1157766532,2222);
/*!40000 ALTER TABLE `ProprietariosMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProprietariosVideo`
--

DROP TABLE IF EXISTS `ProprietariosVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProprietariosVideo` (
  `idVideo` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  PRIMARY KEY (`idVideo`,`idUtilizador`),
  KEY `fk_ProprietariosVideo_Video1_idx` (`idVideo`),
  KEY `fk_ProprietariosVideo_UtilizadorRegistado1` (`idUtilizador`),
  CONSTRAINT `fk_ProprietariosVideo_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`),
  CONSTRAINT `fk_ProprietariosVideo_Video1` FOREIGN KEY (`idVideo`) REFERENCES `video` (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProprietariosVideo`
--

LOCK TABLES `ProprietariosVideo` WRITE;
/*!40000 ALTER TABLE `ProprietariosVideo` DISABLE KEYS */;
INSERT INTO `ProprietariosVideo` VALUES (-1547848440,2220),(-1251370356,2220),(-1251370356,2221),(-1251370356,2222),(-994512811,2220),(-994512811,2221),(-994512811,2222),(1375141236,2221),(1375141236,2222);
/*!40000 ALTER TABLE `ProprietariosVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UtilizadorRegistado`
--

DROP TABLE IF EXISTS `UtilizadorRegistado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UtilizadorRegistado` (
  `idUtilizador` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `password` varchar(10) NOT NULL,
  `idBibliotecaMusica` int(11) NOT NULL,
  `idBibliotecaVideo` int(11) NOT NULL,
  PRIMARY KEY (`idUtilizador`),
  UNIQUE KEY `idUtilizador_UNIQUE` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UtilizadorRegistado`
--

LOCK TABLES `UtilizadorRegistado` WRITE;
/*!40000 ALTER TABLE `UtilizadorRegistado` DISABLE KEYS */;
INSERT INTO `UtilizadorRegistado` VALUES (2220,'maria@gmail.com','Maria','password',300,400),(2221,'hugo@gmail.com','Hugo','password',301,401),(2222,'susana@gmail.com','Susana','password',302,402);
/*!40000 ALTER TABLE `UtilizadorRegistado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Video`
--

DROP TABLE IF EXISTS `Video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Video` (
  `idVideo` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `duracao` double NOT NULL,
  `formato` varchar(5) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `realizador` varchar(45) NOT NULL,
  PRIMARY KEY (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Video`
--

LOCK TABLES `Video` WRITE;
/*!40000 ALTER TABLE `Video` DISABLE KEYS */;
INSERT INTO `Video` VALUES (-1547848440,'Dont Start Now',261.09,'mp4','Disco;Pop','Dua Lipa'),(-1251370356,'Graveyard',235.47,'mp4','Pop','Halsey'),(-994512811,'Radioactive',214.6,'mp4','Rock','Imagine Dragons'),(1375141236,'My Songs Know What You Did in the Dark',187.76,'mp4','Rock','Fall Out Boy');
/*!40000 ALTER TABLE `Video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

select * from Musica;

-- Dump completed on 2019-12-21 20:00:49
