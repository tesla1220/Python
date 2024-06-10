package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

public class CartCompositekey {

    private int cartOwner;

    private int addedBook;

    protected CartCompositekey() {}

    public CartCompositekey(int cartOwner, int addedBook ){
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
    }
}
