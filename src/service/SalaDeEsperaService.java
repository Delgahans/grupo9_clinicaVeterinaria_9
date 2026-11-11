
package service;

import model.domain.Animal;
import model.structures.Cola;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class SalaDeEsperaService {

    private final ListaSimple<Animal> animales = new ListaSimple<>();
    private final Cola<Animal> fila = new Cola<>();

    public void registrarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException(
                    "El animal no puede ser nulo.");
        }

        if (buscarAnimal(animal.getNumeroFicha()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un animal con esa ficha.");
        }

        animales.insertarFinal(animal);
    }

    private Animal buscarAnimal(String numeroFicha) {
        Nodo<Animal> actual = animales.getHead();

        while (actual != null) {
            Animal animal = actual.getDato();

            if (animal.getNumeroFicha().equalsIgnoreCase(numeroFicha)) {
                return animal;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    private Animal buscarAnimalOFallar(String numeroFicha) {
        Animal animal = buscarAnimal(numeroFicha);

        if (animal == null) {
            throw new IllegalArgumentException(
                    "No existe un animal con la ficha " + numeroFicha);
        }

        return animal;
    }

    public void registrarLlegada(String numeroFicha) {
        Animal animal = buscarAnimalOFallar(numeroFicha);
        fila.enqueue(animal);
    }

    public Animal atenderSiguiente() {
        if (fila.isEmpty()) {
            throw new IllegalStateException(
                    "No hay animales en espera.");
        }

        return fila.dequeue();
    }

    public Animal consultarSiguiente() {
        if (fila.isEmpty()) {
            throw new IllegalStateException(
                    "No hay animales en espera.");
        }

        return fila.front();
    }

    public boolean sinAnimalesEnEspera() {
        return fila.isEmpty();
    }

    public int contarEnEspera() {
        return fila.size();
    }

    public String listarEnEspera() {
        if (fila.isEmpty()) {
            return "No hay animales en espera.";
        }

        StringBuilder texto = new StringBuilder();
        int total = fila.size();

        for (int i = 1; i <= total; i++) {
            Animal animal = fila.dequeue();

            texto.append(i)
                    .append(". ")
                    .append(animal.getNombre())
                    .append(" (Ficha: ")
                    .append(animal.getNumeroFicha())
                    .append(")")
                    .append(System.lineSeparator());

            fila.enqueue(animal);
        }

        return texto.toString();
    }
}
