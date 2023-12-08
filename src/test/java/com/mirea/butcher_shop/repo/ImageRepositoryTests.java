package com.mirea.butcher_shop.repo;

import com.mirea.butcher_shop.domain.entities.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ImageRepositoryTests {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void ImageRepository_FindById_ReturnsImage() {

        var image = Image
                .builder()
                .name("image1")
                .originalFileName("originalFileName.img")
                .size(10000L)
                .contentType("contentType1")
                .isPreviewImage(false)
                .content(new byte[100])
                .build();

        imageRepository.save(image);

        var imageFromDb = imageRepository.findById(image.getId()).orElse(null);

        Assertions.assertThat(imageFromDb).isNotNull();
    }
}
