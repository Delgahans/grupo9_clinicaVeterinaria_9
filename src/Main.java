
import model.domain.Animal;
import service.SalaDeEsperaService;
import view.MenuColaView;

public class Main {

    public static void main(String[] args) {

        SalaDeEsperaService salaService = new SalaDeEsperaService();

        Animal animal1 = new Animal(
                "F001", "Max", "Perro", "Labrador", 5);

        Animal animal2 = new Animal(
                "F002", "Luna", "Gato", "Criollo", 3);

        Animal animal3 = new Animal(
                "F003", "Rocky", "Perro", "Beagle", 4);

        salaService.registrarAnimal(animal1);
        salaService.registrarAnimal(animal2);
        salaService.registrarAnimal(animal3);

        MenuColaView menu = new MenuColaView(salaService);
        menu.iniciar();
    }
}
