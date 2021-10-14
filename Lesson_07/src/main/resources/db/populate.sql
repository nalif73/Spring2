CREATE TABLE product (
                         id serial primary key,
                         name text not null,
                         price int not null,
                         categories_id int not null,
                         image_link text
);

INSERT INTO product (name, price, categories_id)
VALUES ('Вода', 20, 1),
       ('Пальто', 35, 3),
       ('Рубашка', 20, 3),
       ('Чай', 9, 1),
       ('CD-проигрыватель', 100, 2),
       ('Молоко', 13, 1),
       ('Смартофон', 89, 2),
       ('Брюки', 13, 3),
       ('Телевизор', 13, 2)
;

CREATE TABLE category (
                          id serial primary key,
                          name text not null
);

INSERT INTO category (name)
VALUES ('Продукты'),
       ('Электроника'),
       ('Одежда')
;

create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    enabled  boolean default true,
    primary key (id)
);

create table role
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE user_role
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references role (id)
);

insert into role (name)
values ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('user1', '$2a$10$Mo6LNuECoMwc7yZWBbp9Hu9uQbeiG1us3cIScLfdeXkRaEVF5Eu3e'),
       ('user2', '$2a$10$3cFQKs01lhVM4nAvvk89Ae2yylKV6xNLvYvHgDMgFdnmxFYbNK5.a'),
       ('admin', '$2a$10$TjuMjM.pUSisYR0.7h5WSOAYjjvcR4fh5slqAhIZthon72Dg6rVLS');

insert into user_role (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2),
       (3, 3);