package com.epam.esm.mapper;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TagRowMapper implements RowMapper<Tag> {

    /**
     * Mapping ResultSet to Tag entity
     *
     * @deprecated No longer necessary.
     */
    @Deprecated
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        return new Tag(id, name);
    }
}
