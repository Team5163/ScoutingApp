-- MySQL dump 10.13  Distrib 5.6.16, for Win32 (x86)
--
-- Host: localhost    Database: scoutdb
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Granite State District Event','NH','0000-00-00','0000-00-00'),(2,'Granite State District Event','NH','2013-08-03','2013-08-05');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logins`
--

DROP TABLE IF EXISTS `logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logins` (
  `username` varchar(25) NOT NULL,
  `passhash` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logins`
--

LOCK TABLES `logins` WRITE;
/*!40000 ALTER TABLE `logins` DISABLE KEYS */;
INSERT INTO `logins` VALUES ('a',97);
/*!40000 ALTER TABLE `logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchdata`
--

DROP TABLE IF EXISTS `matchdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchdata` (
  `eventid` int(4) DEFAULT NULL,
  `matchid` int(4) DEFAULT NULL,
  `red1` varchar(4) DEFAULT NULL,
  `red2` varchar(4) DEFAULT NULL,
  `red3` varchar(4) DEFAULT NULL,
  `blue1` varchar(4) DEFAULT NULL,
  `blue2` varchar(4) DEFAULT NULL,
  `blue3` varchar(4) DEFAULT NULL,
  `redscore` int(4) DEFAULT NULL,
  `bluescore` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchdata`
--

LOCK TABLES `matchdata` WRITE;
/*!40000 ALTER TABLE `matchdata` DISABLE KEYS */;
INSERT INTO `matchdata` VALUES (1,3,'0000','0001','0002','0003','0004','0005',52,63),(1,3,'0000','0001','0002','0003','0004','0005',52,63),(1,2,'0005','0004','0003','0002','0001','0000',52,63);
/*!40000 ALTER TABLE `matchdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teamdata`
--

DROP TABLE IF EXISTS `teamdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teamdata` (
  `teamnum` varchar(4) DEFAULT NULL,
  `teamname` varchar(30) DEFAULT NULL,
  `drivetrain` varchar(30) DEFAULT NULL,
  `speed` varchar(10) DEFAULT NULL,
  `weight` varchar(10) DEFAULT NULL,
  `stratcon` varchar(100) DEFAULT NULL,
  `othercon` varchar(100) DEFAULT NULL,
  `gencon` varchar(100) DEFAULT NULL,
  `driver` varchar(100) DEFAULT NULL,
  `mentor` varchar(100) DEFAULT NULL,
  `sponsors` varchar(300) DEFAULT NULL,
  `miscinfo` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamdata`
--

LOCK TABLES `teamdata` WRITE;
/*!40000 ALTER TABLE `teamdata` DISABLE KEYS */;
INSERT INTO `teamdata` VALUES ('0000','1','2','3','4','5','6','7','8','9','10','11'),('0001','1','2','3','4','5','6','7','8','9','10','11'),('0003','1','2','3','4','5','6','7','8','9','10','11'),('0002','1','2','3','4','5','6','7','8','9','10','11'),('0004','1','2','3','4','5','6','7','8','9','10','11'),('0005','1','2','3','4','5','6','7','8','9','10','11'),('1005','1','2','3','4','5','6','7','8','9','10','11'),('1004','1','2','3','4','5','6','7','8','9','10','11'),('1003','1','2','3','4','5','6','7','8','9','10','11'),('1002','1','2','3','4','5','6','7','8','9','10','11'),('1001','1','2','3','4','5','6','7','8','9','10','11'),('1000','1','2','3','4','5','6','7','8','9','10','11'),('3333',NULL,NULL,NULL,NULL,NULL,NULL,'John Z. Doe 555-867-5309 jzdoe@testing.edu',NULL,NULL,NULL,NULL),('5163','The Aluminum Warriors','4 motors 6 wheels','10 units','40kg','Rish Shadra','Kyle Heavey','Yiwen Dong','Rish Shadra','Mike McFarland','PTC EMC','Us!');
/*!40000 ALTER TABLE `teamdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `test` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('harha'),('harhb');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-20 15:37:50
