package com.mr.sac.oti.etop.agent;

import com.mr.framework.log.StaticLog;
import com.mr.sac.oti.OTIContainer;
import com.mr.sac.oti.Transaction;
import com.mr.sac.oti.bean.Message;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by feng on 18-6-10
 */
public class EtopFacade {

	private static String[] messages = {
			"panda.query.availableInsures.request", "panda.query.availableInsures.response",
			"panda.insure.queryVehicleCategoryConfig.request", "panda.insure.queryVehicleCategoryConfig.response",
			"panda.insure.doRenewalCheck.request", "panda.insure.doRenewalCheck.response",
			"panda.insure.doRenewalConfirm.request", "panda.insure.doRenewalConfirm.response",
			"panda.insure.applyQuery.request", "panda.insure.applyQuery.response",
			"panda.insure.confirmVehicleType.request", "panda.insure.confirmVehicleType.response",
			"panda.insure.applyQuote.request", "panda.insure.applyQuote.response",
			"panda.insure.applyAudit.request", "panda.insure.applyAudit.response",
			"panda.insure.verifyBjApplyVerifyCode.request", "panda.insure.verifyBjApplyVerifyCode.response",
			"panda.insure.resendBjApplyVerifySMS.request", "panda.insure.resendBjApplyVerifySMS.response",
			"panda.insure.modifyVehicleInfo.request", "panda.insure.modifyVehicleInfo.response",
			"panda.insure.canQuoteInsures.request", "panda.insure.canQuoteInsures.response",
			"panda.insure.querySpecialList.request", "panda.insure.querySpecialList.response",
			"panda.insure.getOptionalSpecials.request", "panda.insure.getOptionalSpecials.response",
			"panda.insure.addOptionalSpecial.request", "panda.insure.addOptionalSpecial.response",
			"panda.insure.modifyOptionalSpecial.request", "panda.insure.modifyOptionalSpecial.response",
			"panda.insure.deleteOptionalSpecial.request", "panda.insure.deleteOptionalSpecial.response",
			"panda.insure.verifyMediaSourceInfo.request", "panda.insure.verifyMediaSourceInfo.response"
	};

	private static String ETOP_SERVICE_PRE = "http://panda.etoppaas.com.cn/api/service/";

