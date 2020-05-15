package cn.edu.zucc.configcenter.controller;

import cn.edu.zucc.configcenter.pojo.BeanConfigBase;
import cn.edu.zucc.configcenter.service.impl.ConfigDatabaseServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/13 22:31
 */
@RestController
@RequestMapping("/config/database")
public class ConfigDatabaseController {

    @Resource(name = "configDatabaseServiceImpl")
    private ConfigDatabaseServiceImpl service;

    @RequestMapping("/load")
    public List<BeanConfigBase> load(){
        return service.list();
    }

    @RequestMapping("/add")
    public String add(){
        return "0";
    }
}
