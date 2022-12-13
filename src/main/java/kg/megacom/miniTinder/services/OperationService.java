package kg.megacom.miniTinder.services;

import kg.megacom.miniTinder.models.Users;

import java.util.List;

public interface OperationService {
    void registration();
    Long logIn(String login,String password);
    List<Users> followers(Long id);
    List<Users> followings(Long id);
    int followersCount(Long id);
    int followingsCount(Long id);
    List<Users> recomended4You(Long yourId,int offset,int limit);
    List<Users> recomended4You(Long yourId);





}
