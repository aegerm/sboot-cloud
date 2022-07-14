create table book (
    id int auto_increment primary key,
    author varchar(100) not null,
    launch_date date not null,
    price double not null,
    title varchar(250) not null
);