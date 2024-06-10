package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BiDirectionService {

    private BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository){
        this.biDirectionRepository = biDirectionRepository;
    }

    public Menu findMenu(int menuCode) {
        return biDirectionRepository.findMenu(menuCode);
    }

    /* @OneToMany 이기 때문에 @Transactional 붙여줌. 기냥 외워 */
    @Transactional
    public Category findCategory(int categoryCode) {

        Category foundCategory = biDirectionRepository.findCategory(categoryCode);

        System.out.println("[category menuList] : " + foundCategory.getMenuList());

        return foundCategory;
    }

    @Transactional
    public void registMenu(Menu menu) {

        biDirectionRepository.saveMenu(menu);

    }

    @Transactional
    public void registCategory(Category category) {

        biDirectionRepository.saveCategory(category);


    }
}
