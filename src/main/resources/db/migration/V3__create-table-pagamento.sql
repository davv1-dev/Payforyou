CREATE TABLE pagamento(
      id bigint not null auto_increment,
      datapagamento datetime not null,
      status varchar(50) not null not null,
      valor decimal(10,2) not null,

      PRIMARY KEY(id)
);