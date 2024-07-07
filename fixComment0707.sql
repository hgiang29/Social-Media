ALTER TABLE comments RENAME TO comment;
ALTER TABLE comment CHANGE comment_id id INT AUTO_INCREMENT;
ALTER TABLE comment CHANGE comment_post_id post_id INT;
ALTER TABLE comment CHANGE comment_content content VARCHAR(255);

INSERT INTO  comment(id, content, post_id) values (1,"Hieu da comment bai nay",1);
INSERT INTO  comment(id, content, post_id) values (2,"Hoang Anh da comment bai nay",2);
INSERT INTO  comment(id, content, post_id) values (3,"Giang da comment bai nay",1);

ALTER TABLE comment ADD COLUMN parent_id INT;
ALTER TABLE comment
ADD CONSTRAINT fk_comment_parent
FOREIGN KEY (parent_id)
REFERENCES comment(id);