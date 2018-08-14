package com.yml.framework;

import com.yml.framework.bean.Data;
import com.yml.framework.bean.Handler;
import com.yml.framework.bean.Param;
import com.yml.framework.bean.View;
import com.yml.framework.helper.*;
import com.yml.framework.util.JsonUtil;
import com.yml.framework.util.ReflectionUtil;
import com.yml.framework.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-07
 * 时间： 22:25
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关Helper类
        HelperLoader.init();
        //获取ServletContext 对象(用于注册Servlet)
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request, response);
        try {
            //获取请求的方法和路径
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();
            if (requestPath.equals("/favicon.ico")) {
                return;
            }
            //获取RequestMapping处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
                //获取Controller类及其Bean实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);
                //创建请求参数对象
                Param param;
                if (UploadHelper.isMultipart(request)) {
                    param = UploadHelper.createParam(request);
                } else {
                    param = RequestHelper.createParam(request);
                }
                //调用RequestMapping方法
                Object result;
                Method requestMappingMethod = handler.getRequestMappingMethod();
                if (param.isEmpty()) {
                    result = ReflectionUtil.invokeMethod(controllerBean, requestMappingMethod);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, requestMappingMethod, param);
                }
                //处理requestMapping 方法的返回值
                if (result instanceof View) {
                    handleViewResult(request, response, (View) result);
                } else if (result instanceof Data) {
                    //返回JSON数据
                    handleDataResult(response, (Data) result);
                }
            }
        }finally {
            ServletHelper.destory();
        }

    }

    private void handleViewResult(HttpServletRequest request, HttpServletResponse response, View result) throws IOException, ServletException {
        View view = result;
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }

    private void handleDataResult(HttpServletResponse response, Data result) throws IOException {
        Data data = result;
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
