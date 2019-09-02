package com.joyzone.platform.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;

/**
 * 腾讯云对象存储， 需要指定文件名
 * @author Administrator
 *
 */
@Component
public class COSFileUtil extends BaseFileUtil{

	@Value("${tencent.oss.bucket}")
	private String bucket;

	@Autowired
	private COSClient cosClient;
	
	/**
	 * 保存商户相关文件
	 * @param in
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public String uploadShopImg(MultipartFile file) throws Exception {
		String fileName = genTempFileName(file);
		InputStream inStream = handleFile(file);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, "shop/"+fileName, inStream, null);
		cosClient.putObject(putObjectRequest);
		return "https://" + bucket + ".cos.ap-shenzhen-fsi.myqcloud.com/shop/"+fileName;
	}
	
	/**
	 * 保存用户相关文件
	 * @param in
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public String uploadPersonalImg(MultipartFile file) throws Exception {
		String fileName = genTempFileName(file);
		InputStream inStream = handleFile(file);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, "personal/"+fileName, inStream ,null);
		cosClient.putObject(putObjectRequest);
		return "https://" + bucket + ".cos.ap-shenzhen-fsi.myqcloud.com/personal/"+fileName;
	}
	
}
