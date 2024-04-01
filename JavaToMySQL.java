import java.sql.*;

public class JavaToMySQL {
    // JDBC URL, ім'я користувача та пароль для сервера MySQL
    private static final String url = "jdbc:mysql://localhost:3306/video";
    private static final String user = "root";
    private static final String password = "PHW#84#jeor";

    // Змінні JDBC для встановлення та управління з'єднанням
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;

    public static void main(String[] args) {
        System.out.println("Привіт, MySQL!");
        String query = "SHOW TABLES";
        try {
            // Завантаження драйвера JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Встановлення з'єднання з сервером MySQL
            con = DriverManager.getConnection(url, user, password);

            // Отримання об'єкта Statement для виконання запиту
            stmt = con.createStatement();

            // Виконання запиту SELECT
            rs = stmt.executeQuery(query);

            // Отримання метаданих
            rsmd = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print("[" + rs.getString(i) + "]");
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закриття з'єднання, оператора та результату тут
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
