/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import modelo.Modelo;
import vista.Agregar;
import vista.Listar;
import vista.Menu;

/**
 *
 * @author MBpro_Rafa && MBpro_Erick :P
 */
public class Registro implements ActionListener, MouseListener {
    
    private int busquedin = 0;
    private int otrobusquedin = 0;

    //Declarar vista
    Menu vistaMenu;

    Agregar vistaAgregar = new Agregar();

    Listar vistaListar = new Listar();

    //Declaro el modelo
    Modelo modeloDato = new Modelo();

    String ecivil;

    //Metodo enumerar donde se agregan los botones o elementos que van a desencadenar acciones (que voy a escuchar)
    public enum Accion {
        btnagregar,//boton agregar de vista Agregar
        btnmodificar,// botón modificar de vista Agregar
        btneliminar,// botón eliminar de vista Agregar
        btnsalir,// botón salir vista Agregar
        btnbuscar,// botón buscar vista Agregar
        btnmostrarredes, //botón mostrar empleados redes

        btnmostrar,// botón mostrar de vista Listar  
        btnvolver, // botón volver de vista Mostar
        btnlimpiar,//botón limpiar de vista 
        btnmodificarlistar,
        // opciones barra menúmenú
        msissalir, // opción salir barra menú
        mempmostrar, // opción vista empleado barra menú
        mnvistaagregar,//Menu mostrar ventana agregar
        mnvistalistar,//Menu vista Listar

        // combos
        cboestadocivil, // combo estado civil vista Agregar    
        cbodepartamento, // combo selección departamento vista Agregar
    }

    //Agregamos el constructor de la clase
    public Registro(Menu vistaMenu) {
        this.vistaMenu = vistaMenu;
        this.busquedin = busquedin;

        //this.vistaElDato.setVisible(true);
    }

    //Creamos metodo para iniciar
    public void iniciar() {

        try {
            this.vistaMenu.setVisible(true);//Hago que la vista sea visible
            this.vistaMenu.setLocationRelativeTo(null);
            this.vistaMenu.setTitle("Pacientes");

            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //SwingUtilities.updateComponentTreeUI(vistaAgregar);
        } catch (Exception ex) {
        }

        //catch (UnsupportedLookAndFeelException ex){}
        //catch (ClassNotFoundException ex) {}
        //catch (InstantiationException ex) {}
        //catch (IllegalAccessException ex) {}
        // Escuchamos el boton que ingresa el dato
        this.vistaAgregar.btnagregar.setActionCommand("btnagregar");
        this.vistaAgregar.btnagregar.addActionListener(this);
        // Escuchamos el boton que modifica el dato

        // Escuchamos el boton que elimina el dato
        // Escuchamos el boton que limpia
        this.vistaAgregar.btnlimpiar.setActionCommand("btnlimpiar");
        this.vistaAgregar.btnlimpiar.addActionListener(this);
        // Escuchamos el boton que muestra el dato
        this.vistaListar.btnbuscar.setActionCommand("btnbuscar");
        this.vistaListar.btnbuscar.addActionListener(this);
        // Escuchamos el boton que muestra el dato

        // Escuchamos el boton volver de vista Listar
        this.vistaListar.btnvolver.setActionCommand("btnvolver");
        this.vistaListar.btnvolver.addActionListener(this);
        // Escuchamos el boton eliminar de vista Listar
        this.vistaListar.btneliminar.setActionCommand("btneliminar");
        this.vistaListar.btneliminar.addActionListener(this);
        // Escuchamos el boton modificar de vista Listar
        this.vistaListar.btnmodificarlistar.setActionCommand("btnmodificarlistar");
        this.vistaListar.btnmodificarlistar.addActionListener(this);

        this.vistaMenu.mnvistaagregar.setActionCommand("mnavistagregar");
        this.vistaMenu.mnvistaagregar.addActionListener(this);

        //Escuchamos menu vista listar
        this.vistaMenu.mnvistalistar.setActionCommand("mnvistalistar");
        this.vistaMenu.mnvistalistar.addActionListener(this);

        // Escuchamos el boton que limpia datos de pantalla
        //this.vistaAgregar.btnlimpiar.setActionCommand("btnlimpiar");
        //this.vistaAgregar.btnlimpiar.addActionListener(this);
        // Escuchamos el boton que salir
        this.vistaAgregar.btnsalir.setActionCommand("btnsalir");
        this.vistaAgregar.btnsalir.addActionListener(this);
        // Escuchamos el boton que buscar

        // Escuchamos el bcombo Estado Civil
        // Escuchamos el bcombo departamento
        this.vistaAgregar.cbociudad.setActionCommand("cbodepartamento");
        this.vistaAgregar.cbociudad.addActionListener(this);

        // Escuchamos el opción salir barra menú
        this.vistaMenu.msissalir.setActionCommand("msissalir");
        this.vistaMenu.msissalir.addActionListener(this);
        // Escuchamos opción mostar barra menú listar
        this.vistaMenu.mnvistalistar.setActionCommand("mnvistalistar");
        this.vistaMenu.mnvistalistar.addActionListener(this);
        // Escuchamos opción mostar barra menú agregar
        this.vistaMenu.mnvistaagregar.setActionCommand("mnvistaagregar");
        this.vistaMenu.mnvistaagregar.addActionListener(this);

        this.vistaListar.tbPaciente.addMouseListener(this);

    }

