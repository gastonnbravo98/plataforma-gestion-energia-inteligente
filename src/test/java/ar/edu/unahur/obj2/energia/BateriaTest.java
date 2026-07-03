package ar.edu.unahur.obj2.energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BateriaTest {

    @Test
    public void testCargarEnergiaIncrementaElNivel() {
        
        Bateria bateria = new Bateria("Bat-01", 100.0);
        
        
        bateria.cargar(50.0);
        
       
        assertEquals(150.0, bateria.getNivelEnergia(), 0.01);
    }
}
