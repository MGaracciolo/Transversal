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
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidad.entidad.Alumno;

/**
 *
 * @author mgara
 */
public class AlumnoData {
    private Connection con;
    
    public AlumnoData(Conexion c){
        con=c.getConnection();
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql="INSERT INTO `alumno`(`nombre_alumno`, `legajo`, `activo`) VALUES ( ? , ? , ? );";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1,alumno.getNombre_alumno());
                ps.setInt(2, alumno.getLegajo());
                ps.setBoolean(3, alumno.getActivo());
                
                ps.executeUpdate();
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    alumno.setId_alumno(rs.getInt(1));
                }else{
                    JOptionPane.showMessageDialog(null,"No se pudo obtener el id luego de insertar el alumno");
                }
            }
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al guardar alumno");
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno alumno= new Alumno();
        String sql="SELECT * FROM `alumno` WHERE id_alumno= ? ;";
        
            
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    alumno.setId_alumno(rs.getInt(1));
                    alumno.setNombre_alumno(rs.getString(2));
                    alumno.setLegajo(rs.getInt(3));
                    alumno.setActivo(rs.getBoolean(4));
                }
            }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al buscar alumno");
        }
        return alumno;
    }
    
    public List<Alumno> listarAlumnos(){
        Alumno alumno= new Alumno();
        List<Alumno> lista= new ArrayList<>();
        String sql="SELECT * FROM `alumno`;";
        
        
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    alumno.setId_alumno(rs.getInt(1));
                    alumno.setNombre_alumno(rs.getString(2));
                    alumno.setLegajo(rs.getInt(3));
                    alumno.setActivo(rs.getBoolean(4));
                    lista.add(alumno);
                }
            }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
           JOptionPane.showMessageDialog(null,"Error al buscar alumnos");
        }
        return lista;
    }
    
        public void actualizarAlumno(Alumno alumno){
        String sql="UPDATE `alumno` SET nombre=?, legajo=?, activo=? WHERE id_alumno"
                    + "=?;";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1,alumno.getNombre_alumno());
                ps.setInt(2, alumno.getLegajo());
                ps.setBoolean(3, alumno.getActivo());
                ps.setInt(4, alumno.getId_alumno());
                
                ps.executeUpdate();
            }
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al guardar alumno");
        }
    }
        
    public void borrarAlumno(int id){
        String sql="DELETE FROM `alumno` WHERE id_alumno=? ;";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1,id);
                ps.executeUpdate();
            }
        catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al eliminar alumno");
        }
    }
}
