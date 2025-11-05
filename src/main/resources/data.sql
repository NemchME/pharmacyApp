-- === ТАБЛИЦА producer ===
INSERT INTO producer (id, name, country)
VALUES 
    (100, 'Производитель 100', 'Россия'),
    (101, 'Производитель 101', 'Россия'),
    (102, 'Производитель 102', 'Беларусь'),
    (103, 'Производитель 103', 'Казахстан'),
    (104, 'Производитель 104', 'Россия'),
    (105, 'Производитель 105', 'Германия'),
    (106, 'Производитель 106', 'Россия'),
    (107, 'Производитель 107', 'Индия'),
    (108, 'Производитель 108', 'Китай'),
    (109, 'Производитель 109', 'Россия');

-- добавим остальные до 199
INSERT INTO producer (id, name, country)
SELECT x, CONCAT('Производитель ', x), 
       CASE MOD(x, 5)
         WHEN 0 THEN 'Россия'
         WHEN 1 THEN 'Германия'
         WHEN 2 THEN 'Индия'
         WHEN 3 THEN 'Китай'
         ELSE 'Беларусь'
       END
FROM SYSTEM_RANGE(110, 199);

------------------------------------------------------------
-- === ТАБЛИЦА pharmacy ===
INSERT INTO pharmacy (id, name, address, phone, working_hours, way_from_center)
SELECT x, CONCAT('Аптека №', x),
       CONCAT('ул. Пушкина, д. ', x),
       CONCAT('220-98-', LPAD(MOD(x, 100), 2, '0')),
       '9:00 - 18:00',
       CASE MOD(x, 3)
         WHEN 0 THEN 'Рядом с метро'
         WHEN 1 THEN '10 минут пешком'
         ELSE 'В центре города'
       END
FROM SYSTEM_RANGE(100, 199);

------------------------------------------------------------
-- === ТАБЛИЦА users ===
INSERT INTO users (id, username, password_hash, email, role)
SELECT x, CONCAT('user', x),
       'pass' || x,
       CONCAT('user', x, '@mail.ru'),
       CASE MOD(x, 4)
         WHEN 0 THEN 'admin'
         WHEN 1 THEN 'manager'
         ELSE 'user'
       END
FROM SYSTEM_RANGE(100, 199);

------------------------------------------------------------
-- === ТАБЛИЦА medicine ===
INSERT INTO medicine (id, trade_name, inn, dosage, form, producer_id)
SELECT x, 
       CONCAT('Лекарство ', x), 
       CONCAT('ИНН', x), 
       CONCAT(MOD(x, 50) + 5, ' мг'), 
       CASE MOD(x, 3)
         WHEN 0 THEN 'таблетки'
         WHEN 1 THEN 'капсулы'
         ELSE 'мазь'
       END,
       100 + MOD(x, 100)
FROM SYSTEM_RANGE(100, 199);

------------------------------------------------------------
-- === ТАБЛИЦА availability_of_medicine ===
INSERT INTO availability_of_medicine (id, pharmacy_id, medicine_id, price, quantity)
SELECT x, 
       100 + MOD(x, 100), 
       100 + MOD(x, 100), 
       50 + MOD(x, 200) * 1.5, 
       5 + MOD(x, 20)
FROM SYSTEM_RANGE(100, 199);

------------------------------------------------------------
-- === ТАБЛИЦА orders ===
INSERT INTO orders (id, user_id, medicine_id, pharmacy_id, quantity, status)
SELECT x, 
       100 + MOD(x, 100), 
       100 + MOD(x, 100),
       100 + MOD(x, 100),
       1 + MOD(x, 10),
       CASE MOD(x, 3)
         WHEN 0 THEN 'Создан'
         WHEN 1 THEN 'Оплачен'
         ELSE 'Выдан'
       END
FROM SYSTEM_RANGE(100, 199);
