CREATE DATABASE  IF NOT EXISTS `pi_final_teste` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pi_final_teste`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: pi_final_teste
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
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id_acesso`,`id`),
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
-- Table structure for table `bebida`
--

DROP TABLE IF EXISTS `bebida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bebida` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `precoVenda` float NOT NULL,
  `volume` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebida`
--

LOCK TABLES `bebida` WRITE;
/*!40000 ALTER TABLE `bebida` DISABLE KEYS */;
INSERT INTO `bebida` VALUES (1,'Coca Cola Ltda','Fanta',2.5,'350');
/*!40000 ALTER TABLE `bebida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bebida_pedido`
--

DROP TABLE IF EXISTS `bebida_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bebida_pedido` (
  `id_pedido` bigint(20) NOT NULL,
  `id_bebida` bigint(20) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id_pedido`,`id`),
  KEY `FK364500BD35C9E0E0` (`id_pedido`),
  KEY `FK364500BD6005DE4` (`id_bebida`),
  CONSTRAINT `FK364500BD35C9E0E0` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FK364500BD6005DE4` FOREIGN KEY (`id_bebida`) REFERENCES `bebida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebida_pedido`
--

LOCK TABLES `bebida_pedido` WRITE;
/*!40000 ALTER TABLE `bebida_pedido` DISABLE KEYS */;
INSERT INTO `bebida_pedido` VALUES (2,1,0),(3,1,0),(4,1,0),(5,1,0),(6,1,0),(7,1,0);
/*!40000 ALTER TABLE `bebida_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borda`
--

DROP TABLE IF EXISTS `borda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `valor` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borda`
--

LOCK TABLES `borda` WRITE;
/*!40000 ALTER TABLE `borda` DISABLE KEYS */;
INSERT INTO `borda` VALUES (1,'Delicia hein','Cheddar',5),(2,'blabla','Catupery',4),(3,'blalbalbal','mista',3);
/*!40000 ALTER TABLE `borda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idPessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`idPessoa`),
  KEY `FK96841DDA75AB52DB` (`idPessoa`),
  CONSTRAINT `FK96841DDA75AB52DB` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1),(16),(17),(18),(19);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
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
INSERT INTO `colaborador` VALUES ('05540130960',123456,'123456789','6167558',2000000,1,1),('345636',0,'345635','5678',746,10,10),('6',0,'6','6',6,14,11),('61',0,'61','61',61,15,11);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'bairro teste','88000000','cidade teste',NULL,'rua teste',1),(6,'fgjfgh','457457','asdf','hjljdh','fbgh',10),(10,'6','6','6','6','6',14),(11,'61','61','61','61','61',15),(12,'cliente teste','','cliente teste','','cliente teste',NULL),(13,'cliente teste2','','cliente teste2','','cliente teste2',NULL),(14,'cliente teste3','','cliente teste3','','cliente teste3',18),(15,'cliente teste3','','cliente teste3','','cliente teste3',18),(17,'cliente teste 5','','cliente teste 5','','cliente teste 5',19);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoquebebida`
--

DROP TABLE IF EXISTS `estoquebebida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoquebebida` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qtd` int(11) NOT NULL,
  `bebida_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK62E83A3FA986F0C` (`bebida_id`),
  CONSTRAINT `FK62E83A3FA986F0C` FOREIGN KEY (`bebida_id`) REFERENCES `bebida` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoquebebida`
--

LOCK TABLES `estoquebebida` WRITE;
/*!40000 ALTER TABLE `estoquebebida` DISABLE KEYS */;
INSERT INTO `estoquebebida` VALUES (1,0,1);
/*!40000 ALTER TABLE `estoquebebida` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao`
--

