CREATE TABLE `stock_dict` (
  `id` bigint(20) NOT NULL,
  `exchange` varchar(45) DEFAULT NULL,
  `board` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `ename` varchar(45) DEFAULT NULL,
  `listing_date` timestamp NULL DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
