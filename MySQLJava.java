import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLJava {
  private static final String url = "jdbc:mysql://localhost:3306/video?useSSL=false&serverTimezone=UTC";
  private static final String user = "root";
  private static final String password = "PHW#84#jeor";
  private static Connection con;
  private static Statement stmt;
  private static ResultSet rs;
  private static ResultSetMetaData rsmd;

  public static void main(String[] args) {
    Scanner in = null; // Declare the Scanner object outside the try block
    try {
      con = DriverManager.getConnection(url, user, password);
      stmt = con.createStatement();
      System.out.println("Створіть запит:");
      in = new Scanner(System.in); // Initialize the Scanner object
      String query = in.nextLine();
      rs = stmt.executeQuery(query);
      rsmd = rs.getMetaData();
      System.out.println("Результат:");
      while (rs.next()) {
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
          System.out.print(rs.getString(i) + " | ");
        }
        System.out.println("");
      }
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        con.close();
        stmt.close();
        rs.close();
        if (in != null) {
          in.close(); // Close the Scanner object
        }
      } catch (SQLException se) {
        System.out.print("ERROR: SQLException " + se.getMessage());
      }
    }
  }
}
