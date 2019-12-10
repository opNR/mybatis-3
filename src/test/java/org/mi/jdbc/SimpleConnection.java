package org.mi.jdbc;

import java.sql.*;

/**
 * @author yfwang2012@gmail.com
 * @description
 * @since 2019/12/6 22:13
 */
public class SimpleConnection {

  public static void main(String[] args) throws SQLException {
    Connection collection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_test?user=root&password=root&serverTimezone=UTC");
    viewTable(collection);
    System.out.println(collection);
  }

  private static void viewTable(Connection connection) throws SQLException {
    Statement statement = null;
    String sql = "SELECT id, name FROM class";
    try {
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()){
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        System.out.println("row:id="+id+",name="+name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (statement != null) statement.close();
    }
  }

}
