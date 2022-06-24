package com.catchb.web.controller.store;

import com.catchb.domain.store.Store;
import com.catchb.service.store.StoreService;
import com.catchb.web.dto.store.StoreCountDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/store")
public class StoreAPIController {
    private final StoreService storeService;

    @RequestMapping(method = RequestMethod.POST)
    public Map upload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                      @RequestParam Map<String, String> paramMap){
        String item_category = paramMap.get("item_category");
        String item_name = paramMap.get("item_name");
        String item_credit = paramMap.get("item_credit");

        String path = "C:\\Users\\anht0\\gifticon\\"+item_category;
        String fileName = "";

        Map<String,Object> map = new HashMap<>();
        try{
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iter = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = null;
            String fieldName = "";
            List<Map<String, Serializable>> list = new ArrayList<Map<String, Serializable>>();

            File file = new File(path);
            if(!file.isDirectory()){
                file.mkdir();
            }
            while (iter.hasNext()){
                fieldName = iter.next();
                multipartFile = multipartHttpServletRequest.getFile(fieldName);
                fileName = new String(multipartFile.getOriginalFilename().getBytes("8859_1"), "UTF-8");

                if("".equals(fileName)){
                    continue;
                }
                File serverfile = new File(path + File.separator + fileName);
                multipartFile.transferTo(serverfile);

                Map file2 =new HashMap<>();
                file2.put("fileName", fileName);
                file2.put("serverfile", serverfile);
                list.add(file2);
            }

            map.put("files", list);
            map.put("serverfile", multipartHttpServletRequest.getParameterMap());
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        storeService.save(item_category, item_name, item_credit,fileName);
        return null;
    }
    @GetMapping(value = "/view", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageSearch(@RequestParam("item_category") String item_category, @RequestParam("fileName")String fileName) throws IOException {

        FileInputStream fis = null;
        org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileDir = "C:\\Users\\anht0\\gifticon\\" + item_category + "\\" + fileName;
        try {
            fis = new FileInputStream(fileDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try {
            while ((readCount = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }
    // 기프티콘 재고 수량 조회
    @GetMapping(value = "/count/{item_name}")
    public StoreCountDto countItem(@PathVariable String item_name){
        return storeService.countDto(item_name);
    }

    // 기프티콘 구매 시 DB 업데이트(해당 상품 Credit만큼 차감)
    @PostMapping(value = "/use/{user_id}/{item_name}")
    public void useItem(@PathVariable String user_id, @PathVariable String item_name){
        storeService.useItem(user_id, item_name);
    }
    // 사용자가 구매한 기프티콘 조회
    @GetMapping(value = "/item_credit/{user_id}")
    public List<Store> findByCredit(@PathVariable String user_id){
        return storeService.findByCredit(user_id);
    }

}