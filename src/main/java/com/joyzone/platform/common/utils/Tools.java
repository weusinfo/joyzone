package com.joyzone.platform.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Tools
{
	public static boolean eq(String a, String b, boolean isTrim)
	{
		if (a == null && b == null)
		{
			return true;
		}
		if (a == null && b != null)
		{
			return false;
		}
		if (a != null && b == null)
		{
			return false;
		}
		if (isTrim)
		{
			if (a.trim().equals(b.trim()))
			{
				return true;
			}
		}
		if (a.equals(b))
		{
			return true;
		}
		return false;
	}

	public static boolean eq(String a, String b)
	{
		return eq(a, b, false);
	}

	public static Map<String, String> getParamMap(String str, String division)
	{
		Map<String, String> map = new HashMap<String, String>();
		String[] params = str.split(division);
		for (String param : params)
		{
			int i = param.indexOf("=");
			if (i == -1)
			{
				map.put(param, "");
			} else
			{
				map.put(param.substring(0, i), param.substring(i + 1));
			}
		}
		return map;
	}

	public static void bufferedWriteAndCloseStream(OutputStream outStream,
			InputStream inStream) throws IOException
	{
		BufferedOutputStream boutStream = new BufferedOutputStream(outStream);
		BufferedInputStream binStream = new BufferedInputStream(inStream);

		int length = -1;
		byte[] cache = new byte[1024 * 8];
		while ((length = binStream.read(cache)) != -1)
		{
			boutStream.write(cache, 0, length);
			boutStream.flush();
		}
		boutStream.close();
		binStream.close();
	}

	public static void bufferedWrite(OutputStream outStream,
			InputStream inStream) throws IOException
	{
		BufferedOutputStream boutStream = new BufferedOutputStream(outStream);
		BufferedInputStream binStream = new BufferedInputStream(inStream);

		int length = -1;
		byte[] cache = new byte[1024 * 8];
		while ((length = binStream.read(cache)) != -1)
		{
			boutStream.write(cache, 0, length);
			boutStream.flush();
		}
	}

	public static void bufferedWriteAndCloseStream(OutputStream outStream,
			Reader reader) throws IOException
	{
		BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(
				outStream));
		BufferedReader breader = new BufferedReader(reader);

		int length = -1;
		char[] cache = new char[1024 * 8];
		while ((length = breader.read(cache)) != -1)
		{
			bwriter.write(cache, 0, length);
			bwriter.flush();
		}
		bwriter.close();
		breader.close();
	}

	public static void bufferedWrite(OutputStream outStream, Reader reader)
			throws IOException
	{
		BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(
				outStream));
		BufferedReader breader = new BufferedReader(reader);

		int length = -1;
		char[] cache = new char[1024 * 8];
		while ((length = breader.read(cache)) != -1)
		{
			bwriter.write(cache, 0, length);
			bwriter.flush();
		}
	}

	public static void write(OutputStream outStream, byte[] data)
			throws IOException
	{
		outStream.write(data);
		outStream.flush();
	}

	public static void bufferedWriteAndCloseStream(OutputStream outStream,
			String path, String charset) throws IOException
	{
		bufferedWriteAndCloseStream(outStream, new InputStreamReader(
				new FileInputStream(path), charset));
	}

	/**
	 * 格式化指定日期
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return 格式化的日期字符串
	 */
	public static String getFormatDate(Date date, String pattern)
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 格式化当天日期
	 * 
	 * @param pattern
	 *            指定格式
	 * @return 格式化的日期字符串
	 */
	public static String getFormatDate(String pattern)
	{
		return getFormatDate(new Date(), pattern);
	}

	public static String getID()
	{
		return getFormatDate("yyyyMMddHHmmssSSS" + (num++));
	}

	/**
	 * url编码
	 * 
	 * @param url
	 *            资源定位符
	 * @param encoding
	 *            编码格式
	 * @param urlEncoded
	 *            是否是URLEncoded
	 * @return 编码后的串。
	 */
	public static String encodeUrl(String url, String encoding,
			Boolean urlEncoded)
	{
		if (url == null || "".equals(url))
		{
			return "";
		}
		if (encoding == null || "".equals(encoding))
		{
			return url;
		}
		if (urlEncoded == null)
		{
			return url;
		}
		if (url.indexOf("?") == -1)
		{
			StringBuilder s = new StringBuilder();
			try
			{
				char[] cs = url.toCharArray();
				for (char c : cs)
				{
					if (c > 0x80)
					{ // 对非ASCII，进行编码
						s.append(URLEncoder.encode(c + "", encoding));
					} else
					{
						s.append(c);
					}
				}
			} catch (UnsupportedEncodingException e1)
			{
				throw new RuntimeException("不支持的编码格式:" + encoding);
			}
			return s.toString();
		}
		String baseUrl = url.substring(0, url.indexOf("?") + 1);
		String paramStr = url.substring(url.indexOf("?") + 1);
		StringBuilder sb = new StringBuilder(baseUrl);

		String[] params = paramStr.split("&");
		for (String param : params)
		{
			int eqIndex = param.indexOf("=");
			String proStr = param.substring(eqIndex + 1);
			String newStr = "";
			if (urlEncoded == true)
			{
				try
				{
					newStr = URLEncoder.encode(proStr, encoding);
				} catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException("不支持的编码格式:" + encoding);
				}
			} else
			{
				try
				{
					newStr = new String(proStr.getBytes(), encoding);
				} catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException("不支持的编码格式:" + encoding);
				}
			}
			sb.append(param.substring(0, eqIndex + 1)).append(newStr)
					.append("&");
		}
		if (params.length > 0)
		{
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String hexString(byte[] b)
	{
		StringBuffer d = new StringBuffer();
		for (byte element : b)
		{
			char hi = Character.forDigit((element >> 4) & 0x0F, 16);
			char lo = Character.forDigit(element & 0x0F, 16);
			d.append(Character.toUpperCase(hi));
			d.append(Character.toUpperCase(lo));
		}
		return d.toString();
	}

	/**
	 * IO的流转操作。可能涉及到网络流、文件流等
	 * 
	 * @param inStream
	 *            输入流
	 * @param outStream
	 *            输出流
	 */
	public static void writeFile(InputStream inStream, OutputStream outStream)
	{
		try
		{
			byte[] cache = new byte[1024 * 4];
			int length = -1;
			while ((length = inStream.read(cache)) != -1)
			{
				outStream.write(cache, 0, length);
				outStream.flush();
			}
		} catch (Exception e)
		{
			throw new RuntimeException("M-Adaptor write file error:", e);
		}
	}

	public static String base64Encoder(String content)
	{
		BASE64Encoder base64 = new BASE64Encoder();
		try
		{
			return base64.encode(content.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e)
		{
			return null;
		}
	}

	public static String base64Encoder(byte[] data)
	{
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(data);
	}

	public static String base64Decoder(String content)
	{
		return new String(base64DecoderToBytes(content));
	}

	public static byte[] base64DecoderToBytes(String content)
	{
		BASE64Decoder base64 = new BASE64Decoder();
		try
		{
			return base64.decodeBuffer(content);
		} catch (IOException e)
		{
			throw new RuntimeException("Base64解码错误", e);
		}
	}

	public static String base64Decoder(String content, String charset)
	{
		BASE64Decoder base64 = new BASE64Decoder();
		try
		{
			return new String(base64.decodeBuffer(content), charset);
		} catch (IOException e)
		{
			throw new RuntimeException("Base64解码错误", e);
		}
	}

	public static String getFileName(String fileLocation)
	{
		if (fileLocation.indexOf("/") == -1)
		{
			return fileLocation;
		}
		if (fileLocation.endsWith("/"))
		{
			throw new RuntimeException("the file location don't contain file.");
		}
		int index = fileLocation.lastIndexOf("/");
		return fileLocation.substring(index + 1);
	}

	// HTML字符转换表
	public final static Map<String, String> HTML_CHAR = new HashMap<String, String>();
	static
	{
		HTML_CHAR.put("&", "&#38;");
		HTML_CHAR.put("\"", "&#34;");
		HTML_CHAR.put("<", "&#60;");
		HTML_CHAR.put(">", "&#62;");
		HTML_CHAR.put("'", "&#39;");
	}

	public static String dealStrHtml(String html)
	{
		String str = html.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
				.replaceAll("</[a-zA-Z]+[1-9]?>", "").replace("\r", "")
				.replace("\n", "").replace("\t", "").replace("&nbsp;", "");
		try
		{
			str = full2HalfChange(str);
		} catch (UnsupportedEncodingException e)
		{
			System.out.println("格式化摘要异常：：" + e);
		}
		str = str.replace(" ", "");
		return str;
	}
	
	public static String dealStrHtml1(String html)
	{
		String str = html.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
				.replaceAll("</[a-zA-Z]+[1-9]?>", "").replace("\r", "")
				.replace("\n", "@").replace("\t", "").replace("&nbsp;", "");
		try
		{
			str = full2HalfChange(str);
		} catch (UnsupportedEncodingException e)
		{
			System.out.println("格式化摘要异常：：" + e);
		}
		str = str.replace(" ", "");
		return str;
	}

	// 全角转半角的 转换函数
	public static final String full2HalfChange(String QJstr)
			throws UnsupportedEncodingException
	{

		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++)
		{

			Tstr = QJstr.substring(i, i + 1);

			// 全角空格转换成半角空格
			if (Tstr.equals("　"))
			{
				outStrBuf.append(" ");
				continue;
			}

			b = Tstr.getBytes("unicode"); // 得到 unicode 字节数据

			if (b[3] == -1)
			{ // 表示全角？
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;

				outStrBuf.append(new String(b, "unicode"));
			} else
			{
				outStrBuf.append(Tstr);
			}

		} // end for.

		return outStrBuf.toString();
	}

	public static final StringBuilder toHTMLChar(String str)
	{
		if (str == null)
		{
			return new StringBuilder();
		}
		StringBuilder sb = new StringBuilder(str);

		char tempChar;
		String tempStr;
		for (int i = 0; i < sb.length(); i++)
		{
			tempChar = sb.charAt(i);
			if (HTML_CHAR.containsKey(Character.toString(tempChar)))
			{
				tempStr = HTML_CHAR.get(Character.toString(tempChar));
				sb.replace(i, i + 1, tempStr);
				i += tempStr.length() - 1;
			}
		}
		return sb;
	}

	// 应用程序id计数使用
	private static int num = 0;

	/**
	 * 换算两点之间的距离
	 * 
	 */
	static double DEF_PI = 3.14159265359; // PI

	static double DEF_2PI = 6.28318530712; // 2*PI

	static double DEF_PI180 = 0.01745329252; // PI/180.0

	static double DEF_R = 6370693.5; // radius of earth

	public static double GetShortDistance(double lon1, double lat1,
			double lon2, double lat2)

	{

		double ew1, ns1, ew2, ns2;

		double dx, dy, dew;

		double distance;

		// 角度转换为弧度

		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;

		ew2 = lon2 * DEF_PI180;

		ns2 = lat2 * DEF_PI180;

		// 经度差

		dew = ew1 - ew2;

		// 若跨东经和西经180 度，进行调整

		if (dew > DEF_PI)
		{
			dew = DEF_2PI - dew;
		} else if (dew < -DEF_PI)
		{
			dew = DEF_2PI + dew;
		}

		dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)

		dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)

		// 勾股定理求斜边长

		distance = Math.sqrt(dx * dx + dy * dy);

		return distance;

	}

	public static double GetLongDistance(double lon1, double lat1, double lon2,
			double lat2)

	{

		double ew1, ns1, ew2, ns2;

		double distance;

		// 角度转换为弧度

		ew1 = lon1 * DEF_PI180;

		ns1 = lat1 * DEF_PI180;

		ew2 = lon2 * DEF_PI180;

		ns2 = lat2 * DEF_PI180;

		// 求大圆劣弧与球心所夹的角(弧度)

		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1)
				* Math.cos(ns2) * Math.cos(ew1 - ew2);

		// 调整到[-1..1]范围内，避免溢出

		if (distance > 1.0)
		{
			distance = 1.0;
		} else if (distance < -1.0)
		{
			distance = -1.0;
		}

		// 求大圆劣弧长度

		distance = DEF_R * Math.acos(distance);

		return distance;

	}
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}
	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
}
