create table itempedido(
    id bigint not null auto_increment,
    produto varchar(200) not null,
    quantidade integer CHECK( quantidade <= 50) not null,
    precounitario decimal(10,2) not null,

    PRIMARY KEY(id)
);