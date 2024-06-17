package com.ohgiraffers.springjpa.menu.model.service;

import com.ohgiraffers.springjpa.menu.entity.Menu;
import com.ohgiraffers.springjpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springjpa.menu.model.dto.MenuDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MenuService(MenuRepository theMenuRepository, ModelMapper theModelMapper){
        menuRepository = theMenuRepository;
        modelMapper = theModelMapper;
    }


    /* 컨트롤러에서 @PathVariable을 사용하여 특정 리소스를 식별한 후, 해당 리소스를 처리하는 메서드가 서비스 계층에서 void 반환형을 가지는 이유
    ▶️▶️ 서비스 계층에서는 특정 작업(예: 데이터베이스에서 삭제, 업데이트 등)을 수행하는 것이 목적입니다.
    이러한 작업은 성공적으로 수행되면 반환할 값이 필요하지 않을 수 있습니다.
    예를 들어, 데이터베이스에서 특정 항목을 삭제하는 작업은 성공 여부만 중요하고, 별도로 반환할 데이터가 필요 없을 수 있습니다.
    */

    public MenuDTO findMenuByCode(int menuCode) {

        Menu menuEntity = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(menuEntity, MenuDTO.class);


    }
}
