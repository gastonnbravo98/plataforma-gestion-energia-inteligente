package ar.edu.unahur.obj2.energia;

public class ComandoCargar implements OperacionTransferencia {
    private Bateria bateria;
    private double kwh;

    public ComandoCargar(Bateria bateria, double kwh) {
        this.bateria = bateria;
        this.kwh = kwh;
    }

    @Override
    public void ejecutar() {
        bateria.cargar(kwh);
    }
}