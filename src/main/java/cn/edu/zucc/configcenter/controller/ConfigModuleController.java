package cn.edu.zucc.configcenter.controller;

import cn.edu.zucc.configcenter.pojo.BeanConfigModule;
import cn.edu.zucc.configcenter.service.ConfigModuleService;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import cn.edu.zucc.configcenter.utils.resultformat.BeanResult;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author crabxyj
 * @date 2020/5/13 22:09
 */
@RestController
@CrossOrigin
@RequestMapping("/config/module")
public class ConfigModuleController {

    @Resource(name = "configModuleServiceImpl")
    private ConfigModuleService service;

    @RequestMapping("/loadPage")
    public BeanResult loadPage(
            @RequestParam(required = false) String moduleName,
            @RequestParam(required = false) String configKey,
            @RequestParam(required = false) String version,
            @RequestParam(defaultValue = "0") long curPage,
            @RequestParam(defaultValue = "20") long pageSize) {
        IPage<BeanConfigModule> ipage =  service.loadPage(moduleName, configKey, version, curPage, pageSize);
        return BeanResult.fromSuccess(ipage);
    }

    @RequestMapping("/add")
    public BeanResult add(
            @RequestParam String moduleName,
            @RequestParam String configKey,
            @RequestParam String configValue,
            @RequestParam String version) {
        BeanConfigModule module = new BeanConfigModule()
                .setModuleName(moduleName)
                .setConfigKey(configKey)
                .setConfigValue(configValue)
                .setVersion(version)
                .setCreateTime(new Date());
        System.out.println(JSON.toJSONString(module));
        try {
            service.add(module);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @RequestMapping("/del")
    public BeanResult del(String ids) {
        try {
            Set<Serializable> set = new HashSet<>();
            for(String s : ids.split(",")){
                set.add(Integer.valueOf(s));
            }
            set.remove("");
            service.delByIds(set);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @RequestMapping("/modify")
    public BeanResult modify(int id,String moduleName,
                             String configKey, String configValue, String version) {
        try {
            BeanConfigModule module = new BeanConfigModule()
                    .setId(id)
                    .setModuleName(moduleName)
                    .setConfigKey(configKey)
                    .setConfigValue(configValue)
                    .setVersion(version);
            service.modify(module);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @RequestMapping("/loadNames")
    public BeanResult loadModuleNames() {
        List<String> list = service.loadModuleNames();
        return BeanResult.fromSuccess(list);
    }
}
