package com.joyzone.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

@Configuration
public class COSConfig {
	@Value("${secretId}")
	private String secretId;
	
	@Value("${secretKey}")
	private String secretKey;
	
	@Value("${bucket}")
	private String bucket;

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	
	@Bean
	public COSClient generateClient() {
		COSCredentials cred = new BasicCOSCredentials(getSecretId(), getSecretKey());
		ClientConfig clientConfig = new ClientConfig(new Region("ap-shenzhen-fsi"));
		COSClient cosClient = new COSClient(cred, clientConfig);
		return cosClient;
	}

}
