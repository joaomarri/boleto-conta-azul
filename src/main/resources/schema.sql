create table teste
(
   id integer not null,
   name varchar(255) not null,
   primary key(id)
);

create table bankslips (
	id varchar(255) not null,
	due_date date not null,
	total_in_cents numeric not null,
	customer varchar(200) not null,
	status varchar(20) not null,
	primary key(id)
);