package com.harrisonmauseth.rpgtoolkit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing a magic item found in the database.
 */
public class MagicItem {

    private int itemId;
    private String name;
    @JsonProperty("rarity")
    private String rarityId;
    @JsonProperty("type")
    private String categoryId;
    @JsonProperty("desc")
    private String description;
    private int modifier;
    private String modifier_info;
    private boolean requiresAttunement;
    @JsonProperty("requires_attunement")
    private String attunementRequirements;
    private boolean hasCharges;
    private String chargeConditionId;
    private int numberOfCharges;



    public MagicItem() {

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarityId() {
        return rarityId;
    }

    public void setRarityId(String rarityId) {
        this.rarityId = rarityId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public String getModifier_info() {
        return modifier_info;
    }

    public void setModifier_info(String modifier_info) {
        this.modifier_info = modifier_info;
    }

    public boolean isRequiresAttunement() {
        return requiresAttunement;
    }

    public void setRequiresAttunement(boolean requiresAttunement) {
        this.requiresAttunement = requiresAttunement;
    }

    public String getAttunementRequirements() {
        return attunementRequirements;
    }

    public void setAttunementRequirements(String attunementRequirements) {
        this.attunementRequirements = attunementRequirements;
    }

    public boolean isHasCharges() {
        return hasCharges;
    }

    public void setHasCharges(boolean hasCharges) {
        this.hasCharges = hasCharges;
    }

    public String getChargeConditionId() {
        return chargeConditionId;
    }

    public void setChargeConditionId(String chargeConditionId) {
        this.chargeConditionId = chargeConditionId;
    }

    public int getNumberOfCharges() {
        return numberOfCharges;
    }

    public void setNumberOfCharges(int numberOfCharges) {
        this.numberOfCharges = numberOfCharges;
    }
}
