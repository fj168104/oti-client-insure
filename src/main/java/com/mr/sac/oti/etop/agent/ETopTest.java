package com.mr.sac.oti.etop.agent;

import com.mr.framework.crypto.digest.DigestUtil;
import com.mr.framework.log.StaticLog;
import com.mr.sac.oti.OTIContainer;
import com.mr.sac.oti.bean.Message;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feng on 18-6-11
 */
public class ETopTest {

	static String TID = "S0MHCQIkl03y0o0B";
	static String token = "9f89ece8475e9bdd3ad14540a05f8d3f";

	//京*
	public static void main(String[] s) throws Exception {


//		availableInsures();
//		queryVehicleCategoryConfig();

//		doRenewalCheck();
//		doApplyQuery();
//		Thread.sleep(1000);
//		doConfirmVehicleType(); 	//字段已经调整
//		doPostModifyVehicleInfo();
//		Thread.sleep(1000);
//		doApplyQuote();
//		Thread.sleep(1000);
//		doPostVerifyMediaSourceInfo();
//		doApplyAudit();

		doVerifyBjApplyVerifyCode();    //验证码
//		doPostCanQuoteInsures();
//		doPostQuerySpecialList();

//		doRenewalConfirm();


	}


	/**
	 * 可用保险公司信息查询
	 *
	 * @throws Exception
	 */
	private static void availableInsures() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("contractId", "4141");
		params.put("cityCode", "110000");
		Message message = EtopFacade.postAvailableInsures(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 车辆类型查询
	 *
	 * @throws Exception
	 */
	private static void queryVehicleCategoryConfig() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("vcCode", "1");
		Message message = EtopFacade.getQueryVehicleCategoryConfig(params);
		StaticLog.info(message.toString());
		StaticLog.info(message.getField("returnCode").toString());
		Message objectMessage = message.getField("data").getObjectMessage();
		StaticLog.info(objectMessage.getField("categoryCode").toString());
		List<Message> messages = objectMessage.getField("pcCategories").getArrayMessage();
		for (Message m : messages) {
			StaticLog.info(m.getField("name").toString());
		}
	}

	/**
	 * 续保检查
	 *
	 * @throws Exception
	 */
	private static void doRenewalCheck() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("carMark", "京*");
		params.put("cityCode", "110000");
		params.put("useType", "210");
		params.put("vehicleCategory", "A");
		params.put("carMarkType", "02");
		params.put("mediaSource", "byx");
		params.put("contractId", "4141");
		params.put("btVehicleCategory", "K33");
		params.put("pcVehicleCategory", "A0");

