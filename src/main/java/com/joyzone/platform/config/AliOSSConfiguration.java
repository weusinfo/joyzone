package com.joyzone.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

@Configuration
public class AliOSSConfiguration {
	
	@Value("${ali.oss.secretId}")
	private String secretId;
	
	@Value("${ali.oss.secretKey}")
	private String secretKey;
	
	@Value("${ali.oss.bucket}")
	private String bucket;
	
	@Value("${ali.oss.domain}")
	private String domain;

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Bean
	public OSSClient ossClient() {
		return (OSSClient)(new OSSClientBuilder().build(domain, secretId, secretKey));
	}

}
