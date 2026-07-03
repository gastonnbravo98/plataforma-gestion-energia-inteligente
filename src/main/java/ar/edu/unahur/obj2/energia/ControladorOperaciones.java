package ar.edu.unahur.obj2.energia;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ControladorOperaciones {
    private List<OperacionTransferencia> rutina;

    public ControladorOperaciones() {
        this.rutina = new ArrayList<>();
    }

    public void ejecutarInmediata(OperacionTransferencia operacion) throws LimiteReservaExcedidoException {
        operacion.ejecutar();
    }

    public void registrarEnRutina(OperacionTransferencia operacion) {
        rutina.add(operacion);
    }


    public void ejecutarRutina() throws LimiteReservaExcedidoException {
        Stack<OperacionTransferencia> ejecutadasConExito = new Stack<>();

        try {
            for (OperacionTransferencia operacion : rutina) {
                operacion.ejecutar();
                ejecutadasConExito.push(operacion); 
            }
            rutina.clear();
            
        } catch (LimiteReservaExcedidoException e) {
            
            System.out.println("Falla en la rutina.");
            while (!ejecutadasConExito.isEmpty()) {
                OperacionTransferencia op = ejecutadasConExito.pop();
                op.deshacer();
            }
            rutina.clear(); 
            throw e; 
        }
    }

    public int getCantidadPendientes() {
        return rutina.size();
    }
}