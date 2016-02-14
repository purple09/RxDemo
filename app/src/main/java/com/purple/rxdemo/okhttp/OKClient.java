package com.purple.rxdemo.okhttp;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OKClient {

    public static final MediaType JSON = MediaType.parse("application/json; charset=" + HttpUtils.DEFAULT_CHARSET);
    public static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");

    private static OkHttpClient client;

    public static OKClient instance() {
        return new OKClient();
    }

    private OKClient() {
        if (client == null) {
            client = new OkHttpClient();
            client.setConnectTimeout(HttpUtils.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
            client.setReadTimeout(HttpUtils.READ_TIMEOUT, TimeUnit.MILLISECONDS);
            client.setWriteTimeout(HttpUtils.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        }
    }

    public Response execute(HttpMethod method, String url) throws Exception {
        return execute(method, url, null, null, null);
    }

    public Response execute(HttpMethod method, String url, List<NameValuePair> params, Map<String, String> headers,
                            String body) throws Exception {
        return execute(method, url, params, headers, body, true);
    }

    public Response execute(HttpMethod method, String url, List<NameValuePair> params, Map<String, String> headers,
                            String body, boolean json) throws Exception {

        try {
            HttpUtils.log(method, url, params, headers, body);

            String paramString = "";
            if (params != null) {
                paramString = "?" + HttpUtils.format(params, HttpUtils.DEFAULT_CHARSET);
            }

            Request.Builder build = new Request.Builder().url(url + paramString);

            if (headers != null) {
                for (String key : headers.keySet()) {
                    build.addHeader(key, headers.get(key));
                }
            }

            RequestBody requestBody = null;
            if (body != null) {
                if (json) {
                    requestBody = RequestBody.create(JSON, body);
                } else {
                    requestBody = RequestBody.create(FORM, body);
                }
            }
            build = build.method(method.toString(), requestBody);

            Request request = build.build();

            return client.newCall(request).execute();

        } catch (Exception e) {
            throw e;
        }
    }

}
