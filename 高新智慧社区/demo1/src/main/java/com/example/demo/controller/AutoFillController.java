package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

@RestController
@RequestMapping("/api")
public class AutoFillController {
    private static final String TEMP_DIR = "C:\\Users\\肖\\Pictures\\java实训";
    private static final String APP_ID = "35526591";
    private static final String API_KEY = "r0UnU3M8Z8wxR2sYn78Riia1";
    private static final String SECRET_KEY = "tsnw2phSWjRezpvGAdNT1GDHIcMENDzr";

    @PostMapping("/recognize")
    public String recognizeImages(MultipartHttpServletRequest request) throws IOException {
        // 获取身份证正面图片
        MultipartFile idCardFrontImage = request.getFile("idCardFrontImage");
        String idCardFrontImagePath = null;
        if (idCardFrontImage != null) {
            idCardFrontImagePath = idCardFrontImage.getOriginalFilename(); // 使用前端传递的绝对路径
        } else {
            return "照片空白";
        }

        // 获取身份证反面图片
        MultipartFile idCardBackImage = request.getFile("idCardBackImage");
        String idCardBackImagePath = null;
        if (idCardBackImage != null) {
            idCardBackImagePath = idCardBackImage.getOriginalFilename(); // 使用前端传递的绝对路径
        } else {
            return "照片空白";
        }

        // 调用百度云接口进行图像识别
        JSONObject idCardFrontResult = recognizeIdCardFront(idCardFrontImagePath);
        JSONObject idCardBackResult = recognizeIdCardBack(idCardBackImagePath);


        // 处理识别结果
        // ...
        String name = idCardFrontResult.getJSONObject("words_result").getJSONObject("姓名").getString("words");
        String idNumber = idCardFrontResult.getJSONObject("words_result").getJSONObject("公民身份号码").getString("words");
        String gender = idCardFrontResult.getJSONObject("words_result").getJSONObject("性别").getString("words");
        String ethnicity = idCardFrontResult.getJSONObject("words_result").getJSONObject("民族").getString("words");
        String address = idCardFrontResult.getJSONObject("words_result").getJSONObject("住址").getString("words");
        String expirationDate = idCardBackResult.getJSONObject("words_result").getJSONObject("失效日期").getString("words");


        // 构造返回给前端的数据对象
        JSONObject responseData = new JSONObject();
        responseData.put("姓名", name);
        responseData.put("身份证号", idNumber);
        responseData.put("性别", gender);
        responseData.put("民族", ethnicity);
        responseData.put("住址", address);
        responseData.put("截止日期", expirationDate.substring(0,4)+"-"+expirationDate.substring(4,6)+"-"+expirationDate.substring(6,8));
        responseData.put("出生日期", idNumber.substring(6,10)+"-"+idNumber.substring(10,12)+"-"+idNumber.substring(12,14));
        System.out.println(responseData);
        // 返回数据给前端
        return responseData.toString();
    }

    private JSONObject recognizeIdCardFront(String imagePath) throws IOException {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        byte[] imageData = readImageData(imagePath);
        JSONObject result = client.idcard(imageData, "front", null);
        return result;
    }

    private JSONObject recognizeIdCardBack(String imagePath) throws IOException {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        byte[] imageData = readImageData(imagePath);
        JSONObject result = client.idcard(imageData, "back", null);
        return result;
    }

    private byte[] readImageData(String imagePath) throws IOException {
        File imageFile = new File("D:\\sfz\\"+imagePath);
        FileInputStream fileInputStream = new FileInputStream(imageFile);
        System.out.println(fileInputStream);
        byte[] imageData = new byte[(int) imageFile.length()];
        fileInputStream.read(imageData);
        fileInputStream.close();
        return imageData;
    }
}
