package com.sadvit.services;

import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class FileSystemService {

	private Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserRepository userRepository;

	@EventListener({ContextRefreshedEvent.class})
	public void contextRefreshedEventListener() {
		if (!checkFileSystem()) {
			createFileSystem();
		}
		logger.info("FileSystemService checked the file system");
	}

	public void createFileSystem() {
		List<User> users = userRepository.getAllUsers();
		for (User user : users) {
			String name = user.getName();

		}
	}

	public boolean checkFileSystem() {
		return false;
	}

}
