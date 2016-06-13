package com.xxhwap.common.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * 必须为Number异常类
 */
@SuppressWarnings("serial")
public class MustNumberException extends TemplateModelException {

	public MustNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a Integer.");
	}

}
