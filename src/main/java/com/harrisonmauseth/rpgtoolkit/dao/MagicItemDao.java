package com.harrisonmauseth.rpgtoolkit.dao;

import com.harrisonmauseth.rpgtoolkit.model.MagicItem;
import com.harrisonmauseth.rpgtoolkit.model.MagicItemServiceDto;

import java.util.List;

public interface MagicItemDao {

    /**
     * Add a new MagicItem to the datastore.
     *
     * @param magicItem The MagicItem object to create.
     * @return the added MagicItem object with its new id filled in.
     */
    MagicItem createMagicItem(MagicItem magicItem);

    /**
     * Add a new magic item to the database using an item retrieved using
     * the MagicItemService API.
     *
     * @param magicItemServiceDto The item to create.
     * @return the added MagicItem object with its new id filled in.
     */
    MagicItem createMagicItem(MagicItemServiceDto magicItemServiceDto);

    /**
     * Get a list of all the magic items in the database, ordered by name.
     *
     * @return all magic items as a List of MagicItem objects.
     */
    List<MagicItem> getMagicItems();

    /**
     * Get a specific magic item by item_id.
     *
     * @param id the id of the item to be found.
     * @return the filled out MagicItem object retrieved.
     */
    MagicItem getMagicItemById(int id);

    /**
     * Get a list of all magic items with an item_name containing the search term.
     * Prioritize partial matches over exact matches.
     *
     * @param searchTerm the query string for the item_name
     * @return matching magic items as a List of MagicItem objects.
     */
    List<MagicItem> getMagicItemsByTitle(String searchTerm);

    /**
     * Update an existing magic item in the datastore. The inbound MagicItem
     * should contain all fields for the magic item, even if the field is not
     * being updated.
     *
     * @param magicItem
     * @return the updated magic item as a MagicItem object.
     */
    MagicItem updateMagicItem(MagicItem magicItem);

    /**
     * Delete a magic item from the database.
     *
     * @param id the id of the item to be deleted.
     * @return the number of rows deleted.
     */
    int deleteAuctionById(int id);

}
