package cn.edu.zucc.configcenter.service.impl;

import cn.edu.zucc.configcenter.dao.ConfigModuleDao;
import cn.edu.zucc.configcenter.pojo.BeanConfigModule;
import cn.edu.zucc.configcenter.service.ConfigModuleService;
import cn.edu.zucc.configcenter.utils.MU;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/13 22:08
 */
@Service("configModuleServiceImpl")
public class ConfigModuleServiceImpl extends ServiceImpl<ConfigModuleDao, BeanConfigModule> implements ConfigModuleService {

    @Resource(name = "configModuleDao")
    private ConfigModuleDao dao;

    private BeanConfigModule info(BeanConfigModule configModule)   {
        BeanConfigModule module;
        if (configModule.getId() == 0) {
            QueryWrapper<BeanConfigModule> query = new QueryWrapper<>();
            query.eq("module_name", configModule.getModuleName())
                    .eq("config_key", configModule.getConfigKey())
                    .eq("version", configModule.getVersion());
            module = dao.selectOne(query);
        } else {
            module = this.getById(configModule.getId());
        }

        return module;
    }

    /**
     * 判断是否存在该配置项
     *
     * @return true 存在
     */
    private boolean exist(BeanConfigModule configModule)   {
        return info(configModule) != null;
    }

    @Override
    public void add(@Valid BeanConfigModule configModule) throws BaseException {
        if (exist(configModule)) {
            throw MU.exUtils.exBusinessfromMsg("当前配置项已存在");
        }
        System.out.println(JSON.toJSONString(configModule));
        dao.insert(configModule);
    }

    @Override
    public void addIfNotExist(@Valid BeanConfigModule configModule)   {
        if (!exist(configModule)) {
            dao.insert(configModule);
        }
    }

    @Override
    public void delByIds(Collection<Serializable> ids) {
        dao.deleteBatchIds(ids);
    }

    @Override
    public void modify(@Valid BeanConfigModule configModule) throws BaseException {
        if (!exist(configModule)) {
            throw MU.exUtils.exBusinessfromMsg("当前模块不存在");
        }
        dao.updateById(configModule);
    }

    @Override
    public IPage<BeanConfigModule> loadPage(String moduleName,
                                            String configKey,
                                            int version,
                                            long curPage, long pageSize) {
        QueryWrapper<BeanConfigModule> query = new QueryWrapper<>();
        if (!StringUtils.isEmpty(moduleName)) {
            query.like("module_name", moduleName);
        }
        if (!StringUtils.isEmpty(configKey)) {
            query.like("config_key", configKey);
        }
        if (version != 0) {
            query.eq("version", version);
        }
        Page<BeanConfigModule> page = new Page<>(curPage, pageSize);
        return dao.selectPage(page, query);
    }

    @Override
    public List<String> loadModuleNames(){
        return dao.loadModuleNames();
    }
}
