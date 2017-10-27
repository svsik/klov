package com.aventstack.klov.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.aventstack.klov.domain.Media;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);
    
    void store(MultipartFile file, Media m);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    Resource loadAsResource(Path Path);
    
    Resource loadAsResource(Media m);
    
    String loadBase64(Media m);
    
    void deleteAll();

}