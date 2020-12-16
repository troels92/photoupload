package com.ts.photoupload.controller;

import com.ts.photoupload.model.Infor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class InforController {

    @RequestMapping("/")
    public String register(){
        return "infor";

    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("photo")MultipartFile photo, ModelMap model){

        Infor infor = new Infor();
        infor.setName(name);


        Path path = Paths.get("uploads/");

        try {
            InputStream inputStream = photo.getInputStream();
            Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            infor.setPhoto(photo.getOriginalFilename().toLowerCase());
            //

            model.addAttribute("INFOR", infor);

        } catch (Exception e){
            e.printStackTrace();
        }

        return "view-infor";
    }

}
