package com.musicplatform.services.fileServices;

import com.musicplatform.exceptions.NotImageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageUpload {

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");


    public static String checkAndSaveImage(MultipartFile image, String fileName) throws IOException, NotImageException {
        System.out.println("Step 1");
        if (ImageUpload.checkImage(image) == false) {
            throw new NotImageException();
        }

        System.out.println("Step 2");

        String imageName = getImageName(image.getOriginalFilename(), fileName);

        String imagePath = null;

        try {
            String saveFolder = System.getProperty("user.dir") + "\\uploads\\";
            byte [] bytes = image.getBytes();
            imagePath = saveFolder + imageName;
            Path path = Paths.get(imagePath);
            Files.write(path, bytes);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return imagePath;

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
        System.out.println("Image Name: " + imageName);
        return imageName;
    }

}
