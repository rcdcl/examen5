package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import bd.Conexion;
import vista.Agregar;

public class Modelo extends Conexion {

    Agregar vistaEmpleado;

    public Modelo() {
    }

    public Modelo(Agregar vistaEmpleado) {
        this.vistaEmpleado = vistaEmpleado;
    }
//

    //Agregar datos a la BD//
    public boolean agregarPaciente(String rut, String nombre, String genero, int edad, String direccion, String ciudad, String isapre, int donante) {
        // Se arma la consulta para verificar si el código a ingresar ya existe

        //Se envía el dato
        String query = "INSERT INTO paciente (rut, nombre, genero, edad, direccion, ciudad, isapre, donante)"
                + "values ('" + rut + "', '" + nombre + "', '" + genero + "', '" + edad + "', '" + direccion + "', '" + ciudad + "', '" + isapre + "', '" + donante + "');";

        //se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            getConexion().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

        return false;

    }

    //Buscar datos dentro de la BD
    public Object buscarDato(int rut) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        //int registros = 0;
        String[] columNames = {"Rut", "Nombre", "Género", "Edad", "Direccion", "Ciudad", "Isapre", "Donante"};

        Object[][] data = new String[1][8];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT rut, nombre, genero, edad, direccion, ciudad, isapre, donante FROM paciente where rut like '%" + rut +"%'");
            ResultSet res = pstm.executeQuery();
            System.out.println(rut);
            String scodigo = String.valueOf(rut);

            System.out.println(scodigo);

            int i = 0;
            while (res.next()) {

                data[i][0] = res.getString("rut");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("genero");
                data[i][3] = res.getString("edad");
                data[i][4] = res.getString("direccion");
                data[i][5] = res.getString("ciudad");
                data[i][6] = res.getString("isapre");
                data[i][7] = res.getString("donante");


                i++;
            }
            /*
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "El código no existe en la BD");
            }
            */
            res.close();
            tablemodel.setDataVector(data, columNames);
            getConexion().close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;

    }
    
    
        
    //Metodo Buscar RUT  de la BD
    public DefaultTableModel buscarRut(String rut) throws SQLException  {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Rut", "Nombre", "Género", "Edad", "Direccion", "Ciudad", "Isapre", "Donante"};
                
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total from paciente");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
           //   if (registros == 0){
           //   JOptionPane.showMessageDialog(null, "El Nombre no existe en la BD");
           // }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][8];

        try {
            PreparedStatement pstm = this.getConexion().prepareStatement
            ("SELECT rut, nombre, genero, edad, direccion, ciudad, isapre, donante FROM paciente where rut like '%" + rut +"%'");
            ResultSet res = pstm.executeQuery();
            System.out.println(rut);
            String snombre = String.valueOf(rut);

            System.out.println(snombre);
            
            int i = 0;
            
            while (res.next()) {

                data[i][0] = res.getString("rut");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("genero");
                data[i][3] = res.getString("edad");
                data[i][4] = res.getString("direccion");
                data[i][5] = res.getString("ciudad");
                data[i][6] = res.getString("isapre");
                data[i][7] = res.getString("donante");
 

                i++;
            

            //res.close();
            tablemodel.setDataVector(data, columNames);
            getConexion().close();
            
        }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tablemodel;
    }


    //Eliminar Dato de la BD 
    public boolean eliminarDato(int codigo) {
        // Se arma la consulta
        Agregar ep = new Agregar();
        Object o = new Object();

        System.out.println("buscarDato()");
        System.out.println(codigo);
        //ResultSet rs = null;

        try {
            String query = "delete from empleados where codigo ='" + codigo + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute(query);

            pstm.close();
            getConexion().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

        return false;

    }

    //Muestra los datos de la BD
    public DefaultTableModel mostrarDato() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Rut", "Nombre", "Género", "Edad", "Direccion", "Ciudad", "Isapre", "Donante"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total from paciente");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][8];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT rut, nombre, genero, edad, direccion, ciudad, isapre, donante FROM paciente");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                data[i][0] = res.getString("rut");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("genero");
                data[i][3] = res.getString("edad");
                data[i][4] = res.getString("direccion");
                data[i][5] = res.getString("ciudad");
                data[i][6] = res.getString("isapre");
                data[i][7] = res.getString("donante");
   

                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
            getConexion().close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;

    }
    
   

    //Modificar los datos de la BD
    public boolean modificarDato(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String est_civil, String nom_depto){
    String query = "update empleados set rut= '" + rut + "', nombre= '" + nombre + "', apellido= '" + apellido + "', celular= '" + celular + "', email= '" + email + "', sueldo_bruto= '" + sueldo_bruto + "', est_civil= '" + est_civil + "', nom_depto= '" + nom_depto + "' where codigo ='" + codigo + "'";

        //se ejecuta update
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            getConexion().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

        return false;

    }
    
    public boolean existeCodigo(int codigo) {
            boolean regreso=false;

try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total from empleados where codigo ='" + codigo + "'");
            ResultSet res = pstm.executeQuery();
            res.next();
            int registros = res.getInt("total");
            if (registros != 0 ){
                regreso = true;
                
            } 
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return regreso;
        
    }
        
 
    
    

    //Modificar los datos de la BD

    public boolean agregarPaciente(int parseInt, String text, String text0, String text1, int parseInt0, String text2, int parseInt1, String ecivil, String valueOf) {
        return false;
    }
}
