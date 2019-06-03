-- There are two table
-- products(id, name, type_id, expiration_date, price)
create table types
(
    id   serial,
    name varchar(200)
);
-- types(id, name)
create table products
(
    id              serial,
    name            varchar(200),
    type_id         integer references types (id),
    expiration_date timestamp,
    price           decimal(6, 2)
);

-- Queries:
-- 1. all products with type "CHEESE"
select *
from products as p
         inner join types t on p.type_id = t.id
where t.name = 'CHEESE';

-- 2. all products that name contains "ice cream"
select *
from products
where name like '%ice cream%';

-- 3. all products that shelf life ends next month
select *
from products
where expiration_date between now() + interval '1 month' and now() + interval '2 months';

-- 4. the most expensive product
select max(price)
from products;

-- 5. count products of certain type
select count(p.id)
from products as p
         inner join types t on p.type_id = t.id
where t.name = 'CHEESE';

-- 6. all products with type "CHEESE" or "MILK"
select *
from products as p
         inner join types t on p.type_id = t.id
where t.name in ('CHEESE', 'MILK');

-- 7. type of products with less than 10 left
select *
from (select t.name, count(p.id) as total
      from products as p
               inner join types t on p.type_id = t.id) as n
where n.total < 10;

-- 8. all products and their type
select p.name, t.name
from products as p
         inner join types t on p.type_id = t.id;