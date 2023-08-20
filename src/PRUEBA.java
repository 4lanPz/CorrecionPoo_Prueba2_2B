import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.sql.*;

    /*CREATE DATABASE PruebaAlan;
    USE PruebaAlan;
    CREATE TABLE DATOS(
            CODIGO INT,
            CEDULA INT,
            NOMBRE VARCHAR(25),
    FECHA_NACIMIENTO VARCHAR(25),
    SIGNO VARCHAR(25)
)*/

/*PRUEBA DE ALAN PEREZ*/
public class PRUEBA {
    String Nombre,Fecha,Signo,Cedula;
    int Codigo;
    String conexion= "jdbc:sqlserver://localhost:1433;" +
            "database=CorrecionP2B;" +
            "user=root;" +
            "password=root_1;" +
            "trustServerCertificate=true;";
    private JButton BBORRAR;
    private JButton BACTUALIZAR;
    private JButton BINGRESAR;
    private JButton BLIMPIAR;
    private JButton BUSCARPORCODIGOButton;
    private JButton BUSCARPORNOMBREButton;
    private JButton BOTONBUSCARPORSIGNOButton;
    private JTextField TCODIGO;
    private JTextField TCEDULA;
    private JTextField TNOMBRE;
    private JTextField TFECHA;
    private JComboBox CSIGNO;
    private JLabel LCODIGO;
    private JLabel LCEDULA;
    private JLabel LNOMBRE;
    private JLabel LSIGNO;
    private JLabel LFECHA;
    private JPanel PANTALLA;
    private JLabel LABEL1;
    private JLabel TITULO;


