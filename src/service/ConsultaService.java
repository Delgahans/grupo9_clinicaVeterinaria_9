
package service;

import model.domain.Animal;
import model.domain.Consulta;
import model.structures.Pila;

public class ConsultaService {

    public void agregarConsulta(Animal animal, Consulta consulta) {
        animal.agregarConsulta(consulta);
        animal.getOperacionesRecientes().push(consulta);
    }

    public boolean buscarConsulta(Animal animal, Consulta consulta) {
        return animal.buscarConsulta(consulta);
    }

    public boolean eliminarConsulta(Animal animal, Consulta consulta) {
        boolean eliminada = animal.eliminarConsulta(consulta);

        if (eliminada) {
            Pila<Consulta> auxiliar = new Pila<>();
            Pila<Consulta> operaciones = animal.getOperacionesRecientes();
            boolean encontrada = false;

            while (!operaciones.isEmpty()) {
                Consulta actual = operaciones.pop();

                if (!encontrada && actual.equals(consulta)) {
                    encontrada = true;
                } else {
                    auxiliar.push(actual);
                }
            }

            while (!auxiliar.isEmpty()) {
                operaciones.push(auxiliar.pop());
            }
        }

        return eliminada;
    }

    public int cantidadConsultas(Animal animal) {
        return animal.getConsultas().getTamano();
    }

    public Consulta consultarUltimaConsulta(Animal animal) {
        return animal.getOperacionesRecientes().peek();
    }

    public Consulta retirarUltimaConsulta(Animal animal) {
        Consulta ultima = animal.getOperacionesRecientes().pop();

        if (ultima != null) {
            animal.eliminarConsulta(ultima);
        }

        return ultima;
    }

    public int cantidadOperacionesRecientes(Animal animal) {
        return animal.getOperacionesRecientes().size();
    }
}
