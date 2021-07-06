package com.swagger.petstore.test.api.model;

import java.util.List;

public class PetBuilder {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public PetBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PetBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PetBuilder setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public PetBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Pet createPet() {
        return new Pet(id, category, name, photoUrls, tags, status);
    }
}