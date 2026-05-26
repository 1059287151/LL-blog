-- ============================================================
-- 示例数据
-- ============================================================
USE `ll-blog`;

-- 用户（已有 admin，再添加一个测试用户）
INSERT INTO `users` (`username`, `password_hash`, `nickname`, `email`) VALUES
    ('testuser', '$2a$10$AnotherHashPlaceholder1234567890abcdef', '访客小王', 'xiaowang@test.com');

-- 更多分类
INSERT INTO `categories` (`name`, `slug`, `description`) VALUES
                                                             ('读书笔记', 'reading', '书籍摘录与感悟'),
                                                             ('技术教程', 'tutorial', '详细的技术操作指南');

-- 更多标签
INSERT INTO `tags` (`name`, `slug`) VALUES
                                        ('CSS', 'css'),
                                        ('Python', 'python'),
                                        ('Docker', 'docker'),
                                        ('阅读', 'reading'),
                                        ('旅行', 'travel');

-- 文章（使用 admin 用户，id=1）
INSERT INTO `articles` (`title`, `slug`, `summary`, `cover`, `content`, `category_id`, `user_id`, `is_published`) VALUES
                                                                                                                      (
                                                                                                                          '我的第一个 Vue3 项目复盘',
                                                                                                                          'vue3-first-project-review',
                                                                                                                          '从零搭建 Vue3 + Vite 项目的一些心得与踩坑记录。',
                                                                                                                          'https://picsum.photos/seed/vue3/800/400',
                                                                                                                          '# 项目初始化\n\n使用 `npm create vite@latest` 创建项目，选择 Vue 和 JavaScript。\n\n## 遇到的坑\n\n1. **路径别名配置**：需要在 `vite.config.js` 中配置 `resolve.alias`，同时修改 `jsconfig.json` 让编辑器识别。\n2. **环境变量**：Vite 使用 `import.meta.env`，而不是 `process.env`。\n\n[[vue3-best-practices]] 这篇文章中进一步讨论了最佳实践。\n\n```javascript\n// vite.config.js\nexport default {\n  resolve: {\n    alias: {\n      ''@'': path.resolve(__dirname, ''src'')\n    }\n  }\n}\n```',
                                                                                                                          1, 1, 1
                                                                                                                      ),
                                                                                                                      (
                                                                                                                          'SpringBoot 统一响应格式处理',
                                                                                                                          'springboot-unified-response',
                                                                                                                          '利用 SpringBoot 拦截器与 ResponseBodyAdvice 实现统一 API 返回。',
                                                                                                                          'https://picsum.photos/seed/springboot/800/400',
                                                                                                                          '# 为什么要统一响应\n\n前后端分离项目中，统一返回 `{ code, data, msg }` 格式有利于前端统一处理错误。\n\n## 实现方式\n\n[[springboot-interceptor]] 中我分享了拦截器的用法。这里使用 `@RestControllerAdvice` 实现。\n\n```java\n@RestControllerAdvice\npublic class ResponseAdvice implements ResponseBodyAdvice<Object> {\n    // 实现代码\n}\n```',
                                                                                                                          2, 1, 1
                                                                                                                      ),
                                                                                                                      (
                                                                                                                          '夏日川西自驾游记',
                                                                                                                          'summer-west-sichuan-trip',
                                                                                                                          '成都出发，四天三夜川西小环线，草原、雪山与星空。',
                                                                                                                          'https://picsum.photos/seed/chuanxi/800/400',
                                                                                                                          '## 行程概览\n\n- **Day1**：成都 → 康定 → 新都桥\n- **Day2**：新都桥 → 塔公草原 → 墨石公园\n- **Day3**：丹巴 → 四姑娘山\n- **Day4**：返回成都\n\n一路风景如画，尤其是在塔公草原看到了雅拉雪山的日照金山。\n\n> 旅行提示：海拔较高，注意防晒和保暖。',
                                                                                                                          3, 1, 1
                                                                                                                      ),
                                                                                                                      (
                                                                                                                          'CSS Grid 打造响应式 Bento 布局',
                                                                                                                          'css-grid-bento-layout',
                                                                                                                          '使用纯 CSS Grid 实现时尚的便当盒布局，无需额外库。',
                                                                                                                          'https://picsum.photos/seed/bento/800/400',
                                                                                                                          '# Bento 布局\n\nBento 风格源自苹果的简报卡片设计，各种尺寸的卡片拼成网格。\n\n## 核心代码\n\n```css\n.bento-grid {\n  display: grid;\n  grid-template-columns: repeat(4, 1fr);\n  gap: 1rem;\n  grid-auto-rows: 200px;\n}\n.card-2x2 { grid-column: span 2; grid-row: span 2; }\n.card-2x1 { grid-column: span 2; }\n```\n\n[[vue3-first-project-review]] 项目中我也用了类似的设计。',
                                                                                                                          1, 1, 1
                                                                                                                      ),
                                                                                                                      (
                                                                                                                          'Docker 部署 SpringBoot 应用笔记',
                                                                                                                          'docker-deploy-springboot',
                                                                                                                          '从 Dockerfile 编写到 Compose 编排，记录一次完整的容器化部署。',
                                                                                                                          'https://picsum.photos/seed/docker/800/400',
                                                                                                                          '## Dockerfile\n\n```dockerfile\nFROM openjdk:17-jdk-slim\nCOPY target/app.jar app.jar\nENTRYPOINT ["java", "-jar", "/app.jar"]\n```\n\n结合 [[springboot-unified-response]] 中的项目进行部署测试。',
                                                                                                                          2, 1, 1
                                                                                                                      );

