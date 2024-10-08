--liquibase formatted sql

--changeset part1:create-users
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.users
CREATE TABLE public.users
(
    id            BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    first_name    VARCHAR(20),
    last_name     VARCHAR(20)        NOT NULL,
    patronymic    VARCHAR(20),
    client_id     BIGINT,
    born_date     DATE,
    email         VARCHAR(50)        NOT NULL,
    town          VARCHAR(20),
    street        VARCHAR(20),
    house         VARCHAR(5),
    flat          VARCHAR(5),
    login         VARCHAR(15) UNIQUE NOT NULL,
    user_password VARCHAR(72)        NOT NULL,
    passport_num  VARCHAR(30),
    issued_by     VARCHAR(50)
);

--changeset part2:create-user_roles
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.user_roles
CREATE TABLE public.user_roles
(
    id        BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    role_name VARCHAR(15) UNIQUE NOT NULL
);

--changeset part3:create-users_user_roles
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.users_user_roles
CREATE TABLE public.users_user_roles
(
    user_id      BIGINT,
    user_role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_role_id) REFERENCES user_roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT user_user_role_pk PRIMARY KEY (user_id, user_role_id)
);

--changeset part4:insert-user_roles
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM users_user_roles
INSERT INTO public.user_roles (role_name)
values ('SYS_ADMIN');
INSERT INTO public.user_roles (role_name)
values ('ADMIN');
INSERT INTO public.user_roles (role_name)
values ('DISPATCHER');
INSERT INTO public.user_roles (role_name)
values ('MANAGER');
INSERT INTO public.user_roles (role_name)
values ('DRIVER');
INSERT INTO public.user_roles (role_name)
values ('COMPANY_OWNER');

