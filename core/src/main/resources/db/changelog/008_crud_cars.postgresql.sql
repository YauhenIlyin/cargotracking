--liquibase formatted sql

--changeset part1:create-cars
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.cars
CREATE TABLE public.cars
(
    id               BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    client_id        BIGINT        NOT NULL,
    number           VARCHAR(10)   NOT NULL,
    fuel_consumption NUMERIC(6, 2) NOT NULL,
    load_capacity    NUMERIC(6, 2) NOT NULL,
    car_type         VARCHAR(20)   NOT NULL,

    FOREIGN KEY (client_id) REFERENCES public.clients (id) ON UPDATE CASCADE ON DELETE CASCADE
);