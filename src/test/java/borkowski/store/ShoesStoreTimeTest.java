package borkowski.store;

import borkowski.domain.Shoes;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class ShoesStoreTimeTest {

    private ShoesStoreImpl service = new ShoesStoreImpl();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    TimeStampInterface timeStamp;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    private void assertNotNull(long readTime) {
    }

    @Test
    public void testOfReadTimeOnReadMethod(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(100L,35,"Puma","grey");
        service.create(shoes);
        service.setTime(timeStamp.getTimeNow());
        service.read(100L);
        assertEquals(time, service.read(100L).getReadTime());
    }
    @Test
    public void testOfReadTimeOnReadAllMethod(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes1 = new Shoes(101L,39,"Puma","grey");
        Shoes shoes2 = new Shoes(102L,36,"Nike","red");
        Shoes shoes3 = new Shoes(103L,42,"Adidas","white");

        service.create(shoes1);
        service.create(shoes2);
        service.create(shoes3);
        service.setTime(timeStamp.getTimeNow());

        assertEquals(time, service.readAll().get(0).getReadTime());
        assertEquals(time, service.readAll().get(1).getReadTime());
        assertEquals(time, service.readAll().get(2).getReadTime());
    }

    @Test
    public void testOfAddTimeOnCreateMethod() {
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(104L,42,"Adidas","white");
        service.setTime(timeStamp.getTimeNow());
        service.create(shoes);
        assertEquals(time, service.read(104L).getAddTime());
    }

    @Test
    public void testOfUpdateTimeOnUpdateMethod(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(105L,42,"Adidas","white");
        service.create(shoes);
        service.setTime(timeStamp.getTimeNow());
        Shoes expectedShoes = service.read(shoes.getId());
        expectedShoes.setSize(44);
        expectedShoes.setBrand("Adonis");
        expectedShoes.setColor("pink");
        service.update(expectedShoes);
        assertEquals(time, service.read(105L).getUpdateTime());
    }

    @Test
    public void testOfAllTimesInformationForReadMethod(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(106L,44,"Nike","white");
        service.setTime(timeStamp.getTimeNow());
        service.create(shoes);
        Shoes expectedShoes = service.read(shoes.getId());
        expectedShoes.setBrand("Walonki");
        service.update(expectedShoes);
        ArrayList<LocalDate> listOfTimesMethods = new ArrayList<>();
        listOfTimesMethods.add(0, time);
        listOfTimesMethods.add(1, time);
        listOfTimesMethods.add(2, time);
        ArrayList<LocalDate> listOfTimesForShoes = service.getAllTimesForShoes(106L);
        assertEquals(listOfTimesMethods, listOfTimesForShoes);
    }

    @Test
    public void testOfAddTimeDisabled(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(107L,44,"Nike","white");
        service.setTime(timeStamp.getTimeNow());
        service.setAddTimeDisabled();
        service.create(shoes);
        assertEquals(null,service.read(107L).getAddTime());//mozna zamienic na AssertNull
    }

    @Test
    public void testAddTimeDisabledAndEnabled(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes1 = new Shoes(108L,40,"Puma","yellow");
        Shoes shoes2 = new Shoes(109L,44,"Reabok","white");
        service.setTime(timeStamp.getTimeNow());
        service.setAddTimeDisabled();
        service.create(shoes1);
        service.setAddTimeEnabled();
        service.create(shoes2);
        assertEquals(null, service.read(108L).getAddTime());//mozna zamienic na AssertNull
        assertEquals(time, service.read(109L).getAddTime());
    }

    @Test
    public void testOfReadTimeDisabled(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(110L,44,"Puma","yellow");
        service.create(shoes);
        service.setTime(timeStamp.getTimeNow());
        service.setReadTimeDisabled();
        service.read(110L);
        assertEquals(null, service.read(110L).getReadTime());//mozna zamienic na AssertNull
    }

    @Test
    public void testReadTimeDisabledAndEnabled() {
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(111L,44,"Puma","yellow");
        service.create(shoes);
        service.setTime(timeStamp.getTimeNow());
        service.setReadTimeDisabled();
        service.read(111L);
        assertEquals(null, service.read(111L).getReadTime());
        service.setReadTimeEnabled();
        service.read(111L);
        assertEquals(time, service.read(111L).getReadTime());
    }

    @Test
    public void testOfUpdateTimeDisabled(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(112L,44,"Puma","yellow");
        service.create(shoes);
        service.setTime(timeStamp.getTimeNow());
        service.setUpdateTimeDisabled();
        Shoes expectedShoes = service.read(shoes.getId());
        expectedShoes.setBrand("Spalding");
        expectedShoes.setSize(44);
        expectedShoes.setColor("brown");
        service.update(expectedShoes);
        assertEquals(null,service.read(112L).getUpdateTime());
    }

    @Test
    public void testUpdateTimeDisabledAndEnabled(){
        LocalDate time = LocalDate.of(2019, 11, 01);
        when(timeStamp.getTimeNow()).thenReturn(time);
        Shoes shoes = new Shoes(113L,44,"Puma","yellow");
        service.create(shoes);
        service.setTime(timeStamp.getTimeNow());
        service.setUpdateTimeDisabled();
        Shoes expectedShoes = service.read(shoes.getId());
        expectedShoes.setBrand("Adidas");
        expectedShoes.setSize(43);
        expectedShoes.setColor("black");
        service.update(expectedShoes);
        assertEquals(null,service.read(113L).getUpdateTime());
        service.setUpdateTimeEnabled();
        service.update(expectedShoes);
        assertEquals(time, service.read(113L).getUpdateTime());
    }
}
