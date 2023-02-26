package com.example.demo.controller;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class DemoServiceImpl {
    public String sendHttp() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("www.baidu.com")
                .method("GET", body)
                .addHeader("Cookie", "BAIDUID=0A0FE8B1BB234DB4ABD93EE8A56041FE:FG=1; BIDUPSID=0A0FE8B1BB234DB41833D81DF6E53649; H_PS_PSSID=36558_38091_38125_38116_38144_38264_38175_38171_38226_36805_38034_37920_26350_38119_38099_38008_37881; PSTM=1677370704; BDSVRTM=40; BD_HOME=1")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().toString();
    }

}
