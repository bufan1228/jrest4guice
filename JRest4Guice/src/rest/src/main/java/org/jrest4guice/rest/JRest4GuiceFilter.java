package org.jrest4guice.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jrest4guice.client.ModelMap;
import org.jrest4guice.rest.context.RestContextManager;

public class JRest4GuiceFilter extends AbstractJRest4GuiceFilter {

	@Override
	protected void executeInit(FilterConfig config) throws ServletException {
	}

	@Override
	protected void executeFilter(HttpServletRequest hRequest,
			HttpServletResponse hResponse, FilterChain filterChain, String uri)
			throws IOException, ServletException {
		// REST资源的参数
		ModelMap<String, String> params = new ModelMap<String, String>();
		try {
			// 设置上下文中的环境变量
			RestContextManager.setContext(hRequest, hResponse, params);
			new JRest4GuiceProcessor().setUrlPrefix(this.urlPrefix).process(
					hRequest, hResponse);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// 清除上下文中的环境变量
			RestContextManager.clearContext();
		}
	}
}
