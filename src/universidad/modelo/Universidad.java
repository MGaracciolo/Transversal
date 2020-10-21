/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.modelo;

import java.sql.Connection;
import universidad.entidad.Alumno;
import universidad.entidad.Cursada;
import universidad.entidad.Materia;

/**
 *
 * @author mgara
 */
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion c= new Conexion();
        Connection con=c.getConnection();
        Alumno a= new Alumno("Pepito",4522,true);
        Alumno a1= new Alumno("Marianela",13,true);
        Alumno a2= new Alumno("Ariel",14,true);
        Alumno a3= new Alumno("Malco",15,true);
        Materia m= new Materia("Matematica");
        Materia m2= new Materia("Laboratorio I");
        Materia m3= new Materia("Programacion Web");
        Materia m4= new Materia("EDA");
        AlumnoData ad= new AlumnoData(c);
        CursadaData cd=new CursadaData(c);
        Cursada cur=new Cursada(8,a1,m2);
        Cursada cur2=new Cursada(9,a2,m2);
        Cursada cur3=new Cursada(10,a3,m2);
        /*ad.guardarAlumno(a);
        ad.guardarAlumno(a1);
        ad.guardarAlumno(a2);
        ad.guardarAlumno(a3);*/
        
        /*ad.borrarAlumno(1);
        ad.borrarAlumno(2);*/
        
        System.out.println(ad.listarAlumnos());
        System.out.println(cd.buscarCursadaXMateria(m2.getId_Materia()));
        
    
        
        
    }
    
}
