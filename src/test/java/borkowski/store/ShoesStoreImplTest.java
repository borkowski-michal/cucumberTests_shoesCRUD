package borkowski.store;
import borkowski.domain.Shoes;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ShoesStoreImplTest {

    private ShoesStoreImpl service = new ShoesStoreImpl();

    private ArrayList<Shoes> expectedShoes = new ArrayList<>();


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        Shoes shoes1 = new Shoes(1L, 42, "Adidas", "black");
        Shoes shoes2 = new Shoes(2L, 40, "Nike", "black");
        Shoes shoes3 = new Shoes(3L, 45, "Puma", "black");
        Shoes shoes4 = new Shoes(4L, 41, "Adidas", "orange");


        expectedShoes.clear();

        expectedShoes.add(shoes1);
        expectedShoes.add(shoes2);
        expectedShoes.add(shoes3);
        expectedShoes.add(shoes4);


        service.shoes.clear();

        service.create(shoes1);
        service.create(shoes2);
        service.create(shoes3);
        service.create(shoes4);
    }

    @Test
    public void testForCreate() {
        Shoes expectedShoes = new Shoes(5L, 46, "Adidas", "black");
        Shoes actualShoes = service.create(expectedShoes);

        assertEquals(expectedShoes, actualShoes);
    }

    @Test
    public void testForReadAll() {
        List<Shoes> allShoes = service.readAll();

        assertEquals(expectedShoes.get(0), allShoes.get(0));
        assertEquals(expectedShoes.get(1), allShoes.get(1));
        assertEquals(expectedShoes.get(2), allShoes.get(2));
        assertEquals(expectedShoes.get(3), allShoes.get(3));
    }

    @Test
    public void testForReadById() {
        Shoes expected = expectedShoes.get(3);
        Shoes actualShoes = service.read(expected.getId());

        assertEquals(expected, actualShoes);
    }

    @Test
    public void testForReadByIdWhenObjectDoesNotExist() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("In your Database doesn't exist domain in this Id");
        service.read(1000000L);
    }

    @Test
    public void testForUpdate() {
        //pobrac obiekt zupdatowac i wczytac jescze raz
        Shoes sh = service.read(expectedShoes.get(3).getId());
        sh.setBrand("Spalding");
        sh.setColor("brown");
        sh.setSize(32);
        service.update(sh);
        Shoes changed = service.read(expectedShoes.get(3).getId());
        assertEquals(sh, changed);

    }

    @Test
    public void testForDelete() {
        //pobranie, usuniecie i czy wywali wyjatek
        Shoes taken = service.read(expectedShoes.get(3).getId());
        service.delete(taken.getId());
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("In your Database doesn't exist domain in this Id");
        service.read(taken.getId());
    }

    @Test
    public void testForDeleteWhenIdDoesNotExist() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("In your Database doesn't exist domain in this Id");
        service.delete(1000000L);
    }
}
