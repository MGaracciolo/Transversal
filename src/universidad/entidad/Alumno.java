package universidad.entidad;

public class Alumno {

    private int id_alumno;

    private String nombre_alumno;

    private int legajo;

    private boolean activo;

    public Alumno(int id_alumno, String nombre_alumno, int legajo, boolean activo) {
        this.id_alumno=id_alumno;
        this.nombre_alumno=nombre_alumno;
        this.legajo=legajo;
        this.activo=activo;
    }

    public Alumno(String nombre_alumno, int legajo, boolean activo) {
        this.nombre_alumno = nombre_alumno;
        this.legajo = legajo;
        this.activo = activo;
    }
    
    public Alumno() {
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno=id_alumno;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno=nombre_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setLegajo(int legajo) {
        this.legajo=legajo;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setActivo(boolean activo) {
        this.activo=activo;
    }

    public boolean getActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "ID: "+id_alumno+" Nombre: "+nombre_alumno+" Legajo: "+legajo+" Activo: "+activo; 
    }
    
    
}
