package com.musicplatform.services.fileServices;

import com.musicplatform.exceptions.NotMusicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class MusicUpload {

    private static final List<String> contentTypes = Arrays.asList(
            "audio/basic", "audio/midi", "audio/mpeg", "audio/x-aiff", "audio/x-mpegurl", "audio/x-pn-realaudio", "audio/x-wav", "audio/mp3"
    );

    private static String saveFolder = "\\musicUploads\\";
    private static String savePlace = System.getProperty("user.dir") + saveFolder;

    public static String checkAndUploadMusic(MultipartFile file, String name) throws NotMusicException {
        if (!checkMusic(file)) {
            throw new NotMusicException();
        }

        String musicName = getName(file.getOriginalFilename(), name + System.currentTimeMillis());

        String musicPath = null;

        try {
            byte [] bytes = file.getBytes();
            musicPath = saveFolder + musicName;
            Path path = Paths.get(savePlace + musicName);
            Files.write(path, bytes);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return musicPath;
    }

    private static boolean checkMusic(MultipartFile file) {
        String fileContentType = file.getContentType();
        if (contentTypes.contains(fileContentType)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static String getName(String originalName, String newName) {
        String musicName = newName + originalName.substring(originalName.lastIndexOf('.'));
        System.out.println("Music name: " + musicName);
        return musicName;
    }
}
