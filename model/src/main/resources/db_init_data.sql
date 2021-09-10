INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration)
VALUES (1, 'name', 'description', 11, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 360);
INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration)
VALUES (2, '2nd name', '2nd description', 22, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 120);
INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration)
VALUES (3, 'zzzzzz', 'desc', 22, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 250);
INSERT INTO gift_certificate (id, name, description, price, create_date, last_update_date, duration)
VALUES (4, 'aaaa', 'asc', 22, '2020-12-12 21:34:10.769000', '2020-12-12 21:34:10.769000', 20);

INSERT INTO tag(id, name) VALUES (1, '1 name');
INSERT INTO tag(id, name) VALUES (2, '2 name');
INSERT INTO tag(id, name) VALUES (3, '3 name');
INSERT INTO tag(id, name) VALUES (4, '4 name');


INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag) VALUES (1, 1);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag) VALUES (1, 2);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag) VALUES (2, 4);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag) VALUES (2, 3);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag) VALUES (3, 2);
INSERT INTO gift_certificate_tag(id_gift_certificate, id_tag) VALUES (4, 1);

