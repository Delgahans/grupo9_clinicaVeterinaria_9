package model.domain;

public class Veterinario {
    private String especialidad;

    // Constructor
    public Veterinario(String especialidad) {
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
