package com.musicplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class MusicplatformApplication {

	public static void main(String[] args) throws IOException {
		String saveFolder = System.getProperty("user.dir") + "\\uploads\\";
		Files.createDirectories(Paths.get(saveFolder));

		saveFolder = System.getProperty("user.dir") + "\\postUploads\\";
		Files.createDirectories(Paths.get(saveFolder));

		saveFolder = System.getProperty("user.dir") + "\\musicUploads\\";
		Files.createDirectories(Paths.get(saveFolder));

		SpringApplication.run(MusicplatformApplication.class, args);
	}

}
