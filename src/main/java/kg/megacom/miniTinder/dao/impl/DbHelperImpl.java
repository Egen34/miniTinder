package kg.megacom.miniTinder.dao.impl;

import kg.megacom.miniTinder.dao.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbHelperImpl implements DbHelper {
    @Override
    public PreparedStatement getPrepareStatement(String sql) {
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/mini_tinder","postgres","ren3434");
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            return preparedStatement;

    }catch (Exception e){
            throw new RuntimeException("Произошла ошибка при подключении к бд");
        }
    }
}
