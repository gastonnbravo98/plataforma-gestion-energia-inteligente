package ar.edu.unahur.obj2.energia;

public class NotificadorAdministrador implements MonitorPeriferico {
    private String ultimoMensaje;

    @Override
    public void actualizar(Bateria bateria, double variacionKwh) {
        if (variacionKwh > 0) {
            this.ultimoMensaje = "Se han cargado " + variacionKwh + " kWh en la batería " + bateria.getId();
        } else {
            
            this.ultimoMensaje = "Se han consumido " + Math.abs(variacionKwh) + " kWh en la batería " + bateria.getId();
        }
        System.out.println("Notificación Admin " + this.ultimoMensaje);
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }
}