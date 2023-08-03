package com.bookshop.mapper;

import com.bookshop.entity.Nxb;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NxbMapper implements RowMapper<Nxb> {
    @Override
    public Nxb mapRow(ResultSet resultSet) {
        try{
            Nxb nxb = new Nxb();
            nxb.setId(resultSet.getLong("id"));
            nxb.setNameNxb(resultSet.getString("nameNxb"));
            return nxb;
        }catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        return null;
    }
}
