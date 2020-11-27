# my-finance-watcher
SpringBoot+SpringMVC+Hibernate+thymeleaf Application

mvn install -Dmaven.test.skip=true
docker build . -t my-finance-watcher-1.0.0
docker run -p 8089:8089 --name my-finance-watcher-1.0.0 --link mysql-standalone:mysql -d my-finance-watcher-1.0.0

Check Container:
docker exec -ti my-finance-watcher-1.0.0 bash 

Check Logs: 
docker logs my-finance-watcher-1.0.0
Tail Logs:
docker logs my-finance-watcher-1.0.0 --follow

Runs successfully: 
mvn spring-boot:run

MYSQL : 

docker pull mysql:5.6

docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:5.6

docker exec -ti mysql-standalone bash  

mysql -u sa -p (mysql -u $USERNAME -p)

Enter password:password

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






