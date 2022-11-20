create table memberTest(
	id varchar(255) primary key,
	password varchar(255) not null,
	money int(255) not null
)

alter table member modify column password varchar(255);

select * from member
delete from member