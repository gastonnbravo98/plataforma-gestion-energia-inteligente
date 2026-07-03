package ar.edu.unahur.obj2.energia;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Rutina implements OperacionTransferencia {
    private List<OperacionTransferencia> operaciones;

    public Rutina() {
        this.operaciones = new ArrayList<>();
    }

    public void agregarOperacion(OperacionTransferencia operacion) {
        this.operaciones.add(operacion);
    }

    @Override
    public void ejecutar() throws LimiteReservaExcedidoException {
        Stack<OperacionTransferencia> ejecutadasConExito = new Stack<>();

        try {
            for (OperacionTransferencia operacion : operaciones) {
                operacion.ejecutar();
                ejecutadasConExito.push(operacion);
            }
        } catch (LimiteReservaExcedidoException e) {
            System.out.println("Falla en la Rutina.");
            while (!ejecutadasConExito.isEmpty()) {
                OperacionTransferencia op = ejecutadasConExito.pop();
                op.deshacer(); 
            }
            throw e; 
        }
    }

    @Override
    public void deshacer() {
        
        for (int i = operaciones.size() - 1; i >= 0; i--) {
            operaciones.get(i).deshacer();
        }
    } 
  
    public int getCantidadOperaciones() {
        return operaciones.size();
    }
}
