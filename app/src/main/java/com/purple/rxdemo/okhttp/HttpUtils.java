package com.purple.rxdemo.okhttp;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Description: 工具类
 * @Copyright: Copyright (c) 2015 chexiang.com. All right reserved.
 * @Author: zhangshunjie
 * @Date: 2015/11/17
 * @Modifier: zhangshunjie
 * @Update: 2015/11/17
 */
public class HttpUtils {

    public static final String TAG = "OKClient";

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int CONNECT_TIMEOUT = 15000;
    public static final int READ_TIMEOUT = 15000;
    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";

    /**
     * Returns a String that is suitable for use as an <code>application/x-www-form-urlencoded</code>
     * list of parameters in an HTTP PUT or HTTP POST.
     *
     * @param parameters The parameters to include.
     * @param encoding   The encoding to use.
     */
    public static String format(
            final List<? extends NameValuePair> parameters,
            final String encoding) {
        final StringBuilder result = new StringBuilder();
        for (final NameValuePair parameter : parameters) {
            final String encodedName = encode(parameter.getName(), encoding);
            final String value = parameter.getValue();
            final String encodedValue = value != null ? encode(value, encoding) : "";
            if (result.length() > 0)
                result.append(PARAMETER_SEPARATOR);
            result.append(encodedName);
            result.append(NAME_VALUE_SEPARATOR);
            result.append(encodedValue);
        }
        return result.toString();
    }

    private static String encode(final String content, final String encoding) {
        try {
            return URLEncoder.encode(content,
                    encoding != null ? encoding : DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    public static void log(HttpMethod method, String url, List<NameValuePair> params, Map<String, String> headers,
                           String body) {
        debug("[" + Thread.currentThread().getName() + "]" + "================");
        debug("method:" + method);
        debug("url:" + url);

        if (params != null) {
            debug("params:");
            for (NameValuePair np : params) {
                debug(np.getName() + ":" + np.getValue());
            }
        }

        if (headers != null) {
            debug("header:");
            for (String np : headers.keySet()) {
                debug(np + ":" + headers.get(np));
            }
        }

        if (body != null) {
            debug("body:" + body);
        }
    }

    public static void debug(String msg) {
        Log.d(TAG, msg);
    }

    public static void error(Throwable t) {
        Log.e(TAG, null, t);
    }

}
