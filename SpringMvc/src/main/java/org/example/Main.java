package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.Config.WebConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat=new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        String path="";
        String baseDoc=new File("src/main/Webapp").getAbsolutePath();
        Context context= tomcat.addContext(path,baseDoc);
        AnnotationConfigWebApplicationContext spring=
                new AnnotationConfigWebApplicationContext();
        spring.register(WebConfig.class);
        DispatcherServlet dispatcherServlet=
                new DispatcherServlet( spring);
        tomcat.addServlet(
                context,"dispatcherServlet",dispatcherServlet);
        context.addServletMappingDecoded(
                "/","dispatcherServlet");
        tomcat.start();
        System.out.println("hello Erripuka");
        tomcat.getServer().await();
    }
}