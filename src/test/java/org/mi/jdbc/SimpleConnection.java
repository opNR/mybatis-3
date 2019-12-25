package org.mi.jdbc;

import java.sql.*;

/**
 * @author yfwang2012@gmail.com
 * @description
 * @since 2019/12/6 22:13
 */
public class SimpleConnection {

  public static void main(String[] args) throws SQLException {
    Connection collection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?user=root&password=root_2020&serverTimezone=UTC");
    viewTable(collection);
    System.out.println(collection);
  }

  private static void viewTable(Connection connection) throws SQLException {
    Statement statement = null;
    String sql = "SELECT host, user FROM user";
    try {
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()){
        String id = resultSet.getString("host");
        String name = resultSet.getString("user");
        System.out.println("row:host="+id+",user="+name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (statement != null) statement.close();
    }
  }

}
