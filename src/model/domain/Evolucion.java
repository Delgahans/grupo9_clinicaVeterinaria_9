
package model.domain;

import java.time.LocalDate;

public class Evolucion {

    private String numeroFicha;
    private LocalDate fecha;
    private double temperaturaC;
    private double pesoKg;
    private String observacion;

    public Evolucion(String numeroFicha, LocalDate fecha,
            double temperaturaC, double pesoKg, String observacion) {

        if (numeroFicha == null || numeroFicha.isBlank()) {
            throw new IllegalArgumentException(
                    "El numero de ficha no puede estar vacio");
        }

        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha no puede ser null");
        }

        if (temperaturaC <= 0 || pesoKg <= 0) {
            throw new IllegalArgumentException(
                    "La temperatura y el peso deben ser positivos");
        }

        this.numeroFicha = numeroFicha;
        this.fecha = fecha;
        this.temperaturaC = temperaturaC;
        this.pesoKg = pesoKg;
        this.observacion = observacion;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTemperaturaC() {
        return temperaturaC;
    }

    public void setTemperaturaC(double temperaturaC) {
        this.temperaturaC = temperaturaC;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Ficha: " + numeroFicha
                + " | Fecha: " + fecha
                + " | Temperatura: " + temperaturaC + " °C"
                + " | Peso: " + pesoKg + " kg"
                + " | Observación: " + observacion;
    }
}
