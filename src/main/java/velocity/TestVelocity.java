/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fenghao.xing
 * @version : TestVelocity.java, v 0.1 2018-04-09 20:23 fenhao.xing Exp $
 */
public class TestVelocity {
    public static void main(String[] args) {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        // 获取模板文件
        org.apache.velocity.Template t = null;
        try {
            ve.init();
            t = ve.getTemplate("hellovelocity.vm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置变量
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "Velocity");
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        ctx.put("list", list);
        // 输出
        StringWriter sw = new StringWriter();
        try {
            t.merge(ctx,sw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sw.toString());
    }
}
