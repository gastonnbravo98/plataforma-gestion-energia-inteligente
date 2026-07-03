package ar.edu.unahur.obj2.energia;
import java.util.ArrayList;
import java.util.List;

public class Bateria {
    private String id;
    private double nivelEnergia;
    
    private List<MonitorPeriferico> monitores;

    public Bateria(String id, double nivelInicial) {
        this.id = id;
        this.nivelEnergia = nivelInicial;
        this.monitores = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public double getNivelEnergia() {
        return nivelEnergia;
    }

    public void cargar(double cantidad) {
        this.nivelEnergia += cantidad;
        System.out.println("Batería " + id + " cargada. Nivel actual: " + this.nivelEnergia + " kWh");
        notificarMonitores();
    }

    public void consumir(double cantidad) {
        if (cantidad <= this.nivelEnergia) {
            this.nivelEnergia -= cantidad;
            System.out.println("Batería " + id + " consumida. Nivel actual: " + this.nivelEnergia + " kWh");
            notificarMonitores(); 
        } else {
            System.out.println("Energía insuficiente en la batería " + id);
        }
    }

    public void agregarMonitor(MonitorPeriferico monitor) {
        monitores.add(monitor);
    }

    public void quitarMonitor(MonitorPeriferico monitor) {
        monitores.remove(monitor);
    }

    private void notificarMonitores() {
        for (MonitorPeriferico monitor : monitores) {
            monitor.actualizar(this);
        }
    }
}