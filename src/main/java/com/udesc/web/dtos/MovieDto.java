package com.udesc.web.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovieDto {

    @NotBlank
    @Size(min = 3, max = 128)
    private String title;

    @NotBlank
    @Size(min = 3, max = 32)
    private String genre;

    @NotBlank
    @Size(min = 3, max = 512)
    private String synopsis;

    @NotNull
    @Min(1)
    private Integer duration;

    private List<UUID> actorIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<UUID> getActorIds() {
        return actorIds;
    }

    public void setActorIds(List<UUID> actorIds) {
        this.actorIds = actorIds;
    }

}