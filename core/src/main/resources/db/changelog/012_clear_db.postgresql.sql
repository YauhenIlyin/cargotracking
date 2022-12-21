--liquibase formatted sql

--changeset part1:drop-user_tokens
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.user_tokens
DROP TABLE public.user_tokens;