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
//		availableInsures();
//		queryVehicleCategoryConfig();
//		doRenewalCheck();
//		doRenewalConfirm();
//		doConfirmVehicleType();
		doVerifyBjApplyVerifyCode();

		//TODO
//		doApplyQuery();
//		doApplyQuote();
//		doApplyAudit();

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

	/**
	 * 续保检查
	 * @throws Exception
	 */
	private static void doRenewalCheck()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("carMark", "京GQQ822");
		params.put("cityCode", "110000");
		params.put("useType", "210");
		params.put("vehicleCategory", "A");
		params.put("carMarkType", "02");
		params.put("mediaSource", "byx");
		params.put("contractId", "4141");
		params.put("btVehicleCategory", "K33");
		params.put("pcVehicleCategory", "A0");

		Message message = EtopFacade.postDoRenewalCheck(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 续保验证
	 * @throws Exception
	 */
	private static void doRenewalConfirm()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("certiShortNo", "290010");
		params.put("tid", "D0MGW2Uwe03eVspJ");
		Message message = EtopFacade.postDoRenewalConfirm(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 投保查询
	 * @throws Exception
	 */
	private static void doApplyQuery()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();

		//参数设置
		Message message = EtopFacade.postApplyQuery(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 车型确认
	 * @throws Exception
	 */
	private static void doConfirmVehicleType()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("vehicleTypeCode", "K33");
		params.put("tid", "D0MGW2Uwe03eVspJ");
		Message message = EtopFacade.postConfirmVehicleType(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 保费计算
	 * @throws Exception
	 */
	private static void doApplyQuote()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("tid", "D0MGW2Uwe03eVspJ");
		Message message = EtopFacade.postApplyQuote(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 申请投保
	 * @throws Exception
	 */
	private static void doApplyAudit()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("tid", "D0MGW2Uwe03eVspJ");
		Message message = EtopFacade.postApplyAudit(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 补充投保手机验证码（北京机构）
	 * @throws Exception
	 */
	private static void doVerifyBjApplyVerifyCode()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("validateCode", "000000");
		params.put("insureCode", "pingan");
		params.put("tid", "D0MGW2Uwe03eVspJ");
		Message message = EtopFacade.postVerifyBjApplyVerifyCode(params);
		StaticLog.info(message.toString());
	}


}
