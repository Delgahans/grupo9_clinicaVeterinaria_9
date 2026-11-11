
package view;

import java.time.LocalDate;

import model.domain.Evolucion;
import service.MascotaService;
import service.SeguimientoHospitalizadosService;
import utils.ConsoleUtils;

public class MenuEvolucionesView {

    private final MascotaService mascotaService;
    private final SeguimientoHospitalizadosService evolucionService;

    public MenuEvolucionesView(
            MascotaService mascotaService,
            SeguimientoHospitalizadosService evolucionService) {

        this.mascotaService = mascotaService;
        this.evolucionService = evolucionService;
    }

    public void iniciar() {

        int opcion;

        do {
            mostrarMenu();

            opcion = ConsoleUtils.leerEntero(
                    "Seleccione una opción: ");

            switch (opcion) {

                case 1 -> registrarEvolucion();

                case 2 -> listarAdelante();

                case 3 -> listarAtras();

                case 4 -> verAnterior();

                case 5 -> verSiguiente();

                case 6 -> retirarActual();

                case 7 -> guardarTodo();

                case 8 -> listarMascotas();

                case 9 -> registrarMascota();

                case 0 -> {
                    guardarTodo();
                    System.out.println("Saliendo del sistema...");
                }

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n================================");
        System.out.println("  SEGUIMIENTO DE EVOLUCIONES");
        System.out.println("================================");

        System.out.println("1. Registrar evolución");
        System.out.println("2. Listar evoluciones (antigua a reciente)");
        System.out.println("3. Listar evoluciones (reciente a antigua)");
        System.out.println("4. Ver evolución anterior");
        System.out.println("5. Ver evolución siguiente");
        System.out.println("6. Anular evolución seleccionada");
        System.out.println("7. Guardar archivos");
        System.out.println("8. Listar mascotas");
        System.out.println("0. Guardar y salir");
    }

    private void registrarMascota() {

    try {
        String ficha = ConsoleUtils.leerTexto(
                "Número de ficha: ");

        String nombre = ConsoleUtils.leerTexto(
                "Nombre: ");

        String especie = ConsoleUtils.leerTexto(
                "Especie (Perro o Gato): ");

        if (!especie.equalsIgnoreCase("Perro")
                && !especie.equalsIgnoreCase("Gato")) {
            System.out.println(
                    "La especie debe ser Perro o Gato.");
            return;
        }

        especie = especie.equalsIgnoreCase("Perro")
                ? "Perro" : "Gato";

        String raza = ConsoleUtils.leerTexto("Raza: ");

        String fechaTexto = ConsoleUtils.leerTexto(
                "Fecha de nacimiento (yyyy-MM-dd): ");

        LocalDate fechaNacimiento =
                LocalDate.parse(fechaTexto);

        int edad = java.time.Period.between(
                fechaNacimiento, LocalDate.now()).getYears();

        if (edad < 0) {
            System.out.println(
                    "La fecha de nacimiento no puede ser futura.");
            return;
        }

        model.domain.Animal animal =
                new model.domain.Animal(
                        ficha,
                        nombre,
                        especie,
                        raza,
                        edad,
                        fechaNacimiento
                );

        mascotaService.registrarMascota(animal);

        System.out.println(
                "Mascota registrada correctamente.");

    } catch (RuntimeException e) {
        System.out.println(
                "No se pudo registrar la mascota: "
                + e.getMessage());
    }
}
    private void registrarEvolucion() {

        String ficha = ConsoleUtils.leerTexto(
                "Número de ficha de la mascota: ");

        if (!mascotaService.existeMascota(ficha)) {
            System.out.println(
                    "No existe una mascota con esa ficha.");
            return;
        }

        try {
            String fechaTexto = ConsoleUtils.leerTexto(
                    "Fecha (yyyy-MM-dd): ");

            LocalDate fecha = LocalDate.parse(fechaTexto);

            double temperatura = leerDouble(
                    "Temperatura en °C: ");

            double peso = leerDouble(
                    "Peso en kg: ");

            String observacion = ConsoleUtils.leerTexto(
                    "Observación: ");

            Evolucion evolucion = new Evolucion(
                    ficha,
                    fecha,
                    temperatura,
                    peso,
                    observacion
            );

            evolucionService.registrarEvolucion(evolucion);

            System.out.println(
                    "Evolución registrada correctamente.");

        } catch (RuntimeException e) {
            System.out.println(
                    "No se pudo registrar: " + e.getMessage());
        }
    }

    private double leerDouble(String mensaje) {

        while (true) {
            try {
                String valor = ConsoleUtils.leerTexto(mensaje);
                return Double.parseDouble(valor);

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private void listarAdelante() {
        System.out.println(
                evolucionService.listarEvolucionesEnOrden());
    }

    private void listarAtras() {
        System.out.println(
                evolucionService.listarEvolucionesEnOrdenInverso());
    }

    private void verAnterior() {

        if (!evolucionService.hayEvolucionAnterior()) {
            System.out.println(
                    "No existe una evolución anterior.");
            return;
        }

        System.out.println(
                evolucionService.verEvolucionAnterior());
    }

    private void verSiguiente() {

        if (!evolucionService.hayEvolucionSiguiente()) {
            System.out.println(
                    "No existe una evolución siguiente.");
            return;
        }

        System.out.println(
                evolucionService.verEvolucionSiguiente());
    }

    private void retirarActual() {

        if (evolucionService.verEvolucionActual() == null) {
            System.out.println(
                    "Primero debe seleccionar una evolución.");
            return;
        }

        Evolucion retirada =
                evolucionService.retirarEvolucionActual();

        System.out.println("Evolución anulada:");
        System.out.println(retirada);
    }

    private void listarMascotas() {
        System.out.println(mascotaService.listarMascotas());
    }

    private void guardarTodo() {

        System.out.println(
                mascotaService.guardarMascotas());

        System.out.println(
                evolucionService.guardarEvoluciones());
    }
}
