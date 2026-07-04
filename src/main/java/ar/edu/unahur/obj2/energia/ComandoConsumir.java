package ar.edu.unahur.obj2.energia;

public class ComandoConsumir implements OperacionTransferencia {
    private final Bateria bateria;
    private final double kwh;

    public ComandoConsumir(Bateria bateria, double kwh) {
        if (kwh <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.bateria = bateria;
        this.kwh = kwh;
    }

    @Override
    public void ejecutar() throws LimiteReservaExcedidoException {
        bateria.consumir(kwh);
    }

    @Override
    public void deshacer() {
        bateria.cargar(kwh);
    }
}