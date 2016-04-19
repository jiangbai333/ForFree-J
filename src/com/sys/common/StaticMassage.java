package com.sys.common;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public final class StaticMassage {
	@SuppressWarnings("unchecked")
	public static Map errorType = new HashMap(){{
		put("MissingActoin", "缺少指定的控制器动作");
		put("MissingController", "缺少指定的控制器");
		put("missingErrorViewFile", "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>文件缺失</title><style type='text/css'>*{ padding: 0; margin: 0; }body{ background: #fff; font-family: '微软雅黑'; color: #333; font-size: 16px; }.system-message{ padding: 24px 48px; }.system-message h1{ font-size: 100px; font-weight: normal; line-height: 120px; margin-bottom: 12px; }.system-message .jump{ padding-top: 10px}.system-message .jump a{ color: #333;}.system-message .success,.system-message .error{ line-height: 1.8em; font-size: 36px }.system-message .detail{ font-size: 12px; line-height: 20px; margin-top: 12px; display:none}</style></head><body><div class='system-message'><h1>T _ T</h1><p class='error'>缺失错误信息描述文件</p><p class='detail'></p><p class='jump'>forfreej 在试图输出错误信息时, 无法在 webroot 中找到错误信息描述文件</p><p class='jump'>请在 webroot 目录下建立错误描述文件, 并以 error.html 命名</p><p class='jump'>这不是一个严重错误, 只会影响到您的调试, 不会影响到应用的正常运行与使用</p></div></body></html>");
	}};
}