    //limpia la tabla
    public void eliminar() {
        DefaultTableModel tb = (DefaultTableModel) this.vistaListar.tbPaciente.getModel();
        int a = this.vistaListar.tbPaciente.getRowCount() - 1;
        System.out.println(a);
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }

    }

    //limpia los datos de pantalla
    public void limpiartodo() {
        this.vistaAgregar.txtnombre.setText("");
        this.vistaAgregar.txtrut.setText("");
        this.vistaListar.txtbuscar.setText("");
        this.vistaAgregar.genero.clearSelection();
        this.vistaAgregar.isapre.clearSelection();
        this.vistaAgregar.ckdonante.setSelected(false);

        this.vistaAgregar.cbociudad.setSelectedIndex(0);


        this.vistaAgregar.txtdireccion.setText("");
        this.vistaAgregar.txtedad.setText("");
        this.busquedin=0;
        this.otrobusquedin=0;
        this.vistaAgregar.txtrut.setEnabled(true);
        this.vistaAgregar.btnagregar.setEnabled(true);
        this.vistaAgregar.txtrut.requestFocus();
        eliminar();
        System.out.println(this.busquedin+ " "+ this.otrobusquedin);

    }

    public void buscar()  {
 

                String rut;
                rut = this.vistaAgregar.txtrut.getText();
                boolean bconfirmacion = false;
                System.out.println("buscar");
                
               
                
                
                this.vistaListar.tbPaciente.setModel((TableModel) this.modeloDato.buscarRut(rut));

                if (String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 1)) == "null") {
                    JOptionPane.showMessageDialog(null, "El RUT no existe, vuelva a intentar");
                    limpiartodo();

                } else {

                    this.vistaAgregar.txtrut.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 0)));
                    this.vistaAgregar.txtnombre.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 1)));
                    String sexo = String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 2));
                    if (sexo == "M") {
                        this.vistaAgregar.optmasc.setSelected(true);
                    }
                    if (sexo == "F") {
                        this.vistaAgregar.optfem.setSelected(true);
                    }

                    this.vistaAgregar.txtedad.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 3)));

                    this.vistaAgregar.txtdireccion.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 4)));

                    String ciudad = String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 5));
                    switch (ciudad) {
                        case "Santiago":
                            this.vistaAgregar.cbociudad.setSelectedIndex(1);
                            break;
                        case "Valparaíso":
                            this.vistaAgregar.cbociudad.setSelectedIndex(2);
                            break;
                        case "Concepción":
                            this.vistaAgregar.cbociudad.setSelectedIndex(3);
                            break;

                        case "Antofagasta":
                            this.vistaAgregar.cbociudad.setSelectedIndex(4);
                            break;

                        case "Puerto Montt":
                            this.vistaAgregar.cbociudad.setSelectedIndex(5);
                            break;
                    }

                    String isapre = String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 6));
                    if (isapre == "S") {
                        this.vistaAgregar.optisapresi.setSelected(true);
                    }
                    if (isapre == "N") {
                        this.vistaAgregar.optisapreno.setSelected(true);
                    }

                    String donante = String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 7));
                    if (donante == "1") {
                        this.vistaAgregar.ckdonante.setSelected(true);
                    }

                    this.vistaAgregar.txtrut.setEnabled(false);
                    this.vistaAgregar.btnagregar.setEnabled(false);

                    if (bconfirmacion == true) {

                        JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                    } else {
                        // JOptionPane.showMessageDialog(null, "El código de empleado no existe, vuelva a intentarlo");
                    }
                }
                
            
    }

    static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
        switch (Accion.valueOf(e.getActionCommand())) {

            case mnvistaagregar:
                this.vistaAgregar.setVisible(true);
                this.vistaAgregar.setTitle("Agregar Pacientes");
                this.vistaAgregar.setLocationRelativeTo(null);
                break;

            case mnvistalistar:
                this.vistaListar.setVisible(true);
                this.vistaListar.setTitle("Listar Pacientes");
                this.vistaListar.setLocationRelativeTo(null);
                break;

            case btnagregar:
                //llamamos al metodo que esta en el modelo para agragar el dato y le enviamos lo que captura del textField
                
                
                
                try {

                    String active, active2;

                    active = String.valueOf(this.vistaAgregar.cbociudad.getSelectedItem());

                    //System.out.println(active);
                    boolean correcto1 = false, correcto2 = false;
                    String verificacionrut = String.valueOf(this.vistaAgregar.txtrut.getText());

                    //verificar rut no está en blanco
                    if (this.vistaAgregar.txtrut.isEnabled()==false){
                                                        
                                                        String genero;
                                                        if (this.vistaAgregar.optfem.isSelected()==true){
                                                            genero="F";
                                                        }else{
                                                        
                                                            genero="M";
                                                        }
                                                        String isapre=null;
                                                        if (this.vistaAgregar.optisapresi.isSelected()==true){
                                                            isapre="S";
                                                        }
                                                        if (this.vistaAgregar.optisapreno.isSelected()==true){
                                                            isapre="N";
                                                        }
                                                        int donante=0;
                                                        if (this.vistaAgregar.ckdonante.isSelected()==true){
                                                            donante=1;
                                                        }
                                                        if (this.vistaAgregar.ckdonante.isSelected()==false){
                                                            donante=0;
                                                        }
                        boolean resultado= this.modeloDato.modificarDato(this.vistaAgregar.txtrut.getText(), this.vistaAgregar.txtnombre.getText(), genero, Integer.parseInt(this.vistaAgregar.txtedad.getText()), this.vistaAgregar.txtdireccion.getText(), String.valueOf(this.vistaAgregar.cbociudad.getSelectedItem()), isapre, donante);
                        if (resultado==true){
                            JOptionPane.showMessageDialog(null, "El Registro se modificó correctamente");
                            limpiartodo();
                        } else{
                            JOptionPane.showMessageDialog(null, "El Registro no se pudo modificar");
                        }
                    } else {
                    
                    
                    if (this.vistaAgregar.txtrut.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Ingrese valor en campo RUT, vuelva a intentar");

                    } else {

                        if (this.modeloDato.existeRUT(verificacionrut) == true) {
                            JOptionPane.showMessageDialog(null, "El rut ya existe, vuelva a intentarlo");

                        } else {

                            if (this.vistaAgregar.txtnombre.getText().length() == 0) {
                                JOptionPane.showMessageDialog(null, "Ingrese valor en campo Nombre, vuelva a intentar");

                            } else {
                                if (this.vistaAgregar.optfem.isSelected() == false && this.vistaAgregar.optmasc.isSelected() == false) {
                                    JOptionPane.showMessageDialog(null, "Seleccione Género");

                                } else {

                                    if (this.vistaAgregar.txtedad.getText().length() == 0) {
                                        JOptionPane.showMessageDialog(null, "Ingrese datos en campo edad, vuelva a intentar");

                                    } else {
                                        if (esNumero(this.vistaAgregar.txtedad.getText()) == false) {
                                            JOptionPane.showMessageDialog(null, "El campo edad debe ser numérico, vuelva a untentar");

                                        } else {
                                            if (this.vistaAgregar.txtdireccion.getText().length() == 0) {
                                                JOptionPane.showMessageDialog(null, "Ingrese datos en campo Dirección, vuelva a intentar");

                                            } else {

                                                if (active == "Seleccione") {
                                                    JOptionPane.showMessageDialog(null, "Debe seleccionar Ciudad");

                                                    System.out.println(active);

                                                } else {

                                                    if (this.vistaAgregar.optisapresi.isSelected() == false && this.vistaAgregar.optisapreno.isSelected() == false) {
                                                        JOptionPane.showMessageDialog(null, "Seleccione si tiene Isapre");

                                                    } else {

                                                        String genero=null;
                                                        if (this.vistaAgregar.optfem.isSelected()==true){
                                                            genero="F";
                                                        }
                                                        if (this.vistaAgregar.optmasc.isSelected()==true){
                                                            genero="M";
                                                        }
                                                        String isapre=null;
                                                        if (this.vistaAgregar.optisapresi.isSelected()==true){
                                                            isapre="S";
                                                        }
                                                        if (this.vistaAgregar.optisapreno.isSelected()==true){
                                                            isapre="N";
                                                        }
                                                        int donante=0;
                                                        if (this.vistaAgregar.ckdonante.isSelected()==true){
                                                            donante=1;
                                                        }
                                                        if (this.vistaAgregar.ckdonante.isSelected()==false){
                                                            donante=0;
                                                        }
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        // String rut, String nombre, String genero, int edad, String direccion, String ciudad, String isapre, int donante
                                                        if (this.modeloDato.agregarPaciente(this.vistaAgregar.txtrut.getText(), this.vistaAgregar.txtnombre.getText(), genero, Integer.parseInt(this.vistaAgregar.txtedad.getText()), this.vistaAgregar.txtdireccion.getText(), String.valueOf(this.vistaAgregar.cbociudad.getSelectedItem()), isapre, donante)) {

                                                            JOptionPane.showMessageDialog(null, "El Paciente se agregó correctamente");

                                                            limpiartodo();
                                                            eliminar();

                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "No se pudo agregar Paciente");
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                //limpiartodo();  
                break;

            case btneliminar:
                //llamamos método para eliminar dato
                if (this.vistaListar.txtbuscar.getText().length()==0 && this.busquedin==0 && this.otrobusquedin==0){
                    System.out.println(this.busquedin + " " + this.otrobusquedin );
                    JOptionPane.showMessageDialog(null, "Presione el botón Buscar para listar todos los registros,\no ingrese RUT en casilla y presione buscar");
                } else {
                    if (this.otrobusquedin==0){
                       JOptionPane.showMessageDialog(null, "Seleccione una fila y presione el botón Eliminar"); 
                    } else{

                
                //String rut = String.valueOf(this.vistaListar.tbPaciente.getValueAt(0, 0));
                String rut = this.vistaAgregar.txtrut.getText();
                
                boolean confirmacion = false;
                System.out.println("btneliminar");
                confirmacion = this.modeloDato.eliminarDato(rut);
                if (confirmacion == true) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                }
                this.vistaListar.tbPaciente.setModel(this.modeloDato.mostrarDato());
                limpiartodo();
                    }}
                break;

            case btnmostrar:

                this.vistaListar.tbPaciente.setModel(this.modeloDato.mostrarDato());
                break;

            case btnsalir:
                this.vistaAgregar.setVisible(false);
                break;

            case btnbuscar:
                this.busquedin=1;
                if (this.vistaListar.txtbuscar.getText().length()!=0){
                    String rut = this.vistaListar.txtbuscar.getText();
                    this.vistaListar.tbPaciente.setModel(this.modeloDato.buscarDato(rut));
                    
                } else {
                

                buscar();
                System.out.println(this.busquedin + " " + this.otrobusquedin );
                }
                break;

            case btnmodificarlistar:
                if (this.vistaListar.txtbuscar.getText().length()==0 && this.busquedin==0 && this.otrobusquedin==0){
                    System.out.println(this.busquedin + " " + this.otrobusquedin );
                    JOptionPane.showMessageDialog(null, "Presione el botón Buscar para listar todos los registros,\no ingrese RUT en casilla y presione buscar");
                } else {
                    if (this.otrobusquedin==0){
                       JOptionPane.showMessageDialog(null, "Seleccione una opción de la lista de registros y presione modificar"); 
                    } else{
                this.vistaAgregar.setVisible(true);
                this.vistaAgregar.setTitle("Modificar Paciente");
                this.vistaAgregar.setLocationRelativeTo(null);
                this.vistaAgregar.txtrut.setEnabled(false);
                this.vistaAgregar.btnagregar.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Modifique el dato que desee y presione el botón Guardar");
                }}
                 break;

            case btnlimpiar:
                eliminar();
                limpiartodo();
                break;

            case btnvolver:
                this.vistaListar.setVisible(false);//Hago que la vista sea visible
                limpiartodo();

                break;

            case msissalir:
                System.exit(0);
                break;

            case mempmostrar:
                this.vistaListar.setVisible(true);//Hago que la vista sea visible
                this.vistaListar.setLocationRelativeTo(null);
                this.vistaListar.setTitle("Listar Pacientes");

                break;

        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Registro(new vista.Menu()).iniciar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.otrobusquedin=1;
        System.out.println(this.otrobusquedin);
        int row = this.vistaListar.tbPaciente.rowAtPoint(e.getPoint());

        /* row devolvera -1 si se ha clicado fuera de la fila pero dentro de la tabla, si no devolvera el indice de la fila en la que se ha clicado. */
        this.vistaAgregar.txtrut.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 0)));
        this.vistaAgregar.txtnombre.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 1)));
        String sexo = String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 2));
        System.out.println(sexo);
        switch (sexo){
            case "M":
                this.vistaAgregar.optmasc.setSelected(true);
                break;
                
            case "F":
                this.vistaAgregar.optfem.setSelected(true);
                break;
                
            
            
            
        }
        

        this.vistaAgregar.txtedad.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 3)));

        this.vistaAgregar.txtdireccion.setText(String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 4)));

        String ciudad = String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 5));
        switch (ciudad) {
            case "Santiago":
                this.vistaAgregar.cbociudad.setSelectedIndex(1);
                break;
            case "Valparaíso":
                this.vistaAgregar.cbociudad.setSelectedIndex(2);
                break;
            case "Concepción":
                this.vistaAgregar.cbociudad.setSelectedIndex(3);
                break;

            case "Antofagasta":
                this.vistaAgregar.cbociudad.setSelectedIndex(4);
                break;

            case "Puerto Montt":
                this.vistaAgregar.cbociudad.setSelectedIndex(5);
                break;
        }

        String isapre = String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 6));
        switch(isapre){
            case "S":
                this.vistaAgregar.optisapresi.setSelected(true);
                break;
            case "N":
                this.vistaAgregar.optisapreno.setSelected(true);
                break;
            
        }
        
        String donante = String.valueOf(this.vistaListar.tbPaciente.getValueAt(row, 7));
        switch(donante){
            case "1":
                this.vistaAgregar.ckdonante.setSelected(true);
                break;
            
        }
        

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
