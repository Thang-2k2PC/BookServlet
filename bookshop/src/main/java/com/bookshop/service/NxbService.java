package com.bookshop.service;

import com.bookshop.entity.Nxb;
import com.bookshop.paging.Pageble;

import java.util.List;

public interface NxbService {
    Nxb findNxbById(long id);

    Nxb getNxb_idByNameNxb(String nameNxb);

//    Nxb getTotalItemProductByNxb();
    List<Nxb> getAllNxb();

    List<Nxb> getAllByPageble(Pageble pageble);

    int getTotalItem();

    void saveNxbNew(String nameNxb);

    void updateNxb(Nxb nxb);

    void delete(Long id);
}
