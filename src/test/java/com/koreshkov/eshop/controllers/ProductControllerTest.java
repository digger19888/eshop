package com.koreshkov.eshop.controllers;

import com.koreshkov.eshop.models.Product;
import com.koreshkov.eshop.models.User;
import com.koreshkov.eshop.repositories.ProductRepository;
import com.koreshkov.eshop.services.ProductService;
import com.koreshkov.eshop.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
private final long ID = 1L;
    @Mock
    private Product product;
    @Mock
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private ProductController productController;

    @Test
    void createProduct() throws IOException {
        final Product product = mock(Product.class);
        final Principal principal = mock(Principal.class);
        final MultipartFile file = mock(MultipartFile.class);
        when(productService.saveProducts(principal, product, file, file, file)).thenReturn(product);

        productController.createProduct(file, file, file, product, principal);

        assertNotNull(product);
    }

    @Test
    void deleteProduct() {
        final Product productActual = new Product();
        final User user = new User();
        user.setId(ID);
        productActual.setUser(user);
        when(productService.deleteProduct(user,ID)).thenReturn(productActual);

        final Product actual = productService.deleteProduct(user, ID);

        assertNotNull(actual);
    }
}
