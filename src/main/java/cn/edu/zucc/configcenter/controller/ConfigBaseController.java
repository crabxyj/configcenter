package cn.edu.zucc.configcenter.controller;

import cn.edu.zucc.configcenter.pojo.BeanConfigBase;
import cn.edu.zucc.configcenter.service.ConfigBaseService;
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
import java.util.HashSet;
import java.util.Set;

/**
 * @author crabxyj
 * @date 2020/5/13 22:31
 */
@RestController
@CrossOrigin
@RequestMapping("/config/database")
public class ConfigBaseController {

    @Resource(name = "configBaseServiceImpl")
    private ConfigBaseService service;

    @RequestMapping("/loadPage")
    public BeanResult loadPage(
            @RequestParam(required = false) String baseName,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String url,
            @RequestParam(defaultValue = "0") long curPage,
            @RequestParam(defaultValue = "20") long pageSize) {
        IPage<BeanConfigBase> ipage = service.loadPage(baseName, type, url, curPage, pageSize);
        return BeanResult.fromSuccess(ipage);
    }

    @RequestMapping("/load")
    public BeanResult loadPage() {
        return BeanResult.fromSuccess(service.load());
    }

    @RequestMapping("/add")
    public BeanResult add(
            @RequestParam String baseName,
            @RequestParam String type,
            @RequestParam String url,
            @RequestParam String username,
            @RequestParam String password) {
        BeanConfigBase base = new BeanConfigBase()
                .setBaseName(baseName)
                .setType(type)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password);
        System.out.println(JSON.toJSONString(base));
        try {
            service.add(base);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @RequestMapping("/del")
    public BeanResult del(String ids) {
        try {
            Set<Serializable> set = new HashSet<>();
            for (String s : ids.split(",")) {
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
    public BeanResult modify(int baseId, String baseName,String type, String url, String username, String password) {
        try {
            BeanConfigBase base = new BeanConfigBase()
                    .setBaseId(baseId)
                    .setBaseName(baseName)
                    .setType(type)
                    .setUrl(url)
                    .setUsername(username)
                    .setPassword(password);
            service.modify(base);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }
}
