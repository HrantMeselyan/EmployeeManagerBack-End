package com.example.employeemanager.dto;

import com.example.employeemanager.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String permalink;
    private String excerpt;
    private String postContent;
    private Category category;
    private MultipartFile img;
}
