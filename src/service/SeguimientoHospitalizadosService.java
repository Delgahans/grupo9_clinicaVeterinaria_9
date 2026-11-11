
package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import model.domain.Evolucion;
import model.domain.SeguimientoHospitalizados;
import model.structures.ListaDoble;
import model.structures.NodoDoble;

public class SeguimientoHospitalizadosService {

    private static final String ARCHIVO =
            "data/evoluciones.txt";

    private static final String SEPARADOR = ";";

    private SeguimientoHospitalizados seguimiento;
    private NodoDoble<Evolucion> evolucionActual;
    private MascotaService mascotaService;

    // Constructor que conserva la funcionalidad anterior.
    public SeguimientoHospitalizadosService(
            SeguimientoHospitalizados seguimiento) {

        if (seguimiento == null) {
            throw new IllegalArgumentException(
                    "El seguimiento no puede ser nulo.");
        }

        this.seguimiento = seguimiento;
        this.evolucionActual = null;
        this.mascotaService = null;
    }

    // Constructor para trabajar con persistencia.
    // Primero deben haberse cargado las mascotas.
    public SeguimientoHospitalizadosService(
            SeguimientoHospitalizados seguimiento,
            MascotaService mascotaService) {

        if (seguimiento == null || mascotaService == null) {
            throw new IllegalArgumentException(
                    "El seguimiento y el servicio de mascotas "
                    + "son obligatorios.");
        }

        this.seguimiento = seguimiento;
        this.mascotaService = mascotaService;
        this.evolucionActual = null;

        cargarEvoluciones();
    }

    // REGISTRAR EVOLUCION

    public void registrarEvolucion(Evolucion evolucion) {

        if (evolucion == null) {
            throw new IllegalArgumentException(
                    "La evolucion no puede ser nula.");
        }

        if (mascotaService != null
                && !mascotaService.existeMascota(
                        evolucion.getNumeroFicha())) {

            throw new IllegalArgumentException(
                    "No existe una mascota con la ficha "
                    + evolucion.getNumeroFicha());
        }

        seguimiento.agregarEvolucion(evolucion);

        evolucionActual = seguimiento.getEvoluciones()
                .obtenerUltimoNodo();
    }

    // CONSULTAR PRIMERA EVOLUCION

    public Evolucion consultarPrimeraEvolucion() {
        return seguimiento.getEvoluciones().obtenerPrimero();
    }

    // CONSULTAR ULTIMA EVOLUCION

    public Evolucion consultarUltimaEvolucion() {
        return seguimiento.getEvoluciones().obtenerUltimo();
    }

    // INICIAR RECORRIDO DESDE LA MAS RECIENTE

    public Evolucion iniciarDesdeLaMasReciente() {

        evolucionActual = seguimiento.getEvoluciones()
                .obtenerUltimoNodo();

        return consultarEvolucionActual();
    }

    // INICIAR RECORRIDO DESDE LA MAS ANTIGUA

    public Evolucion iniciarDesdeLaMasAntigua() {

        evolucionActual = seguimiento.getEvoluciones()
                .obtenerPrimerNodo();

        return consultarEvolucionActual();
    }

    // CONSULTAR EVOLUCION ACTUAL

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

    // BUSCAR EVOLUCION POR FICHA Y FECHA

    public Evolucion buscarEvolucion(
            String numeroFicha, LocalDate fecha) {

        NodoDoble<Evolucion> nodo =
                buscarNodo(numeroFicha, fecha);

        if (nodo == null) {
            return null;
        }

        return nodo.getDato();
    }

    // ANULAR EVOLUCION POR FICHA Y FECHA

    public boolean anularEvolucion(
            String numeroFicha, LocalDate fecha) {

        NodoDoble<Evolucion> nodo =
                buscarNodo(numeroFicha, fecha);

        if (nodo == null) {
            return false;
        }

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

        ListaDoble<Evolucion> lista =
                seguimiento.getEvoluciones();

        NodoDoble<Evolucion> actual =
                lista.obtenerPrimerNodo();

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
        return seguimiento.cantidadEvoluciones();
    }

    // CONSULTAR CLINICA

    public String getClinica() {
        return seguimiento.getClinica();
    }

    // VERIFICAR SI HAY EVOLUCIONES

    public boolean hayEvoluciones() {
        return !seguimiento.getEvoluciones().isEmpty();
    }

    // VERIFICAR SI EXISTE UNA EVOLUCION ANTERIOR

    public boolean hayEvolucionAnterior() {
        return evolucionActual != null
                && evolucionActual.getAnterior() != null;
    }

    // VERIFICAR SI EXISTE UNA EVOLUCION SIGUIENTE

