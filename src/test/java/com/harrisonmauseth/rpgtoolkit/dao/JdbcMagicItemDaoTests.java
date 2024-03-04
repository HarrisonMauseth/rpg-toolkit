package com.harrisonmauseth.rpgtoolkit.dao;

import com.harrisonmauseth.rpgtoolkit.model.MagicItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcMagicItemDaoTests extends BaseDaoTests {

    private static final MagicItem ITEM_1 = new MagicItem(
            1, "Item1", "rarity1", "category1", "description1", 0,
            "", false, "", false, "", 0, ""
    );
    private static final MagicItem ITEM_2 = new MagicItem(
            2, "Item2", "rarity2", "category2", "description2", 1,
            "modifies 1", true, "requirements", false,
            "", 0, ""
    );
    private static final MagicItem ITEM_3 = new MagicItem(
            3, "Item3", "rarity3", "category3", "description3", 2,
            "modifies 2", false, "", true,
            "charge3", 3, "charge reset requirement for test item 3"
    );

    private JdbcMagicItemDao dao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcMagicItemDao(jdbcTemplate);
    }

    @Test
    public void getMagicItems_returns_correct_number_of_users() {
        List<MagicItem> items = dao.getMagicItems();
        Assert.assertEquals("getMagicItems() did not return correct number of items", 3, items.size());
        Assert.assertEquals("getMagicItems() did not return in correct order", ITEM_1.getName(), items.get(0).getName());
        Assert.assertEquals("getMagicItems() did not return in correct order", ITEM_2.getName(), items.get(1).getName());
        Assert.assertEquals("getMagicItems() did not return in correct order", ITEM_3.getName(), items.get(2).getName());
    }


    private void assertMagicItemsMatch(MagicItem expected, MagicItem actual) {
        Assert.assertNotNull("Expected a magic item object but came back null", actual);
        Assert.assertEquals("MagicItem IDs do not match", expected.getItemId(), actual.getItemId());
        Assert.assertEquals("MagicItem names do not match", expected.getName(), actual.getName());
        Assert.assertEquals("MagicItem rarity IDs do not match", expected.getRarityId(), actual.getRarityId());
        Assert.assertEquals("MagicItem categories do not match", expected.getCategoryId(), actual.getCategoryId());
        Assert.assertEquals("MagicItem descriptions do not match", expected.getDescription(), actual.getDescription());
        Assert.assertEquals("MagicItem modifiers do not match", expected.getModifier(), actual.getModifier());
        Assert.assertEquals("MagicItem modifier info does not match", expected.getModifierInfo(), actual.getModifierInfo());
        Assert.assertEquals("MagicItem attunements do not match", expected.requiresAttunement(), actual.requiresAttunement());
        Assert.assertEquals("MagicItem attunement requirements do not match", expected.getAttunementRequirements(), actual.getAttunementRequirements());
        Assert.assertEquals("MagicItem charges do not match", expected.hasCharges(), actual.hasCharges());
        Assert.assertEquals("MagicItem charge conditions do not match", expected.getChargeConditionId(), actual.getChargeConditionId());
        Assert.assertEquals("MagicItem number of charges do not match", expected.getNumberOfCharges(), actual.getNumberOfCharges());
    }

}
