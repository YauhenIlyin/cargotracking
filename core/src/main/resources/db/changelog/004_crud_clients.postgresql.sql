--liquibase formatted sql

--changeset part1:create-clients
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.clients
CREATE TABLE public.clients
(
    id          BIGINT PRIMARY KEY NOT NULL,
    client_name VARCHAR(20)        NOT NULL,
    status      VARCHAR(20)        NOT NULL,
    admin_id    BIGINT,
    delete_date TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (admin_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

--changeset part2:alter-table-users-add_fk_clientId
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.users
ALTER TABLE users
    ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients (id);
