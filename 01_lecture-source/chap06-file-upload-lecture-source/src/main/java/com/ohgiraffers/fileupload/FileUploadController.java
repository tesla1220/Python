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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {           // model을 사용하는 이유는 나중에 view 쪽에서 보여주기 위해(?)

        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        /* 파일을 저장할 경로 설정 */
        Resource resource = resourceLoader.getResource("classpath:static/img/single");

        String filePath = null;

        if(!resource.exists()){

            /* 만약 static 폴더에 파일이 없는 경우 만들어준다. */
            String root = "src/main/resources/static/img/single";
            File file = new File(root);

            file.mkdirs();

            filePath = file.getAbsolutePath();

        } else {

            filePath = resourceLoader.getResource("classpath:static/img/single").getFile().getAbsolutePath();
        }

        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);

        /* 확장자 제거 */
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        String savedName = java.util.UUID.randomUUID().toString().replace("-","")+ ext;
        // 위의 출력값 -> savedName = 538b7c7577eb4f3c84ffac28d0a2071c.jpg
        System.out.println("savedName = " + savedName);


        /* 파일을 저장 */
        singleFile.transferTo(new File(filePath + "/" + savedName));

        model.addAttribute("message", "파일 업로드 성공");
        model.addAttribute("img", "static/img/single/"+savedName);


        return "result";

    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles
                                  , @RequestParam String multiFileDescription
                                  , Model model) throws IOException {

        System.out.println("multiPartFiles = " + multiFiles);
        System.out.println("multiFileDescription = " + multiFileDescription);

        Resource resource = resourceLoader.getResource("classpath:static/img/multi");
        String filePath = null;

        if(!resource.exists()){

            String root = "src/main/resources/static/img/multi";
            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            filePath = resourceLoader.getResource("classpath:static/img/multi").getFile().getAbsolutePath();

        }

        System.out.println("filePath = " + filePath);

        List<FileDTO> files = new ArrayList<>();
        List<String> saveFules = new ArrayList<>();

        for(MultipartFile file : multiFiles) {
            /* 파일명 변경 처리 */
            String orginFileName = file.getOriginalFilename();
            String ext = orginFileName.substring(orginFileName.lastIndexOf("."));
            String savedName =UUID.randomUUID().toString().replave("-","")+ext;

            files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription ));

            file.transferTo(new File(filePath + "/" + savedName));
            saveFules.add("static/img/multi/"+savedName);

            model.addAttribute("message", "파일 업로드 성공!!");
            model.addAttribute("imgs", saveFiles);
        }

        return "/";

    }


}
