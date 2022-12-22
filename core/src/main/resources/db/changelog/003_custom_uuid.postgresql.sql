--liquibase formatted sql

--changeset part-1:create-custom_uuid
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.custom_uuid
CREATE TABLE public.custom_uuid
(
    user_id         BIGINT UNIQUE  NOT NULL,
    issued_at_date  TIMESTAMP      NOT NULL,
    expired_at_date TIMESTAMP      NOT NULL,
    uuid            VARCHAR UNIQUE NOT NULL,
    destination     VARCHAR        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT custom_uuid_table_id PRIMARY KEY (user_id, destination)
);
