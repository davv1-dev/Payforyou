
create table pedido(

  id bigint not null auto_increment,
  datacriacao datetime not null,
  status varchar(50) not null not null,
  valortotal decimal(10,2) not null,


  primary key (id)
);

