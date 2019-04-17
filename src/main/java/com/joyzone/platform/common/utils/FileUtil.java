package com.joyzone.platform.common.utils;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${bucket}")
	private String bucket;

	@Autowired
	private COSClient cosClient;
	
	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	
	/**
	 * 保存商户相关文件
	 * @param in
	 * @param fileName
	 * @return
	 */
	public String uploadShopImg(InputStream in, String fileName) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, "shop/"+fileName,in,null);
		cosClient.putObject(putObjectRequest);
		return "https://" + bucket + ".cos.ap-shenzhen-fsi.myqcloud.com/shop/"+fileName;
	}
	
	/**
	 * 保存用户相关文件
	 * @param in
	 * @param fileName
	 * @return
	 */
	public String uploadPersonalImg(InputStream in,String fileName) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, "personal/"+fileName,in,null);
		cosClient.putObject(putObjectRequest);
		return "https://" + bucket + ".cos.ap-shenzhen-fsi.myqcloud.com/personal/"+fileName;
	}
	
}
