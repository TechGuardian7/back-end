drop schema if exists techguardian;

drop user if exists 'user'@'localhost';

create schema techguardian;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on techguardian.* to user@'localhost';

use techguardian;

create table registro_entrada (
    ent_id bigint not null auto_increment,
    data_entrada varchar(10) not null,
    hora_entrada varchar(10) not null, 
    quant_entrada int not null,
    obs_entrada varchar(250),
    primary key (ent_id)
);

create table registro_saida (
    sai_id bigint not null auto_increment,
    data_saida varchar(10) not null,
    hora_saida varchar(10) not null,
    quant_saida int not null,
    obs_saida varchar(250),
    primary key (sai_id)
);

insert into registro_entrada (ent_id, data_entrada, hora_entrada, quant_entrada, obs_entrada)
    values (1, '2024-04-03', '19:10:00', '1', 'registrando a entrada');
insert into registro_saida (sai_id, data_saida, hora_saida, quant_saida, obs_saida)
    values (1, '2024-04-02', '19:20:00', '1,' 'registrando a saida');