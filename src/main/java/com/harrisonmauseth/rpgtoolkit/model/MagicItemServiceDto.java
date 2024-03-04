package com.harrisonmauseth.rpgtoolkit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * MagicItemServiceDto is a Data Transfer Object used to temporarily store the inbound object from
 * the "Open5e" API. This object is primarily used for insertion into the database.
 */
public class MagicItemServiceDto {

    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    @JsonProperty("desc")
    private String description;
    @NotBlank
    private String rarity;
    @NotNull
    private String attunement;

    public MagicItemServiceDto() {
    }

    public MagicItemServiceDto(String name, String type, String description, String rarity, String attunement) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.rarity = rarity;
        this.attunement = attunement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getAttunement() {
        return attunement;
    }

    public void setAttunement(String attunement) {
        this.attunement = attunement;
    }

    @Override
    public String toString() {
        return "MagicItemServiceDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", rarity='" + rarity + '\'' +
                ", attunement='" + attunement + '\'' +
                '}';
    }
}
