package com.joyzone.platform.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.model.DocumentModel;

@Service
public class DocumentService extends BaseService<DocumentModel>{

	private Logger logger = LoggerFactory.getLogger(DocumentService.class);

	/*@Autowired
	private FileUtil fileUtil;

	
	public String saveShopDoc(MultipartFile file) {
		try {
			String filePath = fileUtil.uploadShopImg(file);
		} catch (Exception e) {
			logger.error("Upload shop file error...",e);
		}
		return null;
	}

	public String saveUserDoc(MultipartFile file) {
		try {
			String filePath = fileUtil.uploadPersonalImg(file);
		} catch (Exception e) {
			logger.error("Upload user file error...",e);
		}
		return null;
	}*/

}

