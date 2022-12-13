package kg.megacom.miniTinder.services.impl;

import kg.megacom.miniTinder.dao.DbHelper;
import kg.megacom.miniTinder.dao.impl.DbHelperImpl;
import kg.megacom.miniTinder.models.Orders;
import kg.megacom.miniTinder.services.OrderService;
import kg.megacom.miniTinder.services.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    DbHelper dbHelper=new DbHelperImpl();
    UserService userService=new UserServiceImpl();

    @Override
    public void fallow(Orders orders) {
        try {

            PreparedStatement preparedStatement= preparedStatement=dbHelper.getPrepareStatement
                    ("insert into tb_orders (sender_id,recipient_id,match) VALUES(?,?,?,?)");
            preparedStatement.setLong(1,orders.getSenderId().getId());
            preparedStatement.setLong(2,orders.getRecipientId().getId());
            preparedStatement.setBoolean(3,orders.isMatch());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw  new RuntimeException("Order Save Error");
        }
    }

    @Override
    public Orders findById(Long id) {
        try {
            PreparedStatement preparedStatement=dbHelper.getPrepareStatement
                    ("select * from tb_orders where id=?");
            preparedStatement.setLong(1,id);

            Orders orders=new Orders();
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                orders.setId(resultSet.getLong("id"));
                orders.setSenderId(userService.findById(resultSet.getLong("sender_id")));
                orders.setRecipientId(userService.findById(resultSet.getLong("recipient_id")));
                orders.setMatch(resultSet.getBoolean("match"));
            }
            return orders;

        }catch (Exception e){
            throw  new RuntimeException("Order Save Error");
        }
    }

    @Override
    public List<Orders> findAll() {
        try {
            PreparedStatement preparedStatement=dbHelper.getPrepareStatement
                    ("select * from tb_orders where id=? ");




            ResultSet resultSet=preparedStatement.executeQuery();
            List<Orders> ordersList=new ArrayList<>();
            while (resultSet.next()){
                Orders orders=new Orders();
                orders.setId(resultSet.getLong("id"));
                orders.setSenderId(userService.findById(resultSet.getLong("sender_id")));
                orders.setRecipientId(userService.findById(resultSet.getLong("recipient_id")));
                orders.setMatch(resultSet.getBoolean("match"));
                ordersList.add(orders);
            }
            return ordersList;

        }catch (Exception e){
            throw  new RuntimeException("Order Save Error");
        }
    }

    @Override
    public void unfollow(Long id) {
        try {
            PreparedStatement preparedStatement=dbHelper.getPrepareStatement(
                "UPDATE tb_orders SET match=false WHERE id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
