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


INSERT INTO users(username,email,password,enabled) VALUES ('honey','honey@gmail.com','h0ney',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (31,'honey','player');

INSERT INTO users(username,email,password,enabled) VALUES ('cmuller','cmuller@gmail.com','cmull3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'cmuller','player');

INSERT INTO users(username,email,password,enabled) VALUES ('jonDoe','jonDoe@gmail.com','j0nD03',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'jonDoe','player');

INSERT INTO users(username,email,password,enabled) VALUES ('janeDoe','janeDoe@gmail.com','j4n3D03',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (19,'janeDoe','player');

INSERT INTO users(username,email,password,enabled) VALUES ('curro','curro@gmail.com','curr0',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (20,'curro','player');

INSERT INTO users(username,email,password,enabled) VALUES ('messi','messi@gmail.com','m3ss1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (21,'messi','player');

INSERT INTO users(username,email,password,enabled) VALUES ('elBichoCR7','elBichoCR7@gmail.com','3lB1ch0CR7',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (22,'elBichoCR7','player');

INSERT INTO users(username,email,password,enabled) VALUES ('miscoJones','miscojones@gmail.com','m1sc0J0n3s',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (23,'miscoJones','player');

INSERT INTO users(username,email,password,enabled) VALUES ('vendoOpelCorsa','vendoOpelCorsa@gmail.com','v3nd00p3lC0rsA',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (24,'vendoOpelCorsa','player');

INSERT INTO users(username,email,password,enabled) VALUES ('comproOpelCorsa','comproOpelCorsa@gmail.com','c0mpr00p3lC0rsA',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (25,'comproOpelCorsa','player');

INSERT INTO users(username,email,password,enabled) VALUES ('josepPedrerol','josepPedrerol@gmail.com','j0s3pP3dr3r0l',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (26,'josepPedrerol','player');

INSERT INTO users(username,email,password,enabled) VALUES ('jorgeDAlessandro','jorgeDAlessandro@gmail.com','j0rg3D4l3ss4ndr0',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (27,'jorgeDAlessandro','player');

INSERT INTO users(username,email,password,enabled) VALUES ('alfredoDuro','alfredoDuro@gmail.com','4lfr3d0Dur0',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (28,'alfredoDuro','player');

INSERT INTO users(username,email,password,enabled) VALUES ('tomasRoncero','tomasRoncero@gmail.com','t0m4sR0nc3r0',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (29,'tomasRoncero','player');

INSERT INTO users(username,email,password,enabled) VALUES ('foreverAlone','foreverAlone@gmail.com','f0r3v3r4l0n3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (30,'foreverAlone','player');

INSERT INTO users(username,email,password,enabled) VALUES ('None','noneAlone@gmail.com','n0n3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (32,'None','player');

INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (11, false, false, 11);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (12, false, false, 12);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (13, false, false, 13);

INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (21, false, false, 21);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (22, false, false, 22);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (23, false, false, 23);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (24, false, false, 24);

INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (31, false, false, 31);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (32, false, false, 32);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (33, false, false, 33);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (34, false, false, 34);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (35, false, false, 35);

INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (41, false, false, 41);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (42, false, false, 42);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (43, false, false, 43);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (44, false, false, 44);

INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (51, false, false, 51);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (52, false, false, 52);
INSERT INTO cell(id, is_blocked, is_flipped, position) VALUES (53, false, false, 53);

--1
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (11, 12);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (11, 21);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (11, 22);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (12, 11);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (12, 22);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (12, 23);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (12, 13);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (13, 12);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (13, 23);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (13, 24);
--2
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (21, 11);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (21, 22);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (21, 31);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (21, 32);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (22, 11);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (22, 12);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (22, 21);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (22, 23);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (22, 32);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (22, 33);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (23, 13);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (23, 12);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (23, 22);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (23, 24);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (23, 34);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (23, 33);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (24, 13);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (24, 23);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (24, 34);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (24, 35);
--3
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (31, 21);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (31, 32);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (31, 41);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (32, 21);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (32, 22);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (32, 31);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (32, 33);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (32, 41);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (32, 42);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (33, 22);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (33, 23);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (33, 32);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (33, 34);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (33, 42);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (33, 43);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (34, 23);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (34, 24);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (34, 33);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (34, 35);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (34, 43);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (34, 44);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (35, 24);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (35, 34);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (35, 44);
--4
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (41, 51);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (41, 42);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (41, 31);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (41, 32);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (42, 51);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (42, 52);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (42, 41);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (42, 43);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (42, 32);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (42, 33);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (43, 53);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (43, 52);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (43, 42);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (43, 44);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (43, 34);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (43, 33);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (44, 53);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (44, 43);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (44, 34);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (44, 35);
--5
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (51, 52);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (51, 41);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (51, 42);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (52, 51);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (52, 42);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (52, 53);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (52, 43);

INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (53, 52);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (53, 43);
INSERT INTO cell_adjacents(cell_id, adjacents_id) VALUES (53, 44);

INSERT INTO profile(id, played_games, wins, steals, matches,record,  user_username) VALUES (1, 0, 0, 0, 0, 0,  'honey');
INSERT INTO profile(id, played_games, wins, steals, matches,record,  user_username) VALUES (2, 0, 0, 0, 0, 0,  'None');

INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (1, 'Baby Bee','https://media.istockphoto.com/illustrations/happy-baby-bee-illustration-id92717135?k=6&m=92717135&s=170667a&w=0&h=rT8nF4gaMA1bjExYyX4jTlTERmNtuVhVihHE5bmavIc=','https://i.imgur.com/wYoxz9e.png', 2.0, 'Enter your first game');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (2, 'BumbleBee','https://th.bing.com/th/id/OIP.oJ3oz0uzEgtj_ev6HkXIYQHaFR?pid=ImgDet&rs=1','https://i.imgur.com/cXppzV3.png', 2.0, 'Play 5 Games');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (3, 'Queen Bee','https://th.bing.com/th/id/R.05085dceb44cdf2855c5dc277dd3721c?rik=u0Ft5NRpNJMLwQ&riu=http%3a%2f%2fwww.clipartbest.com%2fcliparts%2fncB%2fEG4%2fncBEG4B7i.jpg&ehk=Zt%2bDJz7mNMTYNW5sDIiV9GH8m7NxOJALbmuUjkZAEpc%3d&risl=&pid=ImgRaw&r=0','https://i.imgur.com/UcPlCM5.png', 2.0, 'Play 10 Games');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (4, 'Queen of the Hive','https://th.bing.com/th/id/R.a0585fed6ea86756e29f60072beee908?rik=%2bImp2dAcp0WH1A&pid=ImgRaw&r=0','https://i.imgur.com/aNizNle.png',2.0, 'Win your first game');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (5, 'Shameless Thief','https://th.bing.com/th/id/OIP.QzqEbxDqzlBH2-WAxCFbaQHaHa?w=209&h=209&c=7&r=0&o=5&pid=1.7','https://i.imgur.com/uPdD7OP.png', 2.0, 'Steal your first tile');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (6, 'Empress Maya','https://www.nicepng.com/png/full/268-2687679_https-transparent-crown-cartoon-png.png','https://imgur.com/Vsrkoe1.png', 2.0, 'You are the player with more wins!!');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (7, 'Laytons apprentice','https://th.bing.com/th/id/R.8c2988e406c697e82bfda57565332915?rik=Xg9mxQtBIx2ZHQ&riu=http%3a%2f%2fclipart-library.com%2fnewhp%2f110-1106529_jigsaw-clip-art-puzzle-pieces-clipart.png&ehk=1DKbFX4LobuXAHQNRAr5NBGWOS98MqfLXlPCXg0lAA0%3d&risl=&pid=ImgRaw&r=0','https://imgur.com/n8Fow3j.png', 2.0, 'Perform your first match');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (8, 'Robin Hood','https://imgur.com/OMfg2r0.png','https://imgur.com/vGmQWOe.png', 2.0, 'You are the player with more steals!!');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (9, 'Veteran Bee','https://imgur.com/rnt4hYX.png','https://imgur.com/3orausb.png', 2.0, 'You are the player with more games!!');
INSERT INTO achievement(id,name, badge_image,blocked_image, threshold, description) VALUES (10, 'Matcher Bee','https://imgur.com/s13z1Rv.png','https://imgur.com/j8p1Xk5.png', 2.0, 'You are the player with more matches!!');
