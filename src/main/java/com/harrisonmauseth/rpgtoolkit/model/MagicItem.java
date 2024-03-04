package com.harrisonmauseth.rpgtoolkit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing a magic item found in the database.
 */
public class MagicItem {

    @JsonProperty("item_id")
    private int itemId;
    private String name;
    @JsonProperty("rarity")
    private String rarityId;
    @JsonProperty("type")
    private String categoryId;
    private String description;
    private int modifier;
    private String modifierInfo;
    @JsonProperty("requires_attunement")
    private boolean attunement;
    @JsonProperty("attunement_requirements")
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

    public String getModifierInfo() {
        return modifierInfo;
    }

    public void setModifierInfo(String modifier_info) {
        this.modifierInfo = modifier_info;
    }

    public boolean requiresAttunement() {
        return attunement;
    }

    public void setAttunement(boolean attunement) {
        this.attunement = attunement;
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
