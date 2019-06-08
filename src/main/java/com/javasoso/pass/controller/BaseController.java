package com.javasoso.pass.controller;

import com.javasoso.pass.constant.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 基础Controller
 */
public class BaseController {
    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected <T> ResultModel<T> buildErrorResponse(String msg) {
		ResultModel<T> model = new ResultModel<T>();
		model.setCode(ResultModel.RESULT_ERROR);
		model.setMsg(msg);
		return model;
	}

	protected <T> ResultModel<T> buildErrorResponse(String msg, T obj) {
		ResultModel<T> model = new ResultModel<T>();
		model.setCode(ResultModel.RESULT_ERROR);
		model.setMsg(msg);
		model.setData(obj);
		return model;
	}


	protected <T> ResultModel<T> buildLimitResponse() {
		return buildLimitResponse("访问过于频繁，请稍后再试!");
	}
	protected <T> ResultModel<T> buildLimitResponse(String msg) {
		ResultModel<T> model = new ResultModel<T>();
		model.setCode(ResultModel.RESULT_NOPERMISSION_LIMIT);
		model.setMsg(msg);
		return model;
	}
	protected <T> ResultModel<T> buildAuthInvalidResponse(String msg) {
		ResultModel<T> model = new ResultModel<T>();
		model.setCode(ResultModel.RESULT_AUTH_INVALID);
		model.setMsg(msg);
		return model;
	}

	protected <T> ResultModel<T> buildSuccessResponse() {
		return buildSuccessResponse(null);
	}
	protected <T> ResultModel<T> buildSuccessResponse(T obj) {
		ResultModel<T> model = new ResultModel<T>();
		model.setCode(ResultModel.RESULT_SUCCESS);
		model.setMsg("成功");
		model.setData(obj);
		return model;
	}

	protected boolean isMoblieClient(HttpServletRequest request) {
		boolean flag = false;
		String agent = request.getHeader("user-agent");
		String[] keywords = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"};
		if (!agent.contains("Windows NT") ||
				(agent.contains("Windows NT") && agent.contains("compatible; MSIE 9.0;"))) {
			if (!agent.contains("Windows NT") && !agent.contains("Macintosh")) {
				for (String item : keywords) {
					if (agent.contains(item)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 通用框架校验异常处理
	 * @param res
	 * @return
	 */
	ResultModel commonValidate(BindingResult res) {
		if (res.getErrorCount() > 0) {
			StringBuilder errorMsg = new StringBuilder();
			res.getFieldErrors().forEach(error -> errorMsg.append("[").append(error.getDefaultMessage()).append("]"));
			return buildErrorResponse(errorMsg.toString());
		}
		return null;
	}

}
