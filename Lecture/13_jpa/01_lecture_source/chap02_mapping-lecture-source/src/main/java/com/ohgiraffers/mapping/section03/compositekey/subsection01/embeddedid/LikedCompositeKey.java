package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class LikedCompositeKey {


    @Embedded
    private LikedMemberNo likedMemberNo;

    @Embedded
    private LikedBookNo likedBookNo;

    protected LikedCompositeKey() {}


    public LikedCompositeKey(LikedMemberNo likedMemberNo, LikedBookNo likedBookNo) {
        this.likedMemberNo = likedMemberNo;
        this.likedBookNo = likedBookNo;
    }

    public LikedMemberNo getLikedMemberNo() {
        return likedMemberNo;
    }

    public LikedBookNo getLikedBookNo() {
        return likedBookNo;
    }


}
