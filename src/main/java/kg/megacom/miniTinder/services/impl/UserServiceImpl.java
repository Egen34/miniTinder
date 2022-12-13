package kg.megacom.miniTinder.services.impl;

import kg.megacom.miniTinder.dao.DbHelper;
import kg.megacom.miniTinder.dao.impl.DbHelperImpl;
import kg.megacom.miniTinder.models.Users;
import kg.megacom.miniTinder.models.enums.Gender;
import kg.megacom.miniTinder.services.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    DbHelper dbHelper=new DbHelperImpl();

    @Override
    public void save(Users user) {
        try {
            PreparedStatement preparedStatement=dbHelper.getPrepareStatement
                    ("insert into tb_users(name, login,password,gender,info,active) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getLogin());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getGender().toString());
            preparedStatement.setString(5,user.getInfo());
            preparedStatement.setBoolean(6,user.isActive());

            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("User Save Error");
        }
    }

    @Override
    public Users findById(Long id) {
        try {
            PreparedStatement preparedStatement=dbHelper.getPrepareStatement
                    ("select * from tb_users u where u.id=?");
            preparedStatement.setLong(1,id);

            ResultSet resultSet=preparedStatement.executeQuery();
            Users users=new Users();

            while (resultSet.next()){
                users.setId(resultSet.getLong("id"));
                users.setLogin(resultSet.getString("login"));
                users.setPassword(resultSet.getString("password"));
                users.setName(resultSet.getString("name"));
                users.setInfo(resultSet.getString("info"));
                users.setGender(Gender.valueOf(resultSet.getString("gender")));
                users.setActive(resultSet.getBoolean("active"));
            }
            return users;
        }catch (Exception e){
            throw  new RuntimeException("User Save Error");
        }
    }

    @Override
    public List<Users> findAll() {
        try {
            PreparedStatement preparedStatement=dbHelper.getPrepareStatement
                    ("select * from tb_users ");


            ResultSet resultSet=preparedStatement.executeQuery();
            List<Users> usersList=new ArrayList<>();
            while (resultSet.next()){
                Users users=new Users();
                users.setId(resultSet.getLong("id"));
                users.setLogin(resultSet.getString("login"));
                users.setPassword(resultSet.getString("password"));
                users.setName(resultSet.getString("name"));
                users.setInfo(resultSet.getString("info"));
                users.setGender(Gender.valueOf(resultSet.getString("gender")));
                users.setActive(resultSet.getBoolean("active"));
                usersList.add(users);
            }
            return usersList;
        }catch (Exception e){
            e.printStackTrace();
//            throw  new RuntimeException("User Save Error");
        }
        return null;
    }

    @Override
    public void delete(Users users) {
        try {
            PreparedStatement preparedStatement=
                    dbHelper.getPrepareStatement("UPDATE tb_users SET active=false WHERE id=?;");
            preparedStatement.setLong(1,users.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
