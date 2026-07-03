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
        notificarMonitores(cantidad); 
    }

    public void consumir(double cantidad) throws LimiteReservaExcedidoException {
        double limiteReserva = -5000.0;
        if ((this.nivelEnergia - cantidad) < limiteReserva) {
            throw new LimiteReservaExcedidoException("Supera el límite de reserva de -5000 kWh");
        }
        this.nivelEnergia -= cantidad;
        notificarMonitores(-cantidad); 
    }

    public void agregarMonitor(MonitorPeriferico monitor) {
        monitores.add(monitor);
    }

    public void quitarMonitor(MonitorPeriferico monitor) {
        monitores.remove(monitor);
    }

    private void notificarMonitores(double variacion) {
        for (MonitorPeriferico monitor : monitores) {
            monitor.actualizar(this, variacion);
        }
    }
}