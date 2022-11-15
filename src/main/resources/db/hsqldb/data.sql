-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,email,password,enabled) VALUES ('admin1','admin1@gmail.com','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,email,password,enabled) VALUES ('bogste', 'bogste@gmail.com','b@gst3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'bogste','player');

INSERT INTO users(username,email,password,enabled) VALUES ('adrrf','adrrf@gmail.com','4drrf',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'adrrf','player');

INSERT INTO users(username,email,password,enabled) VALUES ('manuel','manuel@gmail.com','m4nu3l',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'manuel','player');

INSERT INTO users(username,email,password,enabled) VALUES ('jorromlim','jorromlim@gmail.com','j0rr0ml1m',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'jorromlim','player');

INSERT INTO users(username,email,password,enabled) VALUES ('alematcap','alematcap@gmail.com','4lem4tc4p',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'alematcap','player');

INSERT INTO users(username,email,password,enabled) VALUES ('angel','angel@gmail.com','4ng3l',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'angel','player');

INSERT INTO tile(id, starting_side, filled_side) VALUES (1, 'purple', 'purple');
INSERT INTO tile(id, starting_side, filled_side) VALUES (2, 'red', 'red');
INSERT INTO tile(id, starting_side, filled_side) VALUES (3, 'blue', 'blue');
INSERT INTO tile(id, starting_side, filled_side) VALUES (4, 'yellow', 'yellow');
INSERT INTO tile(id, starting_side, filled_side) VALUES (5, 'green', 'green');
INSERT INTO tile(id, starting_side, filled_side) VALUES (6, 'orange', 'orange');

INSERT INTO modes(id,name,max_players) VALUES (1, 'COMPETITIVE', 4);
INSERT INTO modes(id,name,max_players) VALUES (2, 'SOLO', 1);
INSERT INTO modes(id,name,max_players) VALUES (3, 'SURVIVAL', 1);

INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (1, 'SOLO', 'true', 1, '2013-01-10');
INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (2, 'SOLO', 'false', 1, '2014-02-12');
INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (3, 'COMPETITIVE', 'true', 2, '2015-03-14');
INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (4, 'COMPETITIVE', 'false', 3, '2016-04-16');
INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (5, 'COMPETITIVE', 'false', 4, '2017-05-18');
INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (6, 'SURVIVAL', 'true', 1, '2018-06-20');
INSERT INTO games(id,mode,finished,number_of_players,date_of_creation) VALUES (7, 'SURVIVAL', 'false', 1, '2019-07-22');

