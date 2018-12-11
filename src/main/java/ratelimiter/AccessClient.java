/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package ratelimiter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fenghao.xing
 * @version : AccessClient.java, v 0.1 2018-04-28 10:26 fenhao.xing Exp $
 */
public class AccessClient {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    /**
     * get����
     * @param realUrl
     * @return
     */
    public static String sendGet(URL realUrl) {
        String result = "";
        BufferedReader in = null;
        try {
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();

            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }



    public void access() throws Exception{
        final URL url = new URL("http://localhost:8080/guavalimitdemo/access");

        for(int i=0;i<10;i++) {
            fixedThreadPool.submit(new Runnable() {
                public void run() {
                    System.out.println(sendGet(url));
                }
            });
        }

        fixedThreadPool.shutdown();
        fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception{
        AccessClient accessClient = new AccessClient();
        accessClient.access();
    }
}