	/**
	 * <p></>/panda.query.availableInsures
	 * 可用保险公司信息查询
	 * params
	 * contractId 合同号
	 * cityCode 城市代码
	 *
	 * @return
	 */
	public static Message postAvailableInsures(Map<String, Object> params) throws Exception {
		OTIContainer otiContainer = createOTIContainer();

		//外部导入Parser（JsonParser）,可以自定义解析器
		Transaction transaction = otiContainer.newTransaction("panda.query.availableInsures.request",
				"panda.query.availableInsures.response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + "panda.query.availableInsures");
	}

	/**
	 * <p></>/panda.insure.queryVehicleCategoryConfig
	 * 车辆类型查询
	 * params
	 * vcCode 车辆大类代码
	 *
	 * @return
	 */
	public static Message getQueryVehicleCategoryConfig(Map<String, Object> params) throws Exception {
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction("panda.insure.queryVehicleCategoryConfig.request",
				"panda.insure.queryVehicleCategoryConfig.response",
				new ETopAgent(params));

		return action(transaction, params, ETOP_SERVICE_PRE + "panda.insure.queryVehicleCategoryConfig");
	}


	/**
	 * <p></>/panda.insure.doRenewalCheck
	 * 续保检查
	 * params
	 * carMark 车牌号
	 * cityCode 城市代码
	 * useType 使用性质
	 * vehicleCategory 车辆种类
	 * carMarkType 号牌种类
	 * mediaSource 媒体来源
	 * contractId 合同号
	 * btVehicleCategory 交管车辆类型
	 * pcVehicleCategory 条款车辆类型
	 *
	 * @return
	 */
	public static Message postDoRenewalCheck(Map<String, Object> params) throws Exception {
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction("panda.insure.doRenewalCheck.request",
				"panda.insure.doRenewalCheck.response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + "panda.insure.doRenewalCheck");
	}

	/**
	 * <p></>/panda.insure.doRenewalConfirm
	 * 续保验证
	 * params
	 * certiShortNo 证件号码后六位
	 *
	 * @return
	 */
	public static Message postDoRenewalConfirm(Map<String, Object> params) throws Exception {
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction("panda.insure.doRenewalConfirm.request",
				"panda.insure.doRenewalConfirm.response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + "panda.insure.doRenewalConfirm");
	}

	/**
	 * <p></>/panda.insure.applyQuery
	 * 投保查询
	 * params
	 * vehicleInfo 车辆信息 Object
	 * ownerInfo 车主信息 Object
	 * insuredInfo 被保人信息 Object
	 * applyInfo 投保信息 Object
	 * isUseRenewalInfo 是否使用续保数据（0：否	 1：是）
	 *
	 *
	 * @return
	 */
	public static Message postApplyQuery(Map<String, Object> params) throws Exception {
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction("panda.insure.applyQuery.request",
				"panda.insure.applyQuery.response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + "panda.insure.applyQuery");
	}


	/**
	 * <p></>/panda.insure.confirmVehicleType
	 * 车型确认
	 * params
	 * vehicleTypeCode 车型代码
	 *
	 * @return
	 */
	public static Message postConfirmVehicleType(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.confirmVehicleType";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}


	/**
	 * <p></>/panda.insure.applyQuote
	 * 保费计算
	 * params
	 * coverageList 险别列表 List
	 * packageName 套餐名称
	 * isSelected 是否最终确认的套餐
	 * applyBiz 是否投保商业险
	 * applyForce 是否投保交强险
	 * bizBeginDate 商业保险起期
	 * forceBeginDate 交强保险起期
	 * payTaxFlag 完税标示
	 * payTaxDept 完税机关
	 * payTaxId 完税凭证号
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postApplyQuote(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.applyQuote";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.verifyMediaSourceInfo
	 * 第三方鉴权
	 * params
	 * mediaSource 媒体来源
	 * clientId 客户端 ID
	 * clientSecret MD5(客户端 id+”_”+客户端密钥)
	 * apiVersion 接口版本号(1.0.0)
	 *
	 *
	 * @return
	 */
	public static Message postVerifyMediaSourceInfo(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.verifyMediaSourceInfo";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.applyAudit
	 * 申请投保
	 * params
	 * ownerInfo 车主信息 Object
	 * policyHolderInfo 投保人信息 Object
	 * insuredInfo 被保人信息 Object
	 * insureCode 保险公司代码
	 * issueCode 北京承保验证码
	 * signatureImgData 电子签名 Base64字符串
	 * isForceEPolicy 是否需要交强电子保单
	 * isBizEPolicy 是否需要商业电子保单
	 *
	 *
	 * @return
	 */
	public static Message postApplyAudit(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.applyAudit";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid")), String.valueOf(params.get("token"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.verifyBjApplyVerifyCode
	 * 补充投保手机验证码（北京机构）
	 * params
	 * validateCode 验证码
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postVerifyBjApplyVerifyCode(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.verifyBjApplyVerifyCode";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.resendBjApplyVerifySMS
	 * 重发投保手机验证码（北京机构）
	 * params
	 * mobilePhone 投保手机号
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postResendBjApplyVerifySMS(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.resendBjApplyVerifySMS";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.modifyVehicleInfo
	 * 车型信息确认
	 * params
	 * vehiclePrice 新车购置价
	 * insureCode 保险公司代码
	 * limitLoad 核定载质量
	 * seatNum 核定载客
	 * poWeight 整备质量
	 * exhaustCapacity 排量
	 * payTaxDept 完税机关
	 * payTaxId 完税凭证号
	 *
	 *
	 * @return
	 */
	public static Message postModifyVehicleInfo(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.modifyVehicleInfo";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.canQuoteInsures
	 * 可报价保险公司查询
	 * params
	 * contractId 合同号
	 * cityCode 城市代码
	 *
	 *
	 * @return
	 */
	public static Message postCanQuoteInsures(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.canQuoteInsures";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.querySpecialList
	 * 特约查询
	 * params
	 * tid 交易ID
	 * riskType 险种类别 biz：商业险；force：交强
	 * ownerInfo 车主信息 Object
	 * policyHolderInfo 投保人信息 Object
	 * insuredInfo 被保人信息 Object
	 * insureCode 保险公司代码
	 *
	 * @return
	 */
	public static Message postQuerySpecialList(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.querySpecialList";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent(String.valueOf(params.get("tid"))));

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}


	/**
	 * <p></>/panda.insure.getOptionalSpecials
	 * 可选特约查询
	 * params
	 * tid 交易ID
	 * currentPages 当前页码
	 * pageSize 每页大小
	 * riskType 险种类别 biz：商业险；force：交强
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postGetOptionalSpecials(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.getOptionalSpecials";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.addOptionalSpecial
	 * 交易增加可选特约信息
	 * params
	 * tid 交易ID
	 * specialNo 特约代码
	 * specialContent 特约内容
	 * param1 特约参数1
	 * param2 特约参数2
	 * param3 特约参数3
	 * param4 特约参数4
	 * param5 特约参数5
	 * riskType 险种类别 biz：商业险；force：交强
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postAddOptionalSpecial(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.addOptionalSpecial";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.modifyOptionalSpecial
	 * 交易修改可选特约信息
	 * params
	 * tid 交易ID
	 * specialNo 特约代码
	 * specialContent 特约内容
	 * param1 特约参数1
	 * param2 特约参数2
	 * param3 特约参数3
	 * param4 特约参数4
	 * param5 特约参数5
	 * riskType 险种类别 biz：商业险；force：交强
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postModifyOptionalSpecial(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.modifyOptionalSpecial";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * <p></>/panda.insure.deleteOptionalSpecial
	 * 交易删除可选特约信息
	 * params
	 * tid 交易ID
	 * specialNo 特约代码
	 * riskType 险种类别 biz：商业险；force：交强
	 * insureCode 保险公司代码
	 *
	 *
	 * @return
	 */
	public static Message postDeleteOptionalSpecial(Map<String, Object> params) throws Exception {
		String messagePrefix = "panda.insure.deleteOptionalSpecial";
		OTIContainer otiContainer = createOTIContainer();

		Transaction transaction = otiContainer.newTransaction(messagePrefix + ".request",
				messagePrefix + ".response",
				new ETopAgent());

		return action(transaction, params, ETOP_SERVICE_PRE + messagePrefix);
	}

	/**
	 * 创建 OTIContainer
	 *
	 * @return OTIContainer
	 */
	public static OTIContainer createOTIContainer() {

		//获取Transaction实例
		OTIContainer otiContainer = OTIContainer.getInstance();
		//载入配置
		otiContainer.loadRemoteConfiguration(messages);
		return otiContainer;
	}

	/**
	 * 组包、发送、解包
	 *
	 * @param transaction
	 * @throws Exception
	 */
	private static Message action(Transaction transaction, Map<String, Object> params, String endPoint) throws Exception {
		transaction.addParams(params);
		if (transaction.communicate(endPoint)) {
			StaticLog.info(transaction.getResponseMessage().toString());
		} else {
			StaticLog.error(transaction.getExceptionInfo());
		}
		return transaction.getResponseMessage();
	}

}
