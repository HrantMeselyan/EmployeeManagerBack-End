package com.example.employeemanager.service;

import com.example.employeemanager.model.Category;
import com.example.employeemanager.model.Post;
import com.example.employeemanager.repository.CategoryRepository;
import com.example.employeemanager.repository.PostRepository;
import com.example.employeemanager.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    @Value("${employee-manager.upload.image.path}")
    private String imageUploadPath;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }


    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(id);

        if (existingPost.isPresent()) {
            Post postToUpdate = existingPost.get();
            postToUpdate.setTitle(updatedPost.getTitle());
            postToUpdate.setPermalink(updatedPost.getPermalink());
            postToUpdate.setExcerpt(updatedPost.getExcerpt());
            postToUpdate.setPostContent(updatedPost.getPostContent());
            postToUpdate.setCategory(updatedPost.getCategory());
            postToUpdate.setImgPath(updatedPost.getImgPath());

            return postRepository.save(postToUpdate);
        } else {
            return null;
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void write(MultipartFile img) throws IOException {
        ImageUtil.imageUpload(img, imageUploadPath);
    }

    public Post save(MultipartFile img, String title, String description, String id, String excerpt) throws IOException {
        Post post = new Post();
        post.setImgPath(ImageUtil.imageUpload(img, imageUploadPath));
        post.setTitle(title);
        post.setPermalink(description);
        Optional<Category> byId = categoryRepository.findById(Long.parseLong(id));
        post.setCategory(byId.orElse(null));
        post.setExcerpt(excerpt);
        return postRepository.save(post);
    }


    public byte[] getImage(String imageName) throws IOException {
        if (imageName != null && !imageName.isEmpty()) {
            File file = new File(imageUploadPath + imageName);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                return IOUtils.toByteArray(fis);
            }
        }
        return null;
    }
}

