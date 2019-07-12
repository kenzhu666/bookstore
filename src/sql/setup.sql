CREATE DATABASE  IF NOT EXISTS `bis` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bis`;
-- MySQL dump 10.13  Distrib 5.6.43, for Win64 (x86_64)
--
-- Host: mydb.c9wjiexpqwps.us-east-1.rds.amazonaws.com    Database: bis
-- ------------------------------------------------------
-- Server version	5.6.40-log

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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `city` varchar(20) NOT NULL,
  `province` varchar(2) NOT NULL,
  `zip` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `province` (`province`),
  CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`province`) REFERENCES `Province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,'123 Yonge St','Toronto','ON','K1E 6T5'),(2,'445 Avenue rd','Toronto','ON','M1C 6K5'),(3,'789 Keele St.','Toronto','ON','K3C 9T5'),(4,'100 Bayview St','Toronto','ON','M1M 2N2');
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `bid` varchar(20) NOT NULL,
  `title` varchar(60) NOT NULL,
  `price` int(11) NOT NULL,
  `category` enum('Science','Fiction','Engineering') NOT NULL,
  `author` varchar(20) NOT NULL,
  `release` timestamp NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES ('b001','The Tattooist of Auschwitz',15,'Fiction','Heather Morris','2018-09-04 00:00:00','The Tattooist of Auschwitz is an extraordinary document, a story about the extremes of human behavior existing side by side: calculated brutality alongside impulsive and selfless acts of love. I find it hard to imagine anyone who would not be drawn in, confronted and moved.','https://images-na.ssl-images-amazon.com/images/I/71NU5B1A6JL.jpg'),('b002','Bad Science',49,'Science','Ben Goldacre','2008-01-22 00:00:00','Full of spleen, this is a hilarious, invigorating and informative journey through the world of Bad Science. When Dr Ben Goldacre saw someone on daytime TV dipping her feet in an \'Aqua Detox\' footbath, releasing her toxins into the water, turning it brown, he thought he\'d try the same at home.','https://images.gr-assets.com/books/1327251503l/3272165.jpg'),('b003','Inner Engineering',100,'Engineering','Sadhguru','2016-09-20 00:00:00','NEW YORK TIMES BESTSELLER • Thought leader, visionary, philanthropist, mystic, and yogi Sadhguru presents Western readers with a time-tested path to achieving absolute well-being: the classical science of yoga.','https://images.gr-assets.com/books/1484851488l/29513878.jpg'),('b004','The Rhetoric of Fiction',25,'Fiction','Wayne C. Booth','1983-02-15 00:00:00','The first edition of The Rhetoric of Fiction transformed the criticism of fiction and soon became a classic in the field. One of the most widely used texts in fiction courses, it is a standard reference point in advanced discussions of how fictional form works, how authors make novels accessible','https://images.gr-assets.com/books/1348370698l/436108.jpg'),('b005','Trigger Warning: Short Fictions and Disturbances',30,'Fiction','Neil Gaiman','2015-02-03 00:00:00','Multiple award winning, #1 New York Times bestselling author Neil Gaiman returns to dazzle, captivate, haunt, and entertain with this third collection of short fiction following Smoke and Mirrors and Fragile Things--which includes a never-before published American Gods story, \"Black Dog,\".','https://images.gr-assets.com/books/1415036119l/22522808.jpg'),('b006','Non-Fiction',20,'Fiction','Chuck Palahniuk','2004-09-01 00:00:00','Chuck Palahniuk\'s world has been, well, different from yours and mine. The pieces that comprise Non-Fiction prove just how different, in ways both highly entertaining and deeply unsettling. ','https://images.gr-assets.com/books/1427753923l/24820339.jpg'),('b007','Animal Farm',39,'Science','George Orwell','1996-02-18 00:00:00','Reproducible Series for teachers instructing George Orwell\'s classic Animal Farm.','https://images.gr-assets.com/books/1500068900l/35677367.jpg'),('b008','This Is Your Brain on Music',67,'Science','Daniel J. Levitin','2006-08-03 00:00:00','hether you load your iPod with Bach or Bono, music has a significant role in your life—even if you never realized it. Why does music evoke such powerful moods? The answers are at last be- coming clear, thanks to revolutionary neuroscience and the emerging field of evolutionary psychology.','https://images.gr-assets.com/books/1386922939l/141565.jpg'),('b009','Galileo\'s Daughter',99,'Science','Dava Sobel','2000-11-01 00:00:00','Dramatically recolors the personality and accomplishment of a mythic figure whose seventeenth-century clash with Catholic doctrine continues to define the schism between science and religion.','https://images.gr-assets.com/books/1414607526l/18646.jpg'),('b010','Engineering Infinity',28,'Engineering','Jonathan Strahan','2011-01-06 00:00:00','This science-fiction anthology collects together stories by some of the biggest names in the field including Stephen Baxter, Charles Stross and Greg Bear.','https://images.gr-assets.com/books/1331406766l/8483622.jpg'),('b011','Engineering Physics',97,'Engineering','S.L. Gupta','1993-03-01 00:00:00','Mumbai University Textbook for Engineering Physics','https://images.gr-assets.com/books/1339360957l/15700151.jpg'),('b012','Site Reliability Engineering',107,'Engineering','Betsy Beyer','2016-04-16 00:00:00','The overwhelming majority of a software system\'s lifespan is spent in use, not in design or implementation. So, why does conventional wisdom insist that software engineers focus primarily on the design and development of large-scale computing systems?','https://images.gr-assets.com/books/1459115220l/27968891.jpg');
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PO`
--

DROP TABLE IF EXISTS `PO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PO` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UID` int(10) unsigned NOT NULL,
  `status` enum('ORDERED','PROCESSED','DENIED') NOT NULL,
  `shipName` varchar(45) NOT NULL,
  `shipTel` varchar(20) NOT NULL,
  `shipAddress` int(10) NOT NULL,
  `billName` varchar(45) NOT NULL,
  `billTel` varchar(20) NOT NULL,
  `billAddress` int(10) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `UID` (`UID`),
  CONSTRAINT `PO_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `USER` (`UID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PO`
--

LOCK TABLES `PO` WRITE;
/*!40000 ALTER TABLE `PO` DISABLE KEYS */;
INSERT INTO `PO` VALUES (1,1,'PROCESSED','John White','416-123-4567',1,'John White','416-123-4567',1,'2019-02-10 01:23:34'),(2,2,'DENIED','Peter Black','647-987-4321',2,'Peter Black','647-987-4321',2,'2019-02-14 04:32:19'),(3,3,'ORDERED','Andy Green','647-123-6789',3,'Andy Green','647-123-6789',3,'2019-03-15 15:40:12'),(4,3,'PROCESSED','Andy Green','647-123-6789',3,'Andy Green','647-123-6789',3,'2019-03-17 02:48:08'),(5,1,'PROCESSED','John White','416-123-4567',1,'John White','416-123-4567',1,'2019-04-14 02:49:31'),(6,2,'DENIED','Peter Black','647-987-4321',2,'Peter Black','647-987-4321',2,'2019-04-14 02:54:17'),(7,2,'PROCESSED','Peter Black','647-987-4321',2,'Peter Black','647-987-4321',2,'2019-04-14 07:23:17'),(8,1,'PROCESSED','John White','416-123-4567',1,'John White','416-123-4567',1,'2019-04-14 07:29:13'),(9,4,'DENIED','LL LL','419-283-9394',4,'LL LL','419-283-9394',4,'2019-04-14 07:47:56');
/*!40000 ALTER TABLE `PO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POItem`
--

DROP TABLE IF EXISTS `POItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POItem` (
  `id` int(10) unsigned NOT NULL,
  `bid` varchar(20) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`bid`),
  KEY `id` (`id`),
  KEY `bid` (`bid`),
  CONSTRAINT `POItem_ibfk_1` FOREIGN KEY (`id`) REFERENCES `PO` (`id`) ON DELETE CASCADE,
  CONSTRAINT `POItem_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `Book` (`bid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POItem`
--

LOCK TABLES `POItem` WRITE;
/*!40000 ALTER TABLE `POItem` DISABLE KEYS */;
INSERT INTO `POItem` VALUES (1,'b001',2),(2,'b002',3),(3,'b003',1),(4,'b004',3),(4,'b007',2),(4,'b008',4),(5,'b001',3),(5,'b002',2),(6,'b003',5),(6,'b004',2),(7,'b003',1),(7,'b007',3),(8,'b005',1),(8,'b011',2),(9,'b006',10);
/*!40000 ALTER TABLE `POItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Province`
--

DROP TABLE IF EXISTS `Province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Province` (
  `id` varchar(2) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Province`
--

LOCK TABLES `Province` WRITE;
/*!40000 ALTER TABLE `Province` DISABLE KEYS */;
INSERT INTO `Province` VALUES ('AB','Alberta'),('BC','British Columbia'),('MB','Manitoba'),('NB','New Brunswick'),('NL','Newfoundland and Labrador'),('NS','Nova Scotia'),('NT','Northwest Territories'),('NU','Nunavut'),('ON','Ontario'),('PE','Prince Edward Island'),('QC','Quebec'),('SK','Saskatchewan'),('YT','Yukon');
/*!40000 ALTER TABLE `Province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Review`
--

DROP TABLE IF EXISTS `Review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Review` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bid` varchar(20) NOT NULL,
  `UID` int(10) unsigned NOT NULL,
  `DAY` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rate` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`bid`),
  KEY `UID` (`UID`),
  CONSTRAINT `Review_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `Book` (`bid`) ON DELETE CASCADE,
  CONSTRAINT `Review_ibfk_2` FOREIGN KEY (`UID`) REFERENCES `USER` (`UID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Review`
--

LOCK TABLES `Review` WRITE;
/*!40000 ALTER TABLE `Review` DISABLE KEYS */;
INSERT INTO `Review` VALUES (3,'b002',1,'2008-01-02 01:23:34',3,'This book is just so-so.'),(4,'b003',3,'2008-01-03 15:43:21',1,'This book is so bad.'),(5,'b002',3,'2019-04-14 06:42:35',4,'Ahh, it\'s okay!'),(6,'b007',4,'2019-04-14 06:42:35',4,'I love this book.');
/*!40000 ALTER TABLE `Review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `UID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `address` int(10) unsigned NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `TYPE` enum('VISITOR','CUSTOMER','ADMIN','PARTNER') NOT NULL,
  PRIMARY KEY (`UID`),
  KEY `address` (`address`),
  CONSTRAINT `USER_ibfk_1` FOREIGN KEY (`address`) REFERENCES `Address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'jWhite','4413','John','White',1,'416-123-4567','CUSTOMER'),(2,'pBlack','4413','Peter','Black',2,'647-987-4321','ADMIN'),(3,'aGreen','4413','Andy','Green',3,'647-123-6789','PARTNER'),(4,'ll','ll','LL','LL',4,'419-283-9394','CUSTOMER');
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VisitEvent`
--

DROP TABLE IF EXISTS `VisitEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VisitEvent` (
  `DAY` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bid` varchar(20) NOT NULL,
  `eventtype` enum('VIEW','CART','PURCHASE') NOT NULL,
  KEY `bid` (`bid`),
  CONSTRAINT `VisitEvent_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `Book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VisitEvent`
--

LOCK TABLES `VisitEvent` WRITE;
/*!40000 ALTER TABLE `VisitEvent` DISABLE KEYS */;
INSERT INTO `VisitEvent` VALUES ('2019-03-24 11:39:37','b001','VIEW'),('2019-03-24 11:39:37','b001','CART'),('2019-03-24 11:39:37','b001','PURCHASE');
/*!40000 ALTER TABLE `VisitEvent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-14  8:30:39
