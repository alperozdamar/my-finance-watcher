show databases;
use test;
show tables;

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table my_finance_db.users: ~4 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`username`, `password`, `enabled`) VALUES
        ('alper', '{noop}123', 1),
        ('john', '{bcrypt}$2y$12$2uyIQbVvq2STqGm8BZZpy.NHqm2KHyH8OrItzqDXk.AsiuPdu.D4O', 1),
        ('mary', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1),
        ('susan', '{noop}test123', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Dumping structure for table my_finance_db.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `FK1_authorities_username_users_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table my_finance_db.authorities: ~7 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT IGNORE INTO `authorities` (`username`, `authority`) VALUES
        ('alper', 'ROLE_ADMIN'),
        ('alper', 'ROLE_EMPLOYEE'),
        ('john', 'ROLE_EMPLOYEE'),
        ('mary', 'ROLE_EMPLOYEE'),
        ('mary', 'ROLE_MANAGER'),
        ('susan', 'ROLE_ADMIN'),
        ('susan', 'ROLE_EMPLOYEE');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
