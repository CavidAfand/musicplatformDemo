package com.musicplatform.services.fileServices;

import com.musicplatform.exceptions.NotImageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.NotActiveException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ImageUpload {

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    private static final String folderForProfile = "\\uploads\\";
    private static final String folderForPost = "\\postUploads\\";
    private static final String saveFolderForProfile = System.getProperty("user.dir") + folderForProfile;
    private static final String saveFolderForPost = System.getProperty("user.dir") + folderForPost;

    public static String checkAndChangeImage(MultipartFile image, String filename, String previousImageName) throws NotImageException, IOException {

        if (ImageUpload.checkImage(image) == false) {
            throw new NotImageException();
        }

        deleteImage(System.getProperty("user.dir") + previousImageName);

        String imageName = getImageName(image.getOriginalFilename(), filename);

//        String imagePath = "\\uploads\\" + imageName;

        String imagePath =  saveImage(image, saveFolderForPost, folderForPost, imageName);

//        try {
//            String saveFolder = System.getProperty("user.dir") + "\\uploads\\";
//            byte [] bytes = image.getBytes();
//            imagePath = "\\uploads\\" + imageName;
//            Path path = Paths.get(saveFolder + imageName);
//            Files.write(path, bytes);
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }

        return imagePath;

    }

    public static String checkAndSaveImage(MultipartFile image, String fileName) throws IOException, NotImageException {

        if (ImageUpload.checkImage(image) == false) {
            throw new NotImageException();
        }

        String imageName = getImageName(image.getOriginalFilename(), fileName);

        String imagePath = saveImage(image, saveFolderForProfile, folderForProfile, imageName);

        return imagePath;
    }

    private static String saveImage(MultipartFile file, String generalFolderName, String dbFolderName, String imageName) {
        try {
            byte [] bytes = file.getBytes();
            Path path = Paths.get(generalFolderName + imageName);
            Files.write(path, bytes);
            return dbFolderName + imageName;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static boolean checkImage(MultipartFile name) throws IOException {
        String fileContentType = name.getContentType();
        if (contentTypes.contains(fileContentType)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static String getImageName(String originalName, String newName) {
        String imageName = newName + originalName.substring(originalName.lastIndexOf('.'));
        return imageName;
    }

    private static boolean deleteImage(String file) {

        try {
            Files.deleteIfExists(Paths.get(file));
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
}
