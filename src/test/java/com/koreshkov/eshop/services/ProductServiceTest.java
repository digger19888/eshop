package com.koreshkov.eshop.services;

import com.koreshkov.eshop.models.Image;
import com.koreshkov.eshop.models.Product;
import com.koreshkov.eshop.repositories.ProductRepository;
import com.koreshkov.eshop.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private final static long ID = 1L;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductById_shouldCallRepository() {
        final Product product = mock(Product.class);
        when(productRepository.findById(ID)).thenReturn(Optional.ofNullable(product));

        final Product actual = productService.getProductById(ID);

        assertNotNull(actual);
        assertEquals(product, actual);
        verify(productRepository).findById(ID);
    }

    @Test
    void saveProduct_shouldCallRepository() throws IOException {
        final Product productActual = new Product();
        final List<Image> images = new ArrayList<>();
        final Image image = new Image();
        image.setId(ID);
        images.add(image);
        productActual.setImages(images);
        final Product product = mock(Product.class);
        final Principal principal = mock(Principal.class);
        final MultipartFile file = mock(MultipartFile.class);
        when(productRepository.save(product)).thenReturn(productActual);

        final Product actual = productService.saveProducts(principal, product, file, file, file);

        assertNotNull(actual);
    }
}
