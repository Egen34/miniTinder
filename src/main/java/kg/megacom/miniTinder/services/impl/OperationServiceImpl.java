package kg.megacom.miniTinder.services.impl;

import kg.megacom.miniTinder.dao.DbHelper;
import kg.megacom.miniTinder.dao.impl.DbHelperImpl;
import kg.megacom.miniTinder.models.Users;
import kg.megacom.miniTinder.models.enums.Gender;
import kg.megacom.miniTinder.services.OperationService;
import kg.megacom.miniTinder.services.OrderService;
import kg.megacom.miniTinder.services.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OperationServiceImpl implements OperationService {
    DbHelper dbHelper = new DbHelperImpl();
    OrderService orderService = new OrderServiceImpl();
    UserService userService = new UserServiceImpl();

    Scanner scanner = new Scanner(System.in);


    @Override
    public void registration() {
        Users users = new Users();
        System.out.println("Напишите Ваш логин: ");
        users.setLogin(scanner.next());

        System.out.println("Напишите Ваш Пароль: ");
        users.setPassword(scanner.next());

        System.out.println("Напишите Ваше имя: ");
        users.setName(scanner.next());

        System.out.println("Краткая информация о себе: ");
        users.setInfo(scanner.nextLine());

        System.out.println("Выберит пол: ");

        System.out.println(Arrays.asList(Gender.values()));
//
        users.setGender(Gender.valueOf(scanner.next()));

        userService.save(users);


    }

    @Override
    public Long logIn(String login, String password) {

        try {
            PreparedStatement preparedStatement = dbHelper.getPrepareStatement("SELECT * FROM tb_users WHERE login=? AND password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Вы вошли систему");
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0l;
    }


    @Override
    public List<Users> followers(Long id) {
        try {
            PreparedStatement preparedStatement =
                    dbHelper.getPrepareStatement("SELECT * FROM tb_users WHERE active=true AND (id in(SELECT sender_id FROM tb_orders where recipient_id=? and match=true))");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setInfo(resultSet.getString("info"));
                user.setGender(Gender.valueOf(resultSet.getString("gender")));

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Users> followings(Long id) {
        try {
            PreparedStatement preparedStatement =
                    dbHelper.getPrepareStatement("SELECT * FROM tb_users WHERE active=true AND (id in (SELECT recipient_id FROM tb_orders where sender_id=? and match=true))");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setInfo(resultSet.getString("info"));
                user.setGender(Gender.valueOf(resultSet.getString("gender")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int followersCount(Long id) {
        try {
            PreparedStatement preparedStatement =
                    dbHelper.getPrepareStatement("SELECT * FROM tb_users WHERE active=true AND (id in(SELECT sender_id FROM tb_orders where recipient_id=? and match=true))");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next())
                count++;
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public int followingsCount(Long id) {

        try {
            PreparedStatement preparedStatement =
                dbHelper.getPrepareStatement("SELECT * FROM tb_users WHERE active=true  AND (id in (SELECT recipient_id FROM tb_orders where sender_id=? and match=true))");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next())
                count++;
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Users> recomended4You(Long yourId, int limit,int offset) {
        try {
            PreparedStatement preparedStatement =
                    dbHelper.getPrepareStatement("SELECT * FROM tb_users WHERE active=true AND (gender!=(SELECT gender FROM tb_users WHERE id=?)) ORDER BY id DESC LIMIT ? OFFSET ?");

            preparedStatement.setLong(1, yourId);
            preparedStatement.setInt(2,limit);
            preparedStatement.setInt(3,offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setInfo(resultSet.getString("info"));
                user.setGender(Gender.valueOf(resultSet.getString("gender")));
                user.setActive(resultSet.getBoolean("active"));

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Users> recomended4You(Long yourId) {
        return recomended4You(yourId,100,0);
    }
}
