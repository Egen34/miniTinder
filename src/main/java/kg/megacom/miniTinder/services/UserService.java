package kg.megacom.miniTinder.services;

import kg.megacom.miniTinder.models.Users;

import java.util.List;

public interface UserService {
    void save(Users user);
    Users findById(Long id);
    List<Users> findAll();
    void delete(Users users);
}
