-- 게시판 테이블
Create table board(
	idx 		int 			NOT NULL AUTO_INCREMENT,
	boardId		varchar(10) 	NOT NULL, 
	title 		varchar(50) 	NOT NULL,
	contents	text	NOT NULL,
	delYn		varchar(2)		NOT NULL,
	atchYn		varchar(2)		NOT NULL,
    readCnt		int				NULL,
	rgtId		varchar(20) 	NOT NULL,
	rgtDtm		varchar(15) 	NOT NULL,
	updId		varchar(20) 	NULL,
	updDtm		varchar(15) 	NULL,
    SCRYN       varchar(1)       NULL,
    SCRPW       varchar(4)       NULL,
    PRIMARY KEY (idx)
);

-- 첨부파일 테이블
Create table attach(
	idx 		int 			NOT NULL AUTO_INCREMENT,
	boardIdx	int 			NOT NULL, 
	attachName	varchar(50) 	NOT NULL,
	rgtId		varchar(20) 	NOT NULL,
	rgtDtm		varchar(15) 	NOT NULL,
    PRIMARY KEY (idx)
);

-- 회원 정보
Create table member(
	idx			int 		NOT NULL AUTO_INCREMENT,
	userId		varchar(20) NOT NULL,
	pw			varchar(30) NOT NULL,
	name 		varchar(50) NOT NULL,
	email1		varchar(50) NULL,
	email2		varchar(50) NULL,
	isAdminYn	varchar(2)	NOT NULL,
	delYn		varchar(2)	NOT NULL,
	rgtId		varchar(20) NOT NULL,
	rgtDtm		varchar(15) NOT NULL,
	updId		varchar(20) NULL,
	updDtm		varchar(15) NULL,
    PRIMARY KEY (idx)
);

-- 댓글
CREATE TABLE `reply` (
	`idx` INT(11) NOT NULL AUTO_INCREMENT,
	`bno` INT(11) NULL DEFAULT NULL,
	`contents` TEXT NULL COLLATE 'utf8_unicode_ci',
	`rgtId` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`rgtDtm` VARCHAR(15) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`updId` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`updDtm` VARCHAR(15) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	PRIMARY KEY (`idx`)
);

-- 접속정보
CREATE TABLE visit_info(
	idx 		int 		NOT NULL AUTO_INCREMENT,
    ip          varchar(20) NOT NULL,
    url         TEXT NOT NULL,
	rgtId		varchar(20) NOT NULL,
	rgtDtm		varchar(15) NOT NULL,
	updId		varchar(20) NULL,
	updDtm		varchar(15) NULL,
    PRIMARY KEY (idx)
);