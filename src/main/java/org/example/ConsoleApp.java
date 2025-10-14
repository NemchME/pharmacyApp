package org.example;

import org.example.exception.DBException;
import org.example.menu.Menu;
import org.example.menu.impl.MainMenu;
import org.example.model.*;
import org.example.repository.impl.jdbc.*;
import org.example.service.*;
import org.example.sql.config.DBConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConsoleApp {

    private final DBConnection connection = new DBConnection();
    private final AvailabilityOfMedicineService availabilityOfMedicineService;
    private final MedicineService medicineService;
    private final OrderService orderService;
    private final PharmacyService pharmacyService;
    private final ProducerService producerService;
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);
    private Menu menu = new MainMenu();

    public ConsoleApp() {
        this.pharmacyService = new PharmacyService(new PharmacyRepositoryImpl(connection));
        this.producerService = new ProducerService(new ProducerRepositoryImpl(connection));
        this.userService = new UserService(new UserRepositoryImpl(connection));
        this.medicineService = new MedicineService(new MedicineRepositoryImpl(connection), this.producerService);
        this.availabilityOfMedicineService = new AvailabilityOfMedicineService(
                new AvailabilityOfMedicineRepositoryImpl(connection), this.pharmacyService, this.medicineService);
        this.orderService = new OrderService(
                new OrderRepositoryImpl(connection), this.userService, this.medicineService, this.pharmacyService);
    }

    public void run() {
//        inputTestData();
        executeSQLScripts();
        while (true) {
        menu = menu.show(this);
        }
    }

    public AvailabilityOfMedicineService getAvailabilityOfMedicineService() {
        return availabilityOfMedicineService;
    }

    public MedicineService getMedicineService() {
        return medicineService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public PharmacyService getPharmacyService() {
        return pharmacyService;
    }

    public ProducerService getProducerService() {
        return producerService;
    }

    public UserService getUserService() {
        return userService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Menu getMenu() {
        return menu;
    }


    private void executeSQLScripts() {
        try (Statement statement = connection.getConnection().createStatement()) {

            String sql = Files.readString(Path.of("src/main/java/org/example/sql/schema.sql"));
            statement.execute(sql);
            sql = Files.readString(Path.of("src/main/java/org/example/sql/data.sql"));
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void inputTestData() {
        Pharmacy pharmacy = new Pharmacy(
                "Аптека", "ул. Пушкина", "220-98-29", "9-12",
                "Поверните направо");
        Producer producer = new Producer("Производитель №1", "Россия");
        User user = new User("Пользователь", "Пароль", "mail@mail.ru", "юзер");
        Medicine medicine = new Medicine("лекарство", "инн", "20 мг", "форма", 0);
        AvailabilityOfMedicine availabilityOfMedicine = new AvailabilityOfMedicine(0,0,100.0f,5);
        Order order = new Order(0,0,0,50,"Создан");
        pharmacyService.save(pharmacy);
        producerService.save(producer);
        userService.save(user);
        medicineService.save(medicine);
        availabilityOfMedicineService.save(availabilityOfMedicine);
        orderService.save(order);
    }

    public static void main(String[] args) {
        new ConsoleApp().run();
    }
}
