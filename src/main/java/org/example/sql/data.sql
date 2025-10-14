INSERT INTO Pharmacy (name, address, phone, workingHours, wayFromCenter)
VALUES ('Аптека', 'ул. Пушкина', '220-98-29', '9-12', 'Поверните направо');
INSERT INTO Producer (name, country)
VALUES ('Производитель №1', 'Россия');
INSERT INTO Users (username, passwordHash, email, role)
VALUES ('Пользователь', 'Пароль', 'mail@mail.ru', 'юзер');
INSERT INTO Medicine (tradeName, inn, dosage, form, producerId)
VALUES ('лекарство', 'инн', '20 мг', 'форма', 0);
INSERT INTO AvailabilityOfMedicine (pharmacyId, medicineId, price, quantity)
VALUES (0, 0, 100.0, 5);
INSERT INTO Orders (userId, medicineId, pharmacyId, quantity, status)
VALUES (0, 0, 0, 50, 'Создан');