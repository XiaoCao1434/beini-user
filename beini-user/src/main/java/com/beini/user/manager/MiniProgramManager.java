package com.beini.user.manager;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.beini.core.enums.ResultEnum;
import com.beini.core.utils.HttpClientUtil;
import com.beini.core.utils.ResultVOUtil;
import com.beini.core.vo.MiniProgramLoginResultVO;
import com.beini.core.vo.MiniProgramLoginVO;
import com.beini.core.vo.ResultVO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * 小程序关联管理服务
 * 
 * @author lb_chen
 *
 */
@Service
@Slf4j
public class MiniProgramManager {
	/**
	 * {"session_key":"YD44T\/RjLkdSs9WMrh10Xw==","openid":"oF1135G37sLxVCPQC3-N_kZFR6a0"}
	 * 
	 * @param vo
	 *            请求VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultVO<String> jscode2session(MiniProgramLoginVO vo) {
		// 去微信公众平台请求获取参数
		MiniProgramLoginResultVO miniProgramLoginResultVO = new MiniProgramLoginResultVO();
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + vo.getAppid() + "&secret=" + vo.getSecret()
				+ "&js_code=" + vo.getJsCode() + "&grant_type=" + vo.getGrantType();
		String result = HttpClientUtil.requestForGet(url);
		miniProgramLoginResultVO = new Gson().fromJson(result, MiniProgramLoginResultVO.class);
		System.out.println(miniProgramLoginResultVO);
		/* 结果对象转化为空 */
		if (miniProgramLoginResultVO == null) {
			log.info("请求结果为空对象");
			return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
		}
		/* sessionKey和openId不为空，返回正常 */
		else if (StringUtils.isNotEmpty(miniProgramLoginResultVO.getSession_key())
				&& StringUtils.isNotEmpty(miniProgramLoginResultVO.getOpenid())) {
			log.info("请求结果包含openId和sessionKey");
			return ResultVOUtil.success(miniProgramLoginResultVO.getOpenid());
		}
		/* openId不为空，返回正常 */
		else if (StringUtils.isNotEmpty(miniProgramLoginResultVO.getOpenid())) {
			log.info("请求结果只包含openId");
			return ResultVOUtil.success(miniProgramLoginResultVO.getOpenid());
		}
		/* errorCode不为空，返回失败信息 */
		else if (StringUtils.isNotEmpty("" + miniProgramLoginResultVO.getErrcode())) {
			log.info("errorCode不为空");
			return ResultVOUtil.error(miniProgramLoginResultVO.getErrcode(), miniProgramLoginResultVO.getErrmsg());
		} else {
			log.info("其他异常信息");
			return ResultVOUtil.error(ResultEnum.PROGRAM_ERROR);
		}
	}

}
