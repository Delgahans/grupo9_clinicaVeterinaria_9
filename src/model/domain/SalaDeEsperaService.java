
package service;

import model.domain.Animal;
import model.domain.SalaDeEspera;
import model.structures.Cola;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class SalaDeEsperaService {

    private final ListaSimple<Animal> animales = new ListaSimple<>();
    private final SalaDeEspera sala = new SalaDeEspera("Sala principal");

    public void registrarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException(
                    "El animal no puede ser nulo");
        }

        animales.insertarFinal(animal);
    }

    private Animal buscarAnimalOFallar(String numeroFicha) {
        Nodo<Animal> actual = obtenerInicio();

        while (actual != null) {
            Animal animal = actual.getDato();

            if (animal.getNumeroFicha().equals(numeroFicha)) {
                return animal;
            }

            actual = actual.getSiguiente();
        }

        throw new IllegalArgumentException(
                "No existe un animal con la ficha " + numeroFicha);
    }

    private Nodo<Animal> obtenerInicio() {
        // Se obtiene el primer nodo mediante el método de la lista.
        return animales.getHead();
    }

    public void registrarLlegada(String numeroFicha) {
        Animal animal = buscarAnimalOFallar(numeroFicha);
        sala.getFila().enqueue(animal);
    }

    public Animal atenderSiguiente() {
        return sala.getFila().dequeue();
    }

    public Animal consultarSiguiente() {
        return sala.getFila().front();
    }

    public boolean sinAnimalesEnEspera() {
        return sala.getFila().isEmpty();
    }

    public int contarEnEspera() {
        return sala.getFila().size();
    }

    public String listarEnEspera() {
        Cola<Animal> fila = sala.getFila();

        if (fila.isEmpty()) {
            return "No hay animales en espera";
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
                    .append("\n");

            fila.enqueue(animal);
        }

        return texto.toString();
    }
}
