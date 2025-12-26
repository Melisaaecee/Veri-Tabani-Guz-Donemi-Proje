DROP TABLE IF EXISTS `ceza_sistemi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ceza_sistemi` (
  `ceza_miktarı` int DEFAULT NULL,
  `ödünc_id` int DEFAULT NULL,
  KEY `ödünc_id` (`ödünc_id`),
  CONSTRAINT `ceza_sistemi_ibfk_1` FOREIGN KEY (`ödünc_id`) REFERENCES `oduncalma_sistemi` (`ödünc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategori` (
  `kategori_id` int NOT NULL AUTO_INCREMENT,
  `kategori_ad` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kategori_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kitap`
--

DROP TABLE IF EXISTS `kitap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kitap` (
  `kitap_id` int NOT NULL AUTO_INCREMENT,
  `kategori_id` int DEFAULT NULL,
  `kitap_ad` varchar(100) DEFAULT NULL,
  `durum` tinyint(1) DEFAULT NULL,
  `stok_adedi` int DEFAULT NULL,
  `yazar_id` int DEFAULT NULL,
  PRIMARY KEY (`kitap_id`),
  KEY `kategori_id` (`kategori_id`),
  KEY `fk_kitap_yazar` (`yazar_id`),
  CONSTRAINT `fk_kitap_yazar` FOREIGN KEY (`yazar_id`) REFERENCES `yazar` (`yazar_id`),
  CONSTRAINT `kitap_ibfk_2` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kullanicilar`
--

DROP TABLE IF EXISTS `kullanicilar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanicilar` (
  `kullanıcı_id` int NOT NULL AUTO_INCREMENT,
  `kullanıcı_ad` varchar(20) DEFAULT NULL,
  `kullanıcı_soyad` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `rol` varchar(100) DEFAULT NULL,
  `sifre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kullanıcı_id`),
  UNIQUE KEY `uq_kullanici_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oduncalma_sistemi`
--

DROP TABLE IF EXISTS `oduncalma_sistemi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oduncalma_sistemi` (
  `alınan_tarih` date DEFAULT NULL,
  `durum` tinyint(1) DEFAULT NULL,
  `gercek_iade_tarihi` date DEFAULT NULL,
  `kitap_id` int DEFAULT NULL,
  `kullanıcı_id` int DEFAULT NULL,
  `planlanan_iade_tarihi` date DEFAULT NULL,
  `ödünc_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ödünc_id`),
  KEY `kitap_id` (`kitap_id`),
  KEY `kullanıcı_id` (`kullanıcı_id`),
  CONSTRAINT `oduncalma_sistemi_ibfk_1` FOREIGN KEY (`kitap_id`) REFERENCES `kitap` (`kitap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `stok_kontrol` BEFORE INSERT ON `oduncalma_sistemi` FOR EACH ROW BEGIN
DECLARE mevcut_stok INT;
SELECT stok_adedi INTO mevcut_stok FROM kitap
 WHERE kitap_id=NEW.kitap_id;
 IF mevcut_stok IS NULL THEN 
 SIGNAL SQLSTATE '45000'
 SET message_text = 'KİTAP BULUNAMADI';
 END IF;
 END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `stok_azalt` AFTER INSERT ON `oduncalma_sistemi` FOR EACH ROW BEGIN 
UPDATE kitap
SET stok_adedi=stok_adedi - 1
WHERE kitap_id = NEW.kitap_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `stok_arttİr` AFTER UPDATE ON `oduncalma_sistemi` FOR EACH ROW BEGIN 
IF OLD.durum =true AND NEW.durum=false THEN
UPDATE kitap
SET stok_adedi=stok_adedi + 1
WHERE kitap_id = NEW.kitap_id;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `gec_iade_ceza` AFTER UPDATE ON `oduncalma_sistemi` FOR EACH ROW BEGIN 
DECLARE  gec_gun INT;
DECLARE toplam_ceza INT;
DECLARE gunluk_ceza INT DEFAULT 15;

IF OLD.durum=true AND NEW.durum=false THEN 

    IF NEW.gercek_iade_tarihi > NEW.planlanan_iade_tarihi THEN 
		SET gec_gun = DATEDIFF(NEW.gercek_iade_tarihi,NEW.planlanan_iade_tarihi);
 
		SET toplam_ceza =gec_gun * gunluk_ceza;
 
		INSERT INTO ceza_sistemi (ödünc_id,ceza_miktarı)
		VALUES (NEW.ödünc_id,toplam_ceza);
     END IF;
END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `yazar`
--

DROP TABLE IF EXISTS `yazar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yazar` (
  `yazar_id` int NOT NULL AUTO_INCREMENT,
  `yazar_ad` varchar(50) DEFAULT NULL,
  `yazar_soyad` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`yazar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


