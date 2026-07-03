package ar.edu.unahur.obj2.energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TransaccionesYErroresTest {

    @Test
    public void testComandoConValorCeroONegativoLanzaErrorNoChequeado() {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            new ComandoCargar(bateria, 0.0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new ComandoConsumir(bateria, -10.0);
        });
    }

    @Test
    public void testConsumirPorDebajoDelLimiteLanzaExcepcionChequeada() {
        Bateria bateria = new Bateria("Bat-01", 0.0);
        
        assertThrows(LimiteReservaExcedidoException.class, () -> {
            bateria.consumir(5001.0);
        });
    }

    @Test
    public void testConsumirHastaElLimitePermitidoNoLanzaError() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 0.0);
        bateria.consumir(5000.0); 
        assertEquals(-5000.0, bateria.getNivelEnergia(), 0.01);
    }

    @Test
    public void testRutinaFallaYHaceRollbackAutomatico() {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        ControladorOperaciones controlador = new ControladorOperaciones();
        
       
        Rutina rutina = new Rutina();
        rutina.agregarOperacion(new ComandoCargar(bateria, 1000.0)); 
        rutina.agregarOperacion(new ComandoConsumir(bateria, 7000.0)); 
        
        assertThrows(LimiteReservaExcedidoException.class, () -> {
            controlador.ejecutarOperacion(rutina); 
        });
        
     
        assertEquals(100.0, bateria.getNivelEnergia(), 0.01);
    }
}