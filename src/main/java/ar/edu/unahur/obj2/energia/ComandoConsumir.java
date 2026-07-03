package ar.edu.unahur.obj2.energia;

public class ComandoConsumir implements OperacionTransferencia {
    private Bateria bateria;
    private double kwh;

    public ComandoConsumir(Bateria bateria, double kwh) {
        this.bateria = bateria;
        this.kwh = kwh;
    }

    @Override
    public void ejecutar() {
        bateria.consumir(kwh);
    }
}