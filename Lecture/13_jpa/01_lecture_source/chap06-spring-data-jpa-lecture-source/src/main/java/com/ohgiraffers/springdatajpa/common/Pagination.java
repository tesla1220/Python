package com.ohgiraffers.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagination {

    public static PagingButton getPagingButtonInfo(Page page){
        // Page : 스프링프레임워크에서 제공


        // 설정할 정보들 담아주기 🔽🔽

        /* 현재 페이지에 대한 정보 */
        int currentPage = page.getNumber() + 1;     // +1 이유 : 숫자 체계가 0부터 시작해서 사용자에게 1페이지부터 보여주기 위해

        /* 페이지 버튼의 기본 개수 */
        int defaultButtonCount = 10;        // 페이지 버튼은  단위 10으로 설정

        /* 현재 시작 페이지 계산 */
        int startPage = (int)(Math.ceil((double) currentPage / defaultButtonCount ) - 1 ) * defaultButtonCount + 1;

        /* 끝 페이지 계산 */
        int endPage = startPage + defaultButtonCount -1;

        /* 실제 총 페이지가 endPage 보다 작으면 endPage 를 총 페이지로 */
        if(page.getTotalPages() < endPage) {
            endPage = page.getTotalPages();
        }

        /* 페이지가 0이면 끝 페이지를 시작 페이지로 설정하겠다. */
        if(page.getTotalPages() == 0 && endPage == 0){
            endPage = startPage;
        }

        /* 계산한 정보들을 반환 */
        return new PagingButton(currentPage, startPage, endPage);

    }


    /*  page.getNumber()는 어떤 객체 page의 메서드로 보입니다. 이 객체는 페이징 정보를 다루는 객체일 가능성이 큽니다.
    getNumber() 메서드는 현재 페이지의 번호를 가져오는 메서드입니다. 일반적으로 페이징 라이브러리에서는 0부터 시작하는 페이지 번호를 반환합니다.
            따라서, page.getNumber()는 현재 페이지의 번호에서 1을 빼고 있습니다. */


}
