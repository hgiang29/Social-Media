ALTER TABLE likes RENAME TO `like`;
ALTER TABLE `like` CHANGE like_id id INT AUTO_INCREMENT;

INSERT INTO `like`(id,post_id) values (1, 1);
INSERT INTO `like`(id,post_id) values (2, 3);
INSERT INTO `like`(id,post_id) values (3, 2);