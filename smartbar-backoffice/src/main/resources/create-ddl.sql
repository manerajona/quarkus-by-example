create table category
(
    id          bigint       not null
        primary key,
    description varchar(255) not null,
    name        varchar(255) not null
        constraint ukfoei036ov74bv692o5lh5oi66
            unique
);

alter table category
    owner to bob;

create table article
(
    price         numeric(38, 2) not null,
    category_id   bigint         not null
        constraint fkquyb17us3kaasf0h9xc8y24ox
            references category,
    id            bigint         not null
        primary key,
    description   varchar(255)   not null,
    name          varchar(255)   not null,
    picturebase64 varchar(255)   not null,
    constraint uk4tovo85iqd9ij6pt1t0ftna5q
        unique (name, category_id)
);

alter table article
    owner to bob;

create table sbo_table
(
    active    boolean      not null,
    seatcount integer      not null,
    id        bigint       not null
        primary key,
    name      varchar(255) not null
        constraint ukbx3vg5yawca8fgx3n79kfm0i4
            unique
);

alter table sbo_table
    owner to bob;
