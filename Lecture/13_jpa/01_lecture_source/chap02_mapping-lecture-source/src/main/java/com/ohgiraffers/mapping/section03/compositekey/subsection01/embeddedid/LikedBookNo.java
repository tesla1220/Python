package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable     // 다른 클래스에서 Embbed 할 수 있도록 설정
public class LikedBookNo {

    @Column(name = "liked_book_no")
    private int likedBookNo;

    protected LikedBookNo(int likedBookNo){
        this.likedBookNo = likedBookNo;
    }
}
