package ir.mojir.sample_project;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class CodeGenerator {

	private final static String entityName = "Software";
	private final static String destinationPath = "D:\\generated_code";
	private final static String packageName = "org.cbi.hit.permission_manager";
	
	public static void main(String[] args) {
		
		
		
		try {
//			Files.readString(Paths.get(destinationPath));
			File dir = new File(".\\src\\main\\java\\ir\\mojir\\sample_project");
			File destinationDir = new File(destinationPath);
			FileUtils.copyDirectory(dir, destinationDir);
			readDir(destinationDir.listFiles());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	private static void readDir(File[] files) throws IOException {
		for(File file: files) {
			if (file.isDirectory()) {
                System.out.println("Directory: " + file.getPath());
                
                readDir(file.listFiles()); 
                if(file.getName().equals("xxx"))
                	file.renameTo(new File(file.getPath().replaceAll("xxx", entityName.toLowerCase())));
            } else {
                System.out.println("File: " + file.getPath());
                String fileContent = FileUtils.readFileToString(file, Charset.forName("utf8"));
                fileContent = fileContent.replaceAll("Xxx", entityName);
                fileContent = fileContent.replaceAll("xxx", entityName.toLowerCase());
                fileContent = fileContent.replaceAll("ir.mojir.sample_project", packageName);
                
                FileUtils.writeStringToFile(file, fileContent, Charset.forName("utf8"));
                file.renameTo(new File(file.getPath().replaceAll("Xxx", entityName)));
                
                if(file.getName().equals("CodeGenerator.java") ||
                		file.getName().equals("SampleProjectApplication.java"))
                	file.delete();
            }
		}
	}

}
