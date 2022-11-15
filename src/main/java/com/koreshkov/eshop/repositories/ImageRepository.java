package com.koreshkov.eshop.repositories;

import com.koreshkov.eshop.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
