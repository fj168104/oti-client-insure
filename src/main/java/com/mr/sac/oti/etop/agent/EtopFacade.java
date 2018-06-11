package com.mr.sac.oti.etop.agent;

import com.mr.framework.log.StaticLog;
import com.mr.sac.oti.OTIContainer;
import com.mr.sac.oti.Transaction;
import com.mr.sac.oti.bean.Message;

import java.util.HashMap;
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
			"panda.insure.applyQuery.requset", "panda.insure.applyQuery.response",
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
	 * 创建 OTIContainer
	 *
	 * @return OTIContainer
	 */
	private static OTIContainer createOTIContainer() {

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
