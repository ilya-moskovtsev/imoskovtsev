-- Remove duplicates using SQL

-- The system has a table of cities with the following fields: id, name.
-- The system parses ads and saves city names.
-- There was a bug in the system code. It caused saving duplicate city names.
-- Moscow, Moscow, New York, London.
-- The bug was fixed at the application level, but the table still contains duplicates.
-- Your task is to write an sql script that will leave only unique city names in the table.

drop table cities;

create table cities
(
    id   serial primary key,
    name text
);

insert into cities (name)
values ('Moscow');
insert into cities (name)
values ('Moscow');
insert into cities (name)
values ('New York');
insert into cities (name)
values ('London');

select *
from cities;


-- See unique names:
select distinct name
from cities;

select name
from cities
group by name;


-- Delete duplicates:
delete
from cities duplicates using cities fullTable
where duplicates.name = fullTable.name
  and duplicates.id > fullTable.id;