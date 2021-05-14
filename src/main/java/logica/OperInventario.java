/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dto.inventario;
import database.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author chicu
 */
public class OperInventario implements Operaciones<inventario>{   
    @Override
    public int insertar(inventario dato) {
        conexion c = new conexion();
        Connection cActiva = c.conectarse();
        if (cActiva != null){
            try {
                String sql = "insert into producto (codigo, nombre, fechaVencimiento, fechaRegistro, valorProducto, cantidadComprada, cantidadActual ) values (?,?,?,?,?,?,?)";
                PreparedStatement ps =  cActiva.prepareStatement(sql);
                ps.setInt(1, dato.getCodigoP());
                ps.setString(2, dato.getNombrep());
                ps.setString(3, dato.getFechaVencimientop());
                ps.setString(4, dato.getFechaRegistro());
                ps.setInt(5, dato.getValorProductop());
                ps.setInt(6, dato.getCantidadComprada());
                ps.setInt(7, dato.getCantidadDisponible());

                int rta = ps.executeUpdate();
                return rta;
            } catch (SQLException ex) {
                Logger.getLogger(OperInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                c.desconectase(cActiva);
            }
        }
        return 0;
                
    }
   @Override
    public List<inventario> consultar() {
        conexion c = new conexion();
        Connection cActiva = c.conectarse();
        List<inventario> datos = new ArrayList<>();
        if (cActiva != null){
            try {
                String sql = "select * from producto";
                PreparedStatement ps =  cActiva.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){                  
                    inventario i = new inventario();                                      
                    i.setCodigoP(rs.getInt("codigo"));
                    i.setNombrep(rs.getString("nombre"));
                    i.setFechaVencimientop(rs.getString("fechaVencimiento"));
                    i.setFechaRegistro(rs.getString("fechaRegistro"));
                    i.setValorProductop(rs.getInt("valorProducto"));
                    i.setCantidadComprada(rs.getInt("cantidadComprada"));
                    i.setCantidadDisponible(rs.getInt("cantidadActual"));             
                    datos.add(i);
                }
            }
             catch (SQLException ex) {
                Logger.getLogger(OperInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                c.desconectase(cActiva);
            }
        }
        return datos;
    }
@Override
    public int actualizar(inventario dato) {
        conexion c = new conexion();
        Connection cActiva = c.conectarse();
        if (cActiva != null){
            try {
                String sql = "UPDATE producto SET nombre = ?, fechaVencimiento = ?, fechaRegistro = ?, valorProducto = ?, cantidadComprada = ?, cantidadActual = ? Where  codigo = ?";
                PreparedStatement ps =  cActiva.prepareStatement(sql);
                ps.setString(1, dato.getNombrep());
                ps.setString(2,dato.getFechaVencimientop());
                ps.setString(3,dato.getFechaRegistro());
                ps.setInt(4, dato.getValorProductop());
                ps.setInt(5,dato.getCantidadComprada());
                ps.setInt(6,dato.getCantidadDisponible());
                ps.setInt(7,dato.getCodigoP());

                int rta = ps.executeUpdate();
                return rta;
            } catch (SQLException ex) {
                Logger.getLogger(OperInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                c.desconectase(cActiva);
            }
        }
        return 0;
    }
}
