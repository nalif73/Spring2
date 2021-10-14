CREATE TABLE product (
    id serial primary key,
    name text not null,
    price int not null,
    categories_id int not null,
    image_link text
);