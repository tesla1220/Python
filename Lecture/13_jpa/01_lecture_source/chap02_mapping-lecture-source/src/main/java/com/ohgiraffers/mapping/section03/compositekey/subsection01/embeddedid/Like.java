package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_like")       //  "like"라고 하면 x됨. 데이터베이스 내의 예약어와 중복되는 경우 작동하지 않으므로 이름 설정할 때 조심할 것.
public class Like {

    /* 우리가 세트로 묶어둔 memberNo, bookNo, 를 ID(pk)로서 사용 */
    @EmbeddedId
    private LikedCompositeKey likedCompositeKey;

    protected Like() {}

    public Like(LikedCompositeKey likedCompositeKey){
        this.likedCompositeKey = likedCompositeKey;
    }



}
