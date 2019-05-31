drop table cars;
drop table bodies;
drop table engines;
drop table transmissions;

create table bodies
(
    id   serial primary key,
    type varchar(200)
);

create table engines
(
    id     serial primary key,
    layout varchar(200)
);

create table transmissions
(
    id   serial primary key,
    type varchar(200)
);

create table cars
(
    id              serial primary key,
    model           varchar(200),
    body_id         integer references bodies (id),
    engine_id       integer references engines (id),
    transmission_id integer references transmissions (id)
);

insert into bodies(type)
values ('Coupe');
insert into bodies(type)
values ('Sedan');
insert into bodies(type)
values ('Hatchback');
insert into bodies(type)
values ('Station wagon');
insert into bodies(type)
values ('Sport utility vehicle');
insert into bodies(type)
values ('Minivan');
insert into bodies(type)
values ('Pickup truck');
insert into bodies(type)
values ('Crossover');
insert into bodies(type)
values ('Convertible car');
insert into bodies(type)
values ('Sport cars');

insert into engines (layout)
values ('Straight');
insert into engines (layout)
values ('Inline');
insert into engines (layout)
values ('V');
insert into engines (layout)
values ('Flat');

insert into transmissions(type)
values ('Manual');
insert into transmissions(type)
values ('Automatic');
insert into transmissions(type)
values ('Continuously variable');
insert into transmissions(type)
values ('Semi-automatic');

insert into cars (model, body_id, engine_id, transmission_id)
values ('model1', 1, 2, 3);
insert into cars(model, body_id, engine_id, transmission_id)
values ('model2', 2, 2, 2);
insert into cars(model, body_id, engine_id, transmission_id)
values ('model3', 3, 2, 1);

-- Queries:
-- 1. all cars and all parts attached to them
select c.model  as car_model,
       b.type   as body_type,
       e.layout as engine_layout,
       t.type   as transmission_type
from cars as c
         inner join bodies b on c.body_id = b.id
         inner join engines e on c.engine_id = e.id
         inner join transmissions t on c.transmission_id = t.id;

-- 2. parts that are not used in the car. Bodies, engines, transmissions separately.
select b.type as not_used_body_type
from cars as c
         right outer join bodies b on c.body_id = b.id
where c.model isnull;

select e.layout not_used_engine_layout
from cars as c
         right outer join engines e on c.engine_id = e.id
where c.model isnull;

select t.type as not_used_transmission_type
from cars as c
         right outer join transmissions t on c.transmission_id = t.id
where c.model isnull;