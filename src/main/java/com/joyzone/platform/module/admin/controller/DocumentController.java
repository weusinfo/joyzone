package com.joyzone.platform.module.admin.controller;

import com.joyzone.platform.core.model.DocumentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.joyzone.platform.common.utils.AliFileUtil;
import com.joyzone.platform.common.utils.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 腾讯云对象存储， 需要指定文件名
 * @author Administrator
 *
 */
@Api(tags="文件上传",description="DocumentController")
@RestController
@RequestMapping("/doc")
public class DocumentController {
	
	@Autowired
	private AliFileUtil fileUtil;
	
	@ApiOperation("添加商户文件")
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
	
	@ApiOperation("添加用户文件")
	@PostMapping(path="/uploadUserDoc", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public R uploadUserDoc(MultipartFile file) {
		try {
			String filePath = fileUtil.uploadPersonalImg(file);
			DocumentModel documentModel = new DocumentModel(filePath);
			return R.ok(documentModel);
		} catch (Exception e) {
			//ignore
		}
		return R.error("上传失败");
	}

}
