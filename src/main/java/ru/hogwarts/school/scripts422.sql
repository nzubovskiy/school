create table cars (
    id serial primary key,
    make varchar not null,
    model varchar not null,
    cost numeric not null);

create table peoples (
    name text primary key,
    age integer not null,
    driver_license boolean default true,
    car integer references cars (id));
