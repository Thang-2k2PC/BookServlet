package com.bookshop.dao;

import com.bookshop.entity.Nxb;
import com.bookshop.paging.Pageble;

import java.util.List;

public interface NxbRepository extends GenericDAO<Nxb>{
    Nxb findNxbById(long id);

    Nxb findNxbByNameNxb(String nameNxb);

    List<Nxb> findAllNxb();


    List<Nxb> findNxbByPageble(Pageble pageble);

    int getTotalItem();

    void save(String nameNxb);

    void update(Nxb nxb);

    void delete(Long id);
}
