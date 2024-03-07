package com.harrisonmauseth.rpgtoolkit.dao;

import com.harrisonmauseth.rpgtoolkit.exception.DaoException;
import com.harrisonmauseth.rpgtoolkit.model.MagicItem;
import com.harrisonmauseth.rpgtoolkit.model.MagicItemServiceDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PreAuthorize("isAuthenticated()")
public class JdbcMagicItemDao implements MagicItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMagicItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MagicItem createMagicItem(MagicItem magicItem) {
        return null;
    }

    @Override
    public MagicItem createMagicItem(MagicItemServiceDto magicItemServiceDto) {
        return null;
    }

    @Override
    public List<MagicItem> getMagicItems() {
        List<MagicItem> magicItems = new ArrayList<>();
        String sql = "SELECT * FROM rarity;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                magicItems.add(mapRowToMagicItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return magicItems;
    }

    @Override
    public MagicItem getMagicItemById(int id) {
        return null;
    }

    @Override
    public List<MagicItem> getMagicItemsByTitle(String searchTerm) {
        return null;
    }

    @Override
    public MagicItem updateMagicItem(MagicItem magicItem) {
        return null;
    }

    @Override
    public int deleteAuctionById(int id) {
        return 0;
    }

    private MagicItem mapRowToMagicItem (SqlRowSet rowSet) {
        MagicItem item = new MagicItem();
        item.setRarityId(rowSet.getString("rarity_id"));
        return item;
    }

}
