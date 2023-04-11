package org.learning.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 3, message = "Il nome deve essere di almeno 3 caratteri")
    @Size(max = 50, message = "Il nome non deve superare i 50 caratteri")
    @Column(nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Ingredient() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPizzas(), that.getPizzas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPizzas());
    }
}
