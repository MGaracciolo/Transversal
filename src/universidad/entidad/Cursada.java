package universidad.entidad;

import universidad.entidad.Alumno;

public class Cursada {

    private int id_cursada;

    private double nota;

    private Alumno alumno;

    private Materia materia;

    public Cursada(int id_cursada, double nota, Alumno alumno, Materia materia) {
        this.id_cursada=id_cursada;
        this.nota=nota;
        this.alumno=alumno;
        this.materia=materia;
    }

    public Cursada(double nota, Alumno alumno, Materia materia) {
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }
    
    public Cursada() {
    }

    public void setId_cursada(int id_cursada) {
        this.id_cursada=id_cursada;
    }

    public int getId_cursada() {
        return id_cursada;
    }

    public void setNota(double nota) {
        this.nota=nota;
    }

    public double getNota() {
        return nota;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno=alumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setMateria(Materia materia) {
        this.materia=materia;
    }

    public Materia getMateria() {
        return materia;
    }
    
    @Override
    public String toString() {
        return "ID cursada: "+id_cursada+" Alumno: "+alumno+" Materia: "+materia+" Nota: "+nota; 
    }
}
