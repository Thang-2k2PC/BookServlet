package com.bookshop.service.impl;

import com.bookshop.dao.NxbRepository;
import com.bookshop.entity.Nxb;
import com.bookshop.paging.Pageble;
import com.bookshop.service.NxbService;

import javax.inject.Inject;
import java.util.List;

public class NxbServiceImpl implements NxbService {

    @Inject
    private NxbRepository nxbRepository;

    @Override
    public Nxb findNxbById(long nxb_id) {
        return nxbRepository.findNxbById(nxb_id);
    }

    @Override
    public Nxb getNxb_idByNameNxb(String nameNxb) {
        return nxbRepository.findNxbByNameNxb(nameNxb);
    }

    @Override
    public List<Nxb> getAllNxb() {
        return nxbRepository.findAllNxb();
    }

    @Override
    public List<Nxb> getAllByPageble(Pageble pageble) {
        return nxbRepository.findNxbByPageble(pageble);
    }

    @Override
    public int getTotalItem() {
        return nxbRepository.getTotalItem();
    }

    @Override
    public void saveNxbNew(String nameNxb) {
        nxbRepository.save(nameNxb);
    }

    @Override
    public void updateNxb(Nxb nxb) {
        nxbRepository.update(nxb);
    }

    @Override
    public void delete(Long id) {
        nxbRepository.delete(id);
    }


}
