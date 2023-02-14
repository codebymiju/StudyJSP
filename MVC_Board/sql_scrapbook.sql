show tables;
CREATE TABLE board (
		board_num INT PRIMARY KEY,
		board_name VARCHAR(20) NOT NULL,
		board_pass VARCHAR(16) NOT NULL,
		board_subject VARCHAR(50) NOT NULL,
		board_content VARCHAR(2000) NOT NULL,
		board_file VARCHAR(200) NOT NULL,
		board_real_file VARCHAR(200) NOT NULL,
		board_re_ref INT NOT NULL,
		board_re_lev INT NOT NULL,
		board_re_seq INT NOT NULL,
		board_readcount INT DEFAULT 0,
		board_date DATETIME
	);
desc board;

select max(board_num) from board;

/* 암호화 패스워드 관리를 위해 passwd 컬럼 타입을 VARCHAR(50)으로 변경 */
ALTER TABLE member CHANGE passwd passwd VARCHAR(50) NOT NULL;

