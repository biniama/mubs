-- Run the following script to drop the database and user credential in the database
DROP DATABASE IF EXISTS mubs;

DROP USER mubs;
DROP USER mubs@localhost;

flush privileges;
