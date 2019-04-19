package com.joyzone.platform.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.joyzone.platform.common.utils.FileUtil;
import com.joyzone.platform.common.utils.R;

import io.swagger.annotations.Api;

/**
 * 腾讯云对象存储， 需要指定文件名
 * @author Administrator
 *
 */
@Api(tags="文件处理",description="DocumentController")
@RestController
@RequestMapping("/doc")
public class DocumentController {
	
	@Autowired
	private FileUtil fileUtil;
	
	@PostMapping(path="/uploadShopDoc", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public R uploadShopDoc(MultipartFile file) {
		try {
			String filePath = fileUtil.uploadShopImg(file);
			return R.ok("上传成功").put("filePath", filePath);
		} catch (Exception e) {
			//ignore
		}
		return R.error("上传失败");
	}
	
	@PostMapping(path="/uploadUserDoc", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public R uploadUserDoc(MultipartFile file) {
		try {
			String filePath = fileUtil.uploadPersonalImg(file);
			return R.ok("上传成功").put("filePath", filePath);
		} catch (Exception e) {
			//ignore
		}
		return R.error("上传失败");
	}

}
