package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {


    /* 파일을 저장할 경로 설정 */
    Resource resource = resourceLoader.getResource("classpath:static/img/single");

    String filePath;

    if(!resource.exists()) {
        String root = "src/main/resources/static/img/single";
        File file = new File(root);

        file.mkdirs();

        filePath = file.getAbsolutePath();
    } else {
        try {
            filePath = resourceLoader.getResource("class:static/img/single").getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 파일명 변경 처리

    String originFileName = singleFile.getOriginalFilename();


        // 확장자 제거
    String ext = originFileName.substring(originFileName.lastIndexOf("."));


    String saveName = UUID.randomUUID().toString().replace("-","") + ext;

    // 파일을 저장

        singleFile.transferTo(new File(filePath + "/" + saveName));
        model.addAttribute("message","파일 업로드 성공!!");
        model.addAttribute("img","static/img/single/" + saveName);


        return "result";

    }

}
}