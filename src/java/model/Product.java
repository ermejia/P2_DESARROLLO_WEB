/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mejia
 */
public class Product extends Crud{
    private int idProducto, idMarca, existencia;
    private String nombreProducto, descripcionProducto, precio_costo, precio_venta;
    
    DatabaseConnection cn;
    
    public Product(){}

    public Product(int idProducto, int idMarca, int existencia, String nombreProducto, String descripcionProducto, String precio_costo, String precio_venta) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.existencia = existencia;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(String precio_costo) {
        this.precio_costo = precio_costo;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }

    
    
 public DefaultTableModel ReadTable(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
        cn = new DatabaseConnection();
        cn.openConnetion();
         String query = "SELECT p.idProducto as id, p.nombreProducto, p.descripcionProducto, p.precio_costo, p.precio_venta, p.existencia, m.marca, m.idMarca FROM productos as p inner join marcas as m on p.idMarca = m.idMarca;";
         ResultSet consulta = cn.connection.createStatement().executeQuery(query);
         String encabezado[] = {"id","nombreProducto","descripcionProducto","precio_costo","precio_venta","existencia","marca","idMarca"};
         tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String[9];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("nombreProducto");
          datos[2] = consulta.getString("descripcionProducto");
          datos[3] = consulta.getString("precio_costo");
          datos[4] = consulta.getString("precio_venta");
          datos[5] = consulta.getString("existencia");
          datos[6] = consulta.getString("marca");
          datos[7] = consulta.getString("idMarca");
          tabla.addRow(datos);
         }
        cn.closeConnection();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return tabla;
    }
    
 @Override
    public int Add(){
        int retorno=0;
        try{
            PreparedStatement parametro;
            cn = new DatabaseConnection();
            String query = "insert into productos(idProducto, nombreProducto, idMarca, descripcionProducto, precio_costo, precio_venta, existencia) values(?,?,?,?,?,?,?);";
            cn.openConnetion();
            parametro = (PreparedStatement)cn.connection.prepareStatement(query);
            parametro.setInt(1,getIdProducto());
            parametro.setString(2,getNombreProducto());
            parametro.setInt(3,getIdMarca());
            parametro.setString(4,getDescripcionProducto());
            parametro.setString(5,getPrecio_costo());
            parametro.setString(6,getPrecio_venta());
            parametro.setInt(7, getExistencia());
            retorno = parametro.executeUpdate();
            cn.closeConnection();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    
@Override
   public int Modify(){
       int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new DatabaseConnection();
            String query = "update productos set nombreProducto = ?, idMarca = ?, descripcionProducto = ?, precio_costo = ?, precio_venta = ?, existencia = ? where idProducto = ?;";
            cn.openConnetion();
            parametro = (PreparedStatement)cn.connection.prepareStatement(query);
            parametro.setString(1,getNombreProducto());
            parametro.setInt(2,getIdMarca());
            parametro.setString(3,getDescripcionProducto());
            parametro.setString(4,getPrecio_costo());
            parametro.setString(5,getPrecio_venta());
            parametro.setInt(6, getExistencia());
            parametro.setInt(7,getIdProducto());
            retorno = parametro.executeUpdate();
            cn.closeConnection();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
@Override
    public int Delete(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new DatabaseConnection();
            String query = "delete from productos where idProducto = ?;";
            cn.openConnetion();
            parametro = (PreparedStatement)cn.connection.prepareStatement(query);
            parametro.setInt(1, getIdProducto());
            retorno = parametro.executeUpdate();
            cn.closeConnection();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
   
}

