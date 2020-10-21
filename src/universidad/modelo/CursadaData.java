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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidad.entidad.Alumno;
import universidad.entidad.Cursada;
import universidad.entidad.Materia;

/**
 *
 * @author mgara
 */
public class CursadaData {
    private Connection con = null;
    
    public CursadaData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    public void guardarNota(Cursada c){
        String sql="INSERT INTO `cursada`(`id_alumno`, `id_materia`, `nota`) VALUES (?,?,?);";
        try{
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getAlumno().getId_alumno());
            st.setInt(2, c.getMateria().getId_Materia());
            st.setDouble(3, c.getNota());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            
            if(rs.next()){
                c.setId_cursada(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id de la cursada");
            }
        con.close();
        }catch(SQLException e){
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar Nota");
        }
    }
    
    public void actualizarNota(Cursada c){
        String sql = "\"UPDATE `cursada` SET `idAlumno`=?, `idMateria`=?, `calificacion`=? WHERE 1";
   
        try {
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getAlumno().getId_alumno());
            st.setInt(2, c.getMateria().getId_Materia());
            st.setDouble(3, c.getNota());
            
            st.executeUpdate();
            
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar Nota");
        }        
    
    }
    
    public void borrarNota(int idAlumno, int idMateria){
        Cursada c = new Cursada();
        String sql = "DELETE FROM `cursada` WHERE idAlumno=?, idMateria=?;";
             
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            ps.executeUpdate();
        }
            
        catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscarNota(int id){
        String sql = "SELECT * FROM cursada WHERE id_alumno=?"; 
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo obtener Calificacion");
        }
    }
    
    public Alumno buscarAlumno(int id){
        Conexion c=new Conexion();
        AlumnoData alumnoD= new AlumnoData(c);
        return alumnoD.buscarAlumno(id);
    }
    
    //es lo mismo que buscarCalificacionMateria()
    public Materia buscarMateria(int id){
        Materia materia= new Materia();
        String sql="SELECT * FROM `cursada` WHERE id_materia= ? ;";
        
            
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
            JOptionPane.showMessageDialog(null,"Error al buscar alumno");
        }
        return materia;
    }
    
    public Cursada buscarCursada(int id){
        Cursada cursada= new Cursada();
        String sql="SELECT * FROM `cursada` WHERE id_cursada=?;";
        
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               cursada.setId_cursada(rs.getInt(1));
               Alumno a=buscarAlumno(rs.getInt(2));
               cursada.setAlumno(a);
               Materia m=buscarMateria(rs.getInt(3));
               cursada.setMateria(m);
               cursada.getNota();
               
               ps.close();
            }
        }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
           JOptionPane.showMessageDialog(null,"Error al buscar alumnos");
        }
        return cursada;
    }
    
    public List<Cursada> listarCursadas(){
        Cursada cursada= new Cursada();
        List<Cursada> lista= new ArrayList<>();
        String sql="SELECT * FROM `cursada`;";
        
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               cursada.setId_cursada(rs.getInt(1));
               Alumno a=buscarAlumno(rs.getInt(2));
               cursada.setAlumno(a);
               Materia m=buscarMateria(rs.getInt(3));
               cursada.setMateria(m);
               cursada.getNota();
               lista.add(cursada);
               ps.close();
            }
        }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
           JOptionPane.showMessageDialog(null,"Error al buscar alumnos");
        }
        return lista;
    }
    
    public List<Cursada> bucarCursadaXAlumno(int id){
        Cursada cursada= new Cursada();
        List<Cursada> lista= new ArrayList<>();
        String sql="SELECT * FROM `cursada` WHERE cursada.id_alumno=?;";
        
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               cursada.setId_cursada(rs.getInt(1));
               Alumno a=buscarAlumno(rs.getInt(2));
               cursada.setAlumno(a);
               Materia m=buscarMateria(rs.getInt(3));
               cursada.setMateria(m);
               cursada.getNota();
               lista.add(cursada);
               ps.close();
            }
        }
            
        catch(SQLException e){
            System.err.print(e.getMessage());
           JOptionPane.showMessageDialog(null,"Error al buscar alumnos");
        }
        return lista; 
    }
    
    public List<Materia> buscarCursadaXMateria(int id){
        Conexion c=new Conexion();
        MateriaData materiaD= new MateriaData(c);

        return materiaD.buscarMaterias(id);
 
    }
}
