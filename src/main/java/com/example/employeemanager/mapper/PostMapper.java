package com.example.employeemanager.mapper;

import com.example.employeemanager.dto.PostDto;
import com.example.employeemanager.model.Post;
import com.example.employeemanager.util.ImageUtil;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class PostMapper {

    @Value("${employee-manager.upload.image.path}")
    static String imageUploadPath;

    public static Post mapDtoToEntity(PostDto postDto) throws IOException {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setPermalink(postDto.getPermalink());
        post.setExcerpt(postDto.getExcerpt());
        post.setPostContent(postDto.getPostContent());
        post.setCategory(postDto.getCategory());

        String imgPath = ImageUtil.imageUpload(postDto.getImg(), imageUploadPath);
        post.setImgPath(imgPath);
        return post;
    }

}
