package com.harrisonmauseth.rpgtoolkit.dao;

import com.harrisonmauseth.rpgtoolkit.exception.DaoException;
import com.harrisonmauseth.rpgtoolkit.model.MagicItem;
import com.harrisonmauseth.rpgtoolkit.model.MagicItemServiceDto;
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

    @Test
    public void getMagicItemById_returns_null_with_invalid_id() {
        MagicItem retrievedItem = dao.getMagicItemById(-1);
        Assert.assertNull(retrievedItem);
    }

    @Test
    public void getMagicItemById_returns_correct_item_with_valid_id() {
        MagicItem retrievedItem = dao.getMagicItemById(ITEM_1.getItemId());
        assertMagicItemsMatch(ITEM_1, retrievedItem);

        retrievedItem = dao.getMagicItemById(ITEM_2.getItemId());
        assertMagicItemsMatch(ITEM_2, retrievedItem);

        retrievedItem = dao.getMagicItemById(ITEM_3.getItemId());
        assertMagicItemsMatch(ITEM_3, retrievedItem);
    }

    @Test
    public void getMagicItemsByTitle_returns_empty_with_null_title() {
        List<MagicItem> items = dao.getMagicItemsByTitle(null);
        Assert.assertNotNull(items);
        Assert.assertTrue("getMagicItemsByTitle should have returned an empty string when null is passed", items.isEmpty());
    }

    @Test
    public void getMagicItemsByTitle_returns_partial_matches_and_ignores_case() {
        List<MagicItem> items = dao.getMagicItemsByTitle("it");
        Assert.assertNotNull(items);
        Assert.assertEquals("getMagicItemsByTitle('cat') should have returned 3 results", 3, items.size());
    }

    @Test
    public void getMagicItemsByTitle_returns_exact_matches() {
        List<MagicItem> items = dao.getMagicItemsByTitle(ITEM_2.getName());
        Assert.assertNotNull(items);
        Assert.assertEquals("getMagicItemsByTitle(" + ITEM_2.getName() + ") did not return correct number of results", 1, items.size());
        assertMagicItemsMatch(ITEM_2, items.get(0));
    }

    @Test(expected = DaoException.class)
    public void createMagicItem_with_unfilled_MagicItem_throws_DaoException() {
        dao.createMagicItem(new MagicItem());
    }

    @Test(expected = DaoException.class)
    public void createMagicItem_with_unfilled_MagicItemServiceDto_throws_DaoException() {
        dao.createMagicItem(new MagicItemServiceDto());
    }


    @Test
    public void createMagicItem_creates_magic_item_when_valid_MagicItem_is_passed() {
        MagicItem itemToCreate = new MagicItem(
                0, "new name", "rarity3", "category1", "new description", 2,
                "new modifier info", true, "new attunement requirements",
                true, "charge3", 5, "new charge info"
        );

        MagicItem createdItem = dao.createMagicItem(itemToCreate);
        Assert.assertNotNull("createMagicItem with a valid MagicItem should return non-null MagicItem", createdItem);

        MagicItem retrievedItem = dao.getMagicItemById(createdItem.getItemId());
        Assert.assertNotNull("Unable to retrieve Magic Item from database after creating", retrievedItem);

        assertMagicItemsMatch(createdItem, retrievedItem);
    }

@Test
public void updateMagicItem_updates_magic_item() {
        MagicItem magicItemToUpdate = dao.getMagicItemById(ITEM_1.getItemId());
        magicItemToUpdate.setName("updated name");
        magicItemToUpdate.setRarityId("rarity2");
        magicItemToUpdate.setCategoryId("category2");
        magicItemToUpdate.setDescription("updated description");
        magicItemToUpdate.setModifier(999);
        magicItemToUpdate.setModifierInfo("updated modifier info");
        magicItemToUpdate.setAttunement(true);
        magicItemToUpdate.setAttunementRequirements("updated attunement requirements");
        magicItemToUpdate.setCharges(true);
        magicItemToUpdate.setChargeConditionId("charge2");
        magicItemToUpdate.setNumberOfCharges(999);

        MagicItem updatedItem = dao.updateMagicItem(magicItemToUpdate);
        assertMagicItemsMatch(magicItemToUpdate, updatedItem);

        MagicItem itemThatShouldNotHaveBeenUpdated = dao.getMagicItemById(ITEM_3.getItemId());
        assertMagicItemsMatch(ITEM_3, itemThatShouldNotHaveBeenUpdated);
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
