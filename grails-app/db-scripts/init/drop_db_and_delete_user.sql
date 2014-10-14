-- Run the following script to drop the database and user credential in the database
DROP DATABASE IF EXISTS mubs;

use mysql;
DELETE FROM user where user='mubs';

flush privileges;
