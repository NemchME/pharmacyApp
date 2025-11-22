package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.Order;
import org.example.model.OrderInfo;
import org.example.repository.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final MedicineService medicineService;
    private final PharmacyService pharmacyService;

    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        MedicineService medicineService,
                        PharmacyService pharmacyService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.medicineService = medicineService;
        this.pharmacyService = pharmacyService;
    }

    public void save(Order order) {
        if (checkForeignKeys(order.getUserId(), order.getMedicineId(), order.getPharmacyId())) {
            orderRepository.save(order);
        }
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Заказ с id '" + id + "' не найден!")
        );
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAll(int page, int size) {
        return orderRepository.findAll(page, size);
    }

    public int getTotalPages(int size) {
        int total = orderRepository.countAll();
        return (int) Math.ceil((double) total / size);
    }

    public List<Order> filter(String search) {
        return orderRepository.filter(search);
    }

    public List<Order> sort(String sort, String comparator) {

        return orderRepository.sort(sort, comparator);
    }

    public void update(Order order) {
        if (findById(order.getId()) != null &&
                checkForeignKeys(order.getUserId(), order.getMedicineId(), order.getPharmacyId())) {
            orderRepository.update(order);
        }
    }

    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    private boolean checkForeignKeys(Integer userId, Integer medicineId, Integer pharmacyId) {
        return userService.findById(userId) != null && medicineService.findById(medicineId) != null &&
                pharmacyService.findById(pharmacyId) != null;
    }

    public List<OrderInfo> findByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

    public UserService getUserService() {
        return userService;
    }

    public MedicineService getMedicineService() {
        return medicineService;
    }

    public PharmacyService getPharmacyService() {
        return pharmacyService;
    }
}