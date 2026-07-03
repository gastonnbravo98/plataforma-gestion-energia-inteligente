package ar.edu.unahur.obj2.energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ControladorOperacionesTest {

    @Test
    public void testComandosEncapsulanYEjecutanCorrectamente() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        OperacionTransferencia carga = new ComandoCargar(bateria, 50.0);
        OperacionTransferencia consumo = new ComandoConsumir(bateria, 30.0);
        
        carga.ejecutar();
        assertEquals(150.0, bateria.getNivelEnergia(), 0.01);
        
        consumo.ejecutar();
        assertEquals(120.0, bateria.getNivelEnergia(), 0.01);
    }

    @Test
    public void testControladorEjecutaOperacionInmediata() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        ControladorOperaciones controlador = new ControladorOperaciones();
        OperacionTransferencia carga = new ComandoCargar(bateria, 20.0);
        
        controlador.ejecutarOperacion(carga);
        
        assertEquals(120.0, bateria.getNivelEnergia(), 0.01);
    }

    @Test
    public void testControladorEjecutaRutinaComoComposite() throws LimiteReservaExcedidoException {
        Bateria bateria = new Bateria("Bat-01", 100.0);
        ControladorOperaciones controlador = new ControladorOperaciones();
        
        Rutina rutina = new Rutina();
        rutina.agregarOperacion(new ComandoCargar(bateria, 50.0));
        rutina.agregarOperacion(new ComandoConsumir(bateria, 20.0));
        
        
        assertEquals(2, rutina.getCantidadOperaciones());

      
        controlador.ejecutarOperacion(rutina);
      
        assertEquals(130.0, bateria.getNivelEnergia(), 0.01);
    }
}