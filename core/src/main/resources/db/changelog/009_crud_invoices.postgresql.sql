--liquibase formatted sql

--changeset part1:create-invoices
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.invoices
CREATE TABLE public.invoices
(
    id                BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    storage_id        BIGINT      NOT NULL,
    product_owner_id  BIGINT      NOT NULL,
    creator_id        BIGINT      NOT NULL,
    driver_id         BIGINT      NOT NULL,
    creation_date     DATE,
    verification_date DATE,
    number            VARCHAR(10) NOT NULL,
    status            VARCHAR(35),

    FOREIGN KEY (storage_id) REFERENCES public.storages (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_owner_id) REFERENCES public.product_owners (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (creator_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

--changeset part2:create-products
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition tableExists public.products
CREATE TABLE public.products
(
    id         BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name       VARCHAR(50),
    amount     INTEGER NOT NULL,
    invoice_id BIGINT,

    FOREIGN KEY (invoice_id) REFERENCES public.invoices (id) ON UPDATE CASCADE ON DELETE CASCADE
);