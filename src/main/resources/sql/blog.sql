-- ============================================================
-- LL-Blog 数据库设计
-- 兼容 MySQL 8.0+，InnoDB 引擎，utf8mb4 字符集
-- ============================================================

CREATE DATABASE IF NOT EXISTS `ll-blog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `ll-blog`;

-- ----------------------------
-- 1. 用户表
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR(50) NOT NULL,
                         `password_hash` VARCHAR(255) NOT NULL COMMENT 'bcrypt 加密',
                         `email` VARCHAR(100) DEFAULT NULL,
                         `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
                         `nickname` VARCHAR(50) DEFAULT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `uk_username` (`username`),
                         UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- 2. 文章分类表
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `name` VARCHAR(50) NOT NULL,
                              `slug` VARCHAR(50) NOT NULL COMMENT 'URL 友好标识',
                              `description` VARCHAR(200) DEFAULT NULL,
                              `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章分类';

-- ----------------------------
-- 3. 标签表
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(50) NOT NULL,
                        `slug` VARCHAR(50) NOT NULL,
                        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签';

-- ----------------------------
-- 4. 文章表
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                            `title` VARCHAR(200) NOT NULL,
                            `slug` VARCHAR(200) NOT NULL COMMENT '唯一标识，用于URL',
                            `summary` VARCHAR(500) DEFAULT NULL COMMENT '摘要',
                            `cover` VARCHAR(500) DEFAULT NULL COMMENT '封面图URL',
                            `content` LONGTEXT NOT NULL COMMENT 'Markdown 正文',
                            `category_id` BIGINT UNSIGNED DEFAULT NULL,
                            `user_id` BIGINT UNSIGNED NOT NULL COMMENT '作者ID',
                            `is_published` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否发布 1-是 0-草稿',
                            `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_slug` (`slug`),
                            KEY `idx_category_id` (`category_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_created_at` (`created_at`),
                            CONSTRAINT `fk_articles_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
                            CONSTRAINT `fk_articles_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- ----------------------------
-- 5. 文章-标签关联表
-- ----------------------------
DROP TABLE IF EXISTS `article_tags`;
CREATE TABLE `article_tags` (
                                `article_id` BIGINT UNSIGNED NOT NULL,
                                `tag_id` BIGINT UNSIGNED NOT NULL,
                                PRIMARY KEY (`article_id`, `tag_id`),
                                KEY `idx_tag_id` (`tag_id`),
                                CONSTRAINT `fk_at_article` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `fk_at_tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章标签关联';

-- ----------------------------
-- 6. 短内容表（时光碎片）
-- ----------------------------
DROP TABLE IF EXISTS `notes`;
CREATE TABLE `notes` (
                         `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `content` VARCHAR(280) NOT NULL COMMENT '短内容，最多280字',
                         `user_id` BIGINT UNSIGNED NOT NULL,
                         `likes` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数冗余，提升查询性能',
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `idx_user_id` (`user_id`),
                         KEY `idx_created_at` (`created_at`),
                         CONSTRAINT `fk_notes_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='短内容/时光碎片';

-- ----------------------------
-- 7. 短内容点赞记录表
-- ----------------------------
DROP TABLE IF EXISTS `note_likes`;
CREATE TABLE `note_likes` (
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `note_id` BIGINT UNSIGNED NOT NULL,
                              `user_id` BIGINT UNSIGNED NOT NULL,
                              `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_note_user` (`note_id`, `user_id`),
                              KEY `idx_user_id` (`user_id`),
                              CONSTRAINT `fk_nl_note` FOREIGN KEY (`note_id`) REFERENCES `notes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `fk_nl_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='短内容点赞记录';

-- ----------------------------
-- 8. 此刻状态表（只保留一条记录）
-- ----------------------------
DROP TABLE IF EXISTS `statuses`;
CREATE TABLE `statuses` (
                            `id` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '固定为1，只存一条记录',
                            `music_title` VARCHAR(200) DEFAULT NULL,
                            `music_artist` VARCHAR(200) DEFAULT NULL,
                            `music_cover` VARCHAR(500) DEFAULT NULL,
                            `music_url` VARCHAR(500) DEFAULT NULL,
                            `reading_title` VARCHAR(200) DEFAULT NULL,
                            `reading_cover` VARCHAR(500) DEFAULT NULL,
                            `location` VARCHAR(200) DEFAULT NULL,
                            `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='此刻状态（唯一行）';

-- ----------------------------
-- 9. 初始化数据（示例）
-- ----------------------------
-- 插入默认用户（密码需要用 bcrypt 加密，这里只是占位）
INSERT INTO `users` (`username`, `password_hash`, `nickname`, `email`)
VALUES ('admin', '$2a$10$xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '博主', 'admin@llblog.com');

-- 插入一条空状态记录
INSERT INTO `statuses` (`id`) VALUES (1);

-- 插入示例分类
INSERT INTO `categories` (`name`, `slug`, `description`) VALUES
                                                             ('前端开发', 'frontend', 'HTML/CSS/JavaScript/Vue等'),
                                                             ('后端开发', 'backend', 'Java/SpringBoot/数据库等'),
                                                             ('生活随笔', 'life', '日常记录与思考');

-- 插入示例标签
INSERT INTO `tags` (`name`, `slug`) VALUES
                                        ('Vue', 'vue'),
                                        ('JavaScript', 'javascript'),
                                        ('SpringBoot', 'springboot'),
                                        ('MySQL', 'mysql'),
                                        ('个人成长', 'growth');

-- ----------------------------
-- 10. 为了维护点赞冗余字段的触发器（可选）
-- 当插入点赞时自动增加notes.likes，删除点赞时减少
-- ----------------------------
DELIMITER //
CREATE TRIGGER `tr_note_likes_insert` AFTER INSERT ON `note_likes` FOR EACH ROW
BEGIN
    UPDATE `notes` SET `likes` = `likes` + 1 WHERE `id` = NEW.`note_id`;
END;
//
CREATE TRIGGER `tr_note_likes_delete` AFTER DELETE ON `note_likes` FOR EACH ROW
BEGIN
    UPDATE `notes` SET `likes` = IF(`likes` > 0, `likes` - 1, 0) WHERE `id` = OLD.`note_id`;
END;
//
DELIMITER ;