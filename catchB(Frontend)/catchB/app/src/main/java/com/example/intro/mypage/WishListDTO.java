package com.example.intro.mypage;

public class WishListDTO {

    private String loc_credit;
    private String loc_title;
    private String wish_pic;

    public String getLoc_credit() {
        return loc_credit;
    }

    public void setLoc_credit(String loc_credit) {
        this.loc_credit = loc_credit;
    }

    public String getLoc_title() {
        return loc_title;
    }

    public void setLoc_title(String loc_title) { this.loc_title = loc_title; }

    public String getWish_pic() {
        return wish_pic;
    }

    public void setWish_pic(String wish_pic) {
        this.wish_pic = wish_pic;
    }
}