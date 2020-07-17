package worklab23;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class WorkLab23 {

    /*
    Projenin ana classi.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Giris Ekrani");
        //frame.getContentPane().add(new mainPage());
        frame.getContentPane().add(new loginPage()); //Giris ekranini baslat
        frame.setSize(400, 400); //boyut ayarlama
        frame.setLocation(400, 200); //pozisyon ayarlama
        frame.setVisible(true); //pencernin gorunur hale gelmesi.

    }

}
