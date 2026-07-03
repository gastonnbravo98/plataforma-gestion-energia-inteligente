package ar.edu.unahur.obj2.energia;

public class ControladorOperaciones {
    public void ejecutarOperacion(OperacionTransferencia operacion) throws LimiteReservaExcedidoException {
        operacion.ejecutar();
    }
    
}