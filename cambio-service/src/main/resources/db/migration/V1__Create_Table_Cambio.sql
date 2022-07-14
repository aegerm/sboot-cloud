create table cambio (
	id int auto_increment primary key,
    from_currency char(3) not null,
    to_currency char(3) not null,
    conversion_factor decimal(65,2) not null
);