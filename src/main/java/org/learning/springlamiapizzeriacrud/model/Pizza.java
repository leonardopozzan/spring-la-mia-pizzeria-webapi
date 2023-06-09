package org.learning.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 3, message = "Il nome deve essere di almeno 3 caratteri")
    @Size(max = 50, message = "Il nome non deve superare i 50 caratteri")
    @Column(nullable = false, unique = true)
    private String name;
    @Lob
    private String description;
    @PositiveOrZero
    @Column(nullable = false)
    private Double price;
    private String image;
    @PastOrPresent
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> specialOffers;


    @NotEmpty(message = "Devi selezionare almenon un ingrediente")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ingredient_pizza",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")

    )
    private List<Ingredient> ingredients;

    public Pizza(){
        super();
    }

    public Pizza(Pizza pizza){
        this.name = pizza.name;
        this.price = pizza.price;
        this.description = pizza.description;
        this.image = pizza.image;
        this.createdAt = LocalDateTime.now();
    }

    public void copyFrom(Pizza pizza){
        this.name = pizza.name;
        this.price = pizza.price;
        this.description = pizza.description;
        this.image = pizza.image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<SpecialOffer> getActiveSpecialOffer(){
        if (specialOffers == null)
            return new ArrayList<>();
        return specialOffers.stream()
                .filter(element -> !element.getStartingDate().isAfter(LocalDate.now()) && !element.getEndingDate().isBefore(LocalDate.now()))
                .toList();
    }
    public Double getTotalDiscount(){
        List<SpecialOffer> activeSpecialOffers = this.getActiveSpecialOffer();
        if(activeSpecialOffers.size() == 0)
            return (double) 0;
        return activeSpecialOffers.stream()
                .map(SpecialOffer::getDiscount)
                .reduce((double) 0, Double::sum);
//        return activeSpecialOffers.stream()
//                .reduce(new SpecialOffer(), SpecialOffer::sumDiscount).getDiscount();
    }

    public Double getDiscountedPrice(){
        Double totalDiscount = this.getTotalDiscount();
        if(totalDiscount == 0){
            return null;
        }
        return (double) Math.round((this.price * (1 - totalDiscount / 100)) * 100) / 100;
    }
}
