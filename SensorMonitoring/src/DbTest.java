import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/monitoring?useSSL=false&serverTimezone=UTC";
        String user = "root";   // ganti jika user kamu beda
        String pass = "";   // ganti jika password kamu beda

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Koneksi berhasil!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver JDBC tidak ditemukan");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Koneksi gagal");
            e.printStackTrace();
        }
    }
}
