package client.db;

import java.sql.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class DataBase {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public DataBase() {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/";
            String login = "postgres";
            String password = "12345";

            con = DriverManager.getConnection(url, login, password);

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM boardgames");

            System.out.println("Current data base\nid\tname\t\t\t\t\t\t   price quantity");
            while (rs.next()) {
                String str = rs.getString("id") + "\t" +  rs.getString("name") + " " +  rs.getString("price") + "  " +  rs.getString("quantity");
                System.out.println(str);
            }
        }
        catch (Exception e) {
            System.out.println("Catch");
            System.out.println("Connection error!");
        }
    }

    public VBox select(String query) {
        VBox res = new VBox();
        HBox row = new HBox();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                row.getChildren().clear();

                Label lab = new Label(rs.getString("id"));
                row.getChildren().add(lab);

                lab = new Label(rs.getString("name"));
                row.getChildren().add(lab);

                lab = new Label(rs.getString("price"));
                row.getChildren().add(lab);

                lab = new Label(rs.getString("quantity"));
                row.getChildren().add(lab);

                res.getChildren().add(row);
            }
        }
        catch (Exception e) {
            System.out.println("Catch");
            System.out.println("Connection error!");
        }

        return res;
    }

    public void close() {
        try {
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM boardgames");

                System.out.println("Current data base\nid\tname\t\t\t\t\t\t   price quantity");
                while (rs.next()) {
                    String str = rs.getString("id") + "\t" +  rs.getString("name") + " " +  rs.getString("price") + "  " +  rs.getString("quantity");
                    System.out.println(str);
                }
                rs.close();
                stmt.close();
            }
            finally {
                con.close();
            }
        }
        catch (Exception e) {
            System.out.println("Catch");
            System.out.println("Connection error!");
        }
    }
}