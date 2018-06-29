package com.mr.sac.oti.etop.agent;

import com.mr.framework.core.collection.CollectionUtil;
import com.mr.framework.core.util.RandomUtil;
import com.mr.framework.core.util.StrUtil;
import com.mr.framework.http.HttpRequest;
import com.mr.sac.oti.protocal.ProtocolAgent;
import org.apache.http.protocol.HTTP;

import java.util.Map;
import java.util.Objects;

/**
 * Created by feng on 18-6-10
 */
public class ETopAgent implements ProtocolAgent {
	private static String APPLICATION_JSON = "application/json";
	private int timeout = -1;
	private static int RANDOM_STRING_LENGTH = 32;
	private static String VERSION = "1.0.0";

	private Map<String, Object> paramMap;
	private String tid;
	private String token;

	/**
	 * POST请求
	 */
	public ETopAgent() {
	}

	public ETopAgent(String tid) {
		this.tid = tid;
	}

	public ETopAgent(String tid, String token){
		this.tid = tid;
		this.token = token;
	}

	/**
	 * POST请求
	 *
	 * @param timeout
	 */
	public ETopAgent(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * GET请求
	 *
	 * @param paramMap
	 */
	public ETopAgent(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	/**
	 * GET请求
	 *
	 * @param paramMap
	 */
	public ETopAgent(Map<String, Object> paramMap, int timeout) {
		this.paramMap = paramMap;
		this.timeout = timeout;
	}

	@Override
	public Object exchange(String endPoint, Object mObject) {
		String body = String.valueOf(mObject);

		//POST 请求
		if (Objects.isNull(paramMap)) {
			HttpRequest request = HttpRequest.post(endPoint)
					.header("requestId", RandomUtil.randomString(RANDOM_STRING_LENGTH))
					.header("version", VERSION)
					.header(HTTP.CONTENT_TYPE, APPLICATION_JSON);

			if (StrUtil.isNotEmpty(tid)) {
				request.header("tid", tid);
			}
			if (StrUtil.isNotEmpty(token)) {
				request.header("accToken", token);
			}

			return request.timeout(timeout).body(body).execute().body();
		} else {    //GET 请求
			return HttpRequest.get(endPoint + toUrlParamString(paramMap))
					.header("requestId", RandomUtil.randomString(RANDOM_STRING_LENGTH))
					.header("version", VERSION)
					.timeout(timeout)
					.execute()
					.body();
		}
	}

	private String toUrlParamString(Map<String, Object> paramMap) {
		StringBuffer sb = new StringBuffer();
		if (CollectionUtil.isNotEmpty(paramMap)) {
			sb.append("?");
			for (Map.Entry entry : paramMap.entrySet()) {
				if (!sb.toString().equals("?")) {
					sb.append("&");
				}
				sb.append(entry.getKey() + "=" + entry.getValue());
			}
		}
		return sb.toString();
	}

}
