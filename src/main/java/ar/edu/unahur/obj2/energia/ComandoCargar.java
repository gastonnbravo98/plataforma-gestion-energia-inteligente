package ar.edu.unahur.obj2.energia;

public class ComandoCargar implements OperacionTransferencia {
    
    private final Bateria bateria;
    private final double kwh;

    public ComandoCargar(Bateria bateria, double kwh) {
        if (kwh <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.bateria = bateria;
        this.kwh = kwh;
    }

    @Override
    public void ejecutar() {
        bateria.cargar(kwh);
    }

    @Override
    public void deshacer() {
        try {
            bateria.consumir(kwh);
        } catch (LimiteReservaExcedidoException e) {
           
        }
    }
}