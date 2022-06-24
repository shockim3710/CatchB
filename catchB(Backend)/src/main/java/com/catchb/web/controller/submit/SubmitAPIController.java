package com.catchb.web.controller.submit;

import com.catchb.service.submit.SubmitService;
import com.catchb.web.dto.submit.SubmitResponseDto;
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
@RequestMapping(path = "api/submit")
public class SubmitAPIController {
    private final SubmitService submitService;

    @RequestMapping( method = RequestMethod.POST)
    public Map upload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                      @RequestParam Map<String, String> paramMap){
        String user_id = paramMap.get("user_id");
        String submit_address = paramMap.get("submit_address");
        String submit_check = "0";
        String submit_credit = paramMap.get("submit_credit");
        String hashtag = paramMap.get("hashtag");

        String path = "C:\\Users\\anht0\\userSubmit\\"+submit_address;
        String fileName = "";

        Map<String, Object> map =new HashMap<>();
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
        submitService.save(user_id,submit_address,fileName,submit_check,submit_credit,hashtag);
        return  null;

    }

    @GetMapping(value = "/view", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageSearch(@RequestParam("submit_address") String submit_address,
                              @RequestParam("file_name") String file_name) throws IOException {

        FileInputStream fis = null;
        org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileDir = "C:\\Users\\anht0\\userSubmit\\" + submit_address + "\\" + file_name;
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
    // 사용자가 제출한 모든 항목 조회
    @GetMapping(value = "/all")
    public List<SubmitResponseDto> findAll(){
        return submitService.findsubmit();
    }

    // 처리한 사진에 대하여 DB값 변경(관리자 확인 전 check = 0, 확인 후 check 1로 변경) 같은 항목 예외 처리
    @PostMapping("/process/{user_id}/{hashtag}")
    public void processSubmit(@PathVariable("user_id")String user_id, @PathVariable("hashtag")String hashtag){
        submitService.processSubmit(user_id, hashtag);
    }
}
