import model.domain.SeguimientoHospitalizados;
import service.MascotaService;
import service.SeguimientoHospitalizadosService;
import view.MenuEvolucionesView;

public class Main {

    public static void main(String[] args) {

        // Primero se cargan las mascotas.
        MascotaService mascotaService =
                new MascotaService();

        // Después se crea el seguimiento y se cargan
        // las evoluciones relacionadas con esas mascotas.
        SeguimientoHospitalizados seguimiento =
                new SeguimientoHospitalizados(
                        "Clínica Veterinaria");

        SeguimientoHospitalizadosService evolucionService =
                new SeguimientoHospitalizadosService(
                        seguimiento,
                        mascotaService);

        // Se crea el menú con las mismas instancias
        // de los Services durante toda la ejecución.
        MenuEvolucionesView menu =
                new MenuEvolucionesView(
                        mascotaService,
                        evolucionService);

        menu.iniciar();
    }
}