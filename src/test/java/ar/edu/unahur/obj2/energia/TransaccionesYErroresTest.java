package ar.edu.unahur.obj2.energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TransaccionesYErroresTest {

    // --- 1. Tests de Valores Inválidos (RuntimeException) ---
    @Test
    public void testComandoConValorCeroONegativoLanzaErrorNoChequeado() {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        
        // Verifica que lance la excepción al intentar instanciar con valor <= 0
        assertThrows(IllegalArgumentException.class, () -> {
            new ComandoCargar(bateria, 0.0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new ComandoConsumir(bateria, -10.0);
        });
    }

    // --- 2. Tests de Límite de Reserva (Exception Chequeada) ---
    @Test
    public void testConsumirPorDebajoDelLimiteLanzaExcepcionChequeada() {
        Bateria bateria = new Bateria("Bat-01", 0.0);
        
        // El límite es -5000. Intentamos consumir 5001.
        assertThrows(LimiteReservaExcedidoException.class, () -> {
            bateria.consumir(5001.0);
        });
    }

    @Test
    public void testConsumirHastaElLimitePermitidoNoLanzaError() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 0.0);
        bateria.consumir(5000.0); // Debería quedar en -5000
        assertEquals(-5000.0, bateria.getNivelEnergia(), 0.01);
    }

    // --- 3. Test de Transaccionalidad (Rollback) ---
    @Test
    public void testRutinaFallaYHaceRollbackAutomatico() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0); // Estado Inicial: 100
        ControladorOperaciones controlador = new ControladorOperaciones();
        
        // Encolamos operaciones:
        // 1. Carga 1000 (Pasaría a 1100) -> Éxito
        // 2. Consume 7000 (Intentaría bajar a -5900, supera límite de -5000) -> Falla
        controlador.registrarEnRutina(new ComandoCargar(bateria, 1000.0));
        controlador.registrarEnRutina(new ComandoConsumir(bateria, 7000.0));
        
        // Ejecutamos la rutina. Como sabemos que va a fallar, atajamos la excepción.
        assertThrows(LimiteReservaExcedidoException.class, () -> {
            controlador.ejecutarRutina();
        });
        
        // ASSERT CLAVE: La batería debe haber vuelto a su estado original (100)
        assertEquals(100.0, bateria.getNivelEnergia(), 0.01);
    }
}
