package model.domain;

public class Veterinario extends Persona {
    private String especialidad;

    public Veterinario(String identificacion, String nombre, String telefono, String especialidad) {
        super(identificacion, nombre, telefono);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String rolEnClinica() {
        return "Veterinario especialista en " + especialidad;
    }
}
