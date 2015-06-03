-- Default database and user initialization
CREATE DATABASE IF NOT EXISTS atossla;
CREATE USER atossla@'%' IDENTIFIED BY '_atossla_';
CREATE USER atossla@'localhost' IDENTIFIED BY '_atossla_';
GRANT ALL PRIVILEGES ON atossla.* TO atossla@'%';
GRANT ALL PRIVILEGES ON atossla.* TO atossla@'localhost';
