package com.catchb.web.controller.images;


import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ImageUploadPath {
    public static String attach_path ="api/images";


    public static String path(HttpServletRequest request){
        String uploadPath ="";

        try{
            String root_path =request.getServletContext().getRealPath("/");

            uploadPath =root_path+attach_path.replace('/', File.separatorChar);

        }catch(Exception e){
            e.printStackTrace();
        }

        return uploadPath;
    }
}