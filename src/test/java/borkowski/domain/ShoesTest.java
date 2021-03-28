package borkowski.domain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ShoesTest {

    @Test
    public void classImplementationTest(){
        assertNotNull(new Shoes());
    }

    @Test
    public void isObjectAbleToInstantiate(){
        assertNotNull(new Shoes(1L,43,"Nike","niebieski"));
    }

    @Test
    public void isPropertiesAreProperlyAssigned() {
        Shoes shoes = new Shoes(1L,43,"Nike","niebieski");
        assertEquals(Long.valueOf(1), shoes.getId());
        assertEquals(Integer.valueOf(43), shoes.getSize());
        assertEquals("Nike", shoes.getBrand());
        assertEquals("niebieski", shoes.getColor());
    }

}
