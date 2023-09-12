package com.blogapp.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.services.FileService;

@Component
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String name = file.getOriginalFilename();
		
//		Create random name for file
		String randomId = UUID.randomUUID().toString();
		String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
		
//		Create path where we have to save the file
		String filePath = path + File.separator + fileName;
		
		
		
//		Create folder if not exists
		File file2 = new File(path);
		
		if(!file2.exists()) {
			file2.mkdir();
		}
		
//		Copy that file and paste it to filePath
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String filePath = path + File.separator + fileName;
		
		return new FileInputStream(filePath);
	}

}
