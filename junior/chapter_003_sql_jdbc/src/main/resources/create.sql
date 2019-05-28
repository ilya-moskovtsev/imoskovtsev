-- drop database postgres;

-- drop table users_roles;
-- drop table roles;
-- drop table comments;
-- drop table attachments;
-- drop table items;
-- drop table users;
-- drop table categories;
-- drop table states;


create database postgres;

create table users
(
    id       serial primary key,
    login    varchar(200),
    password varchar(200)
);

create table roles
(
    id   serial primary key,
    name varchar(200)
);

create table users_roles
(
    id      serial primary key,
    user_id integer references users (id),
    role_id integer references roles (id)
);

create table states
(
    id   serial primary key,
    name varchar(200)
);

create table categories
(
    id   serial primary key,
    name varchar(200)
);

create table items
(
    id          serial primary key,
    description text,
    user_id     integer references users (id),
    state_id    integer references states (id),
    category_id integer references categories (id)
);

create table comments
(
    id      serial primary key,
    content text,
    item_id integer references items (id)
);

create table attachments
(
    id      serial primary key,
    path    text,
    item_id integer references items (id)
);


insert into users (id, login, password)
values (1, 'login', 'password');

insert into roles (id, name)
values (1, 'admin');

insert into users_roles (id, user_id, role_id)
values (1, 1, 1);

insert into states (id, name)
values (1, 'new');

insert into categories (id, name)
values (1, 'support');

insert into items (id, description, user_id, state_id, category_id)
values (1, 'description', 1, 1, 1);

insert into comments (id, content, item_id)
values (1, 'content', 1);

insert into attachments(id, path, item_id)
values (1, 'path', 1);