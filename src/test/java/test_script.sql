drop table if exists t_user;
CREATE TABLE `t_user` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT 'id' ,
`user_name`  varchar(255) NULL COMMENT '用户名' ,
`pazzword`  varchar(255) NULL COMMENT '密码' ,
`age`  int NULL COMMENT '年龄' ,
PRIMARY KEY (`id`)
);

insert into t_user values (null ,'tom','123',18);
insert into t_user values (null ,'jack','123',18);
insert into t_user values (null ,'mac','123',18);
