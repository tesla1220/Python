package com.ohgiraffers.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagination {

    public static PagingButton getPagingButtonInfo(Page page){
        // Page : 스프링프레임워크에서 제공


        // 설정할 정보들 담아주기 🔽🔽

        /* 현재 페이지에 대한 정보 */
        int currentPage = page.getNumber() + 1;
        /* +1 이유 : 숫자 체계가 0부터 시작해서 사용자에게 1페이지부터 보여주기 위해
        Current Page Calculation:
        page.getNumber() returns the zero-based index of the current page in the pagination.
        Adding 1 converts it to a one-based index (which is more user-friendly).
        page.getNumber()는 페이지네이션에서 현재 페이지의 0부터 시작하는 인덱스를 반환합니다. 여기에 1을 더해 사용자 친화적인 1부터 시작하는 인덱스로 변환합니다.
*/

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

    /* 시작 페이지 계산:  int startPage = (int)(Math.ceil((double) currentPage / defaultButtonCount ) - 1 ) * defaultButtonCount + 1;


        (double) currentPage / defaultButtonCount
        현재 페이지가 몇 번째 블록에 속하는지 계산합니다. 예를 들어, 현재 페이지가 15번째이고 기본 버튼 수가 10이면, 이 계산은 1.5가 됩니다.

        Math.ceil((double) currentPage / defaultButtonCount)
        이 값의 소수점을 올림하여 다음 블록의 시작을 결정합니다. 예를 들어, 1.5는 2로 올림됩니다.

        (int)(Math.ceil((double) currentPage / defaultButtonCount ) - 1 )
        이제 이 값을 1을 빼서 현재 블록의 인덱스를 가져옵니다. 위 예시에서는 2 - 1 = 1이 됩니다.
        이 값을 다시 defaultButtonCount 와 곱하여 현재 블록의 첫 번째 페이지 번호를 구합니다. 위 예시에서는 1 * 10 = 10이 됩니다.

        마지막으로 1을 더하여 실제 시작 페이지를 설정합니다. 위 예시에서는 10 + 1 = 11이 됩니다.
        이 과정을 통해 코드는 현재 페이지가 속한 블록의 첫 페이지 번호를 계산하여 startPage 변수에 할당합니다.
        이렇게 계산된 startPage 는 페이지네이션 컨트롤에서 보여질 첫 번째 페이지 버튼의 번호가 됩니다.*/

    /* 마지막 페이지 계산 : endPage = startPage + defaultButtonCount - 1;

        시작 페이지(startPage)에 페이지 버튼 수(defaultButtonCount)를 더하고 1을 빼서 마지막 페이지를 계산합니다.
        예를 들어, startPage = 11, defaultButtonCount = 10이면 endPage = 11 + 10 - 1 = 20이 됩니다.
*/


}
