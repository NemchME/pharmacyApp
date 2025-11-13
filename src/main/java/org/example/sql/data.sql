INSERT INTO pharmacy (name, address, phone, working_hours, way_from_center)
VALUES ('Аптека', 'ул. Пушкина', '220-98-29', '9-12', 'Поверните направо');
INSERT INTO producer (name, country)
VALUES ('Производитель №1', 'Россия');
INSERT INTO users (username, password_hash, email, role)
VALUES ('Пользователь', 'Пароль', 'mail@mail.ru', 'юзер');
INSERT INTO medicine (trade_name, inn, dosage, form, producer_id)
VALUES ('лекарство', 'инн', '20 мг', 'форма', 1);
INSERT INTO availability_of_medicine (pharmacy_id, medicine_id, price, quantity)
VALUES (1, 1, 100.0, 5);
INSERT INTO orders (user_id, medicine_id, pharmacy_id, quantity, status)
VALUES (1, 1, 1, 50, 'Создан');