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
		Path directory = Paths.get(saveFolder);
		Files.createDirectories(directory);
		SpringApplication.run(MusicplatformApplication.class, args);
	}

}
