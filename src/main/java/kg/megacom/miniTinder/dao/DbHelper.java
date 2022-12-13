package kg.megacom.miniTinder.dao;

import java.sql.PreparedStatement;

public interface DbHelper {
    PreparedStatement getPrepareStatement(String sql);
}
