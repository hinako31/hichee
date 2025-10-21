CREATE DATABASE hichee  
CHARACTER SET utf8mb4  
COLLATE utf8mb4_unicode_ci;

USE hichee;

CREATE  TABLE areas(          id INT PRIMARY KEY      ,area_name VARCHAR(100) NOT null     ,sort_order INT );

CREATE TABLE diaries(     id INT AUTO_INCREMENT PRIMARY KEY      ,name VARCHAR(255) NOT null      ,period_year int      ,period_month int     ,user_id int NOT null REFERENCES users(id)     ,area_id int NOT null REFERENCES areas(id)     ,file_name VARCHAR(255) NOT null     ,file_path VARCHAR(512) NOT null     ,review VARCHAR(1000) NOT null     ,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP    ,updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  );

CREATE TABLE users(     id INT AUTO_INCREMENT PRIMARY KEY      ,name VARCHAR(64) NOT null      ,email VARCHAR(255) NOT null unique     ,pass VARCHAR(64) NOT null );

INSERT INTO areas (id, area_name) VALUES (1, '北海道'), (2, '青森県'), (3, '岩手県'), (4, '宮城県'), (5, '秋田県'), (6, '山形県'), (7, '福島県'), (8, '茨城県'), (9, '栃木県'), (10, '群馬県'), (11, '埼玉県'), (12, '千葉県'), (13, '東京都'), (14, '神奈川県'), (15, '新潟県'), (16, '富山県'), (17, '石川県'), (18, '福井県'), (19, '山梨県'), (20, '長野県'), (21, '岐阜県'), (22, '静岡県'), (23, '愛知県'), (24, '三重県'), (25, '滋賀県'), (26, '京都府'), (27, '大阪府'), (28, '兵庫県'), (29, '奈良県'), (30, '和歌山県'), (31, '鳥取県'), (32, '島根県'), (33, '岡山県'), (34, '広島県'), (35, '山口県'), (36, '徳島県'), (37, '香川県'), (38, '愛媛県'), (39, '高知県'), (40, '福岡県'), (41, '佐賀県'), (42, '長崎県'), (43, '熊本県'), (44, '大分県'), (45, '宮崎県'), (46, '鹿児島県'), (47, '沖縄県'), (0, '海外'), (99, '分からない');

UPDATE areas SET sort_order = id;