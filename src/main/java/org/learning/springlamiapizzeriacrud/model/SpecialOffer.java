package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
@Entity
@Table(name="special_offers")
public class SpecialOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 3, message = "Il titolo deve essere di almeno 3 caratteri")
    @Size(max = 50, message = "Il titolo non deve superare i 50 caratteri")
    @Column(nullable = false)
    private String title;
    @NotNull
    @Column(nullable = false)
    private LocalDate startingDate;
    @NotNull
    @Column(nullable = false)
    private LocalDate endingDate;
    @PositiveOrZero
    @Column(nullable = false)
    private Double discount;
    @ManyToOne
    @JoinColumn(name="pizza_id", nullable = false)
    private Pizza pizza;


    public SpecialOffer(Integer id, String title, Double discount , LocalDate startingDate, LocalDate endingDate, Pizza pizza) {
        this.id = id;
        this.title = title;
        this.discount = discount;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.pizza = pizza;
    }

    public SpecialOffer(){
        super();
    }

    public void copyFrom(SpecialOffer specialOffer){
        this.title = specialOffer.title;
        this.discount = specialOffer.discount;
        this.startingDate = specialOffer.startingDate;
        this.endingDate = specialOffer.endingDate;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

//    public SpecialOffer sumDiscount(SpecialOffer specialOffer){
//        if(this.discount == null)
//            this.discount = (double) 0;
//        this.discount += specialOffer.discount;
//        return this;
//    }
}
