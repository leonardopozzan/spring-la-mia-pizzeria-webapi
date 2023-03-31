package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min=3, max=50, message="il nome deve avere tra i 3 e i 50 caratteri")
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @Lob
    @Column(nullable = false)
    private String description;
    @PositiveOrZero
    @Column(nullable = false)
    private Double price;
    private String image;
    @PastOrPresent
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    public Pizza(){
        super();
    }

    public Pizza(Pizza pizza){
        this.name = pizza.getName();
        this.price = pizza.getPrice();
        this.description = pizza.getDescription();
        this.image = pizza.getImage();
        this.createdAt = LocalDateTime.now();
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
}
