package com.ohgiraffers.associationmapping.section01.manytoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManyToOneService {

    private ManyToOneRepository manyToOneRepository;

    @Autowired
    public ManyToOneService(ManyToOneRepository manyToOneRepository){
        this.manyToOneRepository = manyToOneRepository;

    }

    public Menu findMenu(int menuCode) {

        return manyToOneRepository.findMenu(menuCode);

    }

    public String findCategoryNameByJOQL(int menuCode) {

        return manyToOneRepository.findCategoryNameByJPQL(menuCode);
    }
}
