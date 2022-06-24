package com.example.catchb_manager;

public class CustomDTO {
    private int resId;
    private String user_id;
    private String submit_address;
    private String credit;
    private String imageUrl;
    private String filename;
    private String hashtag;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSubmit_address() {
        return submit_address;
    }

    public void setSubmit_address(String submit_address) {
        this.submit_address = submit_address;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setImageUrl(String imageUrl){ this.imageUrl = imageUrl;}

    public String getImageUrl(){return imageUrl;}

    public void setFilename(String filename){ this.filename = filename;}

    public String getFilename(){return filename;}

    public void setHashtag(String hashtag){this.hashtag = hashtag;}

    public String getHashtag(){return hashtag;}
}