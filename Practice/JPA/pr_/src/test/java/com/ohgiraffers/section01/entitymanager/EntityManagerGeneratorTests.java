package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityManagerGeneratorTests {

    @Test
    @DisplayName("앤티티 매니저 팩토리 생성 확인하기")
    void testCreateFactory() {
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

    }

}