LOCK TABLES `funcao` WRITE;
/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` VALUES (1,'presidente','presidente'),(10,NULL,'dh'),(11,'Faz os rangos','Cozinheiro'),(12,'kasjdhfi','ajdhfkadfh');
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
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id_perfil`,`id`),
  KEY `FK5BFE58AC35D685EE` (`id_perfil`),
  KEY `FK5BFE58ACE9EF0C05` (`id_menu`),
  CONSTRAINT `FK5BFE58AC35D685EE` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FK5BFE58ACE9EF0C05` FOREIGN KEY (`id_menu`) REFERENCES `menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `label` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (2,'ui-icon-pencil',NULL,'Pedido','admin.faces'),(3,'ui-icon-comment','2','Fazer Pedido','cadPedido.faces'),(4,'ui-icon-heart','2','Cliente','admin.faces'),(5,'ui-icon-cart',NULL,'Produto',NULL),(6,'ui-icon-check','5','Pizza','admin.faces'),(7,'ui-icon-check','5','Tamanho','cad_tamanho.faces'),(8,'ui-icon-check','5','Borda','borda_cad.faces'),(9,'ui-icon-check','5','Sabores','cad_sabor.faces'),(10,'ui-icon-check','5','Bebida','admin.faces'),(11,'ui-icon-person',NULL,'Usuarios',NULL),(12,'ui-icon-document','11','Colaborador','cadFuncionario.faces'),(13,'ui-icon-wrench','11','Funções','cadFuncao.faces');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `delivery` bit(1) NOT NULL,
  `dia` date NOT NULL,
  `hora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mesa` varchar(255) NOT NULL,
  `atendente_idPessoa` bigint(20) DEFAULT NULL,
  `cliente_idPessoa` bigint(20) DEFAULT NULL,
  `endereco_id` bigint(20) DEFAULT NULL,
  `entregador_idPessoa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E4203655136554A` (`entregador_idPessoa`),
  KEY `FK8E420365A719334C` (`endereco_id`),
  KEY `FK8E42036577EE16AF` (`cliente_idPessoa`),
  KEY `FK8E420365C803B297` (`atendente_idPessoa`),
  CONSTRAINT `FK8E4203655136554A` FOREIGN KEY (`entregador_idPessoa`) REFERENCES `colaborador` (`idPessoa`),
  CONSTRAINT `FK8E42036577EE16AF` FOREIGN KEY (`cliente_idPessoa`) REFERENCES `cliente` (`idPessoa`),
  CONSTRAINT `FK8E420365A719334C` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`),
  CONSTRAINT `FK8E420365C803B297` FOREIGN KEY (`atendente_idPessoa`) REFERENCES `colaborador` (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2,'','2013-02-21','2013-02-21 03:00:00','0',1,1,NULL,1),(3,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(4,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(5,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(6,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(7,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(8,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(9,'','2013-02-21','2013-02-21 03:00:00','0',1,1,1,NULL),(10,'','2013-02-21','2013-02-21 05:40:58','0',1,1,1,NULL),(13,'','2013-02-21','2013-02-21 06:56:42','0',1,16,12,NULL),(14,'','2013-02-21','2013-02-21 07:01:52','0',1,17,13,NULL),(15,'','2013-02-21','2013-02-21 07:05:23','0',1,18,15,NULL),(16,'','2013-02-21','2013-02-21 07:08:57','0',1,19,17,NULL);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'admin'),(7,'asdf'),(11,'6');
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
  `celular` varchar(255) DEFAULT NULL,
  `nascimento` date DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'88998899','1986-11-14','Lucio Cesar M Consul','33443344'),(10,'564567','2013-02-12','1234','34563456'),(14,'6','2013-02-06','6','6'),(15,'61','2013-02-06','61','61'),(16,NULL,NULL,'Cliente teste','2456346'),(17,NULL,NULL,'cliente teste2','5656365'),(18,NULL,NULL,'cliente teste3','3563456'),(19,NULL,NULL,'cliente teste 5','876543');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Excecoes` varchar(255) NOT NULL,
  `idBorda` bigint(20) DEFAULT NULL,
  `idPedido` bigint(20) DEFAULT NULL,
  `sabor1_id` bigint(20) DEFAULT NULL,
  `sabor2_id` bigint(20) DEFAULT NULL,
  `sabor3_id` bigint(20) DEFAULT NULL,
  `idTamanho` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK498EC6882BBFD44` (`sabor2_id`),
  KEY `FK498EC68D890A13B` (`idTamanho`),
  KEY `FK498EC6882BB88E5` (`sabor1_id`),
  KEY `FK498EC68E98400D7` (`idBorda`),
  KEY `FK498EC6882BC71A3` (`sabor3_id`),
  KEY `FK498EC68759D6217` (`idPedido`),
  CONSTRAINT `FK498EC68759D6217` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FK498EC6882BB88E5` FOREIGN KEY (`sabor1_id`) REFERENCES `sabor` (`id`),
  CONSTRAINT `FK498EC6882BBFD44` FOREIGN KEY (`sabor2_id`) REFERENCES `sabor` (`id`),
  CONSTRAINT `FK498EC6882BC71A3` FOREIGN KEY (`sabor3_id`) REFERENCES `sabor` (`id`),
  CONSTRAINT `FK498EC68D890A13B` FOREIGN KEY (`idTamanho`) REFERENCES `tamanho` (`id`),
  CONSTRAINT `FK498EC68E98400D7` FOREIGN KEY (`idBorda`) REFERENCES `borda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,'',1,5,2,NULL,NULL,1),(2,'',NULL,6,1,NULL,NULL,1),(3,'',NULL,7,2,NULL,NULL,1),(4,'dfgsdfg',NULL,8,2,NULL,NULL,1),(5,'asdfad',NULL,9,1,NULL,NULL,1),(6,'adfasd',NULL,10,3,NULL,NULL,1),(8,'',NULL,13,2,NULL,NULL,1),(9,'cliente teste 2',NULL,14,2,3,1,1),(10,'cliente teste3',NULL,15,1,NULL,NULL,1),(11,'cliente teste 5',2,16,1,3,NULL,2);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sabor`
--

DROP TABLE IF EXISTS `sabor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sabor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `valorG` float NOT NULL,
  `valorM` float NOT NULL,
  `valorP` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sabor`
--

LOCK TABLES `sabor` WRITE;
/*!40000 ALTER TABLE `sabor` DISABLE KEYS */;
INSERT INTO `sabor` VALUES (1,'mussarela, parmesao, prato e catupiry','Quatro queijos',28,22,18),(2,'blablabla','Calabresa',28,24,20),(3,'adsf asdf ','asdf asdf ',25,20,15);
/*!40000 ALTER TABLE `sabor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tamanho`
--

DROP TABLE IF EXISTS `tamanho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tamanho` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diametro` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamanho`
--

LOCK TABLES `tamanho` WRITE;
/*!40000 ALTER TABLE `tamanho` DISABLE KEYS */;
INSERT INTO `tamanho` VALUES (1,'32','Grande'),(2,'28','Media'),(3,'18','pequena');
/*!40000 ALTER TABLE `tamanho` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'lucioconsul','senhateste',1,1),(7,'asdf','1234',10,7),(11,'6','6',14,11),(12,'61','61',15,7);
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

-- Dump completed on 2013-02-21 21:44:56
