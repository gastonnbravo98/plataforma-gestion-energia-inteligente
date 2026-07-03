package ar.edu.unahur.obj2.energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MonitoresTest {

    @Test
    public void testRegistroAuditoriaGuardaHistorial() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        RegistroAuditoria auditoria = new RegistroAuditoria();
        bateria.agregarMonitor(auditoria);

        bateria.cargar(50.0);
        bateria.consumir(20.0);

        assertEquals(2, auditoria.getHistorial().size());
        assertTrue(auditoria.getHistorial().get(0).contains("Variación: 50.0"));
        assertTrue(auditoria.getHistorial().get(1).contains("Variación: -20.0"));
    }

    @Test
    public void testNotificadorAdministradorGeneraMensajeAdecuado() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        NotificadorAdministrador notificador = new NotificadorAdministrador();
        bateria.agregarMonitor(notificador);

        bateria.cargar(2000.0);
        assertEquals("Se han cargado 2000.0 kWh en la batería Bat-01", notificador.getUltimoMensaje());

        bateria.consumir(500.0);
        assertEquals("Se han consumido 500.0 kWh en la batería Bat-01", notificador.getUltimoMensaje());
    }

    @Test
    public void testAlarmaReservaCriticaSeActivaAlBajarDeCero() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 10.0);
        AlarmaReservaCritica alarma = new AlarmaReservaCritica();
        bateria.agregarMonitor(alarma);

        bateria.consumir(5.0); 
        assertFalse(alarma.isAlarmaActivada());

        bateria.consumir(10.0); 
        assertTrue(alarma.isAlarmaActivada());
    }
}