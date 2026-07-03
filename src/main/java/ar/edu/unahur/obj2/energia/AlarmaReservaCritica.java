package ar.edu.unahur.obj2.energia;

public class AlarmaReservaCritica implements MonitorPeriferico {
    private boolean alarmaActivada = false;

    @Override
    public void actualizar(Bateria bateria, double variacionKwh) {
       
        if (bateria.getNivelEnergia() < 0) {
            this.alarmaActivada = true;
            System.err.println("Alarma Batería " + bateria.getId() + " operando en reserva crítica. Nivel actual: " + bateria.getNivelEnergia() + " kWh");
        } else {
            this.alarmaActivada = false;
        }
    }

    // Para el Test
    public boolean isAlarmaActivada() {
        return alarmaActivada;
    }
}
