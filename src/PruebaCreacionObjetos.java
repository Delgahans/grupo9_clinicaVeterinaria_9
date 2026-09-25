import java.time.LocalDate;

import model.domain.Animal;
import model.domain.Consulta;
import model.domain.Dueno;
import model.domain.Veterinario;

public class PruebaCreacionObjetos {

    public static void main(String[] args) {

        Dueno dueno = new Dueno("123456789", "Carlos Pérez", "3001234567", "Medellín");

        Animal animal1 = new Animal("F001", "Max", "Perro", "Labrador", 5);
        Animal animal2 = new Animal("F002", "Luna", "Gato", "Criollo", 3);

        dueno.agregarAnimal(animal1);
        dueno.agregarAnimal(animal2);

        Veterinario veterinario = new Veterinario("987654321", "Laura Gómez", "3109876543", "Medicina interna");
        Consulta consulta = new Consulta("Dolor abdominal", "Gastritis", "Medicamento y dieta blanda", LocalDate.now(), animal1, veterinario);

    
        System.out.println("===== PRUEBA DE CREACIÓN DE OBJETOS =====");

        System.out.println("\n--- DUEÑO ---");
        System.out.println(dueno.datosResumen());
        System.out.println(dueno.rolEnClinica());

        System.out.println("\n--- ANIMALES ---");
        System.out.println(animal1.getNumeroFicha() + " - " + animal1.getNombre() + " - " + animal1.getEspecie());
        System.out.println(animal2.getNumeroFicha() + " - " + animal2.getNombre() + " - " + animal2.getEspecie());

        System.out.println("\n--- VETERINARIO ---");
        System.out.println(veterinario.datosResumen());
        System.out.println(veterinario.rolEnClinica());

        System.out.println("\n--- CONSULTA ---");
        System.out.println("Motivo: " + consulta.getMotivo());
        System.out.println("Diagnóstico: " + consulta.getDiagnostico());
        System.out.println("Tratamiento: " + consulta.getTratamiento());
        System.out.println("Fecha: " + consulta.getFecha());
        System.out.println("Animal: " + consulta.getAnimal().getNombre());
        System.out.println("Veterinario: " + consulta.getVeterinario().getNombre());
    }
}