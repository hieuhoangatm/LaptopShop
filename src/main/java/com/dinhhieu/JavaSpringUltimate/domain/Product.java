package com.dinhhieu.JavaSpringUltimate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private long quantity;
    private String factory;
    private String target;
    private String avatar;
    private long sold;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;
    private String shortDesc;

}
