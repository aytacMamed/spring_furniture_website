package com.changeside.demo_furniture_website.models.dto.response;

import com.changeside.demo_furniture_website.models.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
}
