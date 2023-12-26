package com.mirea.butcher_shop.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String originalFileName;

    private Long size;

    private String contentType;

    private boolean isPreviewImage;

    @Lob
    private byte[] content;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;
}
