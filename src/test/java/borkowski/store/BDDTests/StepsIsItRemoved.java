package borkowski.store.BDDTests;

import borkowski.domain.Shoes;
import borkowski.store.ShoesStoreImpl;
import cucumber.api.java.en.*;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;

public class StepsIsItRemoved {

    ShoesStoreImpl shoes;
    private int counter;
    private String chosenBrand;
    private Integer chosenSize;

    @Given("Customer chooses a shoes from list and buy")
    public void customer_chooses_a_shoes_from_list_and_buy() {
        shoes = new ShoesStoreImpl();
        ShoesStoreImpl.shoes = new ArrayList<>();
        Collections.addAll(ShoesStoreImpl.shoes,
                new Shoes(10L, 41, "Puma", "orange"),
                new Shoes(11L, 42, "Puma", "black"),
                new Shoes(12L, 43, "Puma", "blue"),
                new Shoes(13L, 44, "Puma", "blue"),
                new Shoes(14L, 45, "Nike", "pink"));
        counter = ShoesStoreImpl.shoes.size();
    }

    @When("Customer choose model \"([^\"]*)\"$")
    public void customer_choose_model(String brand) {
        chosenBrand = brand;
    }

    @When("Customer choose size equal {int}")
    public void customer_choose_size_equal(Integer size) {
        chosenSize = size;
    }

    @Then("shoes has been bought and removed from list of shoes")
    public void shoes_has_been_bought_and_removed_from_list_of_shoes() {
        Shoes choosedSchoes = shoes.readAll().stream()
                .filter(shoes -> shoes.getBrand().equals(chosenBrand)
                        && shoes.getSize().equals(chosenSize)).findFirst().get();
        assertEquals(choosedSchoes, shoes.read(choosedSchoes.getId()));
        shoes.delete(choosedSchoes);
        assertEquals(4, ShoesStoreImpl.shoes.size());
    }

}
