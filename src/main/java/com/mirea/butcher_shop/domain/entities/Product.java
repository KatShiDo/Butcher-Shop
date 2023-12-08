package com.mirea.butcher_shop.domain.entities;

import com.mirea.butcher_shop.domain.enums.TypeOfProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private double price;

    private TypeOfProduct type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    private LocalDateTime creationDate;

    @PrePersist
    private void init() {
        creationDate = LocalDateTime.now();
    }

    public void addImage(Image image) {
        images.add(image);
    }
}
