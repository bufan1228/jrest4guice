package org.jrest4guice.rest.render;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jrest4guice.rest.JRestResult;
import org.jrest4guice.rest.annotations.PageFlow;

import com.google.inject.Inject;

/**
 * Veloction的视力渲染器
 * 
 * @author <a href="mailto:zhangyouqun@gmail.com">cnoss (QQ:86895156)</a>
 */
public class RedirectViewRender implements ViewRender {
	@Inject
	private HttpServletRequest request;
	@Inject
	protected HttpServletResponse response;

	/* (non-Javadoc)
	 * @see org.jrest4guice.rest.render.ViewRender#render(java.io.PrintWriter, org.jrest4guice.rest.annotations.PageFlow, org.jrest4guice.rest.JRestResult, boolean)
	 */
	@Override
	public void render(PrintWriter out, PageFlow annotation, JRestResult result,
			boolean cache) throws Exception {
		String path = annotation.success().value();
		String contextPath = request.getContextPath();
		if(!contextPath.endsWith("/"))
			contextPath += "/";
		if(path.startsWith("/"))
			path = path.substring(1);
			
		response.sendRedirect(contextPath+ path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jrest4guice.rest.render.ViewRender#getRenderType()
	 */
	@Override
	public String getRenderType() {
		return ResultType.REDIRECT;
	}

	@Override
	public String getRenderTypeShortName() {
		return null;
	}
}
