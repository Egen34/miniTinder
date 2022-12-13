package kg.megacom.miniTinder.services;

import kg.megacom.miniTinder.models.Orders;
import kg.megacom.miniTinder.models.Users;

import java.util.List;

public interface OrderService {
    void fallow(Orders user);
    Orders findById(Long id);
    List<Orders> findAll();
    void unfollow(Long id);
}
