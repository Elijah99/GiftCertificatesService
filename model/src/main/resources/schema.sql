CREATE TABLE gift_certificate (
    name character varying(50) NOT NULL,
    description character varying(255),
    price numeric NOT NULL,
    create_date timestamp with time zone NOT NULL,
    last_update_date timestamp with time zone NOT NULL,
    id bigint NOT NULL IDENTITY,
    duration integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tag (
    name character varying(50) NOT NULL,
    id bigint NOT NULL IDENTITY,
    PRIMARY KEY (id)
);


CREATE TABLE gift_certificate_tag (
    id_gift_certificate bigint NOT NULL,
    id_tag bigint NOT NULL,
    id bigint NOT NULL IDENTITY,
    PRIMARY KEY (id),
    FOREIGN KEY (id_gift_certificate) REFERENCES gift_certificate(id),
    FOREIGN KEY (id_tag) REFERENCES tag(id)
);


