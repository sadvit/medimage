package com.sadvit.services;

import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class FileSystemService {

	private Log logger = LogFactory.getLog(getClass());

	@Value("${medimage.content}")
	private String content;

	public void createUserFolder(String userName) {
		String imagesPath = content + "/" + userName + "/" + "images";
		String tempPath = content + "/" + userName + "/" + "temp";
		File imagesFolder = new File(imagesPath);
		File tempFolder = new File(tempPath);
		if (!imagesFolder.mkdirs()) {
			logger.error("Error creating user folder: " + imagesPath);
		}
		if (!tempFolder.mkdirs()) {
			logger.error("Error creating user folder: " + tempPath);
		}
	}

}
