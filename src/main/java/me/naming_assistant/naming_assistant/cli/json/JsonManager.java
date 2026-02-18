package me.naming_assistant.naming_assistant.cli.json;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;

public class JsonManager {
	
    public void saveToFile(NamingContext nc, ObjectMapper mapper) throws Exception {
        String projectName = nc.getProjectName();
        
        
        Path configDirPath = Paths.get(System.getProperty("user.home"), ".naming-assistant");
        Path filePath = configDirPath.resolve(projectName + ".json");
        Files.createDirectories(configDirPath);

        // ファイル書き込みだけを行う（System.out.printlnなどは消してスッキリさせる）
        mapper.writeValue(filePath.toFile(), nc);
    }
}