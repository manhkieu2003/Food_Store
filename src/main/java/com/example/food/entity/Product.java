package com.example.food.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product_table")
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String pname;
    private double pprice;
    private String pdescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String pImage;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", pprice=" + pprice +
                ", pdescription='" + pdescription + '\'' +
                '}';
    }
}
