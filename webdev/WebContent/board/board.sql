
/* Drop Tables */

DROP TABLE tb_board CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE seq_board;




/* Create Sequences */

CREATE SEQUENCE seq_board;



/* Create Tables */

CREATE TABLE tb_board
(
	no number NOT NULL,
	name varchar2(20) NOT NULL,
	title varchar2(100) NOT NULL,
	content varchar2(4000) NOT NULL,
	pwd varchar2(128) NOT NULL,
	regdate date DEFAULT SYSDATE NOT NULL,
	viewcount number DEFAULT 0 NOT NULL,
	PRIMARY KEY (no)
);

select * from tb_board;

select no, name, title, regdate, viewcount
from tb_board
order by no desc;

select no, name, title, regdate, viewcount, content
from tb_board
where no=1;


select b.*
from (select rownum as rnum, a.*
from (select  no, title, name, regdate, viewcount
from tb_board order 
by no desc) a) b
where rnum between 11 and 20


select count(no) from tb_board;

update tb_board set pwd=(select pwd from tb_board where no = 1);
