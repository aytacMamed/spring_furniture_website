package com.changeside.demo_furniture_website.repository;

import com.changeside.demo_furniture_website.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
