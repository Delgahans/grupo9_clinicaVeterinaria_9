
package view;

import model.domain.Animal;
import service.SalaDeEsperaService;
import utils.ConsoleUtils;

public class MenuColaView {

    private final SalaDeEsperaService salaService;

    public MenuColaView(SalaDeEsperaService salaService) {
        this.salaService = salaService;
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1 -> registrarLlegada();
                case 2 -> atender();
                case 3 -> consultarSiguiente();
                case 4 -> verSiHayEnEspera();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println(
                        "Saliendo del menú de colas...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n===== SALA DE ESPERA =====");
        System.out.println("1. Registrar llegada (enqueue)");
        System.out.println("2. Atender siguiente (dequeue)");
        System.out.println("3. Consultar siguiente (front)");
        System.out.println("4. ¿Hay animales en espera? (isEmpty)");
        System.out.println("5. Cantidad en espera (size)");
        System.out.println("6. Listar animales en espera");
        System.out.println("0. Salir");
    }

    private void registrarLlegada() {
        String ficha = ConsoleUtils.leerTexto(
                "Número de ficha del animal: ");

        salaService.registrarLlegada(ficha);
        System.out.println("Llegada registrada correctamente.");
    }

    private void atender() {
        if (salaService.sinAnimalesEnEspera()) {
            System.out.println("No hay animales en espera");
            return;
        }

        Animal atendido = salaService.atenderSiguiente();

        System.out.println("Se atiende a: " + atendido.getNombre()
                + " (Ficha: " + atendido.getNumeroFicha() + ")");
    }

    private void consultarSiguiente() {
        if (salaService.sinAnimalesEnEspera()) {
            System.out.println("No hay animales en espera");
            return;
        }

        Animal siguiente = salaService.consultarSiguiente();

        System.out.println("Siguiente animal: " + siguiente.getNombre()
                + " (Ficha: " + siguiente.getNumeroFicha() + ")");
    }

    private void verSiHayEnEspera() {
        if (salaService.sinAnimalesEnEspera()) {
            System.out.println("No hay animales en espera");
        } else {
            System.out.println("Hay animales en espera");
        }
    }

    private void contar() {
        System.out.println("Animales en espera: "
                + salaService.contarEnEspera());
    }

    private void listar() {
        System.out.println(salaService.listarEnEspera());
    }
}
