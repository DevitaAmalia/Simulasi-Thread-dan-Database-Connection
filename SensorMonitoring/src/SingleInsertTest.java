import java.sql.*;

public class SingleInsertTest {
  public static void main(String[] args) throws Exception {
    String url = "jdbc:mysql://localhost:3306/monitoring?useSSL=false&serverTimezone=UTC";
    String user = "root";         // sesuaikan
    String pass = "";             // sesuaikan

    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection c = DriverManager.getConnection(url, user, pass);
         PreparedStatement ps = c.prepareStatement(
           "INSERT INTO sensor_logs (source, value) VALUES (?, ?)")) {
      ps.setString(1, "TEST-1");
      ps.setDouble(2, 23.5);
      int n = ps.executeUpdate();
      System.out.println("Rows inserted: " + n);
    }
  }
}
