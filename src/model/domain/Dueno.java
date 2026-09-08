package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Dueno extends Persona {
    private String direccion;
    private List<Animal> animales;

    public Dueno(String identificacion, String nombre, String telefono, String direccion) {
        super(identificacion, nombre, telefono);
        this.direccion = direccion;
        this.animales = new ArrayList<>();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void agregarAnimal(Animal animal) {
        animales.add(animal);
    }

    public void eliminarAnimal(String numeroFicha) {
        animales.removeIf(a -> a.getNumeroFicha().equals(numeroFicha));
    }

    public Animal buscarAnimal(String numeroFicha) {
        for (Animal animal : animales) {
            if (animal.getNumeroFicha().equals(numeroFicha)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public String rolEnClinica() {
        return "Tiene " + animales.size()
                + " animal" + (animales.size() == 1 ? "" : "es")
                + " a cargo";
    }
}