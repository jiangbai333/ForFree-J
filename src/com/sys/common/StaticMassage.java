package com.sys.common;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public final class StaticMassage {
	@SuppressWarnings("unchecked")
	public static Map errorType = new HashMap(){{
		put("MissingActoin", "ȱ��ָ���Ŀ���������");
		put("MissingController", "ȱ��ָ���Ŀ�����");
		put("missingErrorViewFile", "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>�ļ�ȱʧ</title><style type='text/css'>*{ padding: 0; margin: 0; }body{ background: #fff; font-family: '΢���ź�'; color: #333; font-size: 16px; }.system-message{ padding: 24px 48px; }.system-message h1{ font-size: 100px; font-weight: normal; line-height: 120px; margin-bottom: 12px; }.system-message .jump{ padding-top: 10px}.system-message .jump a{ color: #333;}.system-message .success,.system-message .error{ line-height: 1.8em; font-size: 36px }.system-message .detail{ font-size: 12px; line-height: 20px; margin-top: 12px; display:none}</style></head><body><div class='system-message'><h1>T _ T</h1><p class='error'>ȱʧ������Ϣ�����ļ�</p><p class='detail'></p><p class='jump'>forfreej ����ͼ���������Ϣʱ, �޷��� webroot ���ҵ�������Ϣ�����ļ�</p><p class='jump'>���� webroot Ŀ¼�½������������ļ�, ���� error.html ����</p><p class='jump'>�ⲻ��һ�����ش���, ֻ��Ӱ�쵽���ĵ���, ����Ӱ�쵽Ӧ�õ�����������ʹ��</p></div></body></html>");
	}};
}
