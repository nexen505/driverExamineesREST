create schema public;

create table exdr_owner
(
  id            serial not null
    constraint exdr_owner_pkey
    primary key,
  date_of_birth timestamp,
  name          varchar(255),
  patronymic    varchar(255),
  surname       varchar(255)
);

create table exdr_type_of_vehicle
(
  id             serial not null
    constraint exdr_type_of_vehicle_pkey
    primary key,
  maximum_weight real,
  minimum_weight real,
  name           varchar(255)
);

create table exdr_transport
(
  id            serial  not null
    constraint exdr_transport_pkey
    primary key,
  brand         varchar(255),
  name          varchar(255),
  year_of_issue integer,
  id_owner      integer
    constraint fk1f2bbceb5d430d7e
    references exdr_owner,
  type_id       integer not null
    constraint fk1f2bbceb6a6344b7
    references exdr_type_of_vehicle
);

create table exdr_changes
(
  id                serial       not null
    constraint exdr_changes_pkey
    primary key,
  entity_class_name varchar(255) not null,
  details           varchar(255) not null,
  time              timestamp    not null,
  type              varchar(255) not null
);

create table exdr_changes_subscribers
(
  id                serial       not null
    constraint exdr_changes_subscribers_pkey
    primary key,
  email             varchar(255) not null,
  entity_class_name varchar(255) not null,
  type              varchar(255) not null
);

