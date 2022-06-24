package com.example.intro.touchpicture;

public class Listview {
    private int iconDrawable ;
    private String nicknameStr ;
    private String contentsStr ;

    public void setIcon(int icon) { this.iconDrawable =  icon ; }
    public void setNickname(String nickname) {
        this.nicknameStr = nickname ;
    }
    public void setContents(String contents) { this.contentsStr = contents ; }

    public int getIcon() { return iconDrawable ; }
    public String getNickname() {
        return nicknameStr ;
    }
    public String getContents() {
        return contentsStr ;
    }
}