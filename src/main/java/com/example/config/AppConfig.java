// File: src/main/java/com/example/config/AppConfig.java
package com.example.config;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Namespace("/")
@Results({
        @Result(name = "success", location = "/WEB-INF/views/home.jsp")
})
public class AppConfig {

    @Action("")
    public String home() {
        return "success";
    }
}