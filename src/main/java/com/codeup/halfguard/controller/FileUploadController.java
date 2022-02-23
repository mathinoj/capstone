//package com.codeup.halfguard.controller;
//
//import com.codeup.halfguard.repositories.MediaRepository;
//import com.codeup.halfguard.services.UserService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import com.codeup.halfguard.models.Image;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//
//@Controller
//public class FileUploadController {
////    @Value("${fileStackApiKey")
//
//    @Value("${file-upload-path}")
//    private String uploadPath;
//
//    private final MediaRepository mediaDao;
//    private final UserService userService;
//
//    public FileUploadController(MediaRepository mediaDao, UserService userService) {
//        this.mediaDao = mediaDao;
//        this.userService = userService;
//    }
//
//    @GetMapping("/fileupload")
//    public String showUploadFileForm() {
//        return "/profile/profilePicture";
//    }
//
//
//    @PostMapping("/actionToPostPic")
//    public String saveFile(@RequestParam(name = "filePicYou") MultipartFile uploadedFile, Model model) {
////    String filename = uploadedFile.getOriginalFilename();
//////    String fileName = StringUtils.cleanPath(uploadedFile.getOriginalFilename()));
////
////    String filepath = Paths.get(uploadPath, filename).toString();
////    File destinationFile = new File(filepath);
//
//
//        String filename = uploadedFile.getOriginalFilename();
//        String filepath = Paths.get(uploadPath, filename).toString();
//        File destinationFile = new File(filepath);
//
//
//        try {
//            uploadedFile.transferTo(destinationFile);
//            model.addAttribute("message", "File successfully uploaded!");
//        } catch (IOException e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Oops! Something went wrong!" + e);
//        }
////        return "fileupload";
////        return "/posts/userProfile";
//        return "/profile/userProfileEdited";
//    }
//
//
//}
