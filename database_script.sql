CREATE ROLE temp WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'temp';


CREATE DATABASE temp
    WITH 
    OWNER = temp
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;


CREATE TABLE addresses
(
        id serial,
        street varchar(100),
        line1 varchar(100),
        line2 varchar(100),
        country varchar(100),
        postcode varchar(100),
        state_id int,
        CONSTRAINT addresses_pk PRIMARY KEY (id)
);

CREATE TABLE timeslots
(
        id serial,
        start_time bigint,
        end_time bigint,
        booked_deliveries_amount int,
        CONSTRAINT timeslots_pk PRIMARY KEY (id)
);

CREATE TABLE deliveries
(
        id serial,
        timeslot_id int,
        user_name varchar(100),
        completed boolean,
        CONSTRAINT deliveries_pk PRIMARY KEY (id),
        CONSTRAINT deliveries_timeslot_id_fk FOREIGN KEY (timeslot_id)
              REFERENCES timeslots (id) MATCH SIMPLE
              ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE timeslot_address
(
        id serial,
        timeslot_id bigint,
        supported_delivery_state varchar(100),
        CONSTRAINT timeslot_address_timeslot_id_fk FOREIGN KEY (timeslot_id)
              REFERENCES timeslots (id) MATCH SIMPLE
              ON UPDATE NO ACTION ON DELETE NO ACTION
);


