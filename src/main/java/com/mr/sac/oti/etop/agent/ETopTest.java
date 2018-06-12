package com.mr.sac.oti.etop.agent;

import com.mr.framework.log.StaticLog;
import com.mr.sac.oti.bean.Message;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feng on 18-6-11
 */
public class ETopTest {


	public static void main(String[] s) throws Exception {
		availableInsures();
//		queryVehicleCategoryConfig();
	}

	/**
	 * 可用保险公司信息查询
	 * @throws Exception
	 */
	private static void availableInsures()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("contractId", "4141");
		params.put("cityCode", "110000");
		Message message = EtopFacade.postAvailableInsures(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 车辆类型查询
	 * @throws Exception
	 */
	private static void queryVehicleCategoryConfig()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("vcCode", "1");
		Message message = EtopFacade.getQueryVehicleCategoryConfig(params);
		StaticLog.info(message.toString());
		StaticLog.info(message.getField("returnCode").toString());
		Message objectMessage = message.getField("data").getObjectMessage();
		StaticLog.info(objectMessage.getField("categoryCode").toString());
		List<Message> messages = objectMessage.getField("pcCategories").getArrayMessage();
		for(Message m : messages){
			StaticLog.info(m.getField("name").toString());
		}

	}
}
