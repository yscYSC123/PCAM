package com.xxx.pcam.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     *
     * @param request
     * @return int
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr");
        if (StringUtils.isBlank(userIdString)) {
            return 0;
        }
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        return userId;
    }

    /**
     * 从cookie中获取userId
     *
     * @param request
     * @return int
     */
    public static String releaseUserNameFromCookie(HttpServletRequest request) {
        String userName = CookieUtil.getCookieValue(request, "userName");
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        return userName;
    }
}
