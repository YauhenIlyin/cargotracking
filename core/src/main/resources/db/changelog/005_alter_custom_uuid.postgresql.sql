--liquibase formatted sql

--changeset part1:alter-table-custom_uuid
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.custom_uuid
ALTER TABLE public.custom_uuid
    ADD email varchar(100);