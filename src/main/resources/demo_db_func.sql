CREATE DATABASE IF NOT EXISTS `demo_db`;
CREATE TABLE IF NOT EXISTS `demo_info`
(
  `id` varchar(32) primary key,
  `name` varchar(32) not null,
  `idex` int unsigned,
  `timestamp` bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;