use member;

create table members(
id int primary key auto_increment,
name varchar(50) not null,
password varchar(50) not null,
sex char(10) not null,
age int not null,
phone varchar(50),
email varchar(50),
message varchar(50),
pic varchar(50)
);