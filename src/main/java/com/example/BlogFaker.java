package com.example;

import java.time.LocalDateTime;
import java.util.Locale;

import com.example.entities.CategoryEntity;
import com.example.entities.PostEntity;
import com.example.entities.PostTagEntity;
import com.example.entities.TagEntity;
import com.example.repositories.CategoriesRepository;
import com.example.repositories.PostTagsRepository;
import com.example.repositories.PostsRepository;
import com.example.repositories.TagsRepository;
import com.github.javafaker.Faker;

public class BlogFaker {

    public static CategoryEntity createCategory(CategoriesRepository categoriesRepository) {
        Faker faker = new Faker();
        CategoryEntity entity = new CategoryEntity();
        entity.setName(faker.harryPotter().book());
        entity.setDescription(faker.harryPotter().location());
        entity.setUrlSlug(faker.harryPotter().spell());

        categoriesRepository.save(entity);

        return entity;
    }

    public static PostEntity createPost(PostsRepository postsRepository, CategoryEntity category) {
        Faker faker = new Faker();
        PostEntity entity = new PostEntity();
        entity.setCategory(category);
        entity.setTitle(faker.harryPotter().character());
        entity.setDescription(faker.harryPotter().quote());
        entity.setPublished(false);
        entity.setMeta(faker.harryPotter().house());
        entity.setShortDescription(faker.harryPotter().character());
        entity.setUrlslug(faker.harryPotter().house());
        entity.setPostedOn(LocalDateTime.now());
        entity.setModified(LocalDateTime.now());

        postsRepository.save(entity);
        return entity;
    }

    public static TagEntity createTag(TagsRepository tagsRepository) {
        Faker faker = new Faker(new Locale("uk"));
        TagEntity entity = new TagEntity();
        entity.setName(faker.artist().name());
        entity.setDescription(faker.beer().name());
        entity.setUrlSlug(faker.beer().name());

        tagsRepository.save(entity);
        return entity;
    }

    public static PostTagEntity connectPostAndTag(PostTagsRepository postTagsRepository, PostEntity post,
            TagEntity tag) {

        PostTagEntity entity = new PostTagEntity();
        entity.setPost(post);
        entity.setTag(tag);

        postTagsRepository.save(entity);

        return entity;
    }
}
