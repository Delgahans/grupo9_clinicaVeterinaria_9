public abstract class Persona implements RolEnClinica {
    private String identificacion;
    private String nombre;
    private String telefono;

    public Persona(String identificacion, String nombre, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }      
    
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String datosResumen() {
        return identificacion + " - " + nombre + " - " + telefono;
    } 
}
