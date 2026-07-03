package ar.edu.unahur.obj2.energia;

public interface OperacionTransferencia {
    void ejecutar() throws LimiteReservaExcedidoException;
    void deshacer();
}