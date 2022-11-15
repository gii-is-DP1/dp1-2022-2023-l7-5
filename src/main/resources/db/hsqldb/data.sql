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

