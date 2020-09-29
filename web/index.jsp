<%-- 
    Document   : main
    Created on : 20/09/2020, 06:54:41 PM
    Author     : mejia
--%>

<%@page import="model.Brand" %>
<%@page import="model.Product" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Page Products</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <h1>Products Form</h1>
        <button type="button" class="btn btn-info btn-success" data-toggle="modal" data-target="#modal_producto" onclick="clean()">New Product</button>        
    <div class="container">
          <div class="modal fade" id="modal_producto" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
            <form action="ProductServlet" method="post" class="form-group">
               <label for="lbl_id" ><b>Product ID</b></label>
                <input type="text" name="txt_idProducto" id="txt_idProducto" class="form-control" value="Add Product Id" required> 
                <label for="lbl_nombres" ><b>Product Name</b></label>
                <input type="text" name="txt_nombre" id="txt_nombre" class="form-control" placeholder="Add Product Name" required>
                <label for="lbl_apellidos" ><b>Product Description</b></label>
                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Add Product Description" required>
                <label for="lbl_direccion" ><b>Buy Cost</b></label>
                <input type="text"  name="txt_precio_costo" id="txt_precio_costo" class="form-control" placeholder="Add Product Price" required>
                <label for="lbl_telefono" ><b>Sell Cost</b></label>
                <input type="text" name="txt_precio_venta" id="txt_precio_venta" class="form-control" placeholder="Add Product Price to Sell" required>
                <label for="lbl_telefono" ><b>Available</b></label>
                <input type="text" name="txt_existencia" id="txt_existencia" class="form-control" placeholder="Add Product Existencies" required>
                <label for="lbl_puesto" ><b>Brand</b></label>
                <select name="ChooseBrand" id="ChooseBrand" class="form-control">
                    <% 
                        Brand brand = new Brand();
                        HashMap<String,String> drop = brand.ChooseBrand();
                         for (String i:drop.keySet())
                         {
                             out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                         }
                    %>
                </select>
                <br>
                <button name="btn_agregar" id="btn_agregar"  value="Add" class="btn btn-primary btn-lg">Add</button>
                <button name="btn_modificar" id="btn_modificar"  value="Modify" class="btn btn-success btn-lg">Modify</button>
                <button name="btn_eliminar" id="btn_modificar"  value="Delete" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('Delete?'))return false" >Delete</button>
                <button type="button" class="btn btn-warning btn-lg" data-dismiss="modal">Close</button>
            </form>
        </div>
      </div>
      
    </div>
  </div>
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Product Description</th>
        <th>Buy Cost</th>
        <th>Sell Cost</th>
        <th>Existencies</th>
        <th>Brand</th>
      </tr>
    </thead>
    <tbody id="tbl_productos">
        <% 
        Product product = new Product();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = product.ReadTable();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-idMarca=" + tabla.getValueAt(t,7) + ">");
            out.println("<td>" + tabla.getValueAt(t,0) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
            out.println("</tr>");
        }
        %>
    </tbody>
  </table>
  </div>
      

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script type="text/javascript">
    function clean(){
       $("#txt_idProducto").val(0);
       $("#txt_nombre").val('');
       $("#txt_descripcion").val('');
       $("#txt_precio_costo").val('');
       $("#txt_precio_venta").val('');
       $("#txt_existencia").val('');
       $("#ChooseBrand").val(7);
    }
    
    $('#tbl_productos').on('click','tr td',function(evt){
       var target, id, idMarca, nombre, descripcion, costo, venta, existencias; 
       target = $(event.target);
       idMarca = target.parent().data('idMarca'); 
       id =  target.parent("tr").find("td").eq(0).html();
       nombre = target.parent("tr").find("td").eq(1).html();
       descripcion= target.parent("tr").find("td").eq(2).html();
       costo = target.parent("tr").find("td").eq(3).html();
       venta = target.parent("tr").find("td").eq(4).html();
       existencias = target.parent("tr").find("td").eq(5).html();
       
       $("#txt_idProducto").val(id);
       $("#txt_nombre").val(nombre);
       $("#txt_descripcion").val(descripcion);
       $("#txt_precio_costo").val(costo);
       $("#txt_precio_venta").val(venta);
       $("#txt_existencia").val(existencias);
       $("#ChooseBrand").val(idMarca);
       $("#modal_producto").modal('show');
    });
    
</script>
    </body>
</html>
