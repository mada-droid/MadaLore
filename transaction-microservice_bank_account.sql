-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: transaction-microservice
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_number` varchar(12) NOT NULL,
  `state` varchar(45) NOT NULL,
  `iban` varchar(45) NOT NULL,
  `balance` double NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_conto_UNIQUE` (`account_number`),
  UNIQUE KEY `iban_UNIQUE` (`iban`),
  KEY `FK_DETAIL_idx` (`user_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (20,'234855113116','inattivo','IT54Y6862344867234855113116',0,54),(21,'363418216817','inattivo','IT37O2242384522363418216817',0,55),(22,'245456323445','inattivo','IT56H2651172352245456323445',0,55),(23,'425172623434','inattivo','IT37X1325686535425172623434',0,57),(24,'157334814355','inattivo','IT76G5848886762157334814355',0,58),(25,'215812552818','inattivo','IT36Z1468556643215812552818',0,59),(26,'884511112222','inattivo','IT42B8233855681884511112222',0,60),(27,'812886558427','inattivo','IT25G4471686878812886558427',0,61),(28,'875147761463','inattivo','IT38C8512314263875147761463',0,63),(29,'161125558856','inattivo','IT16A3788178878161125558856',0,64),(30,'138381754621','inattivo','IT12C8426385421138381754621',0,65),(31,'112384462586','inattivo','IT28X4183415137112384462586',0,67),(32,'477374666144','inattivo','IT67U6735832748477374666144',0,68),(33,'762426237353','inattivo','IT55W8365175736762426237353',0,69),(34,'382521375214','inattivo','IT81F1182517147382521375214',0,70),(35,'252886871485','inattivo','IT24Y3748488723252886871485',0,71);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-01 10:44:16
