package cn.edu.zucc.configcenter.controller;

import cn.edu.zucc.configcenter.pojo.BeanConfigModuleBase;
import cn.edu.zucc.configcenter.service.ConfigModuleBaseService;
import cn.edu.zucc.configcenter.utils.MU;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import cn.edu.zucc.configcenter.utils.resultformat.BeanResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author crabxyj
 * @date 2020/5/15 18:21
 */
@RestController
@CrossOrigin
@RequestMapping("/config/moduleBase")
public class ConfigModuleBaseController {
    @Resource(name = "configModuleBaseServiceImpl")
    private ConfigModuleBaseService service;

    @RequestMapping("/loadPage")
    public BeanResult loadPage(
            @RequestParam(required = false) String moduleName,
            @RequestParam(required = false) String version,
            @RequestParam(defaultValue = "0") long curPage,
            @RequestParam(defaultValue = "20") long pageSize) {
        IPage<BeanConfigModuleBase> ipage = service.loadPage(moduleName,version, curPage, pageSize);
        return BeanResult.fromSuccess(ipage);
    }


    @RequestMapping("/add")
    public BeanResult add(String moduleName, String version ,String baseIds) {
        baseIds = MU.str.rmDouble(baseIds,",");
        BeanConfigModuleBase moduleBase = new BeanConfigModuleBase()
                .setModuleName(moduleName)
                .setVersion(version)
                .setBaseIds(baseIds);
        try {
            service.add(moduleBase);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @RequestMapping("/modify")
    public BeanResult modify(String moduleName, String version ,String baseIds) {
        baseIds = MU.str.rmDouble(baseIds,",");
        BeanConfigModuleBase moduleBase = new BeanConfigModuleBase()
                .setModuleName(moduleName)
                .setVersion(version)
                .setBaseIds(baseIds);
        try {
            service.modify(moduleBase);
            return BeanResult.fromSuccess();
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }
}
