package com.raczkowski.springintro.github.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryDto {
    @JsonSetter("full_name")
    private String fullName;

    @JsonProperty("description")
    private String description;

    @JsonSetter("clone_url")
    private String cloneUrl;

    @JsonSetter("stargazers_count")
    private String stars;

    public GithubRepositoryDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getStars() {
        return stars;
    }
}
