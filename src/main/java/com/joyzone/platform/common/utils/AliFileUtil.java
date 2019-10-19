package com.joyzone.platform.common.utils;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.joyzone.platform.config.AliConfiguration;

@Component
public class AliFileUtil extends BaseFileUtil {
	
	@Autowired
	private OSS ossClient;
	
	@Autowired
	private AliConfiguration ossConf;

	@Override
	public String uploadShopImg(MultipartFile file) throws Exception {
		String fileName = genTempFileName(file);
		InputStream inStream = handleFile(file);
		PutObjectRequest putObjectRequest = new PutObjectRequest(ossConf.getBucket(), "joyzone/"+fileName, inStream, null);
		PutObjectResult rst = ossClient.putObject(putObjectRequest);
		return "http://" + ossConf.getBucket() + "." + ossConf.getDomain() + "/joyzone/" + fileName;
	}

	@Override
	public String uploadPersonalImg(MultipartFile file) throws Exception {
		String fileName = genTempFileName(file);
		InputStream inStream = handleFile(file);
		PutObjectRequest putObjectRequest = new PutObjectRequest(ossConf.getBucket(), "joyzone/"+fileName, inStream ,null);
		PutObjectResult rst = ossClient.putObject(putObjectRequest);
		return "http://" + ossConf.getBucket() + "." + ossConf.getDomain() + "/joyzone/" + fileName;
	}

}
