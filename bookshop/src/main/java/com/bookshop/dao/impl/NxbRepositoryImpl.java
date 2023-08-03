package com.bookshop.dao.impl;

import com.bookshop.dao.NxbRepository;
import com.bookshop.entity.Nxb;
import com.bookshop.mapper.NxbMapper;
import com.bookshop.paging.Pageble;

import java.util.List;

public class NxbRepositoryImpl extends AbstractDAO<Nxb> implements NxbRepository {

    @Override
    public Nxb findNxbById(long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM nxbs");
        sql.append(" WHERE id = ?");
        List<Nxb> nxbs = query(sql.toString(), new NxbMapper(), id);
        return nxbs.isEmpty()? null: nxbs.get(0);
    }

    @Override
    public Nxb findNxbByNameNxb(String nameNxb) {
        StringBuilder sql = new StringBuilder("SELECT * FROM nxbs");
        sql.append(" WHERE nameNxb LIKE '%" + nameNxb + "%'");
        List<Nxb> nxbs = query(sql.toString(), new NxbMapper());
        return nxbs.isEmpty()?null: nxbs.get(0);
    }

    @Override
    public List<Nxb> findAllNxb() {
        StringBuilder sql = new StringBuilder("SELECT * FROM nxbs");
        return  query(sql.toString(),new NxbMapper());
    }

    @Override
    public List<Nxb> findNxbByPageble(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM nxbs ");
        sql.append(" LIMIT " + pageble.getOffset() + "," + pageble.getLimit());
        return query(sql.toString(), new NxbMapper());
    }

    @Override
    public int getTotalItem() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS totalItem FROM nxbs");
        return count(sql.toString());
    }

    @Override
    public void save(String nameNxb) {
        StringBuilder sql = new StringBuilder("INSERT INTO nxbs (nameNxb) ");
        sql.append("VALUES(?)");
        insert(sql.toString(), nameNxb);
    }

    @Override
    public void update(Nxb nxb) {
        StringBuilder sql = new StringBuilder("UPDATE nxbs ");
        sql.append(" SET nameNxb = ?");
        sql.append(" WHERE id = ?");
        update(sql.toString(), nxb.getNameNxb(), nxb.getId());
    }

    @Override
    public void delete(Long id) {
        StringBuilder sql = new StringBuilder("DELETE FROM nxbs ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), id);
    }
}
