```java
package view;

import model.domain.Animal;
import model.domain.Consulta;
import model.domain.Vacuna;
import service.ConsultaService;
import service.VacunaService;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuListasView {

    private final Scanner scanner;
    private final VacunaService vacunaService;
    private final ConsultaService consultaService;
    private final Animal animal;

    public MenuListasView() {
        scanner = new Scanner(System.in);
        vacunaService = new VacunaService();
        consultaService = new ConsultaService();

        animal = new Animal(
                "A001",
                "Max",
                "Perro",
                "Labrador",
                3
        );
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuVacunas();
                    break;

                case 2:
                    menuConsultas();
                    break;

                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 3);
    }

    private void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("======================================");
        System.out.println("       CLINICA VETERINARIA");
        System.out.println("======================================");
        System.out.println("Animal: " + animal.getNombre());
        System.out.println("Ficha: " + animal.getNumeroFicha());
        System.out.println("--------------------------------------");
        System.out.println("1. Gestionar vacunas");
        System.out.println("2. Gestionar consultas");
        System.out.println("3. Salir");
        System.out.println("======================================");
    }

    private void menuVacunas() {
        int opcion;

        do {
            System.out.println();
            System.out.println("======================================");
            System.out.println("          GESTION DE VACUNAS");
            System.out.println("======================================");
            System.out.println("1. Agregar vacuna");
            System.out.println("2. Buscar vacuna");
            System.out.println("3. Listar vacunas");
            System.out.println("4. Eliminar vacuna");
            System.out.println("5. Volver");
            System.out.println("======================================");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    agregarVacuna();
                    break;

                case 2:
                    buscarVacuna();
                    break;

                case 3:
                    listarVacunas();
                    break;

                case 4:
                    eliminarVacuna();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);
    }

    private void menuConsultas() {
        int opcion;

        do {
            System.out.println();
            System.out.println("======================================");
            System.out.println("         GESTION DE CONSULTAS");
            System.out.println("======================================");
            System.out.println("1. Agregar consulta");
            System.out.println("2. Buscar consulta");
            System.out.println("3. Listar consultas");
            System.out.println("4. Eliminar consulta");
            System.out.println("5. Volver");
            System.out.println("======================================");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    agregarConsulta();
                    break;

                case 2:
                    buscarConsulta();
                    break;

                case 3:
                    listarConsultas();
                    break;

                case 4:
                    eliminarConsulta();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);
    }

    private void agregarVacuna() {
        System.out.println();
        System.out.println("----- AGREGAR VACUNA -----");

        String nombre = leerTexto("Nombre de la vacuna: ");
        LocalDate fechaAplicacion = leerFecha("Fecha de aplicacion (AAAA-MM-DD): ");
        LocalDate proximaFecha = leerFecha("Proxima fecha (AAAA-MM-DD): ");

        Vacuna vacuna = new Vacuna(
                nombre,
                fechaAplicacion,
                proximaFecha,
                animal
        );

        vacunaService.agregarVacuna(animal, vacuna);

        System.out.println("Vacuna agregada correctamente.");
        System.out.println("Total de vacunas: "
                + vacunaService.cantidadVacunas(animal));
    }

    private void buscarVacuna() {
        System.out.println();
        System.out.println("----- BUSCAR VACUNA -----");

        String nombre = leerTexto("Nombre de la vacuna: ");
        LocalDate fechaAplicacion = leerFecha("Fecha de aplicacion (AAAA-MM-DD): ");
        LocalDate proximaFecha = leerFecha("Proxima fecha (AAAA-MM-DD): ");

        Vacuna vacuna = new Vacuna(
                nombre,
                fechaAplicacion,
                proximaFecha,
                animal
        );

        boolean encontrada = vacunaService.buscarVacuna(animal, vacuna);

        if (encontrada) {
            System.out.println("La vacuna fue encontrada.");
        } else {
            System.out.println("La vacuna no fue encontrada.");
        }
    }

    private void listarVacunas() {
        System.out.println();
        System.out.println("----- LISTA DE VACUNAS -----");

        int cantidad = vacunaService.cantidadVacunas(animal);

        if (cantidad == 0) {
            System.out.println("El animal no tiene vacunas registradas.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            Vacuna vacuna = animal.getVacunas().buscarPorIndice(i);

            System.out.println();
            System.out.println("Vacuna #" + (i + 1));
            System.out.println("Nombre: " + vacuna.getNombre());
            System.out.println("Fecha de aplicacion: " + vacuna.getFechaAplicacion());
            System.out.println("Proxima fecha: " + vacuna.getProximaFecha());
        }
    }

    private void eliminarVacuna() {
        System.out.println();
        System.out.println("----- ELIMINAR VACUNA -----");

        String nombre = leerTexto("Nombre de la vacuna: ");
        LocalDate fechaAplicacion = leerFecha("Fecha de aplicacion (AAAA-MM-DD): ");
        LocalDate proximaFecha = leerFecha("Proxima fecha (AAAA-MM-DD): ");

        Vacuna vacuna = new Vacuna(
                nombre,
                fechaAplicacion,
                proximaFecha,
                animal
        );

        boolean eliminada = vacunaService.eliminarVacuna(animal, vacuna);

        if (eliminada) {
            System.out.println("Vacuna eliminada correctamente.");
        } else {
            System.out.println("No se encontro la vacuna.");
        }
    }

    private void agregarConsulta() {
        System.out.println();
        System.out.println("----- AGREGAR CONSULTA -----");

        String motivo = leerTexto("Motivo: ");
        String diagnostico = leerTexto("Diagnostico: ");
        String tratamiento = leerTexto("Tratamiento: ");
        LocalDate fecha = leerFecha("Fecha (AAAA-MM-DD): ");

        Consulta consulta = new Consulta(
                motivo,
                diagnostico,
                tratamiento,
                fecha,
                animal,
                null
        );

        consultaService.agregarConsulta(animal, consulta);

        System.out.println("Consulta agregada correctamente.");
        System.out.println("Total de consultas: "
                + consultaService.cantidadConsultas(animal));
    }

    private void buscarConsulta() {
        System.out.println();
        System.out.println("----- BUSCAR CONSULTA -----");

        String motivo = leerTexto("Motivo: ");
        String diagnostico = leerTexto("Diagnostico: ");
        String tratamiento = leerTexto("Tratamiento: ");
        LocalDate fecha = leerFecha("Fecha (AAAA-MM-DD): ");

        Consulta consulta = new Consulta(
                motivo,
                diagnostico,
                tratamiento,
                fecha,
                animal,
                null
        );

        boolean encontrada = consultaService.buscarConsulta(animal, consulta);

        if (encontrada) {
            System.out.println("La consulta fue encontrada.");
        } else {
            System.out.println("La consulta no fue encontrada.");
        }
    }

    private void listarConsultas() {
        System.out.println();
        System.out.println("----- LISTA DE CONSULTAS -----");

        int cantidad = consultaService.cantidadConsultas(animal);

        if (cantidad == 0) {
            System.out.println("El animal no tiene consultas registradas.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            Consulta consulta = animal.getConsultas().buscarPorIndice(i);

            System.out.println();
            System.out.println("Consulta #" + (i + 1));
            System.out.println("Motivo: " + consulta.getMotivo());
            System.out.println("Diagnostico: " + consulta.getDiagnostico());
            System.out.println("Tratamiento: " + consulta.getTratamiento());
            System.out.println("Fecha: " + consulta.getFecha());
        }
    }

    private void eliminarConsulta() {
        System.out.println();
        System.out.println("----- ELIMINAR CONSULTA -----");

        String motivo = leerTexto("Motivo: ");
        String diagnostico = leerTexto("Diagnostico: ");
        String tratamiento = leerTexto("Tratamiento: ");
        LocalDate fecha = leerFecha("Fecha (AAAA-MM-DD): ");

        Consulta consulta = new Consulta(
                motivo,
                diagnostico,
                tratamiento,
                fecha,
                animal,
                null
        );

        boolean eliminada = consultaService.eliminarConsulta(animal, consulta);

        if (eliminada) {
            System.out.println("Consulta eliminada correctamente.");
        } else {
            System.out.println("No se encontro la consulta.");
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Formato invalido. Use AAAA-MM-DD.");
            }
        }
    }

    public static void main(String[] args) {
        MenuListasView menu = new MenuListasView();
        menu.iniciar();
    }
}
