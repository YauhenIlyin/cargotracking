--liquibase formatted sql

--changeset part1:create-user_tokens
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.user_tokens
CREATE TABLE public.user_tokens
(
    user_id         BIGINT         NOT NULL,
    issued_at_date  TIMESTAMP      NOT NULL,
    expired_at_date TIMESTAMP      NOT NULL,
    token           VARCHAR UNIQUE NOT NULL,
    is_not_blocked  BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT token_str_id PRIMARY KEY (token)
);
