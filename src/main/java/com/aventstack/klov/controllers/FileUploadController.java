package com.aventstack.klov.controllers;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aventstack.klov.domain.Media;
import com.aventstack.klov.repository.MediaRepository;
import com.aventstack.klov.storage.StorageService;

import net.iharder.Base64;

@Controller
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private MediaRepository<Media, String> mediaRepo;
    
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @PostMapping("/upload")
    public String upload(@RequestParam("f") MultipartFile file, @RequestParam("id") String id, RedirectAttributes redirectAttributes) {
        Media m = mediaRepo.findById(id);

        if (m != null) {
            storageService.store(file, m);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
        }

        return "redirect:/";
    }
    
    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<Resource> download(@PathVariable("id") String id) {
        Media m = mediaRepo.findById(id);
        Resource file = storageService.loadAsResource(m);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    
    @GetMapping("/downloadBase64")
    @ResponseBody
    public Media downloadBase64(@RequestParam("id") String id) {
        try {
            Media m = mediaRepo.findById(id);
            Resource file = storageService.loadAsResource(m);
            InputStream is = file.getInputStream();
            byte[] bytes = IOUtils.toByteArray(is);
            String encoded = Base64.encodeBytes(bytes);
            m.setBase64String(encoded);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
