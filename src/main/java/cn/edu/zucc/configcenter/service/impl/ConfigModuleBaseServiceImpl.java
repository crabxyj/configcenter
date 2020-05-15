package cn.edu.zucc.configcenter.service.impl;

import cn.edu.zucc.configcenter.dao.ConfigBaseDao;
import cn.edu.zucc.configcenter.dao.ConfigModuleBaseDao;
import cn.edu.zucc.configcenter.pojo.BeanConfigModuleBase;
import cn.edu.zucc.configcenter.service.ConfigModuleBaseService;
import cn.edu.zucc.configcenter.utils.MU;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author crabxyj
 * @date 2020/5/15 18:20
 */
@Service("configModuleBaseServiceImpl")
public class ConfigModuleBaseServiceImpl extends ServiceImpl<ConfigModuleBaseDao, BeanConfigModuleBase> implements ConfigModuleBaseService {

    @Resource(name = "configModuleBaseDao")
    private ConfigModuleBaseDao dao;
    @Resource(name = "configBaseDao")
    private ConfigBaseDao baseDao;

    private BeanConfigModuleBase getOne(@NotEmpty String moduleName, @NotEmpty String version){
        QueryWrapper<BeanConfigModuleBase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("module_name", moduleName)
                .eq("version", version);
        return dao.selectOne(queryWrapper);
    }

    @Override
    public BeanConfigModuleBase info(String moduleName,String version) throws BaseException {
        BeanConfigModuleBase moduleBase = getOne(moduleName,version);
        if (moduleBase==null){
            throw MU.exUtils.exBusinessfromMsg("当前模块不存在");
        }
        List<Integer> ids = Stream.of(moduleBase.getBaseIds().split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        moduleBase.setModuleBases(baseDao.selectBatchIds(ids));
        return moduleBase;
    }

    @Override
    public IPage<BeanConfigModuleBase> loadPage(String moduleName, String version, long curPage, long pageSize) {
        QueryWrapper<BeanConfigModuleBase> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(moduleName)) {
            queryWrapper.like("module_name", moduleName);
        }
        if (!StringUtils.isEmpty(version)) {
            queryWrapper.eq("version", version);
        }
        return dao.selectPage(new Page<>(curPage,pageSize),queryWrapper);
    }

    @Override
    public void add(@Valid BeanConfigModuleBase moduleBase) throws BaseException{
        dao.insert(moduleBase);
    }

    @Override
    public void modify(@Valid BeanConfigModuleBase moduleBase) throws BaseException{
        dao.updateById(moduleBase);
    }
//    @Override
//    public void add(String moduleName, String version, Collection<Integer> baseIds) {
//        BeanConfigModuleBase moduleBase = getOne(moduleName, version);
//        boolean flag = true;
//        if (moduleBase!=null){
//            List<Integer> existIds = Stream.of(moduleBase.getBaseIds().split(","))
//                    .map(Integer::valueOf)
//                    .collect(Collectors.toList());
//            baseIds.addAll(existIds);
//        }else{
//            moduleBase = new BeanConfigModuleBase().setModuleName(moduleName).setVersion(version);
//            flag = false;
//        }
//
//        String ids = baseIds.stream().map(String::valueOf).collect(Collectors.joining(","));
//        moduleBase.setBaseIds(ids);
//
//        if (flag){
//            dao.updateById(moduleBase);
//        }else{
//            dao.insert(moduleBase);
//        }
//    }
//
//    @Override
//    public void delBaseIds(String moduleName, String version, Collection<Integer> baseIds) throws BaseException {
//        BeanConfigModuleBase moduleBase = getOne(moduleName, version);
//        if (moduleBase==null){
//            throw MU.exUtils.exBusinessfromMsg("当前模块不存在");
//        }
//        List<Integer> existIds = Stream.of(moduleBase.getBaseIds().split(","))
//                .map(Integer::valueOf)
//                .collect(Collectors.toList());
//        existIds.removeAll(baseIds);
//        String ids = existIds.stream().map(String::valueOf).collect(Collectors.joining(","));
//        moduleBase.setBaseIds(ids);
//        dao.updateById(moduleBase);
//    }

}
