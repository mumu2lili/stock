CREATE TABLE `stock_quote` (
  `id` bigint(20) NOT NULL,
  `exchange` varchar(45) DEFAULT NULL,
  `board` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `opening` double DEFAULT NULL,
  `closing` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `trade_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
