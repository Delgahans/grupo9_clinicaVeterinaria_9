
package service;

import java.time.LocalDate;

import model.domain.Evolucion;
import model.domain.SeguimientoHospitalizados;
import model.structures.ListaDoble;
import model.structures.NodoDoble;

public class SeguimientoHospitalizadosService {

    private SeguimientoHospitalizados seguimiento;
    private NodoDoble<Evolucion> evolucionActual;

    public SeguimientoHospitalizadosService(
            SeguimientoHospitalizados seguimiento) {

        if (seguimiento == null) {
            throw new IllegalArgumentException(
                    "El seguimiento no puede ser nulo.");
        }

        this.seguimiento = seguimiento;
        this.evolucionActual = null;
    }

    // REGISTRAR UNA EVOLUCION

    public void registrarEvolucion(Evolucion evolucion) {

        if (evolucion == null) {
            throw new IllegalArgumentException(
                    "La evolucion no puede ser nula.");
        }

        seguimiento.agregarEvolucion(evolucion);
        evolucionActual = seguimiento.getEvoluciones()
                .obtenerUltimoNodo();
    }

    // CONSULTAR LA PRIMERA EVOLUCION

    public Evolucion consultarPrimeraEvolucion() {

        return seguimiento.getEvoluciones().obtenerPrimero();
    }

    // CONSULTAR LA ULTIMA EVOLUCION

    public Evolucion consultarUltimaEvolucion() {

        return seguimiento.getEvoluciones().obtenerUltimo();
    }

    // INICIAR RECORRIDO DESDE LA EVOLUCION MAS RECIENTE

    public Evolucion iniciarDesdeLaMasReciente() {

        evolucionActual = seguimiento.getEvoluciones()
                .obtenerUltimoNodo();

        return consultarEvolucionActual();
    }

    // INICIAR RECORRIDO DESDE LA EVOLUCION MAS ANTIGUA

    public Evolucion iniciarDesdeLaMasAntigua() {

        evolucionActual = seguimiento.getEvoluciones()
                .obtenerPrimerNodo();

        return consultarEvolucionActual();
    }

    // CONSULTAR LA EVOLUCION ACTUAL

    public Evolucion consultarEvolucionActual() {

        if (evolucionActual == null) {
            return null;
        }

        return evolucionActual.getDato();
    }

    // AVANZAR A LA SIGUIENTE EVOLUCION

    public Evolucion consultarSiguienteEvolucion() {

        if (evolucionActual == null
                || evolucionActual.getSiguiente() == null) {
            return null;
        }

        evolucionActual = evolucionActual.getSiguiente();

        return consultarEvolucionActual();
    }

    // RETROCEDER A LA EVOLUCION ANTERIOR

    public Evolucion consultarAnteriorEvolucion() {

        if (evolucionActual == null
                || evolucionActual.getAnterior() == null) {
            return null;
        }

        evolucionActual = evolucionActual.getAnterior();

        return consultarEvolucionActual();
    }

    // BUSCAR UNA EVOLUCION POR FICHA Y FECHA

    public Evolucion buscarEvolucion(
            String numeroFicha, LocalDate fecha) {

        NodoDoble<Evolucion> nodo = buscarNodo(numeroFicha, fecha);

        if (nodo == null) {
            return null;
        }

        return nodo.getDato();
    }

    // ANULAR UNA EVOLUCION POR FICHA Y FECHA

    public boolean anularEvolucion(
            String numeroFicha, LocalDate fecha) {

        NodoDoble<Evolucion> nodo = buscarNodo(numeroFicha, fecha);

        if (nodo == null) {
            return false;
        }

        // Si se elimina la evolucion actual, movemos el cursor
        // a la evolucion siguiente o, si no existe, a la anterior.

        if (evolucionActual == nodo) {

            if (nodo.getSiguiente() != null) {
                evolucionActual = nodo.getSiguiente();

            } else {
                evolucionActual = nodo.getAnterior();
            }
        }

        seguimiento.getEvoluciones().eliminar(nodo);

        return true;
    }

    // BUSQUEDA INTERNA DE NODOS

    private NodoDoble<Evolucion> buscarNodo(
            String numeroFicha, LocalDate fecha) {

        if (numeroFicha == null || fecha == null) {
            return null;
        }

        ListaDoble<Evolucion> lista = seguimiento.getEvoluciones();

        NodoDoble<Evolucion> actual = lista.obtenerPrimerNodo();

        while (actual != null) {

            Evolucion evolucion = actual.getDato();

            if (evolucion.getNumeroFicha().equals(numeroFicha)
                    && evolucion.getFecha().equals(fecha)) {

                return actual;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    // CONSULTAR CANTIDAD DE EVOLUCIONES

    public int cantidadEvoluciones() {

        return seguimiento.getEvoluciones().getTamanio();
    }

    // CONSULTAR NOMBRE DE LA CLINICA

    public String getClinica() {

        return seguimiento.getClinica();
    }
}
