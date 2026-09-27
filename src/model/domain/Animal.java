package model.domain;

import model.structures.ListaSimple;

public class Animal {
    private String numeroFicha;
    private String nombre;
    private String especie;
    private String raza;
    private int edadAnios;
    private ListaSimple<Vacuna> vacunas;

    public Animal(String numeroFicha, String nombre, String especie, String raza, int edadAnios) {
        if (numeroFicha == null || numeroFicha.isBlank()) {
            throw new IllegalArgumentException("El numero de ficha no puede estar vacio");
        }

        this.numeroFicha = numeroFicha;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edadAnios = edadAnios;
        this.vacunas = new ListaSimple<>();
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdadAnios() {
        return edadAnios;
    }

    public void setEdadAnios(int edadAnios) {
        this.edadAnios = edadAnios;
    }

    public ListaSimple<Vacuna> getVacunas() {
        return vacunas;
    }

    public void agregarVacuna(Vacuna vacuna) {
        vacunas.insertarFinal(vacuna);
    }

    public boolean buscarVacuna(Vacuna vacuna) {
        return vacunas.buscarPorValor(vacuna);
    }

    public boolean eliminarVacuna(Vacuna vacuna) {
        return vacunas.eliminarPorValor(vacuna);
    }
}