CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce`;
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
-- Table structure for table `audit_log`
--

DROP TABLE IF EXISTS `audit_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `entity_name` varchar(255) DEFAULT NULL,
  `new_value` text,
  `performed_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=499 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_log`
--

LOCK TABLES `audit_log` WRITE;
/*!40000 ALTER TABLE `audit_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1,355,NULL,NULL,'newuser','2024-06-01 14:10:45.918994'),(2,2,200,NULL,NULL,NULL,NULL),(3,3,142,NULL,NULL,NULL,NULL),(4,4,266,NULL,NULL,NULL,NULL),(5,5,262,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `total` double NOT NULL,
  `discount` double DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (16,7,10100,10,'2024-05-29 16:19:40','2024-05-30 02:46:51',NULL,NULL,NULL,NULL),(17,2,200,20,'2024-05-29 16:19:40','2024-05-29 16:19:40',NULL,NULL,NULL,NULL),(18,7,150,15,'2024-05-29 16:19:40','2024-05-29 16:19:40',NULL,NULL,NULL,NULL),(19,2,300,30,'2024-05-29 16:19:40','2024-05-29 16:19:40',NULL,NULL,NULL,NULL),(20,5,250,25,'2024-05-29 16:19:40','2024-05-29 16:19:40',NULL,NULL,NULL,NULL),(34,7,40,10,'2024-05-29 22:10:03','2024-05-29 22:10:03',NULL,NULL,NULL,NULL),(36,7,40,10,'2024-05-29 22:16:59','2024-05-29 22:16:59',NULL,NULL,NULL,NULL),(37,7,0,10,'2024-05-29 22:22:27','2024-05-29 22:22:27',NULL,NULL,NULL,NULL),(39,7,0,10,'2024-05-30 01:28:50','2024-05-30 01:28:50',NULL,NULL,NULL,NULL),(41,7,0,10,'2024-05-30 01:31:43','2024-05-30 01:31:43',NULL,NULL,NULL,NULL),(42,7,0,10,'2024-05-30 01:33:42','2024-05-30 01:33:42',NULL,NULL,NULL,NULL),(45,7,0,10,'2024-05-30 01:34:59','2024-05-30 01:34:59',NULL,NULL,NULL,NULL),(46,7,0,10,'2024-05-30 01:36:32','2024-05-30 01:36:32',NULL,NULL,NULL,NULL),(48,7,0,10,'2024-05-30 01:37:52','2024-05-30 01:37:52',NULL,NULL,NULL,NULL),(50,7,0,10,'2024-05-30 01:39:09','2024-05-30 01:39:09',NULL,NULL,NULL,NULL),(56,7,0,10,'2024-05-30 01:57:40','2024-05-30 01:57:40',NULL,NULL,NULL,NULL),(78,5,312311,123123,'2024-06-03 02:08:22','2024-06-03 02:09:06','newuser','2024-06-02 21:08:22.375054','newuser','2024-06-02 21:09:06.288273'),(79,7,100,10,'2024-06-04 01:11:43','2024-06-04 01:11:43','newuser','2024-06-03 20:11:43.030660','newuser','2024-06-03 20:11:43.030660'),(80,7,100,10,'2024-06-04 01:12:12','2024-06-04 01:12:12','newuser','2024-06-03 20:12:12.184943','newuser','2024-06-03 20:12:12.184943'),(81,7,100,10,'2024-06-04 01:12:13','2024-06-04 01:12:13','newuser','2024-06-03 20:12:13.047389','newuser','2024-06-03 20:12:13.047389'),(82,7,100,10,'2024-06-04 01:12:14','2024-06-04 01:12:14','newuser','2024-06-03 20:12:14.020704','newuser','2024-06-03 20:12:14.020704'),(83,7,100,10,'2024-06-04 01:12:14','2024-06-04 01:12:14','newuser','2024-06-03 20:12:14.715601','newuser','2024-06-03 20:12:14.715601'),(84,7,100,10,'2024-06-04 01:12:15','2024-06-04 01:12:15','newuser','2024-06-03 20:12:15.347028','newuser','2024-06-03 20:12:15.347028'),(85,7,100,10,'2024-06-04 01:12:15','2024-06-04 01:12:15','newuser','2024-06-03 20:12:15.925831','newuser','2024-06-03 20:12:15.925831'),(97,7,100,10,'2024-06-04 04:37:19','2024-06-04 04:37:19','newuser','2024-06-03 23:37:19.518249','newuser','2024-06-03 23:37:19.518249');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Product A','Description for product A',10,0,'2024-05-29 16:15:07','2024-06-02 20:46:14',NULL,NULL,'newuser','2024-06-02 15:46:14.182029'),(2,'Product B','Description for product B',20,1,'2024-05-29 16:15:07','2024-05-29 16:15:07',NULL,NULL,NULL,NULL),(3,'Product C','Description for product C',30,1,'2024-05-29 16:15:07','2024-05-29 16:15:07',NULL,NULL,NULL,NULL),(4,'Product D','Description for product D',40,1,'2024-05-29 16:15:07','2024-05-29 16:15:07',NULL,NULL,NULL,NULL),(5,'Product E','Description for product E',50,1,'2024-05-29 16:15:07','2024-05-29 16:15:07',NULL,NULL,NULL,NULL),(9,'Product F','Product F',12,1,'2024-06-01 19:23:14','2024-06-06 15:31:47','newuser','2024-06-01 14:23:14.272595','newuser','2024-06-01 14:23:14.272595'),(10,'product Z','product Z',20,1,'2024-06-01 19:52:03','2024-06-06 15:31:47','newuser','2024-06-01 14:52:03.402790','newuser','2024-06-01 14:52:03.402790'),(11,'Product Y','Product Y',50000,1,'2024-06-02 19:00:32','2024-06-06 15:31:47','newuser','2024-06-02 14:00:32.541571','newuser','2024-06-02 14:00:32.541571');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',NULL,NULL,NULL,NULL),(2,'ROLE_SUPERVISOR',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'UserA','$2a$10$7qH8F0uOlBv/sWv1nCHDuul8xA6eU1h69BcFyzG7sBx9IWjyd29uy','usera@example.com','2024-05-29 04:25:48','2024-06-06 15:32:33',NULL,NULL,'newuser','2024-06-03 22:17:28.655640'),(5,'newuserTest','$2a$10$4QGZY1RKylgEn5r4Dk.Vp.jTUpoiIb0a3Ol51H4LJ8CsJqALsBSU6','newuserTest@example.com','2024-05-29 04:45:14','2024-06-04 03:17:53',NULL,NULL,'newuser','2024-06-03 22:17:53.446676'),(7,'admintrador','$2a$10$7qH8F0uOlBv/sWv1nCHDuul8xA6eU1h69BcFyzG7sBx9IWjyd29uy','admin@example.com','2024-05-29 05:26:18','2024-06-04 03:18:11',NULL,NULL,'newuser','2024-06-03 22:18:11.575288'),(14,'newuser','$2a$10$FFHYK33g66S.jI3XXWKjBuzVeh4I6J2LmZv.A1/OW7aracYJZKKFW','newuser@example.com','2024-06-04 02:16:19','2024-06-04 03:18:18','anonymousUser','2024-06-03 21:16:19.366271','newuser','2024-06-03 22:18:18.546144'),(22,'admin','518a2dd74bfe95e137ceab21cb3f38bed4cb8f541a2ad3139e558967d2482341bb0e35c95b481911d82e0aa0f6a8ae8db2178bc7a6f43a770ddcb60725c3993a','administrador@example.com','2024-06-04 05:31:09','2024-06-04 05:33:05',NULL,NULL,NULL,NULL),(24,'user','$2a$10$7qH8F0uOlBv/sWv1nCHDuul8xA6eU1h69BcFyzG7sBx9IWjyd29uy','user@example.com','2024-06-04 05:45:09','2024-06-04 05:45:09',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (5,1),(14,1),(2,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-06 10:33:18
