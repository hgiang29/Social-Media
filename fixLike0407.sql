ALTER TABLE likes RENAME TO `like`;
ALTER TABLE `like` CHANGE like_id id INT AUTO_INCREMENT;
