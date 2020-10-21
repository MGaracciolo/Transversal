/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidad.entidad.Materia;

/**
 *
 * @author mgara
 */
public class MateriaData {
    private Connection con;
    
    public MateriaData(Conexion c){
        con=c.getConnection();
    }
    
    public void guardarMateria(Materia materia){
        String sql="INSERT INTO `materia`(`nombre_materia`) VALUES ( ? );";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1,materia.getNombre_Materia());
                
                ps.executeUpdate();
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    materia.setId_Materia(rs.getInt(1));
                }else{
                    JOptionPane.showMessageDialog(null,"No se pudo obtener el id luego de insertar la materia");
                }
            }
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al guardar materia");
        }
    }
    
     public Materia buscarMateria(int id){
        Materia materia= new Materia();
        String sql="SELECT * FROM `materia` WHERE id_materia= ? ;";
        
            
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    materia.setId_Materia(rs.getInt(1));
                    materia.setNombre_Materia(rs.getString(2));
                }
            }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al buscar materia");
        }
        return materia;
    }
     
     public List<Materia> buscarMaterias(int id){
        Materia materia= new Materia();
        List<Materia> lista= new ArrayList<>();
        String sql="SELECT * FROM `materia` WHERE materia.id_materia=? ;";
        
        
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    materia.setId_Materia(rs.getInt(1));
                    materia.setNombre_Materia(rs.getString(2));

                    lista.add(materia);
                }
            }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
           JOptionPane.showMessageDialog(null,"Error al buscar materias");
        }
        return lista;
    }
    
        public void actualizarMateria(Materia materia){
        String sql="UPDATE `materia` SET nombre_materia=? WHERE id_materia=?;";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1,materia.getNombre_Materia());
                
                ps.executeUpdate();
            }
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al guardar materia");
        }
    }
        
    public void borrarMateria(int id){
        String sql="DELETE FROM `materia` WHERE id_materia=? ;";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1,id);
                ps.executeUpdate();
            }
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al eliminar materia");
        }
    }
}


