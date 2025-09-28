package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.Order;
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
        checkForeignKeys(order.getUserId(), order.getMedicineId(), order.getPharmacyId());
        orderRepository.save(order);
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Заказ с id '" + id + "' не найден!")
        );
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void update(Order order) {
        findById(order.getId());
        checkForeignKeys(order.getUserId(), order.getMedicineId(), order.getPharmacyId());
        orderRepository.update(order);
    }

    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    private void checkForeignKeys(Integer userId, Integer medicineId, Integer pharmacyId) {
        userService.findById(userId);
        medicineService.findById(medicineId);
        pharmacyService.findById(pharmacyId);
    }
}
