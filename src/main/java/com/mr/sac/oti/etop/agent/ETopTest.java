package com.mr.sac.oti.etop.agent;

import com.mr.framework.log.StaticLog;
import com.mr.sac.oti.OTIContainer;
import com.mr.sac.oti.bean.Message;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feng on 18-6-11
 */
public class ETopTest {

	static String TID = "C0MGAxXIT00glfJD";
	public static void main(String[] s) throws Exception {


		availableInsures();
//		queryVehicleCategoryConfig();
//		doRenewalCheck();
//		doRenewalConfirm();
//		doConfirmVehicleType();
//		doVerifyBjApplyVerifyCode();	//验证码

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
		params.put("tid", TID);
		Message message = EtopFacade.postDoRenewalConfirm(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 投保查询
	 * @throws Exception
	 */
	private static void doApplyQuery()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();
		OTIContainer otiContainer = EtopFacade.createOTIContainer();
		Message reqMessage = otiContainer.newMessage("panda.insure.applyQuery.request");

		Message vehicleInfoMessage = reqMessage.getField("vehicleInfo").getMessageTemplete().clone();
		vehicleInfoMessage.getField("carMark").setValue("京*");
		vehicleInfoMessage.getField("rackNo").setValue("L12345123451234512");
		vehicleInfoMessage.getField("engineNo").setValue("12345678");
		vehicleInfoMessage.getField("registerDate").setValue("2018-06-25");
		vehicleInfoMessage.getField("brandModel").setValue("昂科雷ENCLAVE 3.6L");
		vehicleInfoMessage.getField("fuelType").setValue("A");
		vehicleInfoMessage.getField("invoiceNo").setValue("132131321323");
		vehicleInfoMessage.getField("invoiceDate").setValue("2018-05-25");
		vehicleInfoMessage.getField("ifChangeOwner").setValue("0");
		vehicleInfoMessage.getField("vehilceCode").setValue("");
		params.put("vehicleInfo", vehicleInfoMessage);

		Message ownerInfoMessage = reqMessage.getField("ownerInfo").getMessageTemplete().clone();
		ownerInfoMessage.getField("carOwner").setValue("冯江");
		ownerInfoMessage.getField("certiType").setValue("01");
		ownerInfoMessage.getField("certiNo").setValue("14010719820625221X");
		ownerInfoMessage.getField("birthday").setValue("1982-06-25");
		ownerInfoMessage.getField("gender").setValue("");
		params.put("ownerInfo", ownerInfoMessage);

		Message insuredInfoMessage = reqMessage.getField("insuredInfo").getMessageTemplete().clone();
		insuredInfoMessage.getField("name").setValue("冯江");
		insuredInfoMessage.getField("certiType").setValue("01");
		insuredInfoMessage.getField("certiNo").setValue("14010719820625221X");
		insuredInfoMessage.getField("birthday").setValue("1982-06-25");
		insuredInfoMessage.getField("gender").setValue("");
		params.put("insuredInfo", insuredInfoMessage);

		Message applyInfoMessage = reqMessage.getField("applyInfo").getMessageTemplete().clone();
		applyInfoMessage.getField("forceBeginDate").setValue("2018-06-25");
		applyInfoMessage.getField("bizBeginDate").setValue("2018-06-25");
		params.put("applyInfo", applyInfoMessage);

		params.put("tid", TID);

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
		params.put("vehicleTypeCode", "AKI1009TYQ");
		params.put("tid", TID);
		Message message = EtopFacade.postConfirmVehicleType(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 保费计算
	 * @throws Exception
	 */
	private static void doApplyQuote()throws Exception{
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("tid", TID);
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
		params.put("tid", TID);
		Message message = EtopFacade.postVerifyBjApplyVerifyCode(params);
		StaticLog.info(message.toString());
	}


}
