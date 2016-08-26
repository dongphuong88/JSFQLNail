CREATE DATABASE  IF NOT EXISTS `qlnail` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `qlnail`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: qlnail
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `service_categories`
--

DROP TABLE IF EXISTS `service_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_categories`
--

LOCK TABLES `service_categories` WRITE;
/*!40000 ALTER TABLE `service_categories` DISABLE KEYS */;
INSERT INTO `service_categories` VALUES (1,'Pedi','blue'),(2,'Mani','green'),(3,'Dex','yellow'),(4,'Ogan','black');
/*!40000 ALTER TABLE `service_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `turn` float DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `additional_info` varchar(45) DEFAULT NULL,
  `service_group_id` int(11) DEFAULT NULL,
  `supply_deduction` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Pedi Classic',NULL,NULL,NULL,NULL,NULL,1,NULL),(2,'Pedi Gel',NULL,NULL,NULL,NULL,NULL,1,NULL),(3,'Mani Classic',NULL,NULL,NULL,NULL,NULL,2,NULL),(4,'Mani Gel',NULL,NULL,NULL,NULL,NULL,2,NULL),(5,'Pedi Deluxe',NULL,NULL,NULL,NULL,NULL,3,NULL);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_availables`
--

DROP TABLE IF EXISTS `staff_availables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_availables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `startHour` datetime DEFAULT NULL,
  `endHour` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_availables`
--

LOCK TABLES `staff_availables` WRITE;
/*!40000 ALTER TABLE `staff_availables` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_availables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_refs`
--

DROP TABLE IF EXISTS `staff_refs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_refs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) DEFAULT NULL,
  `turn` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_refs`
--

LOCK TABLES `staff_refs` WRITE;
/*!40000 ALTER TABLE `staff_refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_skills`
--

DROP TABLE IF EXISTS `staff_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_skills` (
  `staff_id` int(11) NOT NULL,
  `service_group_id` int(11) NOT NULL,
  PRIMARY KEY (`staff_id`,`service_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_skills`
--

LOCK TABLES `staff_skills` WRITE;
/*!40000 ALTER TABLE `staff_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffs`
--

DROP TABLE IF EXISTS `staffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staffs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */;
INSERT INTO `staffs` VALUES (4,'Kelly',NULL,NULL,NULL),(5,'Nancy',NULL,NULL,NULL),(6,'Jenny',NULL,NULL,NULL);
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_services`
--

DROP TABLE IF EXISTS `transaction_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) DEFAULT NULL,
  `service_group` varchar(45) DEFAULT NULL,
  `service_name` varchar(45) DEFAULT NULL,
  `staff_name` varchar(45) DEFAULT NULL,
  `amount` decimal(13,2) DEFAULT NULL,
  `discount` decimal(13,2) DEFAULT NULL,
  `service_deduction` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_services`
--

LOCK TABLES `transaction_services` WRITE;
/*!40000 ALTER TABLE `transaction_services` DISABLE KEYS */;
INSERT INTO `transaction_services` VALUES (1,1,'pedi','deluxe','Kelly',100.00,20.00,1.00),(2,12,'Pedicure','Pedi Classic','Kelly',111.00,1.00,2.00),(3,13,'Pedicure','Pedi Classic','Kelly',111.00,1.00,3.00),(4,13,'Manicure','Mani Classic','Nancy',222.00,2.00,4.00);
/*!40000 ALTER TABLE `transaction_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cash` decimal(13,2) DEFAULT NULL,
  `total` decimal(13,2) DEFAULT NULL,
  `tip` decimal(13,2) DEFAULT NULL,
  `total_discount` decimal(13,2) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,1.00,1.00,1.00,1.00,'2016-12-31 16:24:00'),(2,1.00,1.00,1.00,1.00,'2016-12-30 17:11:00'),(3,1.00,1.00,1.00,1.00,'2016-12-30 17:11:00'),(4,321.00,321.00,0.00,0.00,'2016-08-25 09:49:00'),(5,321.00,321.00,0.00,0.00,'2016-08-25 09:49:00'),(6,111.00,111.00,0.00,0.00,'2016-08-25 11:01:00'),(7,76.00,76.00,0.00,12.00,'2016-08-25 15:12:00'),(8,67.00,67.00,0.00,12.00,'2016-08-25 15:14:00'),(9,98.00,98.00,0.00,1.00,'2016-08-25 15:16:00'),(10,187.00,187.00,0.00,12.00,'2016-08-25 15:18:00'),(11,108.00,108.00,0.00,2.00,'2016-08-25 15:19:00'),(12,109.00,109.00,0.00,1.00,'2016-08-25 15:20:00'),(13,328.00,328.00,0.00,2.00,'2016-08-25 15:20:00');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-25 21:10:51
