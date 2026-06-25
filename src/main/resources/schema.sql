-- ============================================
-- 1. 独立表（无外键依赖）
-- ============================================

create table if not exists users
(
    id          bigint unsigned auto_increment primary key,
    username    varchar(50)  not null unique,
    password    varchar(255) not null comment 'bcrypt 加密',
    email       varchar(100) null,
    avatar      varchar(500) null comment '头像URL',
    role        varchar(50)  null comment '昵称',
    created_at  datetime default CURRENT_TIMESTAMP not null,
    updated_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint uk_email unique (email),
    constraint uk_username unique (username)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';

create table if not exists categories
(
    id          bigint unsigned auto_increment primary key,
    name        varchar(50)  not null,
    slug        varchar(50)  not null unique,
    description varchar(200) null,
    created_at  datetime     default CURRENT_TIMESTAMP not null
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '文章分类';

create table if not exists tags
(
    id         bigint unsigned auto_increment primary key,
    name       varchar(50) not null,
    slug       varchar(50) not null unique,
    created_at datetime    default CURRENT_TIMESTAMP not null
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '标签';

-- ============================================
-- 2. 依赖 users 的表
-- ============================================

create table if not exists statuses
(
    id            bigint unsigned auto_increment primary key,
    user_id       bigint unsigned                    not null,
    music_title   varchar(200)                       null,
    music_artist  varchar(200)                       null,
    music_cover   varchar(500)                       null,
    music_url     varchar(500)                       null,
    reading_title varchar(200)                       null,
    reading_cover varchar(500)                       null,
    location      varchar(200)                       null,
    created_at    datetime default CURRENT_TIMESTAMP not null,
    updated_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint uk_user_id unique (user_id)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table if not exists notes
(
    id         bigint unsigned auto_increment primary key,
    content    varchar(280)                           not null,
    user_id    bigint unsigned                        not null,
    likes      int unsigned default '0'               not null,
    created_at datetime     default CURRENT_TIMESTAMP not null
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '短内容';

-- ============================================
-- 3. 依赖 categories 和 users 的表
-- ============================================

create table if not exists articles
(
    id           bigint unsigned auto_increment primary key,
    title        varchar(200)                         not null,
    slug         varchar(200)                         not null unique,
    summary      varchar(500)                         null,
    cover        varchar(500)                         null,
    content      longtext                             not null,
    category_id  bigint unsigned                      null,
    user_id      bigint unsigned                      not null,
    is_published tinyint(1) default 1                 not null,
    created_at   datetime   default CURRENT_TIMESTAMP not null,
    updated_at   datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '文章表';

create index idx_articles_category_id on articles (category_id);
create index idx_articles_created_at on articles (created_at);
create index idx_articles_user_id on articles (user_id);

-- ============================================
-- 4. 关联表（多对多）
-- ============================================

create table if not exists article_tags
(
    article_id bigint unsigned not null,
    tag_id     bigint unsigned not null,
    primary key (article_id, tag_id)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '文章标签关联';

create index idx_article_tags_tag_id on article_tags (tag_id);

-- ============================================
-- 5. 其他无外键依赖的表
-- ============================================

create table if not exists about_timeline
(
    id          bigint auto_increment primary key,
    year        varchar(20)                        not null comment '时间节点',
    title       varchar(100)                       not null comment '标题',
    description text                               null comment '描述',
    sort_order  int      default 0                 null comment '排序',
    created_at  datetime default CURRENT_TIMESTAMP null,
    updated_at  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '关于页时间线';

create table if not exists animes
(
    id                bigint unsigned auto_increment primary key,
    title             varchar(200)                       not null,
    title_ja          varchar(200)                       null,
    cover             varchar(500)                       null,
    summary           text                               null,
    type              varchar(50)                        null,
    episodes_total    int                                null,
    season            varchar(20)                        null,
    rating            decimal(3, 1)                      null,
    air_date          date                               null,
    production_status tinyint  default 0                 null,
    created_at        datetime default CURRENT_TIMESTAMP not null,
    updated_at        datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '番剧元数据';

create index idx_animes_air_date on animes (air_date);
create index idx_animes_season on animes (season);
create index idx_animes_title on animes (title);

create table if not exists footprint
(
    id         int unsigned auto_increment primary key,
    user_id    int unsigned                       not null,
    city       varchar(100)                       not null,
    country    varchar(100)                       null,
    lat        double                             not null,
    lng        double                             not null,
    date       date                               null,
    note       text                               null,
    photo      varchar(500)                       null,
    created_at datetime default CURRENT_TIMESTAMP not null,
    updated_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户足迹';

create index idx_footprint_date on footprint (date);
create index idx_footprint_user_id on footprint (user_id);

create table if not exists friend_link
(
    id          int unsigned auto_increment primary key,
    user_id     int unsigned                       not null,
    name        varchar(100)                       not null,
    url         varchar(500)                       not null,
    avatar      varchar(500)                       null,
    city        varchar(100)                       null,
    lat         double                             null,
    lng         double                             null,
    description text                               null,
    created_at  datetime default CURRENT_TIMESTAMP not null,
    updated_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户友链';

create index idx_friend_link_user_id on friend_link (user_id);

create table if not exists note_likes
(
    id         bigint unsigned auto_increment primary key,
    note_id    bigint unsigned                    not null,
    user_id    bigint unsigned                    not null,
    created_at datetime default CURRENT_TIMESTAMP not null,
    constraint uk_note_user unique (note_id, user_id)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '短内容点赞记录';

create index idx_note_likes_user_id on note_likes (user_id);

create table if not exists tarot_cards
(
    id          bigint auto_increment primary key,
    name        varchar(50)                                   not null,
    name_en     varchar(100)                                  not null,
    type        enum ('major', 'minor')                       not null,
    suit        enum ('cups', 'wands', 'swords', 'pentacles') null,
    card_number tinyint                                       not null,
    description text                                          null,
    upright     text                                          null,
    reversed    text                                          null,
    keywords    varchar(200)                                  null,
    image_url   varchar(500)                                  null,
    created_at  datetime default CURRENT_TIMESTAMP            null,
    updated_at  datetime default CURRENT_TIMESTAMP            null on update CURRENT_TIMESTAMP,
    constraint uk_type_number_suit unique (type, card_number, suit)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '塔罗牌数据表';

create table user_animes
(
    id               bigint unsigned auto_increment
        primary key,
    user_id          bigint unsigned                    not null comment '用户ID',
    anime_id         bigint unsigned                    not null comment '番剧ID',
    watch_status     tinyint  default 0                 null comment '观看状态：0-想看,1-在看,2-已看完,3-弃了',
    episodes_watched int      default 0                 null comment '已看集数',
    rating           decimal(3, 1)                      null comment '个人评分',
    comment          varchar(500)                       null comment '个人短评',
    created_at       datetime default CURRENT_TIMESTAMP not null,
    updated_at       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint uk_user_anime
        unique (user_id, anime_id)
)
    comment '用户追番记录' charset = utf8mb4;