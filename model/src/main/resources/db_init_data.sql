INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration) OVERRIDING SYSTEM VALUE
VALUES (1, 'name', 'description', 11, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 360);
INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration) OVERRIDING SYSTEM VALUE
VALUES (2, '2nd name', '2nd description', 22, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 120);
INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration) OVERRIDING SYSTEM VALUE
VALUES (3, '3rd name', 'desc', 22, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 250);
INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration) OVERRIDING SYSTEM VALUE
VALUES (4, '4th name', 'asc', 22, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 20);

INSERT INTO tag(id, name) OVERRIDING SYSTEM VALUE
VALUES (1, '1 name');
INSERT INTO tag(id, name) OVERRIDING SYSTEM VALUE
VALUES (2, '2 name');
INSERT INTO tag(id, name) OVERRIDING SYSTEM VALUE
VALUES (3, '3 name');
INSERT INTO tag(id, name) OVERRIDING SYSTEM VALUE
VALUES (4, '4 name');


INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag)
VALUES (1, 1);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag)
VALUES (1, 2);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag)
VALUES (2, 4);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag)
VALUES (2, 3);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag)
VALUES (3, 2);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag)
VALUES (4, 1);


INSERT INTO USER(id, name, login, password, role) OVERRIDING SYSTEM VALUE
VALUES (1, '1st name', '1st login', '1st password', 'ROLE_USER');
INSERT INTO USER(id, name, login, password, role) OVERRIDING SYSTEM VALUE
VALUES (2, '2nd name', '2nd login', '2nd password', 'ROLE_USER');
INSERT INTO USER(id, name, login, password, role) OVERRIDING SYSTEM VALUE
VALUES (3, '3rd name', '3rd login', '3rd password', 'ROLE_USER');
INSERT INTO USER(id, name, login, password, role) OVERRIDING SYSTEM VALUE
VALUES (4, '4th name', '4th login', '4th password', 'ROLE_ADMIN');

INSERT INTO "ORDER"(id, cost, purchase_date, id_user) OVERRIDING SYSTEM VALUE
VALUES (1, 33, '2020-12-12 21:34:10.769000', 1);
INSERT INTO "ORDER"(id, cost, purchase_date, id_user) OVERRIDING SYSTEM VALUE
VALUES (2, 44, '2020-12-12 21:34:10.769000', 1);
INSERT INTO "ORDER"(id, cost, purchase_date, id_user) OVERRIDING SYSTEM VALUE
VALUES (3, 77, '2020-12-12 21:34:10.769000', 2);
INSERT INTO "ORDER"(id, cost, purchase_date, id_user) OVERRIDING SYSTEM VALUE
VALUES (4, 22, '2020-12-12 21:34:10.769000', 3);
INSERT INTO "ORDER"(id, cost, purchase_date, id_user) OVERRIDING SYSTEM VALUE
VALUES (5, 44, '2020-12-12 21:34:10.769000', 3);

INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (1, 1);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (1, 2);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (2, 3);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (2, 4);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (3, 1);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (3, 2);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (3, 3);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (3, 4);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (4, 3);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (5, 2);
INSERT INTO order_gift_certificate(id_order, id_gift_certificate)
VALUES (5, 3);



