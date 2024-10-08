package com.project.home.service;

import com.project.home.repository.HomePicturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HomePicturesService {

    @Autowired
    private HomePicturesRepository homePicturesRepository;

    @Transactional
    public void deleteByHomeId(Long id)
    {
        homePicturesRepository.deleteByHome(id);
    }
}
