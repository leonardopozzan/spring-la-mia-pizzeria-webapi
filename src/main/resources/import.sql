INSERT INTO pizzeria.pizzas(name, description, price, image, created_at)VALUES('margherita', 'pomodoro, mozzarella, basilico', 4.50, "https://www.finedininglovers.it/sites/g/files/xknfdk1106/files/styles/recipes_1200_800_fallback/public/fdl_content_import_it/margherita-50kalo.jpg?itok=v9nHxNMS", NOW());
INSERT INTO pizzeria.pizzas(name, description, price, image, created_at)VALUES('prosciutto funghi', 'pomodoro, mozzarella, prosciutto, funghi', 6.50, "https://www.italianstylecooking.net/wp-content/uploads/2022/08/Pizza-mit-Schinken-und-Pilzen-768x512.jpg", NOW());
INSERT INTO pizzeria.pizzas(name, description, price, image, created_at)VALUES('diavola', 'pomodoro, mozzarella, salamino', 5.50, "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_rustica.jpg?itok=Lbp_jtZW", NOW());
INSERT INTO pizzeria.pizzas(name, description, price, image, created_at)VALUES('patatosa', 'pomodoro, mozzarella, papatine fritte', 5.50, "https://www.pizzeriagiuseppesalsiera.it/wp-content/uploads/2021/01/WhatsApp-Image-2021-01-19-at-14.03.20.jpeg", NOW());
INSERT INTO pizzeria.pizzas(name, description, price, image, created_at)VALUES('boscaiola', 'pomodoro, mozzarella, speck, salsiccia, funghi', 8.50, null, NOW());

INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-05-25', '2023-05-05', 'offerta di maggio', 1);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-04-25', '2023-04-02', 'offerta di primavera', 1);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-04-25', '2023-04-02', 'offerta studenti', 1);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-02-25', '2023-02-05', 'offerta di febbraio', 1);

INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-05-25', '2023-05-05', 'offerta di maggio', 2);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-04-25', '2023-04-02', 'offerta di primavera', 2);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-02-25', '2023-02-05', 'offerta di febbraio', 2);

INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-05-25', '2023-05-05', 'offerta di maggio', 3);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-04-25', '2023-04-02', 'offerta di primavera', 3);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-02-25', '2023-02-05', 'offerta di febbraio', 3);

INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-05-25', '2023-05-05', 'offerta di maggio', 4);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-04-25', '2023-04-02', 'offerta di primavera', 4);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-04-25', '2023-04-02', 'offerta bambini', 4);
INSERT INTO pizzeria.special_offers(ending_date, starting_date, title, pizza_id) VALUES('2023-02-25', '2023-02-05', 'offerta di febbraio', 4);