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
USE qlnail;
--
-- Dumping data for table `color_refs`
--

LOCK TABLES `color_refs` WRITE;
/*!40000 ALTER TABLE `color_refs` DISABLE KEYS */;
INSERT INTO `color_refs` VALUES ('Aqua'),('Beige'),('Chartreuse'),('CornflowerBlue'),('Cyan'),('DarkGreen'),('FireBrick'),('Fuchsia'),('Gold'),('Lavender'),('LightGreen'),('LightSkyBlue'),('MediumOrchid'),('PaleGreen'),('SeaShell'),('Wheat'),('Yellow');
/*!40000 ALTER TABLE `color_refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_categories`
--

LOCK TABLES `service_categories` WRITE;
/*!40000 ALTER TABLE `service_categories` DISABLE KEYS */;
INSERT INTO `service_categories` VALUES (1,'Combination','Aqua'),(2,'Manicures','CornflowerBlue');
/*!40000 ALTER TABLE `service_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Classic mani &pedi',50.00,'01:10:00',1.5,'','',1,2.00),(2,'Gel mani with removal & Classic pedi',70.00,'01:20:00',2,'','',1,3.00),(3,'Classic M+ spa P',64.00,'01:15:00',1.5,'','',1,3.00),(4,'Gel M+ Gel P',68.00,'01:30:00',2,'','',1,4.00),(5,'Classic M',16.00,'00:30:00',0.5,'','',2,0.00),(6,'Gel M with removal',35.00,'00:55:00',1,'','',2,1.00),(7,'Gel mani without removal',33.00,'00:45:00',1,'','',2,1.00),(8,'Signature organic spa',30.00,'00:00:00',1,'','',2,1.00);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop_hours`
--

LOCK TABLES `shop_hours` WRITE;
/*!40000 ALTER TABLE `shop_hours` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop_info`
--

LOCK TABLES `shop_info` WRITE;
/*!40000 ALTER TABLE `shop_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff_availables`
--

LOCK TABLES `staff_availables` WRITE;
/*!40000 ALTER TABLE `staff_availables` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_availables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff_refs`
--

LOCK TABLES `staff_refs` WRITE;
/*!40000 ALTER TABLE `staff_refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff_skills`
--

LOCK TABLES `staff_skills` WRITE;
/*!40000 ALTER TABLE `staff_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */;
INSERT INTO `staffs` VALUES (1,'Kelly','',NULL,'6177842360',0,0,'Manager');
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction_services`
--

LOCK TABLES `transaction_services` WRITE;
/*!40000 ALTER TABLE `transaction_services` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
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

-- Dump completed on 2016-09-05 18:18:57