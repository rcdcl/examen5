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
 * @author MBpro_Rafa//
 */
public class Registro implements ActionListener, MouseListener {

    //Declarar vista
    Menu vistaMenu;
    
    Agregar vistaEmpleado = new Agregar();
    
    Listar vistaMostrar = new Listar();
    

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
        
        //this.vistaElDato.setVisible(true);

    }


    //Creamos metodo para iniciar
    public void iniciar() {

        try {
            this.vistaMenu.setVisible(true);//Hago que la vista sea visible
            this.vistaMenu.setLocationRelativeTo(null);
            this.vistaMenu.setTitle("Pacientes");
            this.vistaEmpleado.btneliminar.setEnabled(false);
            this.vistaEmpleado.btnmodificar.setEnabled(false);



            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //SwingUtilities.updateComponentTreeUI(vistaEmpleado);
        } catch (Exception ex) {
        }

        //catch (UnsupportedLookAndFeelException ex){}
        //catch (ClassNotFoundException ex) {}
        //catch (InstantiationException ex) {}
        //catch (IllegalAccessException ex) {}
        // Escuchamos el boton que ingresa el dato
        this.vistaEmpleado.btnagregar.setActionCommand("btnagregar");
        this.vistaEmpleado.btnagregar.addActionListener(this);
        // Escuchamos el boton que modifica el dato
        this.vistaEmpleado.btnmodificar.setActionCommand("btnmodificar");
        this.vistaEmpleado.btnmodificar.addActionListener(this);
        // Escuchamos el boton que elimina el dato
        this.vistaEmpleado.btneliminar.setActionCommand("btneliminar");
        this.vistaEmpleado.btneliminar.addActionListener(this);
        // Escuchamos el boton que muestra el dato
        this.vistaMostrar.btnmostrar.setActionCommand("btnmostrar");
        this.vistaMostrar.btnmostrar.addActionListener(this);
        // Escuchamos el boton que muestra el dato

        // Escuchamos el boton volver de vista Listar
        this.vistaMostrar.btnvolver.setActionCommand("btnvolver");
        this.vistaMostrar.btnvolver.addActionListener(this);
       

        this.vistaMenu.mnvistaagregar.setActionCommand("mnavistagregar");
        this.vistaMenu.mnvistaagregar.addActionListener(this);
        
        //Escuchamos menu vista listar
        this.vistaMenu.mnvistalistar.setActionCommand("mnvistalistar");
        this.vistaMenu.mnvistalistar.addActionListener(this);
      
        
        
        
        // Escuchamos el boton que limpia datos de pantalla
        //this.vistaEmpleado.btnlimpiar.setActionCommand("btnlimpiar");
        //this.vistaEmpleado.btnlimpiar.addActionListener(this);
        
        // Escuchamos el boton que salir
        this.vistaEmpleado.btnsalir.setActionCommand("btnsalir");
        this.vistaEmpleado.btnsalir.addActionListener(this);
        // Escuchamos el boton que buscar
        this.vistaEmpleado.btnbuscar.setActionCommand("btnbuscar");
        this.vistaEmpleado.btnbuscar.addActionListener(this);
        // Escuchamos el bcombo Estado Civil
        this.vistaEmpleado.cbootro.setActionCommand("cboestadocivil");
        this.vistaEmpleado.cbootro.addActionListener(this);
        // Escuchamos el bcombo departamento
        this.vistaEmpleado.cbociudad.setActionCommand("cbodepartamento");
        this.vistaEmpleado.cbociudad.addActionListener(this);
        
        // Escuchamos el opción salir barra menú
        this.vistaMenu.msissalir.setActionCommand("msissalir");
        this.vistaMenu.msissalir.addActionListener(this);
        // Escuchamos opción mostar barra menú
        this.vistaMenu.mnvistalistar.setActionCommand("mempmostrar");
        this.vistaMenu.mnvistalistar.addActionListener(this);
        
        this.vistaMostrar.tbEmpleado.addMouseListener(this);
        

    }
    
    //limpia la tabla
    public void eliminar() {
        DefaultTableModel tb = (DefaultTableModel) this.vistaMostrar.tbEmpleado.getModel();
        int a = this.vistaMostrar.tbEmpleado.getRowCount() - 1;
        System.out.println(a);
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }

    }
    
    //limpia los datos de pantalla
    public void limpiartodo() {
        this.vistaEmpleado.txtnombre.setText("");
        this.vistaEmpleado.cbootro.setSelectedIndex(0);
        this.vistaEmpleado.txtrut.setText("");
        this.vistaEmpleado.txtcelular.setText("");
        this.vistaEmpleado.cbociudad.setSelectedIndex(0);
        this.vistaEmpleado.txtrut.setText("");
        this.vistaEmpleado.txtapellido.setText("");
        this.vistaEmpleado.txtdireccion.setText("");
        this.vistaEmpleado.txtedad.setText("");
        this.vistaEmpleado.btneliminar.setEnabled(false);
        this.vistaEmpleado.btnmodificar.setEnabled(false);
        this.vistaEmpleado.txtrut.setEnabled(true);
        this.vistaEmpleado.btnagregar.setEnabled(true);
        this.vistaEmpleado.txtrut.requestFocus();
        eliminar();

    }
    
    public void buscar(){
        if (esNumero(this.vistaEmpleado.txtrut.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Ingrese datos numéricos en Codigo, vuelva a intentar");
                    

                } else {

                    int verificacioncodigo = Integer.parseInt(this.vistaEmpleado.txtrut.getText());

                    if (verificacioncodigo < 1 || verificacioncodigo > 100) {

                        JOptionPane.showMessageDialog(null, "El código debe ser > a 0 y <= 100. Intente nuevamente");

                    } else {

                        int codigoss = Integer.parseInt(this.vistaEmpleado.txtrut.getText());
                        boolean bconfirmacion = false;
                        System.out.println("buscar");
                        
                        this.vistaMostrar.tbEmpleado.setModel((TableModel) this.modeloDato.buscarDato(codigoss));
                        
                        if (String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 1))=="null"){
                            JOptionPane.showMessageDialog(null, "El Código no existe, vuelva a intentar");
                            limpiartodo();
                            
                        } else {
                        
                        this.vistaEmpleado.txtrut.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 1)));
                        this.vistaEmpleado.txtnombre.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 2)));
                        this.vistaEmpleado.txtapellido.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 3)));
                        this.vistaEmpleado.txtcelular.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 4)));
                        this.vistaEmpleado.txtdireccion.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 5)));
                        this.vistaEmpleado.txtedad.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 6)));
                        
                        String ecivil = String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 7));
                        switch (ecivil){
                            case "C":
                            this.vistaEmpleado.cbootro.setSelectedIndex(1);
                            break;    
                            case "S":
                            this.vistaEmpleado.cbootro.setSelectedIndex(2);
                                break;
                            case "V":
                            this.vistaEmpleado.cbootro.setSelectedIndex(3);    
                        break;
                    }

                        
                        
                        String departamento = String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 8));
                        switch (departamento){
                            case "Administración":
                            this.vistaEmpleado.cbociudad.setSelectedIndex(1);
                            break;    
                            case "Bienestar":
                            this.vistaEmpleado.cbociudad.setSelectedIndex(2);
                                break;
                            case "Finanzas":
                            this.vistaEmpleado.cbociudad.setSelectedIndex(3);    
                        break;
                        
                            case "Informática":
                               this.vistaEmpleado.cbociudad.setSelectedIndex(4);   
                                break;
                                
                            case "Redes":
                                this.vistaEmpleado.cbociudad.setSelectedIndex(5);  
                                break;
                    }
                        
                                    this.vistaEmpleado.btneliminar.setEnabled(true);
                                    this.vistaEmpleado.btnmodificar.setEnabled(true);
                                    this.vistaEmpleado.txtrut.setEnabled(false);
                                    this.vistaEmpleado.btnagregar.setEnabled(false);
                        
                        
                        if (bconfirmacion == true) {
                            
                            
                            JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                        } else {
                           // JOptionPane.showMessageDialog(null, "El código de empleado no existe, vuelva a intentarlo");
                        }
                    }}
                }
    }
    
    static boolean esNumero(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
        }
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case btnagregar:
                //llamamos al metodo que esta en el modelo para agragar el dato y le enviamos lo que captura del textField
                
                try{
                
                String active, active2;


                active = String.valueOf(this.vistaEmpleado.cbociudad.getSelectedItem());
                active2 = String.valueOf(this.vistaEmpleado.cbootro.getSelectedItem());
                

                //System.out.println(active);
                boolean correcto1 = false,
                 correcto2 = false;
                
                if (esNumero(this.vistaEmpleado.txtrut.getText())==false){
                    JOptionPane.showMessageDialog(null, "Ingrese datos numéricos en Codigo, vuelva a intentar");
                    break;
                    
                }else {
                
                
                int verificacioncodigo = Integer.parseInt(this.vistaEmpleado.txtrut.getText());

                //int verificacioncelular = Integer.parseInt(this.vistaEmpleado.txtcelular.getText());

                if (verificacioncodigo < 1 || verificacioncodigo > 100) {

                    JOptionPane.showMessageDialog(null, "El código debe ser > a 0 y <= 100. Intente nuevamente");

                } else {
                    
                    if (this.modeloDato.existeCodigo(verificacioncodigo)==true){
                        JOptionPane.showMessageDialog(null, "El código ya existe, vuelva a intentarlo");
                        
                    } else {
                    
                    
                    
                    
                    
                    //verificar rut no está en blanco
                    
                    if (this.vistaEmpleado.txtrut.getText().length()== 0){
                        JOptionPane.showMessageDialog(null, "Ingrese valor en campo RUT, vuelva a intentar");
                        
                    } else {
                        
                        if (this.vistaEmpleado.txtnombre.getText().length()== 0){
                        JOptionPane.showMessageDialog(null, "Ingrese valor en campo Nombre, vuelva a intentar");
                        
                    } else {
                        
                        if (this.vistaEmpleado.txtapellido.getText().length()== 0){
                        JOptionPane.showMessageDialog(null, "Ingrese datos en campo Apellido, vuelva a intentar");
                        
                    }  else {


                    if (active == "Seleccione" || active2 == "Seleccione") {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar Estado Civil y Departamento");

                        System.out.println(active);

                    } else {
                        
                        if (esNumero(this.vistaEmpleado.txtcelular.getText())==false){
                            
                            JOptionPane.showMessageDialog(null, "Ingrese numeros en el campo Celular, vuelva a intentarlo");
                        } else {

                        if (this.vistaEmpleado.txtcelular.getText().length() < 9 || this.vistaEmpleado.txtcelular.getText().length() > 9) {
                            JOptionPane.showMessageDialog(null, "El numero de celular debe tener 9 digitos, vuelva a intentar");
                        } else {
                            
                            if (esNumero(this.vistaEmpleado.txtedad.getText())==false){
                                
                                JOptionPane.showMessageDialog(null, "Debe ingresar monto de Sueldo Bruto, vuelva a intentar");
                            } else {
                                
                                if (Integer.parseInt(this.vistaEmpleado.txtedad.getText())<120000){
                                    JOptionPane.showMessageDialog(null, "El sueldo bruto debe ser mayor o igual a 120000, vuelva a intentar");
                                } else {
                                
                                if (this.vistaEmpleado.txtdireccion.getText().length() == 0){
                                    JOptionPane.showMessageDialog(null, "Ingrese datos en campo Email, vuelva a intentar");
                                } else {

                            
                                                                                    // int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String est_civil, String nom_depto
                            if (this.modeloDato.agregarPaciente(Integer.parseInt(this.vistaEmpleado.txtrut.getText()), this.vistaEmpleado.txtrut.getText() ,this.vistaEmpleado.txtnombre.getText(), this.vistaEmpleado.txtapellido.getText(), Integer.parseInt(this.vistaEmpleado.txtcelular.getText()), this.vistaEmpleado.txtdireccion.getText(), Integer.parseInt(this.vistaEmpleado.txtedad.getText()), ecivil, String.valueOf(this.vistaEmpleado.cbociudad.getSelectedItem()))) {

                                JOptionPane.showMessageDialog(null, "El Empleado se agregó correctamente");

                                limpiartodo();
                                eliminar();

                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo agregar porque el código ya existe,\nverifiqué el código ingresado");
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
                }
                }
                }catch (Exception ex){}
                //limpiartodo();  
                break;



            case btneliminar:
                //llamamos método para eliminar dato
                int codigos = Integer.parseInt(this.vistaEmpleado.txtrut.getText());
                boolean confirmacion = false;
                System.out.println("btneliminar");
                confirmacion = this.modeloDato.eliminarDato(codigos);
                if (confirmacion == true) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                }
                this.vistaMostrar.tbEmpleado.setModel(this.modeloDato.mostrarDato());
                limpiartodo();
                break;

            case btnmodificar:
                
                
                //lamamos método para modificar valores del producto menos el código
                if (this.modeloDato.modificarDato(Integer.parseInt(this.vistaEmpleado.txtrut.getText()), this.vistaEmpleado.txtrut.getText() ,this.vistaEmpleado.txtnombre.getText(), this.vistaEmpleado.txtapellido.getText(), Integer.parseInt(this.vistaEmpleado.txtcelular.getText()), this.vistaEmpleado.txtdireccion.getText(), Integer.parseInt(this.vistaEmpleado.txtedad.getText()), ecivil, String.valueOf(this.vistaEmpleado.cbociudad.getSelectedItem()))) {

                    JOptionPane.showMessageDialog(null, "El Empleado se modificó correctamente");

                    //Limpiamos
                    limpiartodo();
                    eliminar();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar");
                }

                System.out.println("btnmodificar");
                this.vistaMostrar.tbEmpleado.setModel(this.modeloDato.mostrarDato());
                break;

            case btnmostrar:

                this.vistaMostrar.tbEmpleado.setModel(this.modeloDato.mostrarDato());
                break;
                


            case btnsalir:
                limpiartodo();
                break;
                
            case btnbuscar:

                buscar();
                
                break;
                
            case btnlimpiar:
                eliminar();
                limpiartodo();
                break;
                
            case btnvolver:
                this.vistaMostrar.setVisible(false);//Hago que la vista sea visible
                limpiartodo();

                break;


            case msissalir:
                System.exit(0);
                break;
                
            case mempmostrar:
                this.vistaMostrar.setVisible(true);//Hago que la vista sea visible
                this.vistaMostrar.setLocationRelativeTo(null);
                this.vistaMostrar.setTitle("Mostras Datos Empleados");
                
                break;
               
            case cboestadocivil:
 
                if (this.vistaEmpleado.cbootro.getSelectedItem() == "Casado") {
                    ecivil = "C";
                }
                if (this.vistaEmpleado.cbootro.getSelectedItem() == "Soltero") {
                    ecivil = "S";
                }
                if (this.vistaEmpleado.cbootro.getSelectedItem() == "Viudo") {
                    ecivil = "V";
                }

                break;
                

                

                

                

        }

    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Registro(new vista.Menu()).iniciar();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.vistaMostrar.tbEmpleado.rowAtPoint(e.getPoint());

        /* row devolvera -1 si se ha clicado fuera de la fila pero dentro de la tabla, si no devolvera el indice de la fila en la que se ha clicado. */
        this.vistaEmpleado.txtrut.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 0)));
        this.vistaEmpleado.txtrut.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 1)));
        this.vistaEmpleado.txtnombre.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 2)));
        this.vistaEmpleado.txtapellido.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 3)));
        this.vistaEmpleado.txtcelular.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 4)));
        this.vistaEmpleado.txtdireccion.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 5)));
        this.vistaEmpleado.txtedad.setText(String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(row, 6)));

        String ecivil = String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 7));
        switch (ecivil) {
            case "C":
                this.vistaEmpleado.cbootro.setSelectedIndex(1);
                break;
            case "S":
                this.vistaEmpleado.cbootro.setSelectedIndex(2);
                break;
            case "V":
                this.vistaEmpleado.cbootro.setSelectedIndex(3);
                break;
        }
        
                        
                        
                        String departamento = String.valueOf(this.vistaMostrar.tbEmpleado.getValueAt(0, 8));
                        switch (departamento){
                            case "Administración":
                            this.vistaEmpleado.cbociudad.setSelectedIndex(1);
                            break;    
                            case "Bienestar":
                            this.vistaEmpleado.cbociudad.setSelectedIndex(2);
                                break;
                            case "Finanzas":
                            this.vistaEmpleado.cbociudad.setSelectedIndex(3);    
                        break;
                        
                            case "Informática":
                               this.vistaEmpleado.cbociudad.setSelectedIndex(4);   
                                break;
                                
                            case "Redes":
                                this.vistaEmpleado.cbociudad.setSelectedIndex(5);  
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