    public boolean hayEvolucionSiguiente() {
        return evolucionActual != null
                && evolucionActual.getSiguiente() != null;
    }

    // RETIRAR LA EVOLUCION ACTUAL

    public Evolucion retirarEvolucionActual() {

        if (evolucionActual == null) {
            return null;
        }

        NodoDoble<Evolucion> nodo = evolucionActual;

        if (nodo.getSiguiente() != null) {
            evolucionActual = nodo.getSiguiente();
        } else {
            evolucionActual = nodo.getAnterior();
        }

        return seguimiento.getEvoluciones().eliminar(nodo);
    }

    // LISTAR EVOLUCIONES DE LA MAS ANTIGUA A LA MAS RECIENTE

    public String listarEvolucionesEnOrden() {

        StringBuilder texto = new StringBuilder();

        NodoDoble<Evolucion> actual =
                seguimiento.getEvoluciones().obtenerPrimerNodo();

        if (actual == null) {
            return "No hay evoluciones registradas.";
        }

        while (actual != null) {
            texto.append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
        }

        return texto.toString();
    }

    // LISTAR EVOLUCIONES DE LA MAS RECIENTE A LA MAS ANTIGUA

    public String listarEvolucionesEnOrdenInverso() {

        StringBuilder texto = new StringBuilder();

        NodoDoble<Evolucion> actual =
                seguimiento.getEvoluciones().obtenerUltimoNodo();

        if (actual == null) {
            return "No hay evoluciones registradas.";
        }

        while (actual != null) {
            texto.append(actual.getDato()).append("\n");
            actual = actual.getAnterior();
        }

        return texto.toString();
    }

    // GUARDAR EVOLUCIONES EN EL ARCHIVO

    public String guardarEvoluciones() {

        File archivo = new File(ARCHIVO);
        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(archivo))) {

            NodoDoble<Evolucion> actual =
                    seguimiento.getEvoluciones().obtenerPrimerNodo();

            while (actual != null) {

                Evolucion evolucion = actual.getDato();

                String observacion =
                        evolucion.getObservacion() == null
                        ? ""
                        : evolucion.getObservacion()
                                .replace(";", ",")
                                .replace("\n", " ")
                                .replace("\r", " ");

                String linea =
                        evolucion.getNumeroFicha() + SEPARADOR
                        + evolucion.getFecha() + SEPARADOR
                        + evolucion.getTemperaturaC() + SEPARADOR
                        + evolucion.getPesoKg() + SEPARADOR
                        + observacion;

                escritor.write(linea);
                escritor.newLine();

                actual = actual.getSiguiente();
            }

            return "Se guardaron "
                    + cantidadEvoluciones()
                    + " evoluciones correctamente.";

        } catch (IOException e) {
            return "Error al guardar evoluciones: "
                    + e.getMessage();
        }
    }

    // CARGAR EVOLUCIONES DESDE EL ARCHIVO

    public String cargarEvoluciones() {

        // Limpiar las evoluciones anteriores.
        while (!seguimiento.getEvoluciones().isEmpty()) {

            NodoDoble<Evolucion> primero =
                    seguimiento.getEvoluciones().obtenerPrimerNodo();

            seguimiento.getEvoluciones().eliminar(primero);
        }

        evolucionActual = null;

        File archivo = new File(ARCHIVO);

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] campos = linea.split(SEPARADOR, -1);

                // La línea debe tener exactamente cinco campos.
                if (campos.length != 5) {
                    continue;
                }

                try {
                    String numeroFicha = campos[0].trim();

                    // Comprobar que la mascota exista.
                    if (mascotaService != null
                            && !mascotaService.existeMascota(
                                    numeroFicha)) {
                        continue;
                    }

                    LocalDate fecha =
                            LocalDate.parse(campos[1].trim());

                    double temperatura =
                            Double.parseDouble(campos[2].trim());

                    double peso =
                            Double.parseDouble(campos[3].trim());

                    String observacion = campos[4].trim();

                    Evolucion evolucion = new Evolucion(
                            numeroFicha,
                            fecha,
                            temperatura,
                            peso,
                            observacion
                    );

                    seguimiento.agregarEvolucion(evolucion);

                } catch (RuntimeException e) {
                    // Se omite la línea incorrecta.
                }
            }

        } catch (FileNotFoundException e) {

            return "No existe el archivo de evoluciones. "
                    + "Se iniciará con una lista vacía.";

        } catch (IOException e) {

            return "Error al leer evoluciones: "
                    + e.getMessage();
        }

        // Dejar seleccionada la última evolución cargada.
        evolucionActual =
                seguimiento.getEvoluciones().obtenerUltimoNodo();

        return "Se cargaron "
                + cantidadEvoluciones()
                + " evoluciones correctamente.";
    }
}
