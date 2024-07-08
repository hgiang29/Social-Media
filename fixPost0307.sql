ALTER TABLE posts RENAME TO post;
ALTER TABLE post CHANGE post_id id INT AUTO_INCREMENT;
ALTER TABLE post CHANGE post_content content VARCHAR(255);

INSERT INTO  post(id, content) values (1,"Hieu dang bai nay");
INSERT INTO  post(id, content) values (2,"Hoang Anh dang bai nay");
INSERT INTO  post(id, content) values (3,"Giang dang bai nay");
INSERT INTO  post(id, content) values (4,"Dao dang bai nay");

UPDATE post
SET creator_user_id = 1

ALTER TABLE user CHANGE user_id id INT AUTO_INCREMENT;

