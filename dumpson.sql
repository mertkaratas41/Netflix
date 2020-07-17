-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: netflixdb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `program` (
  `idprogram` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `chapterNo` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprogram`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,'Recep İvedik 6','dizi',5,120),(2,'Assassın\'s Creed','film',1,120),(3,'Alaca Karanlık','film',1,120),(4,'Yüzüklerin Efendisi İki Kule','film',1,120),(5,'Maske','film',1,120),(6,'Fantastik Canavarlar','film',1,120),(7,'Kuşlarla Dans','dizi',10,120),(8,'Mission Blue','dizi',10,120),(9,'Mercan Peşinde','film',1,120),(10,'Arif V 216','film',1,120),(11,'pk','film',1,120),(12,'Jurassıc Park','film',1,120),(13,'Frankestein ','film',1,120),(14,'Gezegenimiz','dizi',5,120),(15,'72 sevimli hayvan','dizi',72,120),(16,'Marsta Keşif','film',1,120),(17,'Pokemon','film',20,120),(18,'Şirinler','film',20,120),(19,'Charlie\'nin Çikolata Fabrikası','film',1,120),(20,'Alvin ve Sincaplar','film',1,120),(21,'Kung Fu Panda','film',1,120),(22,'Scooby Doo','film',1,120),(23,'Bizi Hatırla','film',1,120),(24,'Delibal','film',1,120),(25,'Kardeşim Benim','film',1,120),(26,'Dangal','film',1,120),(27,'Yerçekimi','film',1,120),(28,'How I met your mother','dizi',50,120),(29,'Leyla ile Mecnun','dizi',30,120),(30,'Beni Böyle Sev','dizi',40,120),(31,'Car Masters','dizi',20,120),(32,'Büyük Tasarımlar','dizi',30,90),(33,'The Big Family Cooking','dizi',50,90);
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programtype`
--

DROP TABLE IF EXISTS `programtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programtype` (
  `idprogramtype` int(11) NOT NULL AUTO_INCREMENT,
  `idprogram` int(11) DEFAULT NULL,
  `idtype` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprogramtype`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programtype`
--

LOCK TABLES `programtype` WRITE;
/*!40000 ALTER TABLE `programtype` DISABLE KEYS */;
INSERT INTO `programtype` VALUES (1,1,1),(2,2,1),(3,2,2),(4,3,1),(5,3,7),(6,4,1),(7,4,2),(8,5,1),(9,5,2),(10,6,1),(11,6,4),(12,7,5),(13,8,5),(14,9,5),(15,10,2),(16,10,6),(17,11,2),(18,11,7),(19,12,1),(20,12,2),(21,13,1),(22,13,2),(23,13,8),(24,14,5),(25,14,10),(26,15,5),(27,15,10),(28,16,5),(29,16,10),(30,17,4),(31,18,4),(32,18,6),(33,19,4),(34,19,6),(35,20,4),(36,21,1),(37,21,4),(38,22,4),(39,23,3),(40,24,3),(41,24,7),(42,25,3),(43,25,6),(44,26,3),(45,27,2),(46,27,3),(47,28,7),(48,29,7),(49,30,3),(50,30,7),(51,31,2),(52,32,2),(53,33,2);
/*!40000 ALTER TABLE `programtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `type` (
  `idtype` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Aksiyon ve Macera'),(2,'Bilim Kurgu ve Fantastik Yapımlar'),(3,'Drama'),(4,'Çocuk ve Aile'),(5,'Belgesel'),(6,'Komedi'),(7,'Romantik'),(8,'Korku'),(9,'Gerilim'),(10,'Bilim ve Doğa');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `borndate` date DEFAULT NULL,
  PRIMARY KEY (`iduser`,`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ilker','ilker','ilker',NULL),(2,'ali','ali','ali','2000-10-19'),(3,'onur','onur','onur','1990-10-10'),(4,'elif','elif','elif','1995-08-09'),(7,'selim','selim','selim','2000-10-10'),(26,'xxx','xxx','xxx','2000-05-05'),(27,'xxx','xxx','xxx','2000-05-05'),(28,'lara','lara','lara','2005-10-10'),(29,'lol','lol','lol','2000-10-10'),(30,'pol','pol','pol','2000-05-05'),(31,'peter','peter','peter','2000-05-05'),(32,'lal','lal','lal','2000-05-05'),(33,'ela','ela','ela','1999-10-10'),(34,'mert','mert','mert','2000-07-07'),(35,'eren','eren','eren','1999-05-19'),(36,'egemen','egemen','egemen','1999-11-15'),(37,'vedat','vedat','vedat','1997-10-10'),(38,'veli','veli','veli','2000-05-05'),(39,'behzat','behzat','behzat','1970-10-10');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprogram`
--

DROP TABLE IF EXISTS `userprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `userprogram` (
  `iduserprogram` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `programid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `episode` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  PRIMARY KEY (`iduserprogram`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprogram`
--

LOCK TABLES `userprogram` WRITE;
/*!40000 ALTER TABLE `userprogram` DISABLE KEYS */;
INSERT INTO `userprogram` VALUES (22,1,3,NULL,60,1,3),(23,1,5,NULL,0,1,0),(24,1,4,NULL,120,1,0),(25,1,1,NULL,120,5,0),(26,2,3,NULL,90,1,5),(27,1,9,NULL,0,1,0),(28,1,8,'2020-05-28',0,2,4),(29,1,7,NULL,0,1,0),(30,2,10,'2020-05-28',60,1,5),(31,2,18,NULL,90,1,4),(32,2,19,NULL,60,1,5),(37,35,1,'2020-05-28',30,4,4),(38,35,4,NULL,0,1,0),(39,35,5,NULL,0,1,0),(40,35,12,'2020-05-28',30,1,5),(41,36,26,'2020-05-28',90,1,5),(42,36,23,'2020-05-28',120,1,3),(43,36,30,'2020-05-29',90,1,10),(44,35,16,NULL,0,1,0),(45,35,17,NULL,0,1,0),(46,35,18,'2020-05-28',30,1,5),(47,2,2,NULL,0,1,0),(48,2,4,NULL,0,1,0),(49,2,6,NULL,0,1,0),(50,2,30,NULL,0,1,0),(51,2,29,NULL,0,1,0),(52,2,21,NULL,0,1,0),(53,2,24,NULL,0,1,0),(54,2,11,NULL,0,1,0),(55,2,28,NULL,0,1,0),(56,2,27,'2020-05-29',60,1,10),(57,39,2,NULL,0,1,0),(58,39,1,NULL,0,1,0),(59,39,28,NULL,0,1,0),(60,39,26,NULL,0,1,0),(61,36,3,NULL,0,1,0),(62,36,6,'2020-05-29',30,1,10),(63,36,21,NULL,0,1,0),(64,36,13,'2020-05-29',30,1,9),(65,36,17,NULL,0,1,0);
/*!40000 ALTER TABLE `userprogram` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-29 16:47:13
