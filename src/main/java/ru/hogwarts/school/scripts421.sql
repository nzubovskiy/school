alter table student add constraint age_constraint check (age >= 16);
alter table student add constraint name_unique unique(name);
alter table student alter name set not null;
alter table student alter age set default 20;
alter table faculty
    add constraint name_color_unique unique (name, color);
