create table myboard
(
	  no number primary key,
	  writer varchar2(20),
      passwd varchar2(20),
	  subject varchar2(50),
	  content varchar2(100),
	  readcount number,
	  register date 
);

insert into myboard values(myboard_seq.nextval,'홍길동','1234','게시판연습','게시판내용',0,sysdate);

create sequence myboard_seq;

select * from myboard;

select count(*) from myboard;

select * from seq;