# mysql

`docker run --name mysql -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=true -d mysql:5.7`

`docker run --name lxy-mysql-master -v /Users/yuulongxue/docker/data/mysql/cnf/master:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=lxy -d -p 3306:3306 -p 33060:33060  mysql:5.7`

`docker run --name lxy-mysql-slave -v /Users/yuulongxue/docker/data/mysql/cnf/slave:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=lxy -d -p 3307:3306 -p 33061:33060  mysql:5.7`

# jdbc url
`jdbc:mysql://localhost:3306/test?user=root&password=&charset=utf8mb4&parseTime=true&loc=Local`

# init database
`create database test;`

# init table
```
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(32) NOT NULL,
  `name` varchar(128) NOT NULL,

  `ctime` bigint(20) NOT NULL DEFAULT 0,
  `mtime` bigint(20) NOT NULL DEFAULT 0,


  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `acount` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(32) NOT NULL,
  `name` varchar(128) NOT NULL,

  `ctime` bigint(20) NOT NULL DEFAULT 0,
  `mtime` bigint(20) NOT NULL DEFAULT 0,


  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

# connect
`mysql -uroot --protocol=tcp`
