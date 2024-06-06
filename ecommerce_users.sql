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
INSERT INTO `users` VALUES (2,'useraaaa','$2a$10$7qH8F0uOlBv/sWv1nCHDuul8xA6eU1h69BcFyzG7sBx9IWjyd29uy','useraaaa@example.com','2024-05-29 04:25:48','2024-06-04 05:45:05',NULL,NULL,'newuser','2024-06-03 22:17:28.655640'),(5,'newuserTest','$2a$10$4QGZY1RKylgEn5r4Dk.Vp.jTUpoiIb0a3Ol51H4LJ8CsJqALsBSU6','newuserTest@example.com','2024-05-29 04:45:14','2024-06-04 03:17:53',NULL,NULL,'newuser','2024-06-03 22:17:53.446676'),(7,'admintrador','$2a$10$7qH8F0uOlBv/sWv1nCHDuul8xA6eU1h69BcFyzG7sBx9IWjyd29uy','admin@example.com','2024-05-29 05:26:18','2024-06-04 03:18:11',NULL,NULL,'newuser','2024-06-03 22:18:11.575288'),(14,'newuser','$2a$10$FFHYK33g66S.jI3XXWKjBuzVeh4I6J2LmZv.A1/OW7aracYJZKKFW','newuser@example.com','2024-06-04 02:16:19','2024-06-04 03:18:18','anonymousUser','2024-06-03 21:16:19.366271','newuser','2024-06-03 22:18:18.546144'),(22,'admin','518a2dd74bfe95e137ceab21cb3f38bed4cb8f541a2ad3139e558967d2482341bb0e35c95b481911d82e0aa0f6a8ae8db2178bc7a6f43a770ddcb60725c3993a','administrador@example.com','2024-06-04 05:31:09','2024-06-04 05:33:05',NULL,NULL,NULL,NULL),(24,'user','$2a$10$7qH8F0uOlBv/sWv1nCHDuul8xA6eU1h69BcFyzG7sBx9IWjyd29uy','user@example.com','2024-06-04 05:45:09','2024-06-04 05:45:09',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
