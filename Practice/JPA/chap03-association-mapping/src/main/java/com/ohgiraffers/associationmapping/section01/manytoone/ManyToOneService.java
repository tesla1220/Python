package com.ohgiraffers.associationmapping.section01.manytoone;

import org.springframework.stereotype.Service;

@Service
public class ManyToOneService {

    private ManyToOneRepository manyToOneRepository;

    public ManyToOneService(ManyToOneRepository theManyToOneRepository){
        this.manyToOneRepository = theManyToOneRepository;
    }

    public Menu findMenu(int menuCode) {

        return manyToOneRepository.findMenu(menuCode);
    }
}
