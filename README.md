预计工时：2周

实际工时：2周半

数据库选择：Mysql+Redis
数据表设计：
user表:

+--------------+-------------+------+-----+---------+----------------+
| Field        | Type        | Null | Key | Default | Extra          |
+--------------+-------------+------+-----+---------+----------------+
| id           | bigint      | NO   | PRI | NULL    | auto_increment |
| user_name    | varchar(50) | YES  |     | NULL    |                |
| pwd          | varchar(32) | YES  |     | NULL    |                |
| mobile       | varchar(20) | YES  |     | NULL    |                |
| email        | varchar(32) | YES  |     | NULL    |                |
| avatar       | varchar(32) | YES  |     | NULL    |                |
| gmt_created  | datetime    | YES  |     | NULL    |                |
| gmt_modified | datetime    | YES  |     | NULL    |                |
+--------------+-------------+------+-----+---------+----------------+
comment表:
+--------------+---------------+------+-----+---------+----------------+
| Field        | Type          | Null | Key | Default | Extra          |
+--------------+---------------+------+-----+---------+----------------+
| id           | bigint        | NO   | PRI | NULL    | auto_increment |
| ref_id       | varchar(100)  | YES  |     | NULL    |                |
| user_id      | bigint        | YES  |     | NULL    |                |
| content      | varchar(1000) | YES  |     | NULL    |                |
| parent_id    | bigint        | YES  |     | NULL    |                |
| gmt_created  | datetime      | YES  |     | NULL    |                |
| gmt_modified | datetime      | YES  |     | NULL    |                |
| favor        | int           | YES  |     | NULL    |                |
+--------------+---------------+------+-----+---------+----------------+
content表：
+--------------+--------------+------+-----+---------+-------+
| Field        | Type         | Null | Key | Default | Extra |
+--------------+--------------+------+-----+---------+-------+
| id           | varchar(100) | NO   | PRI | NULL    |       |
| title        | varchar(50)  | YES  |     | NULL    |       |
| detail       | text         | YES  |     | NULL    |       |
| media        | text         | YES  |     | NULL    |       |
| favor        | int          | YES  |     | NULL    |       |
| user_id      | bigint       | YES  |     | NULL    |       |
| gmt_created  | datetime     | YES  |     | NULL    |       |
| gmt_modified | datetime     | YES  |     | NULL    |       |
+--------------+--------------+------+-----+---------+-------+

触发登录事件：主页面的喜欢按钮和踩按钮，以及登录按钮，评论界面的喜欢，回复，踩，举报按钮.

