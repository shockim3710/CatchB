package com.example.intro.mypage;

public class CreditHistoryDTO {

    private int resId;
    private String user_id;
    private String content;
    private String credit;
    private String credit_info;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCredit_info() {
        return credit_info;
    }

    public void setCredit_info(String credit_info) {
        this.credit_info = credit_info;
    }
}
