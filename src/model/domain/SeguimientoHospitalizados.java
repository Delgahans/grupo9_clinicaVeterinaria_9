
package model.domain;

import model.structures.ListaDoble;

public class SeguimientoHospitalizados {

    private String clinica;
    private ListaDoble<Evolucion> evoluciones;

    public SeguimientoHospitalizados(String clinica) {

        if (clinica == null || clinica.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la clinica no puede estar vacio");
        }

        this.clinica = clinica;
        this.evoluciones = new ListaDoble<>();
    }

    public String getClinica() {
        return clinica;
    }

    public ListaDoble<Evolucion> getEvoluciones() {
        return evoluciones;
    }

    public void agregarEvolucion(Evolucion evolucion) {

        if (evolucion == null) {
            throw new IllegalArgumentException(
                    "La evolucion no puede ser null");
        }

        evoluciones.insertarFinal(evolucion);
    }

    public int cantidadEvoluciones() {
        return evoluciones.getTamanio();
    }
}

