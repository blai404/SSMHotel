package io.github.blai44.interceptor.home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ǰ̨������
 * @author blai
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {

		Object admin = request.getSession().getAttribute("customer");
		if(admin == null){
			//��ʾδ��½���½ʧЧ
			String header = request.getHeader("X-Requested-With");
			//�ж��Ƿ���ajax����
			if("XMLHttpRequest".equals(header)){
				//��ʾ��ajax����
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "��½�Ự��ʱ�������µ�½��");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//��ʾ����ͨ������ת��ֱ���ض��򵽵�½ҳ��
			response.sendRedirect(request.getServletContext().getContextPath() + "/home/login");
			return false;
		}
		return true;
	}
}
