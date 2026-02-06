CREATE TABLE usuarios(
    id bigint not null auto_increment,
    login VARCHAR(100) NOT NULL UNIQUE,
     senha VARCHAR(250) NOT NULL,

     PRIMARY KEY(id)
);