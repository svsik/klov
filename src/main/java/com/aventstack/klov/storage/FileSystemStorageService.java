package com.aventstack.klov.storage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aventstack.klov.domain.Media;

import net.iharder.Base64;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileSystemStorageService implements StorageService {

    private Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getStorageLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            
            File f = new File(rootLocation + file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(), f);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    @Override
    public void store(MultipartFile file, Media m) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            
            Path savePath = getBuildPath(m);
            FileUtils.copyInputStreamToFile(file.getInputStream(), savePath.toFile());            
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    private Path getBuildPath(Media m) {
        String imgPath = m.getReport() + "/" + m.getTest() + "/" + m.getId() + ".png";
        Path path = rootLocation.resolve(imgPath);
        return path;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public String loadBase64(Media m) {
        try {
            Resource file = loadAsResource(m);
            
            if (file != null) {
                InputStream is = file.getInputStream();
                byte[] bytes = IOUtils.toByteArray(is);
                String encoded = Base64.encodeBytes(bytes);
                return encoded;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        Path file = load(filename);
        return loadAsResource(file);
    }
    
    @Override
    public Resource loadAsResource(Path file) {
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                //throw new StorageFileNotFoundException("Could not read file: " + file.getFileName());
                return null;
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + file.getFileName(), e);
        }
    }
    
    @Override
    public Resource loadAsResource(Media m) {
        Path path = getBuildPath(m);
        return loadAsResource(path);
    }

    @Override
    public void deleteAll() {
        
    }

    @Override
    public void init() {
        
    }
    
}