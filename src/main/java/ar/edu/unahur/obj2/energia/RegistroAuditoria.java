package ar.edu.unahur.obj2.energia;
import java.util.ArrayList;
import java.util.List;

public class RegistroAuditoria implements MonitorPeriferico {
    private List<String> historial = new ArrayList<>();

    @Override
    public void actualizar(Bateria bateria, double variacionKwh) {
        String log = "Operación exitosa en " + bateria.getId() + ". Variación: " + variacionKwh + " kWh. Nivel actual: " + bateria.getNivelEnergia();
        historial.add(log);
        System.out.println("[Auditoría] " + log);
    }

    public List<String> getHistorial() {
        return historial;
    }
}
