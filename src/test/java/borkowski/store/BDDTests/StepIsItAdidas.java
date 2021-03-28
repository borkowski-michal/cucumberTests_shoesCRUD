package borkowski.store.BDDTests;

import borkowski.domain.Shoes;
import borkowski.store.ShoesStoreImpl;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StepIsItAdidas {

    ShoesStoreImpl shoes;
    private String chosenBrand;
    private Integer chosenSize;

    private List<Shoes> listOfShoes = new ArrayList<>();

    @Given("Client decided to look at the shoes")
    public void client_decided_to_look_at_the_shoes() {
        shoes = new ShoesStoreImpl();
        ShoesStoreImpl.shoes = new ArrayList<>();
        Collections.addAll(listOfShoes,
                new Shoes(5L, 43, "Adidas", "orange"),
                new Shoes(6L, 41, "Adidas", "black"),
                new Shoes(7L, 43, "Adidas", "blue"),
                new Shoes(8L, 42, "Puma", "blue"),
                new Shoes(9L, 43, "Nike", "pink"));
    }

    @When("Client says Bring me all shoes named {string}")
    public void client_says_Bring_me_all_shoes_named_Adidas(String brand) {
        this.chosenBrand = brand;
    }

    @When("Bring me all shoes with size equal {int}")
    public void bring_me_all_shoes_with_size_equal(Integer size) {
        this.chosenSize = size;
    }

    @Then("Seller should brings him {int} pairs shoes.")
    public void seller_should_brings_him_pairs_shoes(int number) {

        List<Shoes> filterShoes = listOfShoes.stream()
                .filter((Shoes sh) -> sh.getBrand().equals(this.chosenBrand))
                .filter((Shoes sh) -> sh.getSize().equals(this.chosenSize))
                .collect(Collectors.toList());
        Assert.assertEquals(number, filterShoes.size());
    }
}
