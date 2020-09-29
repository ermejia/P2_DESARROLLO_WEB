package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author mejia
 */
public class Brand {
    private int idMarca;
    private String Marca;
    DatabaseConnection cn;
    
    public Brand(){}

    public Brand(int idMarca, String Marca) {
        this.idMarca = idMarca;
        this.Marca = Marca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
    
    public HashMap ChooseBrand(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="Select idMarca as id, Marca from marcas";
         cn = new DatabaseConnection();
         cn.openConnetion();
            ResultSet consulta = cn.connection.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("id"),consulta.getString("Marca"));
            }
         cn.closeConnection();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
    
    
}
