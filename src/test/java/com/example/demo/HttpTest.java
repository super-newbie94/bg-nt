package com.example.demo;




import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * User: GaoYuan
 * Date: 17/12/18
 * Time: 15:22
 */
public class HttpTest {
    public static void main(String[] args) throws IOException {
        HttpPost httpPost = new HttpPost("http://120.27.230.150:30010/DoPay.aspx");
        StringEntity stringEntity = new StringEntity("", ContentType.MULTIPART_FORM_DATA);
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentEncoding("multipart/form-data");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        response = httpClient.execute(httpPost);
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));

    }
}
