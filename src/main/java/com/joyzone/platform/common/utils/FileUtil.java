package com.joyzone.platform.common.utils;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;

/**
 * 腾讯云对象存储， 需要指定文件名
 * @author Administrator
 *
 */
@Component
public class FileUtil {

	@Autowired
	private COSClient cosClient;
	
	public String uploadShopImg(InputStream in, String fileName) {
		PutObjectRequest putObjectRequest = new PutObjectRequest("joyzone-1257127706", "shop/"+fileName,in,null);
		cosClient.putObject(putObjectRequest);
		return "https://joyzone-1257127706.cos.ap-shenzhen-fsi.myqcloud.com/shop/"+fileName;
	}
	
	public String uploadPersonalImg(InputStream in,String fileName) {
		PutObjectRequest putObjectRequest = new PutObjectRequest("joyzone-1257127706", "personal/"+fileName,in,null);
		cosClient.putObject(putObjectRequest);
		return "https://joyzone-1257127706.cos.ap-shenzhen-fsi.myqcloud.com/personal/"+fileName;
	}
	
}
