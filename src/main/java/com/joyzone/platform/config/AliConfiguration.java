package com.joyzone.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

@Configuration
public class AliConfiguration {
	
	@Value("${ali.oss.secretId}")
	private String secretId;
	
	@Value("${ali.oss.secretKey}")
	private String secretKey;
	
	@Value("${ali.oss.bucket}")
	private String bucket;
	
	@Value("${ali.oss.domain}")
	private String domain;
	
	@Value("${ali.sms.signName}")
	private String signName;
	
	@Value("${ali.sms.genericSignName}")
	private String genericSignName;

	@Value("${ali.sms.codeTemplateCode}")
	private String codeTempateCode;
	
	@Value("${ali.sms.msgTemplateCode}")
	private String msgTemplateCode;

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
	
	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getCodeTempateCode() {
		return codeTempateCode;
	}

	public void setCodeTempateCode(String codeTempateCode) {
		this.codeTempateCode = codeTempateCode;
	}
	
	public String getMsgTemplateCode() {
		return msgTemplateCode;
	}

	public void setMsgTemplateCode(String msgTemplateCode) {
		this.msgTemplateCode = msgTemplateCode;
	}
	
	public String getGenericSignName() {
		return genericSignName;
	}

	public void setGenericSignName(String genericSignName) {
		this.genericSignName = genericSignName;
	}
	
	@Bean
	public OSSClient ossClient() {
		return (OSSClient)(new OSSClientBuilder().build(domain, secretId, secretKey));
	}

}
