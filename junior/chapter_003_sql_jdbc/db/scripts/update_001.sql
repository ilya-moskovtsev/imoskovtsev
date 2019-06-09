create table items
(
    id          serial primary key,
    name        varchar(200),
    assignee    varchar(200),
    description text,
    create_date timestamp,
    update_date timestamp
);