-- 文章-标签关联（假定上面的文章id分别为1~5）
INSERT INTO `article_tags` (`article_id`, `tag_id`) VALUES
                                                        (1, 1), (1, 2), (1, 5),   -- Vue, JavaScript, 个人成长
                                                        (2, 3), (2, 4),            -- SpringBoot, MySQL
                                                        (3, 5),                    -- 旅行（假设tag_id=5，实际可能需要插入新标签'旅行'，这里用已有的'个人成长'代替。最好插入新标签）
                                                        (4, 1), (4, 2), (4, 6),    -- Vue, JavaScript, CSS (CSS tag_id=6)
                                                        (5, 3), (5, 7);            -- SpringBoot, Docker (Docker tag_id=7)

-- 需要补上旅行和CSS、Docker标签（前面可能没插入），先确保存在
INSERT IGNORE INTO `tags` (`name`, `slug`) VALUES
('CSS', 'css'),
('Docker', 'docker'),
('旅行', 'travel');

-- 重新执行关联（如果上面的关联因为tag_id不对失败，可以再次执行）
-- 简便起见，直接给出正确关联（假设标签id修正后）：
TRUNCATE `article_tags`;
INSERT INTO `article_tags` (`article_id`, `tag_id`) VALUES
                                                        (1, 1), (1, 2), (1, 5),   -- Vue, JavaScript, 个人成长
                                                        (2, 3), (2, 4),            -- SpringBoot, MySQL
                                                        (3, (SELECT id FROM tags WHERE slug='travel')),  -- 旅行
                                                        (4, 1), (4, 2), (4, (SELECT id FROM tags WHERE slug='css')),
                                                        (5, 3), (5, (SELECT id FROM tags WHERE slug='docker'));

-- 短内容（时光碎片）
INSERT INTO `notes` (`content`, `user_id`) VALUES
                                               ('刚刚完成了博客的暗色模式切换功能，翻书动画效果很赞！ #日常 #coding', 1),
                                               ('推荐一本最近在读的书：《置身事内》，对中国经济分析非常透彻。 #阅读', 1),
                                               ('今天成都的晚霞太美了，随手拍了一张。', 1),
                                               ('周末去爬了青城山，累并快乐着 #旅行', 1),
                                               ('优化了数据库查询，加了几个索引，接口响应速度提升了40%。 #SpringBoot', 1);

-- 点赞记录（user_id=2 给第1、3条短内容点赞）
INSERT INTO `note_likes` (`note_id`, `user_id`) VALUES (1, 2), (3, 2);

-- 此刻状态
UPDATE `statuses` SET
                      music_title = '晴天',
                      music_artist = '周杰伦',
                      music_cover = 'https://picsum.photos/seed/music/200/200',
                      music_url = 'https://music.163.com/#/song?id=186016',
                      reading_title = '置身事内：中国政府与经济发展',
                      reading_cover = 'https://picsum.photos/seed/book/200/300',
                      location = '成都·高新区'
WHERE id = 1;

-- 插入示例时可能引用的文章间双链，已经在文章内容中包含 [[slug]]，后端扫描即可。