package ar.edu.unahur.obj2.energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BateriaTest {

    @Test
    public void testCargarEnergiaIncrementaElNivel() {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        bateria.cargar(50.0);
        assertEquals(150.0, bateria.getNivelEnergia(), 0.01);
    }

    @Test
    public void testConsumirEnergiaReduceElNivel() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        bateria.consumir(40.0);
        assertEquals(60.0, bateria.getNivelEnergia(), 0.01);
    }

    @Test
    public void testConsumirPermiteReservaNegativa() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 50.0);
        bateria.consumir(100.0);
        assertEquals(-50.0, bateria.getNivelEnergia(), 0.01, "El nivel debería quedar en reserva negativa (-50.0)");
    }


    class MonitorEspia implements MonitorPeriferico {
        boolean fueNotificado = false;

        @Override
        public void actualizar(Bateria bateria) {
            this.fueNotificado = true;
        }
    }

    @Test
    public void testNotificaAlMonitorCuandoSeCargaEnergia() {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        MonitorEspia espia = new MonitorEspia();
        bateria.agregarMonitor(espia);

        bateria.cargar(10.0);

        assertTrue(espia.fueNotificado, "El monitor debió ser notificado al cargar la batería");
    }

    @Test
    public void testNotificaAlMonitorCuandoSeConsumeEnergia() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        MonitorEspia espia = new MonitorEspia();
        bateria.agregarMonitor(espia);

        bateria.consumir(10.0);

        assertTrue(espia.fueNotificado, "El monitor debió ser notificado al consumir la batería");
    }
}
