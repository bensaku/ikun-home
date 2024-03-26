package com.example.demo.controller;
import com.example.demo.entity.Problem;
import com.example.demo.service.ProblemService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Resource
    private ProblemService problemService;

    @GetMapping("/getAll")
    public List<Problem> getProblems() {
        // 调用MyBatis Plus的查询方法来获取问题列表
        return problemService.getAllProblem();
    }

    @PostMapping("/addPro")
    public ResponseEntity<String> addProblem(@RequestBody Problem problem) {
        try {
            problemService.addPro(problem);
            return ResponseEntity.ok("问题上报成功");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("问题上报失败");
        }
    }

    @GetMapping("/getByUserNo")
    public List<Problem> getByUserNo(@RequestParam("userNo") String userNo) {
        return problemService.getByUserNo(userNo);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请选择文件！");
        }
        // 将文件保存到服务器的某个目录下
        String filename = file.getOriginalFilename();
        String filepath = "G:" + File.separatorChar + "study" + File.separatorChar + "java_shixun" + File.separatorChar + "project2"+ File.separatorChar + "demo1" + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "resources" + File.separatorChar + "static" + File.separatorChar + "file" + File.separatorChar + filename;
        try {
            file.transferTo(new File(filepath));
            return ResponseEntity.ok(filepath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败！");
        }
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam("filePath") String filePath) {
        boolean result = problemService.deleteFile(filePath);
        if (result) {
            return new ResponseEntity<>("文件删除成功！", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("文件删除失败！", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProblemFile")
    public void getProblemFile(@RequestParam("id") String id, HttpServletResponse response)throws IOException{
        String filePath=problemService.gerURLbyID(id);
        String[] parts = filePath.split("\\\\");
        String fileName = parts[parts.length - 1];
        File file = new File(filePath);
        if (file.exists()) {
            String fileExtension = StringUtils.getFilenameExtension(fileName);
            String contentType = getContentType(fileExtension);

            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        }

    private String getContentType(String fileExtension) {
        // 根据文件扩展名返回对应的 MIME 类型
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "png":
                return "image/png";
            case "jpg": case "jpeg":
                return "image/jpeg";
            default:
                return "application/octet-stream";
        }
    }
}



