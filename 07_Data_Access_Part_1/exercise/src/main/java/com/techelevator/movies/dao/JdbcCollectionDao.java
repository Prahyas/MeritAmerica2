package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionDao implements CollectionDao {
    private final String COLLECTION_SELECT = "SELECT c.collection_id, c.collection_name from collection c ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public List<Collection> getCollections() {
        String sql = COLLECTION_SELECT;
        List<Collection> collections = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Collection singleCollection = mapRowToCollection(results);
            collections.add(singleCollection);
        }
        return collections;
    }

    @Override
    public Collection getCollectionById(int id) {
        String sql = COLLECTION_SELECT + "WHERE c.collection_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Collection singleCollection = mapRowToCollection(results);
            return singleCollection;
        } else {
            return null;
        }
    }

    @Override
    public List<Collection> getCollectionsByName(String name, boolean useWildCard) {
        String sql = COLLECTION_SELECT + "WHERE LOWER(c.collection_name) LIKE ?";

        if (useWildCard) {
            name = "%" + name.toLowerCase() + "%";
        } else {
            name = name.toLowerCase();
        }

        List<Collection> collections = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

        while (results.next()){
            Collection singleCollection = mapRowToCollection(results);
            collections.add(singleCollection);
        }
        return collections;
    }
    public Collection mapRowToCollection(SqlRowSet results) {
        Collection collection = new Collection();
        collection.setId(results.getInt("collection_id"));
        collection.setName(results.getString("collection_name"));
        return collection;
    }

}
