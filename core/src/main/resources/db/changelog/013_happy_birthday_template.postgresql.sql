--liquibase formatted sql

--changeset part1:create-happy_birthday_template
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.clients
CREATE TABLE public.happy_birthday_template
(
    id        BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    template  VARCHAR(200),
    client_id BIGINT UNIQUE,
    FOREIGN KEY (client_id) REFERENCES clients (id) ON UPDATE CASCADE ON DELETE CASCADE
);