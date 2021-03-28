package borkowski.domain;
import java.time.LocalDate;
import java.util.Objects;

public class Shoes {
    private Long id;
    private Integer size;
    private String brand;
    private String color;
    private LocalDate addTime;
    private LocalDate updateTime;
    private LocalDate readTime;


    public Shoes() {
    }

    public Shoes(Long id, Integer size, String brand, String color) {
        this.id = id;
        this.size = size;
        this.brand = brand;
        this.color = color;
    }

    public Shoes(Long id, Integer size, String brand, String color, LocalDate addTime, LocalDate updateTime, LocalDate readTime) {
        this.id = id;
        this.size = size;
        this.brand = brand;
        this.color = color;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.readTime = readTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDate addTime) {
        this.addTime = addTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDate getReadTime() {
        return readTime;
    }

    public void setReadTime(LocalDate readTime) {
        this.readTime = readTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shoes)) return false;
        Shoes shoes = (Shoes) o;
        return getId().equals(shoes.getId()) &&
                getSize().equals(shoes.getSize()) &&
                getBrand().equals(shoes.getBrand()) &&
                getColor().equals(shoes.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize(), getBrand(), getColor());
    }
}