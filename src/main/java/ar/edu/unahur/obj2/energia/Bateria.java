package ar.edu.unahur.obj2.energia;
import java.util.ArrayList;
import java.util.List;

public class Bateria {
   
    private static final double LIMITE_RESERVA_KWH = -5000.0;

    private final String id;
    private double nivelEnergia;
    private final List<MonitorPeriferico> monitores;

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
        
        if ((this.nivelEnergia - cantidad) < LIMITE_RESERVA_KWH) {
            throw new LimiteReservaExcedidoException("Supera el límite de reserva de " + LIMITE_RESERVA_KWH + " kWh");
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