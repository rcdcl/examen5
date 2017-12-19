package bd;

import java.sql.Connection;
import java.sql.DriverManager;

//Clase genérica de conexión a Base de Datos//
public class Conexion {

    public static Connection getConexion() {

        Connection connection = null;

        try {

            String driverClassName = "com.mysql.jdbc.Driver";
            String driverUrl = "jdbc:mysql://localhost/examen";
            Class.forName(driverClassName);
            System.out.println("Proceso de conexión...");
            connection = DriverManager.getConnection(driverUrl, "root", "1Gonzalito");
            System.out.println("Conexión Establecida");

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return connection;
    }
    

}
