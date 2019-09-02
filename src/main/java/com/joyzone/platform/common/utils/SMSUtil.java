package com.joyzone.platform.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.joyzone.platform.config.AliConfiguration;

/**
 * 短信发送工具类
 * 
 * @author Administrator
 *
 */
@Component
public class SMSUtil {

	@Autowired
	private AliConfiguration conf;

	private final String domain = "dysmsapi.aliyuncs.com";

	private final String product = "Dysmsapi";

	public void sendCodeSMS(String mobile, String codeParam) throws ClientException {
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", conf.getSecretId(), conf.getSecretKey());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

		IAcsClient acsClient = new DefaultAcsClient(profile);
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(mobile);
		request.setSignName(conf.getSignName());
		request.setTemplateCode(conf.getCodeTempateCode());
		request.setTemplateParam(codeParam);
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	}

}
