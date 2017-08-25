package com.amwell.ecar.vo.request;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.amwell.ecar.utils.Base64Util;

public class ECarCommonQuery {

	private static final String appKey = "TEhBWTAwMDAxOTE2MTc=";

	private static final String appSecret = "B7B1CFC8DA5418BF054485823F2090EA";

	private static final String HMAC_SHA1 = "HmacSHA1";

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> buildRequestParams(Map<String, Object> paramMap) {
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.putAll(paramMap);
		params.put("appKey", appKey);
		params.put("nonce", UUID.randomUUID().toString());
		params.put("timestamp", System.currentTimeMillis());
		try {
			StringBuffer sb = new StringBuffer();
			Set es = params.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				Object v = entry.getValue();
				if (null != v && !"".equals(v)) {
					sb.append(k + "=" + v + "&");
				}
			}
			sb.deleteCharAt(sb.lastIndexOf("&"));
			// Base64编码
			String st64Encode = Base64Util.encode(sb.toString().getBytes("UTF-8"));
			Mac mac = Mac.getInstance(HMAC_SHA1);
			String key = appKey + appSecret;
			SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), mac.getAlgorithm());
			mac.init(secret);
			byte[] origin = mac.doFinal(st64Encode.getBytes("UTF-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			String sign = byteArrayToHexString(md.digest(origin));
			params.put("sign", sign);
			params.put("appSecret", appSecret);
			return params;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
