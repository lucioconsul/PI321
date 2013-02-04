CREATE DATABASE  IF NOT EXISTS `pi_final` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pi_final`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: pi_final
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acesso_perfil`
--

DROP TABLE IF EXISTS `acesso_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acesso_perfil` (
  `id_acesso` bigint(20) NOT NULL,
  `id_perfil` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE0447ABF35D685EE` (`id_perfil`),
  KEY `FKE0447ABF970D9125` (`id_acesso`),
  CONSTRAINT `FKE0447ABF35D685EE` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FKE0447ABF970D9125` FOREIGN KEY (`id_acesso`) REFERENCES `acessos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acesso_perfil`
--

LOCK TABLES `acesso_perfil` WRITE;
/*!40000 ALTER TABLE `acesso_perfil` DISABLE KEYS */;
INSERT INTO `acesso_perfil` VALUES (1,1,1),(2,1,2),(3,1,3);
/*!40000 ALTER TABLE `acesso_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acessos`
--

DROP TABLE IF EXISTS `acessos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acessos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pagina` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acessos`
--

LOCK TABLES `acessos` WRITE;
/*!40000 ALTER TABLE `acessos` DISABLE KEYS */;
INSERT INTO `acessos` VALUES (1,'/admin.xhtml'),(2,'/cadFuncionario.xhtml'),(3,'/logar.xhtml');
/*!40000 ALTER TABLE `acessos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colaborador`
--

DROP TABLE IF EXISTS `colaborador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colaborador` (
  `cpf` varchar(255) NOT NULL,
  `matricula` int(11) NOT NULL,
  `pis` varchar(255) NOT NULL,
  `rg` varchar(255) NOT NULL,
  `salario` float NOT NULL,
  `idPessoa` bigint(20) NOT NULL,
  `idFuncao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idPessoa`),
  KEY `FKD0E45A6A75AB52DB` (`idPessoa`),
  KEY `FKD0E45A6A55484F71` (`idFuncao`),
  CONSTRAINT `FKD0E45A6A55484F71` FOREIGN KEY (`idFuncao`) REFERENCES `funcao` (`id`),
  CONSTRAINT `FKD0E45A6A75AB52DB` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colaborador`
--

LOCK TABLES `colaborador` WRITE;
/*!40000 ALTER TABLE `colaborador` DISABLE KEYS */;
INSERT INTO `colaborador` VALUES ('05540130960',123456,'123456789','6167558',2000000,1,1),('345636',0,'345635','33567',2345,2,2),('456658',0,'345635','5678',2435,3,3),('345636',0,'37','33567',346,4,4),('1234',0,'1234','1234',1234,5,5),('1234',0,'1234','1234',1234,6,6),('456658',0,'37','33567',1234,7,7),('345636',0,'345635','33567',2345,8,8),('456658',0,'345635','5678',58,9,9),('345636',0,'345635','5678',746,10,10);
/*!40000 ALTER TABLE `colaborador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) NOT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `rua` varchar(255) NOT NULL,
  `idPessoa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6B07CBE975AB52DB` (`idPessoa`),
  CONSTRAINT `FK6B07CBE975AB52DB` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'bairro teste','88000000','cidade teste',NULL,'rua teste',1),(2,'1234','1234','1234','1234','1234',NULL),(3,'dfghfg','456746','fghjb','sdfgs','dfghdfh35',NULL),(4,'dfghfg','457457','fghjb','ergfgh','dfghdfh35',NULL),(5,'dfghfg','456746','1234','dfhghj','dfghdfh35',NULL),(6,'fgjfgh','457457','asdf','hjljdh','fbgh',10);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcao`
--

DROP TABLE IF EXISTS `funcao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao`
--

LOCK TABLES `funcao` WRITE;
/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` VALUES (1,'presidente','presidente'),(2,NULL,'dfghf'),(3,NULL,'dh'),(4,NULL,'dfghf'),(5,NULL,'asdf'),(6,NULL,'1234'),(7,NULL,'dh'),(8,NULL,'dfghf'),(9,NULL,'dfghf'),(10,NULL,'dh');
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_perfil`
--

DROP TABLE IF EXISTS `menu_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_perfil` (
  `id_menu` bigint(20) NOT NULL,
  `id_perfil` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK5BFE58AC35D685EE` (`id_perfil`),
  KEY `FK5BFE58ACE9EF0C05` (`id_menu`),
  CONSTRAINT `FK5BFE58AC35D685EE` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FK5BFE58ACE9EF0C05` FOREIGN KEY (`id_menu`) REFERENCES `menus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_perfil`
--

LOCK TABLES `menu_perfil` WRITE;
/*!40000 ALTER TABLE `menu_perfil` DISABLE KEYS */;
INSERT INTO `menu_perfil` VALUES (2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13);
/*!40000 ALTER TABLE `menu_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icone` varchar(255) DEFAULT NULL,
  `id_menu_pai` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (2,'ui-icon-pencil',NULL,'Pedido','admin.faces'),(3,'ui-icon-comment','2','Fazer Pedido','admin.faces'),(4,'ui-icon-heart','2','Cad. Cliente','admin.faces'),(5,'ui-icon-cart',NULL,'Produto',NULL),(6,'ui-icon-check','5','Pizza','admin.faces'),(7,'ui-icon-check','5','Tamanho','admin.faces'),(8,'ui-icon-check','5','Borda','admin.faces'),(9,'ui-icon-check','5','Sabores','admin.faces'),(10,'ui-icon-check','5','Bebida','admin.faces'),(11,'ui-icon-person',NULL,'Usuarios',NULL),(12,'ui-icon-document','11','Cad. Colaborador','cadFuncionario.faces'),(13,'ui-icon-wrench','11','Funções','admin.faces');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'admin'),(2,'asdf'),(3,'1234'),(4,'dfg'),(5,'dfg'),(6,'1234'),(7,'asdf');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `celular` varchar(255) NOT NULL,
  `nascimento` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'88998899','1986-11-14','Lucio Cesar M Consul','33443344'),(2,'45674','2013-02-27','fdghfh','34563456'),(3,'45674','2013-02-11','bgfj','457467'),(4,'3456','2013-02-12','sdfgcd','34563456'),(5,'1234','2013-02-12','tsete','1234'),(6,'1234','2013-02-11','1234','1234'),(7,'45674','2013-02-11','fdghfh','457467'),(8,'45674','2013-02-18','bgfj','34563456'),(9,'45674','2013-02-10','fdghfh','457467'),(10,'564567','2013-02-12','1234','34563456');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `idColaborador` bigint(20) DEFAULT NULL,
  `idPerfil` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5B4D8B0E75AA0725` (`idPerfil`),
  KEY `FK5B4D8B0EDCB561A7` (`idColaborador`),
  CONSTRAINT `FK5B4D8B0E75AA0725` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FK5B4D8B0EDCB561A7` FOREIGN KEY (`idColaborador`) REFERENCES `colaborador` (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'lucioconsul','senhateste',1,1),(2,'asdf','asdf',NULL,2),(3,'1324','1234',NULL,3),(4,'dfghe','dfhvfg',NULL,4),(5,'dfghf','1234',NULL,5),(6,'1324','dfhdf',NULL,6),(7,'asdf','1234',10,7);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-02-04  0:43:33
