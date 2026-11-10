
package service;

import model.domain.Evolucion;
import model.domain.SeguimientoHospitalizados;
import model.structures.ListaDoble;
import model.structures.NodoDoble;

public class SeguimientoHospitalizadosService {

    private SeguimientoHospitalizados seguimiento;

    public SeguimientoHospitalizadosService(
            SeguimientoHospitalizados seguimiento) {

        if (seguimiento == null) {
            throw new IllegalArgumentException(
                    "El seguimiento no puede ser null");
        }

        this.seguimiento = seguimiento;
    }

    public void registrarEvolucion(Evolucion evolucion) {
        seguimiento.agregarEvolucion(evolucion);
    }

    public Evolucion consultarPrimeraEvolucion() {
        return seguimiento.getEvoluciones().obtenerPrimero();
    }

    public Evolucion consultarUltimaEvolucion() {
        return seguimiento.getEvoluciones().obtenerUltimo();
    }

    public Evolucion buscarEvolucion(Evolucion evolucion) {
        ListaDoble<Evolucion> lista = seguimiento.getEvoluciones();

        NodoDoble<Evolucion> nodo = lista.buscar(evolucion);

        if (nodo == null) {
            return null;
        }

        return nodo.getDato();
    }

    public boolean anularEvolucion(Evolucion evolucion) {
        ListaDoble<Evolucion> lista = seguimiento.getEvoluciones();

        NodoDoble<Evolucion> nodo = lista.buscar(evolucion);

        if (nodo == null) {
            return false;
        }

        lista.eliminar(nodo);
        return true;
    }

    public int cantidadEvoluciones() {
        return seguimiento.cantidadEvoluciones();
    }

    public String getClinica() {
        return seguimiento.getClinica();
    }
}
