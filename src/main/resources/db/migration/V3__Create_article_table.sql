create table article
(
	id int auto_increment primary key,
	title varchar(50),
	description text,
	gtm_create bigint,
	gtm_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256)
);