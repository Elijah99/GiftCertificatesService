CREATE TABLE IF NOT EXISTS public.gift_certificate
(
    name             character varying(50),
    description      character varying(255),
    price            numeric                  NOT NULL,
    create_date      timestamp with time zone NOT NULL,
    last_update_date timestamp with time zone NOT NULL,
    id               bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    duration         integer                  NOT NULL,
    CONSTRAINT gift_certificate_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.gift_certificate_audit
(
    id               bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_row           bigint,
    name             character varying(50),
    description      character varying(255),
    price            numeric,
    create_date      timestamp with time zone,
    last_update_date timestamp with time zone,
    duration         integer,
    operation        character varying(10),
    audit_date       timestamp with time zone NOT NULL,
    CONSTRAINT gift_certificate_audit_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tag
(
    name character varying(50),
    id   bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    CONSTRAINT tag_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tag_audit
(
    id         bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_row     bigint,
    name       character varying(50),
    operation  character varying(10),
    audit_date timestamp with time zone NOT NULL,
    CONSTRAINT tag_audit_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.user
(
    id   bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(50),
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_name_key UNIQUE (name)
);


CREATE TABLE IF NOT EXISTS public.user_audit
(
    id         bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_row     bigint,
    name       character varying(50),
    operation  character varying(10),
    audit_date timestamp with time zone NOT NULL,
    CONSTRAINT user_audit_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.order
(
    id            bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    cost          numeric                  NOT NULL,
    purchase_date timestamp with time zone NOT NULL,
    id_user       bigint                   NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT order_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.order_audit
(
    id            bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_row        bigint,
    cost          numeric                  NOT NULL,
    purchase_date timestamp with time zone NOT NULL,
    operation     character varying(10),
    audit_date    timestamp with time zone NOT NULL,
    CONSTRAINT order_audit_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.gift_certificate_tag
(
    id_gift_certificate bigint,
    id_tag              bigint,
    id                  bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    CONSTRAINT "FK_gift_certificate" FOREIGN KEY (id_gift_certificate)
        REFERENCES public.gift_certificate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_tag" FOREIGN KEY (id_tag)
        REFERENCES public.tag (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.gift_certificate_tag_audit
(
    id                  bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_row              bigint,
    id_gift_certificate bigint,
    id_tag              bigint,
    operation           character varying(10),
    audit_date          timestamp with time zone NOT NULL,
    CONSTRAINT gift_certificate_tag_audit_pkey PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS public.order_gift_certificate
(
    id                  bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_order            bigint NOT NULL,
    id_gift_certificate bigint NOT NULL,
    CONSTRAINT order_gift_certificate_pkey PRIMARY KEY (id),
    CONSTRAINT order_gift_certificate_id_gift_certificate_fkey FOREIGN KEY (id_gift_certificate)
        REFERENCES public.gift_certificate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT order_gift_certificate_id_order_fkey FOREIGN KEY (id_order)
        REFERENCES public.order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.order_gift_certificate_audit
(
    id                  bigint                   NOT NULL GENERATED ALWAYS AS IDENTITY,
    id_order            bigint,
    id_gift_certificate bigint                   NOT NULL,
    operation           character varying(10),
    audit_date          timestamp with time zone NOT NULL,
    id_row              bigint                   NOT NULL,
    CONSTRAINT order_gift_certificate_audit_pkey PRIMARY KEY (id)
);