		Message message = EtopFacade.postDoRenewalCheck(params);
		TID = String.valueOf(message.getField("tid").getValue());
		StaticLog.info(message.toString());
	}

	/**
	 * 续保验证
	 *
	 * @throws Exception
	 */
	private static void doRenewalConfirm() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("certiShortNo", "290010");
		params.put("tid", TID);
		Message message = EtopFacade.postDoRenewalConfirm(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 投保查询
	 *
	 * @throws Exception
	 */
	private static void doApplyQuery() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		OTIContainer otiContainer = EtopFacade.createOTIContainer();
		Message reqMessage = otiContainer.newMessage("panda.insure.applyQuery.request");

		Message vehicleInfoMessage = reqMessage.getField("vehicleInfo").getMessageTemplete().clone();
		vehicleInfoMessage.getField("carMark").setValue("京*");
		vehicleInfoMessage.getField("rackNo").setValue("L12345123451234512");
		vehicleInfoMessage.getField("engineNo").setValue("12345678");
		vehicleInfoMessage.getField("registerDate").setValue("2018-07-01");
		vehicleInfoMessage.getField("brandModel").setValue("昂科雷ENCLAVE 3.6L");
		vehicleInfoMessage.getField("fuelType").setValue("A");
		vehicleInfoMessage.getField("invoiceNo").setValue("132131321323");
		vehicleInfoMessage.getField("invoiceDate").setValue("2018-06-25");
		vehicleInfoMessage.getField("ifChangeOwner").setValue("0");
		vehicleInfoMessage.getField("vehilceCode").setValue("SVW71810");
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
		applyInfoMessage.getField("forceBeginDate").setValue("2018-07-01");
		applyInfoMessage.getField("bizBeginDate").setValue("2018-07-01");
		params.put("applyInfo", applyInfoMessage);

		params.put("tid", TID);

		//参数设置
		Message message = EtopFacade.postApplyQuery(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 车型确认
	 *
	 * @throws Exception
	 */
	private static void doConfirmVehicleType() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("vehicleTypeCode", "AKI1009TYQ");
		params.put("tid", TID);
		Message message = EtopFacade.postConfirmVehicleType(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 车型信息确认
	 *
	 * @throws Exception
	 */
	private static void doPostModifyVehicleInfo() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("vehiclePrice", "559000");
		params.put("insureCode", "pingan");
		params.put("limitLoad", "0");
		params.put("seatNum", "5");
		params.put("poWeight", "10000");
		params.put("exhaustCapacity", "3300");
//		params.put("payTaxDept", "ttt");
//		params.put("payTaxId", "321321332132");
		params.put("tid", TID);
		Message message = EtopFacade.postModifyVehicleInfo(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 保费计算
	 *
	 * @throws Exception
	 */
	private static void doApplyQuote() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		OTIContainer otiContainer = EtopFacade.createOTIContainer();
		Message reqMessage = otiContainer.newMessage("panda.insure.applyQuote.request");

		List<Message> coverageList = new ArrayList<>();
		Message coverageMessage1 = reqMessage.getField("coverageList").getMessageTemplete().clone();
		coverageMessage1.getField("coverageCode").setValue("RC0001");
		coverageMessage1.getField("totalLimitAmount").setValue("150000");
		coverageList.add(coverageMessage1);
		Message coverageMessage2 = reqMessage.getField("coverageList").getMessageTemplete().clone();
		coverageMessage2.getField("coverageCode").setValue("RC0002");
		coverageMessage2.getField("totalLimitAmount").setValue("10000");
		coverageList.add(coverageMessage2);
		Message coverageMessage3 = reqMessage.getField("coverageList").getMessageTemplete().clone();
		coverageMessage3.getField("coverageCode").setValue("RC0005");
		coverageMessage3.getField("totalLimitAmount").setValue("100000");
		coverageList.add(coverageMessage3);

		params.put("coverageList", coverageList);
		params.put("packageName", "custom");
		params.put("isSelected", "1");
		params.put("applyBiz", true);
		params.put("applyForce", true);
//		params.put("bizBeginDate", "");
		params.put("payTaxFlag", "");
		params.put("payTaxDept", "");
		params.put("payTaxId", "");
		params.put("insureCode", "pingan");

		params.put("tid", TID);
		Message message = EtopFacade.postApplyQuote(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 第三方鉴权
	 *
	 * @throws Exception
	 */
	private static void doPostVerifyMediaSourceInfo() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("mediaSource", "byx");
		params.put("clientId", "126");
		params.put("clientSecret", DigestUtil.md5Hex("126" + "_" + "123456"));
		params.put("apiVersion", "1.0.0");
		params.put("tid", TID);
		Message message = EtopFacade.postVerifyMediaSourceInfo(params);
		token = String.valueOf(message.getField("token").getValue());
		StaticLog.info(message.toString());
	}

	/**
	 * 申请投保
	 *
	 * @throws Exception
	 */
	private static void doApplyAudit() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		OTIContainer otiContainer = EtopFacade.createOTIContainer();
		Message reqMessage = otiContainer.newMessage("panda.insure.applyAudit.request");
		Message ownerInfoMessage = reqMessage.getField("ownerInfo").getMessageTemplete().clone();
		ownerInfoMessage.getField("name").setValue("冯江");
		ownerInfoMessage.getField("certiType").setValue("01");
		ownerInfoMessage.getField("certiNo").setValue("14010719820625221X");
		ownerInfoMessage.getField("linkPhone").setValue("15502120393");
		ownerInfoMessage.getField("address").setValue("北京市朝阳区南京路300号");
		ownerInfoMessage.getField("relateType").setValue("1");
		ownerInfoMessage.getField("customerLevel").setValue("0");
		ownerInfoMessage.getField("email").setValue("fengj@jxltech.com");
		ownerInfoMessage.getField("nation").setValue("汉");
		ownerInfoMessage.getField("issuer").setValue("北京市车管局");
		ownerInfoMessage.getField("certiStartDate").setValue("2015-01-12");
		ownerInfoMessage.getField("certiEndDate").setValue("2035-01-12");
		params.put("ownerInfo", ownerInfoMessage);

		Message policyHolderInfoMessage = reqMessage.getField("policyHolderInfo").getMessageTemplete().clone();
		policyHolderInfoMessage.getField("name").setValue("冯江");
		policyHolderInfoMessage.getField("certiType").setValue("01");
		policyHolderInfoMessage.getField("certiNo").setValue("14010719820625221X");
		policyHolderInfoMessage.getField("linkPhone").setValue("15502120393");
		policyHolderInfoMessage.getField("address").setValue("北京市朝阳区南京路300号");
		policyHolderInfoMessage.getField("relateType").setValue("1");
		policyHolderInfoMessage.getField("customerLevel").setValue("0");
		ownerInfoMessage.getField("email").setValue("fengj@jxltech.com");
		policyHolderInfoMessage.getField("nation").setValue("汉");
		policyHolderInfoMessage.getField("issuer").setValue("北京市车管局");
		policyHolderInfoMessage.getField("certiStartDate").setValue("2015-01-12");
		policyHolderInfoMessage.getField("certiEndDate").setValue("2035-01-12");
		params.put("policyHolderInfo", policyHolderInfoMessage);

		Message insuredInfoMessage = reqMessage.getField("insuredInfo").getMessageTemplete().clone();
		insuredInfoMessage.getField("name").setValue("冯江");
		insuredInfoMessage.getField("certiType").setValue("01");
		insuredInfoMessage.getField("certiNo").setValue("14010719820625221X");
		insuredInfoMessage.getField("linkPhone").setValue("15502120393");
		insuredInfoMessage.getField("address").setValue("北京市朝阳区南京路300号");
		insuredInfoMessage.getField("relateType").setValue("1");
		insuredInfoMessage.getField("customerLevel").setValue("0");
		ownerInfoMessage.getField("email").setValue("fengj@jxltech.com");
		insuredInfoMessage.getField("nation").setValue("汉");
		insuredInfoMessage.getField("issuer").setValue("北京市车管局");
		insuredInfoMessage.getField("certiStartDate").setValue("2015-01-12");
		insuredInfoMessage.getField("certiEndDate").setValue("2035-01-12");
		params.put("insuredInfo", insuredInfoMessage);

		params.put("insureCode", "pingan");

		params.put("tid", TID);
		params.put("token", token);
		Message message = EtopFacade.postApplyAudit(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 补充投保手机验证码（北京机构）
	 *
	 * @throws Exception
	 */
	private static void doVerifyBjApplyVerifyCode() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("validateCode", "2F47DE");
		params.put("insureCode", "pingan");
		params.put("tid", TID);
		params.put("token", token);
		Message message = EtopFacade.postVerifyBjApplyVerifyCode(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 可报价保险公司查询
	 *
	 * @throws Exception
	 */
	private static void doPostCanQuoteInsures() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("contractId", "4141");
		params.put("cityCode", "110000");
		params.put("tid", TID);
		Message message = EtopFacade.postCanQuoteInsures(params);
		StaticLog.info(message.toString());
	}

	/**
	 * 特约查询
	 *
	 * @throws Exception
	 */
	private static void doPostQuerySpecialList() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		OTIContainer otiContainer = EtopFacade.createOTIContainer();
		Message reqMessage = otiContainer.newMessage("panda.insure.applyAudit.request");
		Message ownerInfoMessage = reqMessage.getField("ownerInfo").getMessageTemplete().clone();
		ownerInfoMessage.getField("name").setValue("冯江");
		ownerInfoMessage.getField("certiType").setValue("01");
		ownerInfoMessage.getField("certiNo").setValue("14010719820625221X");
		ownerInfoMessage.getField("linkPhone").setValue("15502120393");
		ownerInfoMessage.getField("address").setValue("北京市朝阳区南京路300号");
		ownerInfoMessage.getField("relateType").setValue("1");
		ownerInfoMessage.getField("customerLevel").setValue("0");
		params.put("ownerInfo", ownerInfoMessage);

		Message policyHolderInfoMessage = reqMessage.getField("policyHolderInfo").getMessageTemplete().clone();
		policyHolderInfoMessage.getField("name").setValue("冯江");
		policyHolderInfoMessage.getField("certiType").setValue("01");
		policyHolderInfoMessage.getField("certiNo").setValue("14010719820625221X");
		policyHolderInfoMessage.getField("linkPhone").setValue("15502120393");
		policyHolderInfoMessage.getField("address").setValue("北京市朝阳区南京路300号");
		policyHolderInfoMessage.getField("relateType").setValue("1");
		policyHolderInfoMessage.getField("customerLevel").setValue("0");
		params.put("policyHolderInfo", policyHolderInfoMessage);

		Message insuredInfoMessage = reqMessage.getField("insuredInfo").getMessageTemplete().clone();
		insuredInfoMessage.getField("name").setValue("冯江");
		insuredInfoMessage.getField("certiType").setValue("01");
		insuredInfoMessage.getField("certiNo").setValue("14010719820625221X");
		insuredInfoMessage.getField("linkPhone").setValue("15502120393");
		insuredInfoMessage.getField("address").setValue("北京市朝阳区南京路300号");
		insuredInfoMessage.getField("relateType").setValue("1");
		insuredInfoMessage.getField("customerLevel").setValue("0");
		params.put("insuredInfo", insuredInfoMessage);

		params.put("riskType", "force");
		params.put("insureCode", "pingan");
		params.put("tid", TID);
		Message message = EtopFacade.postQuerySpecialList(params);
		StaticLog.info(message.toString());
	}

}
