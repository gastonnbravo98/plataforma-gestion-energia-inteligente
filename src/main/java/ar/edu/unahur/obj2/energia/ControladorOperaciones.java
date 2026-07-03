package ar.edu.unahur.obj2.energia;
import java.util.ArrayList;
import java.util.List;

public class ControladorOperaciones {
    private List<OperacionTransferencia> rutina;

    public ControladorOperaciones() {
        this.rutina = new ArrayList<>();
    }

   
    public void ejecutarInmediata(OperacionTransferencia operacion) {
        operacion.ejecutar();
    }

   
    public void registrarEnRutina(OperacionTransferencia operacion) {
        rutina.add(operacion);
    }

    
    public void ejecutarRutina() {
        for (OperacionTransferencia operacion : rutina) {
            operacion.ejecutar();
        }
        rutina.clear(); 
    }

    
    public int getCantidadPendientes() {
        return rutina.size();
    }
}