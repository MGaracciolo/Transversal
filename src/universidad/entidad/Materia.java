package universidad.entidad;

public class Materia {

    private int id_materia;

    private String nombre_materia;

    public Materia(int id, String nombre) {
        id_materia=id;
        nombre_materia=nombre;
    }
    
    public Materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
    
    public Materia() {
    }

    public void setId_Materia(int id_materia) {
        this.id_materia=id_materia;
    }

    public int getId_Materia() {
        return id_materia;
    }

    public void setNombre_Materia(String nombre_materia) {
        this.nombre_materia=nombre_materia;
    }

    public String getNombre_Materia() {
        return nombre_materia;
    }
    
    @Override
    public String toString() {
        return "ID: "+id_materia+" Nombre: "+nombre_materia; 
    }
}
