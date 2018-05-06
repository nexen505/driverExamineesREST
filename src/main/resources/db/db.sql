CREATE TABLE exdr_owner
(
  id            SERIAL NOT NULL
    CONSTRAINT exdr_owner_pkey
    PRIMARY KEY,
  date_of_birth TIMESTAMP,
  name          VARCHAR(255),
  patronymic    VARCHAR(255),
  surname       VARCHAR(255)
);

CREATE TABLE exdr_transport
(
  id            SERIAL  NOT NULL
    CONSTRAINT exdr_transport_pkey
    PRIMARY KEY,
  brand         VARCHAR(255),
  name          VARCHAR(255),
  year_of_issue INTEGER,
  id_owner      INTEGER
    CONSTRAINT fk1f2bbceb5d430d7e
    REFERENCES exdr_owner,
  type_id       INTEGER NOT NULL
);

CREATE TABLE exdr_type_of_vehicle
(
  id             SERIAL NOT NULL
    CONSTRAINT exdr_type_of_vehicle_pkey
    PRIMARY KEY,
  maximum_weight REAL,
  minimum_weight REAL,
  name           VARCHAR(255)
);

ALTER TABLE exdr_transport
  ADD CONSTRAINT fk1f2bbceb6a6344b7
FOREIGN KEY (type_id) REFERENCES exdr_type_of_vehicle;

