package worklab23;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    /*
    Veritabani baglantisi olusturmak icin olusturulmus bir siniftir.
    
    */
    private Connection con;

    public Connection getConnection() {
//        try {     //Veritabanı baglantisi     
//            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/netflixdb", "root", "password");
//        } catch (SQLException e) {
//            System.out.println("exception"); //Hata olmasi durumu.
//            System.out.println(e);
//        }
        try {     //Veritabanı baglantisi     
            con = (Connection) DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7343865", "sql7343865", "sifre123");
        } catch (SQLException e) {
            System.out.println("exception"); //Hata olmasi durumu.
            System.out.println(e);
        }
        return con;
    }
}
