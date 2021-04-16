-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: ib
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'102001403548','debit',1100000,'123',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_card` int DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cif_UNIQUE` (`cif`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Mạc Anh Thanh','Hà Nội',25,'123','thanhma@gmail.com',174532256,'0942996736',1,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ewallet_linked`
--

DROP TABLE IF EXISTS `ewallet_linked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ewallet_linked` (
  `trans_id` int NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `ewallet_id` varchar(255) DEFAULT NULL,
  `linked_date` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ewallet_linked`
--

LOCK TABLES `ewallet_linked` WRITE;
/*!40000 ALTER TABLE `ewallet_linked` DISABLE KEYS */;
INSERT INTO `ewallet_linked` VALUES (80,'102001403548','0942996736','2021-04-15 16:19:28','00',NULL);
/*!40000 ALTER TABLE `ewallet_linked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cif` varchar(20) DEFAULT NULL,
  `otp` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES (1,'123','123');
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `trans_id` int NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `trans_date` datetime DEFAULT NULL,
  `trans_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (98,0,'2021-04-16 10:38:22','Kiểm tra thông tin tài khoản','Kiểm tra thành công','00'),(99,0,'2021-04-16 11:19:49','Kiểm tra thông tin tài khoản','Kiểm tra thành công','00'),(100,0,'2021-04-16 11:19:49','Kiểm tra đăng nhập','Đăng nhập thành công','00'),(101,0,'2021-04-16 11:19:49','Kiểm tra đăng nhập','Thông tin đăng nhập không đúng','04'),(102,0,'2021-04-16 11:31:13','Kiểm tra đăng nhập','Đăng nhập thành công','00'),(103,0,'2021-04-16 11:58:06','Kiểm tra OTP','Xác thực OTP thành công','00'),(104,0,'2021-04-16 11:58:58','Kiểm tra thông tin tài khoản','Kiểm tra thành công','00'),(105,0,'2021-04-16 11:58:58','Kiểm tra đăng nhập','Đăng nhập thành công','00'),(106,0,'2021-04-16 11:58:58','Kiểm tra OTP','Xác thực OTP thành công','00'),(107,0,'2021-04-16 15:08:20','Kiểm tra thông tin tài khoản','Kiểm tra thành công','00'),(108,0,'2021-04-16 15:08:20','Kiểm tra đăng nhập','Đăng nhập thành công','00'),(109,0,'2021-04-16 15:08:20','Kiểm tra đăng nhập','Đăng nhập thành công','00'),(110,0,'2021-04-16 15:08:20','Kiểm tra OTP','Xác thực OTP thành công','00'),(111,0,'2021-04-16 15:08:20','Thực hiện liên kết ví 0942996736với tài khoản 102001403548','Liên kết thành công','00'),(112,0,'2021-04-16 15:08:20','Kiểm tra OTP','Xác thực OTP thành công','00'),(113,0,'2021-04-16 15:08:20','Thực hiện liên kết ví 0942996736với tài khoản 102001403548','Liên kết thành công','00'),(114,0,'2021-04-16 18:47:42','Kiểm tra thông tin tài khoản','Kiểm tra thành công','00'),(115,0,'2021-04-16 18:47:42','Kiểm tra đăng nhập','Đăng nhập thành công','00'),(116,0,'2021-04-16 18:47:42','Kiểm tra OTP','Xác thực OTP thành công','00'),(117,0,'2021-04-16 18:47:42','Thực hiện liên kết ví 0942996736với tài khoản 102001403548','Liên kết thành công','00'),(118,200000,'2021-04-16 19:16:45','Nạp tiền','Số tiền trong tài khoản không đủ','09'),(119,200000,'2021-04-16 19:16:45','Nạp tiền','Nạp tiền vào Momo từ 102001403548','00'),(120,300000,'2021-04-16 19:28:48','Rút tiền','Rút tiền từ Momo về 102001403548','00');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_ewallet`
--

DROP TABLE IF EXISTS `transaction_ewallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_ewallet` (
  `trans_id` int NOT NULL,
  `ewallet_id` varchar(255) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `id_card` int DEFAULT NULL,
  `id_card_type` varchar(255) DEFAULT NULL,
  `issue_date` datetime DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_ewallet`
--

LOCK TABLES `transaction_ewallet` WRITE;
/*!40000 ALTER TABLE `transaction_ewallet` DISABLE KEYS */;
INSERT INTO `transaction_ewallet` VALUES (16,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(20,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(24,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(28,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(32,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(36,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(40,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(44,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(48,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(52,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(56,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(60,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(64,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(68,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(72,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(76,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(80,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(83,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(87,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(88,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(119,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL),(120,'0942996736','102001403548',NULL,'Mạc Anh Thanh',174532256,NULL,NULL);
/*!40000 ALTER TABLE `transaction_ewallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'thanhma','thanhma','123',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-16 19:45:07
