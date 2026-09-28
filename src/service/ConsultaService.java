package service;

import model.domain.Animal;
import model.domain.Consulta;

public class ConsultaService {

    public void agregarConsulta(Animal animal, Consulta consulta) {
        animal.agregarConsulta(consulta);
    }

    public boolean buscarConsulta(Animal animal, Consulta consulta) {
        return animal.buscarConsulta(consulta);
    }

    public boolean eliminarConsulta(Animal animal, Consulta consulta) {
        return animal.eliminarConsulta(consulta);
    }

    public int cantidadConsultas(Animal animal) {
        return animal.getConsultas().getTamano();
    }
}
