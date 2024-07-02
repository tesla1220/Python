package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ManyToOneService {

    private ManyToOneRepository manyToOneRepository;

    public ManyToOneService(ManyToOneRepository manyToOneRepository){
        this.manyToOneRepository = manyToOneRepository;
    }
    /*  ManyToOneService 클래스의 생성자
    이 생성자는 ManyToOneRepository 타입의 인스턴스를 매개변수로 받아서, 클래스의 멤버 변수(manyToOneRepository)로 초기화합니다.
    이를 통해 ManyToOneService 클래스는 ManyToOneRepository 클래스의 기능을 사용할 수 있게 됩니다.
    이 패턴은 종속성 주입(Dependency Injection)의 한 예로, 클래스 간의 결합도를 낮추고 테스트 용이성을 높이는 데 도움을 줍니다. */
    /* ManyToOneService 클래스는 ManyToOneRepository의 기능을 사용하여 데이터베이스와 상호작용하는 비즈니스 로직을 구현할 수 있습니다. */

    public Menu findMenu(int menuCode) {

        return manyToOneRepository.find(menuCode);
    }

    public String findCategoryNameByJpql(int theMenuCode) {

        return manyToOneRepository.findCategoryName(theMenuCode);
    }

    @Transactional
    public void registMenu(MenuDTO menu) {

        Menu newMenu = new Menu(
                menu.getMenuCode(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                new Category(
                        menu.getCategoryDTO().getCategoryCode(),
                        menu.getCategoryDTO().getCategoryName(),
                        menu.getCategoryDTO().getRefCategoryCode()
                ),
                menu.getOrderableStatus()
        );

        manyToOneRepository.save(newMenu);
    }
}


/* 생성자(Constructor):

    생성자는 클래스가 인스턴스화될 때 호출되는 특별한 메서드입니다.
    주어진 생성자는 ManyToOneService 클래스의 인스턴스를 생성할 때 ManyToOneRepository 인스턴스를 전달받아, 이를 manyToOneRepository 멤버 변수에 할당합니다.*/