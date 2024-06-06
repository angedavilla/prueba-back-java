-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `price` double NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (21,16,11,51,202,NULL,NULL,'newuser','2024-06-02 23:12:54.180349'),(22,16,2,3,60,NULL,NULL,NULL,NULL),(23,17,3,1,150,NULL,NULL,'newuser','2024-06-03 23:35:07.383383'),(24,17,4,1,40,NULL,NULL,NULL,NULL),(25,18,5,2,100,NULL,NULL,NULL,NULL),(26,18,1,1,10,NULL,NULL,NULL,NULL),(27,19,2,4,80,NULL,NULL,NULL,NULL),(28,19,3,3,90,NULL,NULL,NULL,NULL),(29,20,4,2,80,NULL,NULL,NULL,NULL),(30,16,5,1,50,NULL,NULL,NULL,NULL),(32,34,1,2,20,NULL,NULL,NULL,NULL),(33,36,1,2,20,NULL,NULL,NULL,NULL),(37,56,1,2,20,NULL,NULL,NULL,NULL),(38,56,1,2,20,'newuser','2024-06-01 12:22:28.792466','newuser','2024-06-01 12:22:28.792466'),(39,16,1,5,2022,'newuser','2024-06-01 12:25:37.017996','newuser','2024-06-02 14:28:37.509096'),(40,56,1,12,12,'newuser','2024-06-02 21:52:48.816489','newuser','2024-06-02 21:52:48.816489'),(41,56,5,123,12,'newuser','2024-06-02 22:51:09.997339','newuser','2024-06-02 22:51:09.997339'),(42,45,9,12,12,'newuser','2024-06-02 23:09:14.039785','newuser','2024-06-02 23:09:14.039785'),(43,46,10,123,123,'newuser','2024-06-02 23:38:10.087363','newuser','2024-06-02 23:38:10.087363'),(44,50,4,34,534,'newuser','2024-06-03 18:37:04.970970','newuser','2024-06-03 18:37:43.297812'),(45,78,11,12,1123,'newuser','2024-06-03 18:44:27.516297','newuser','2024-06-03 18:48:42.814817'),(46,17,3,12,12,'newuser','2024-06-03 22:10:30.415217','newuser','2024-06-03 22:10:30.415217'),(47,85,5,1000,10,'newuser','2024-06-03 23:33:53.377035','newuser','2024-06-03 23:33:53.377035'),(48,45,5,10,50,'newuser','2024-06-03 23:34:38.363515','newuser','2024-06-03 23:34:38.363515');
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-06  9:50:25