    public PRUEBA() {
        BINGRESAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Codigo = Integer.parseInt(TCODIGO.getText());
                Cedula = TCEDULA.getText();
                Nombre = TNOMBRE.getText();
                Fecha = TFECHA.getText();
                Signo = (String) CSIGNO.getSelectedItem();
                String QUERY="INSERT INTO DATOS(CODIGO,CEDULA,NOMBRE,FECHA_NACIMIENTO,SIGNO)" +
                        "VALUES(?,?,?,?,?)";
                try(Connection conn=DriverManager.getConnection(conexion))
                {
                    PreparedStatement statement = conn.prepareStatement(QUERY);
                    // Establecer valores para los parámetros de la sentencia SQL
                    statement.setInt(1,Codigo);
                    statement.setString(2, Cedula);
                    statement.setString(3, Nombre);
                    statement.setString(4, Fecha);
                    statement.setString(5, Signo);
                    // Ejecutar sentencia SQL
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        LABEL1.setText("Datos insertados correctamente");
                    }
                }
                catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        BACTUALIZAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToUpdate; // Reemplaza 1 con el ID de la fila que deseas actualizar
                String nuevoNombre; //
                idToUpdate= Integer.parseInt(TCODIGO.getText());
                Cedula = TCEDULA.getText();
                Nombre = TNOMBRE.getText();
                Fecha = TFECHA.getText();
                Signo = (String) CSIGNO.getSelectedItem();
                String UPDATE_QUERY = "UPDATE DATOS SET CEDULA = ?, NOMBRE = ?, FECHA_NACIMIENTO = ? , SIGNO = ? WHERE CODIGO = ?";
                try (Connection conn = DriverManager.getConnection(conexion)) {
                    PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
                    // Establecer nuevos valores para los campos siguiendo el orden del query
                    statement.setString(1,Cedula);
                    statement.setString(2,Nombre);
                    statement.setString(3,Fecha);
                    statement.setString(4,Signo);
                    // Establecer valor para el parámetro del ID de la fila que deseas actualizar
                    statement.setInt(5, idToUpdate);
                    // Ejecutar sentencia UPDATE
                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        LABEL1.setText("Se actualizaron los datos correctamente");
                    } else {
                        LABEL1.setText("No hay datos con ese codigo, ingrese nuevamente");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        BLIMPIAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vacio1="",vacio2="ELEJIR";
                TCODIGO.setText(vacio1);
                TCEDULA.setText(vacio1);
                TNOMBRE.setText(vacio1);
                TFECHA.setText(vacio1);
                CSIGNO.setSelectedItem(vacio2);
                LABEL1.setText("Formulario vacio");
            }
        });
        BBORRAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToDelete;
                idToDelete= Integer.parseInt(TCODIGO.getText());
                String DELETE_QUERY = "DELETE FROM DATOS WHERE CODIGO = ?";
                try (Connection conn = DriverManager.getConnection(conexion)) {
                    PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
                    // Establecer valor para el parámetro del ID que deseas eliminar
                    statement.setInt(1, idToDelete);

                    // Ejecutar sentencia DELETE
                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted > 0) {
                        LABEL1.setText("Se eliminó la fila correctamente");
                    } else {
                        LABEL1.setText("No se encontró datos con ese codigo");
                    }
                } catch (SQLException eX) {
                    eX.printStackTrace();
                }
            }
        });
        BOTONBUSCARPORSIGNOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SIGNO1;
                SIGNO1 = (String) CSIGNO.getSelectedItem();
                String SELECT_QUERY="SELECT * FROM DATOS WHERE SIGNO = ?";
                try(Connection conn=DriverManager.getConnection(conexion);)
                {
                    PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                    statement.setString(1, SIGNO1);
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        Codigo = Integer.parseInt(rs.getString("CODIGO"));
                        Cedula = rs.getString("CEDULA");
                        Nombre = rs.getString("NOMBRE");
                        Fecha = rs.getString("FECHA_NACIMIENTO");
                    }
                    TCODIGO.setText(String.valueOf(Codigo));
                    TCEDULA.setText(Cedula);
                    TNOMBRE.setText(Nombre);
                    TFECHA.setText(Fecha);
                }
                catch (SQLException eX){
                    throw new RuntimeException(eX);
                }
                LABEL1.setText("Datos impresos / 0 si es que no existe");
            }
        });
        BUSCARPORCODIGOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Codigo= Integer.parseInt(TCODIGO.getText());
                String SELECT_QUERY="SELECT * FROM DATOS WHERE CODIGO = ?";
                try(Connection conn=DriverManager.getConnection(conexion);)
                {
                    PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                    statement.setString(1, String.valueOf(Codigo));
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        Nombre = rs.getString("NOMBRE");
                        Cedula = rs.getString("CEDULA");
                        Signo = rs.getString("SIGNO");
                        Fecha = rs.getString("FECHA_NACIMIENTO");
                    }
                    TNOMBRE.setText(String.valueOf(Nombre));
                    TCEDULA.setText(Cedula);
                    CSIGNO.setSelectedItem(Signo);
                    TFECHA.setText(Fecha);
                    LABEL1.setText("Datos impresos si existe / 0 null si no existe");
                }
                catch (SQLException eX){
                    throw new RuntimeException(eX);
                }
            }
        });
        BUSCARPORNOMBREButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nombre=TNOMBRE.getText();
                String SELECT_QUERY="SELECT * FROM DATOS WHERE NOMBRE = ?";
                try(Connection conn=DriverManager.getConnection(conexion);)
                {
                    PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                    statement.setString(1,Nombre);
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        Codigo = Integer.parseInt(rs.getString("CODIGO"));
                        Cedula = rs.getString("CEDULA");
                        Signo = rs.getString("SIGNO");
                        Fecha = rs.getString("FECHA_NACIMIENTO");
                    }
                    TCODIGO.setText(String.valueOf(Codigo));
                    TCEDULA.setText(Cedula);
                    CSIGNO.setSelectedItem(Signo);
                    TFECHA.setText(Fecha);
                    LABEL1.setText("Datos impresos si existe");
                }
                catch (SQLException eX){
                    throw new RuntimeException(eX);
                }
            }
        });
    }
    static public void main(String[] args){
        JFrame frame = new JFrame("Datos");
        frame.setContentPane(new PRUEBA().PANTALLA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
