package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    // String RESET_COLOR = "\u001B[0m";
    // String GREEN_COLOR = "\u001B[32m";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // @Bean
    // CommandLineRunner runner(CategoriesRepository categoriesRepository,
    // PostsRepository postsRepository,
    // TagsRepository tagsRepository,
    // PostTagsRepository postTagsRepository) {
    // return args -> {
    // CategoryEntity category = BlogFaker.createCategory(categoriesRepository);
    // System.out.println(GREEN_COLOR + category + RESET_COLOR);

    // PostEntity post = BlogFaker.createPost(postsRepository, category);
    // System.out.println(GREEN_COLOR + post + RESET_COLOR);

    // TagEntity tag = BlogFaker.createTag(tagsRepository);
    // System.out.println(GREEN_COLOR + tag + RESET_COLOR);

    // PostTagEntity postTag = BlogFaker.connectPostAndTag(postTagsRepository, post,
    // tag);
    // System.out.println(GREEN_COLOR + postTag + RESET_COLOR);
    // };
    // }
}