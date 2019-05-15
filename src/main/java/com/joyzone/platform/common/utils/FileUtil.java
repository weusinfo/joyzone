package com.joyzone.platform.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

/**
 * 腾讯云对象存储， 需要指定文件名
 * @author Administrator
 *
 */
@Component
public class FileUtil {

	@Value("${tencent.oss.bucket}")
	private String bucket;
	
	@Value("${fileSize}")
	private String fileSize;
	
	@Value("${scale}")
	private String scale;

	@Autowired
	private COSClient cosClient;
	
	private int sequence = 1;
	
	private int getNextNumber()
	{
		if (sequence == Integer.MAX_VALUE)
		{
			sequence = 1;
		}
		else
		{
			sequence = sequence  + 1;
		}
		return sequence;
	}
	
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
	
	/**
	 * 生成唯一文件名
	 * @param name
	 * @return
	 */
	public String genTempFileName(MultipartFile file)
    {
		String name = file.getOriginalFilename();
    	StringBuilder fileNameBuilder = new StringBuilder();
    	fileNameBuilder.append(System.currentTimeMillis());
    	fileNameBuilder.append(getNextNumber());
    	fileNameBuilder.append("_");
    	fileNameBuilder.append(name);
    	return fileNameBuilder.toString();
    }
	
	/**
	 * 在本地生成临时文件
	 * @param file
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public File genTempFile(MultipartFile file) throws IOException {
		String originalFileName = file.getOriginalFilename();
		int suffixIdx = originalFileName.lastIndexOf(".");
		String suffix = originalFileName.substring(suffixIdx);
		File tmpFile = File.createTempFile(UUID.randomUUID().toString(),suffix);
		return tmpFile;
	}
	
	/**
	 * 不是图片类型文件部压缩
	 * @param file
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public InputStream handleFile(MultipartFile file) throws Exception {
		File tmpFile = genTempFile(file);
		boolean isValid  = isValidPic(file);
		if(!isValid) return file.getInputStream();
		if(StringUtils.isNoneEmpty(fileSize) && StringUtils.isNoneEmpty(scale)) {
			if(file.getSize() >= Long.parseLong(fileSize)) {
				Thumbnails.of(file.getInputStream()).scale(Float.parseFloat(scale)).toFile(tmpFile);
				return new FileInputStream(tmpFile);
			}
		}
		return file.getInputStream();
	}
	
	/**
	 * 检查是否是图片
	 * @param file
	 * @return
	 * @throws MagicParseException
	 * @throws MagicMatchNotFoundException
	 * @throws MagicException
	 * @throws IOException
	 */
	public boolean isValidPic(MultipartFile file) throws MagicParseException, MagicMatchNotFoundException, MagicException, IOException {
		MagicMatch matcher = Magic.getMagicMatch(file.getBytes());
		String mimeType = matcher.getMimeType();
		if(mimeType.equals("image/jpeg") || mimeType.equals("image/gif") || mimeType.equals("image/png") || mimeType.equals("image/bmp")) {
			return true;
		}
		return false;
	}
}
