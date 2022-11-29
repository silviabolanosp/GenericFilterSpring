package com.pgd.recruitingplatformservice.interceptor;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
public class RecruitingControllerInterceptor implements HandlerInterceptor {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";

    /**
     * Intance of Logger to manage log.info data.
     */
    private final Logger logger =
            LoggerFactory
                    .getLogger(RecruitingControllerInterceptor.class);

    /**
     * Override of preHandle to personalize HandlerInterceptor.
     * @param request from call of api.
     * @param response after process request.
     * @param handler managed by override.
     * @return always true.
     * @throws Exception managed by override.
     */
    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler
    ) throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        logger.info(
                "--> RecruitingControllerInterceptor preHandle (End) <--"
        );
        return true;
    }

    /**
     * Override of preHandle to personalize HandlerInterceptor.
     * @param request from call of api.
     * @param response after process request.
     * @param handler managed by override.
     * @param modelAndView managed by override.
     * @throws Exception managed by override.
     */
    @Override
    public void postHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler,
            final ModelAndView modelAndView
    ) throws Exception {
        logger.info(
                "--> RecruitingControllerInterceptor postHandle (End) <--"
        );
    }

    /**
     * Override of preHandle to personalize HandlerInterceptor.
     * @param request  from call of api.
     * @param response after process request.
     * @param handler managed by override.
     * @param ex managed by override.
     * @throws Exception managed by override.
     */
    @Override
    public void afterCompletion(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler,
            final Exception ex
    ) throws Exception {

        long endTime = System.currentTimeMillis();
        long startTime = Long.parseLong(request.getAttribute("startTime") + "");

        JSONObject infoObject = new JSONObject();
        infoObject.put("remote_addres", getStringDecorated(request.getRemoteAddr()));
        infoObject.put("accessed_resource", getStringDecorated(request.getRequestURI()));
        infoObject.put("current_time", getStringDecorated(getCurrentTime()));
        infoObject.put("total_time_taken(ms)", getStringDecorated( String.valueOf (endTime - startTime) ));
        infoObject.put("status_code", getStringDecorated(String.valueOf(response.getStatus())));

        var jsonInfo = infoObject.toString();

        logger.info(
                jsonInfo
        );
        logger.info(
                "--> RecruitingControllerInterceptor afterCompletion(End) <--"
        );

    }
    /**
     * get the current server time, and format it with an ISO 8601
     * @Refer to <a href="https://docs.microsoft.com/en-us/azure/data-explorer/kusto/query/scalar-data-types/datetime">Supported formats</a>
     */
    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }

    private String getStringDecorated(String value){
        final String INIT_DELIMITER = "[";
        final String END_DELIMITER  = "]";

        return INIT_DELIMITER.concat(value).concat(END_DELIMITER);
    }

}
