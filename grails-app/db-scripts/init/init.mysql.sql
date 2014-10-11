CREATE DATABASE IF NOT EXISTS mubs;

USE mysql;

create user mubs IDENTIFIED BY 'mubs';
create user mubs@localhost IDENTIFIED BY 'mubs';

GRANT ALL ON mubs.* TO 'mubs' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT ALL ON mubs.* TO 'mubs'@'%' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT ALL ON mubs.* TO 'mubs'@'localhost' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

GRANT GRANT OPTION ON mubs.* TO 'mubs' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT GRANT OPTION ON mubs.* TO 'mubs'@'%' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT GRANT OPTION ON mubs.* TO 'mubs'@'localhost' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

GRANT ALL ON `mysql`.`proc` TO mubs;
GRANT ALL ON `mysql`.`proc` TO mubs@'%';
GRANT ALL ON `mysql`.`proc` TO mubs@'localhost';

flush privileges;