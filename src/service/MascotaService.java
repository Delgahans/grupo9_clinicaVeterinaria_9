
package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import model.domain.Animal;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class MascotaService {

    private static final String ARCHIVO = "data/mascotas.txt";
    private static final String SEPARADOR = ";";

    private final ListaSimple<Animal> mascotas;

    public MascotaService() {
        mascotas = new ListaSimple<>();
        cargarMascotas();
    }

    public void registrarMascota(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException(
                    "La mascota no puede ser nula");
        }

        if (buscarMascota(animal.getNumeroFicha()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una mascota con esa ficha");
        }

        mascotas.insertarFinal(animal);
    }

    public Animal buscarMascota(String numeroFicha) {
        if (numeroFicha == null) {
            return null;
        }

        Nodo<Animal> actual = mascotas.getHead();

        while (actual != null) {
            Animal animal = actual.getDato();

            if (animal.getNumeroFicha().equals(numeroFicha)) {
                return animal;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public boolean existeMascota(String numeroFicha) {
        return buscarMascota(numeroFicha) != null;
    }

    public int contarMascotas() {
        return mascotas.getTamano();
    }

    public String listarMascotas() {
        if (mascotas.estaVacia()) {
            return "No hay mascotas registradas.";
        }

        StringBuilder texto = new StringBuilder();
        Nodo<Animal> actual = mascotas.getHead();

        while (actual != null) {
            texto.append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
        }

        return texto.toString();
    }

    private String obtenerTipo(Animal animal) {
        if (animal.getEspecie() == null) {
            return "";
        }

        if (animal.getEspecie().equalsIgnoreCase("Perro")) {
            return "PERRO";
        }

        if (animal.getEspecie().equalsIgnoreCase("Gato")) {
            return "GATO";
        }

        return "";
    }

    private void escribirMascotas() throws IOException {

        File archivo = new File(ARCHIVO);
        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        StringBuilder contenido = new StringBuilder();

        Nodo<Animal> actual = mascotas.getHead();

        while (actual != null) {
            Animal animal = actual.getDato();

            String tipo = obtenerTipo(animal);

            if (tipo.isEmpty()) {
                throw new IOException(
                        "La especie debe ser Perro o Gato: "
                        + animal.getNumeroFicha());
            }

            String[] campos = {
                tipo,
                animal.getNumeroFicha(),
                animal.getNombre(),
                animal.getEspecie(),
                animal.getFechaNacimiento().toString(),
                animal.getRaza()
            };

            contenido.append(String.join(SEPARADOR, campos))
                    .append("\n");

            actual = actual.getSiguiente();
        }

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(ARCHIVO))) {

            escritor.write(contenido.toString());
        }
    }

    public String guardarMascotas() {
        try {
            escribirMascotas();

            return "Se guardaron " + mascotas.getTamano()
                    + " mascotas en " + ARCHIVO;

        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarMascotas() {

        while (!mascotas.estaVacia()) {
            mascotas.eliminarInicio();
        }

        File archivo = new File(ARCHIVO);

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] campos = linea.split(SEPARADOR, -1);

                if (campos.length != 6) {
                    continue;
                }

                try {
                    String tipo = campos[0].trim();
                    String numeroFicha = campos[1].trim();
                    String nombre = campos[2].trim();
                    String especie = campos[3].trim();
                    LocalDate fechaNacimiento =
                            LocalDate.parse(campos[4].trim());
                    String dato = campos[5].trim();

                    if (!tipo.equals("PERRO")
                            && !tipo.equals("GATO")) {
                        continue;
                    }

                    if (tipo.equals("PERRO")
                            && !especie.equalsIgnoreCase("Perro")) {
                        continue;
                    }

                    if (tipo.equals("GATO")
                            && !especie.equalsIgnoreCase("Gato")) {
                        continue;
                    }

                    if (numeroFicha.isEmpty()
                            || buscarMascota(numeroFicha) != null) {
                        continue;
                    }

                    int edad = Period.between(
                            fechaNacimiento, LocalDate.now()).getYears();

                    if (edad < 0) {
                        continue;
                    }

                    Animal animal = new Animal(
                            numeroFicha,
                            nombre,
                            especie,
                            dato,
                            edad,
                            fechaNacimiento
                    );

                    mascotas.insertarFinal(animal);

                } catch (RuntimeException e) {
                    // Omite la línea incorrecta y continúa.
                }
            }

        } catch (FileNotFoundException e) {
            return "No existe " + ARCHIVO
                    + "; se inicia con la lista vacía.";

        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + mascotas.getTamano()
                + " mascotas.";
    }
}
