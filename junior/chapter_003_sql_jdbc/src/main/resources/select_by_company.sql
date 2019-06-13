CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);


insert into company (id, name)
VALUES (1, 'company 1');
insert into company (id, name)
VALUES (2, 'company 2');
insert into company (id, name)
VALUES (3, 'company 3');
insert into company (id, name)
VALUES (4, 'company 4');
insert into company (id, name)
VALUES (5, 'company 5');
insert into company (id, name)
VALUES (6, 'company 6');


select *
from company;


insert into person (id, name, company_id)
values (1, 'person 1', 1);
insert into person (id, name, company_id)
values (2, 'person 2', 2);
insert into person (id, name, company_id)
values (3, 'person 3', 3);
insert into person (id, name, company_id)
values (4, 'person 4', 4);
insert into person (id, name, company_id)
values (5, 'person 5', 5);
insert into person (id, name, company_id)
values (6, 'person 6', 6);
insert into person (id, name, company_id)
values (7, 'person 7', 1);


select *
from person;


-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
select p.name, c.name
from person as p
         inner join company c on p.company_id = c.id
where c.id != 5;
-- 2) Select the name of the company with the maximum number of persons
-- + number of persons in this company
select c.name, count(p.name) as count
from company as c
         inner join person p on p.company_id = c.id
group by c.name
order by count desc
limit 1;
