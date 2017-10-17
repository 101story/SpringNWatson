
/* Drop Tables */

DROP TABLE t_meno CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE seq_meno;




/* Create Sequences */

CREATE SEQUENCE seq_memo;



/* Create Tables */

CREATE TABLE t_memo
(
	no number NOT NULL,
	title varchar2(100) NOT NULL,
	content varchar2(1000) NOT NULL,
	name varchar2(20) NOT NULL,
	PRIMARY KEY (no)
);

alter table t_meno rename to t_memo;

select * from t_memo;
