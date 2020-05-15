package cn.edu.zucc.configcenter.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author crabxyj
 * @date 2020/5/14 13:40
 */
public class ConfigInfo {
    public static final String MODULE_NAME = "configcenter";
    public static final int MODULE_VERSION = 1;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${driver}")
    private String driver;

}
