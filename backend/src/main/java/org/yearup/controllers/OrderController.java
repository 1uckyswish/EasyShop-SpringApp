package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.OrderDao;
import org.yearup.data.mysql.MySqlOrdersDao;

import javax.sql.DataSource;

@RestController
@RequestMapping("orders")
@CrossOrigin
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class OrderController {
    private OrderDao orderDao;

    @Autowired
    public OrderController(DataSource dataSource) {
        // Initialize the OrderDao with the appropriate implementation
        this.orderDao = new MySqlOrdersDao(dataSource);
    }
    @PostMapping
    public void addOrder(@RequestParam("userId") int userId) {
        // Call the addOrder method of the OrderDao
        orderDao.addOrder(userId);
    }

}
