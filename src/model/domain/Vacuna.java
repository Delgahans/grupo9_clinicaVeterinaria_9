```java
package model.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Vacuna {

    private String nombre;
    private LocalDate fechaAplicacion;
    private LocalDate proximaFecha;
    private Animal animal;

    public Vacuna(String nombre, LocalDate fechaAplicacion, LocalDate proximaFecha, Animal animal) {
        this.nombre = nombre;
        this.fechaAplicacion = fechaAplicacion;
        this.proximaFecha = proximaFecha;
        this.animal = animal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(LocalDate fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public LocalDate getProximaFecha() {
        return proximaFecha;
    }

    public void setProximaFecha(LocalDate proximaFecha) {
        this.proximaFecha = proximaFecha;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Vacuna)) {
            return false;
        }

        Vacuna vacuna = (Vacuna) o;

        return Objects.equals(nombre, vacuna.nombre)
                && Objects.equals(fechaAplicacion, vacuna.fechaAplicacion)
                && Objects.equals(proximaFecha, vacuna.proximaFecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaAplicacion, proximaFecha);
    }
}
