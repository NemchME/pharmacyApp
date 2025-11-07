package org.example.context;

import org.example.repository.AvailabilityOfMedicineRepository;
import org.example.repository.impl.jdbc.*;
import org.example.service.*;
import org.example.sql.config.DBConnection;

import java.sql.SQLException;


public class AppContext implements AutoCloseable {

    private static AppContext INSTANCE;

    private final AvailabilityOfMedicineService availabilityOfMedicineService;
    private final MedicineService medicineService;
    private final OrderService orderService;
    private final PharmacyService pharmacyService;
    private final ProducerService producerService;
    private final UserService userService;
    private final StatsService statsService;
    private final AvailabilityInfoService availabilityInfoService;
    private final DBConnection connection;

    private AppContext() {
        connection = new DBConnection();
        this.pharmacyService = new PharmacyService(new PharmacyRepositoryImpl(connection));
        this.producerService = new ProducerService(new ProducerRepositoryImpl(connection));
        this.userService = new UserService(new UserRepositoryImpl(connection));
        this.medicineService = new MedicineService(new MedicineRepositoryImpl(connection), this.producerService);
        this.availabilityOfMedicineService = new AvailabilityOfMedicineService(
                new AvailabilityOfMedicineRepositoryImpl(connection), this.pharmacyService, this.medicineService);
        this.orderService = new OrderService(
                new OrderRepositoryImpl(connection), this.userService, this.medicineService, this.pharmacyService);
        this.statsService = new StatsService(new StatsRepositoryImpl(connection));
        this.availabilityInfoService = new AvailabilityInfoService(new AvailabilityInfoRepositoryImpl(connection));
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public static synchronized AppContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppContext();
        }
        return INSTANCE;
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

    public StatsService getStatsService() {
        return statsService;
    }

    public UserService getUserService() {
        return userService;
    }

    public AvailabilityInfoService getAvailabilityInfoService() {
        return availabilityInfoService;
    }

    public DBConnection getConnection() {
        return connection;
    }
}
