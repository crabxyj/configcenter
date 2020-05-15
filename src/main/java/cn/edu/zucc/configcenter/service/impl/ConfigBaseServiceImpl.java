package cn.edu.zucc.configcenter.service.impl;

import cn.edu.zucc.configcenter.dao.ConfigBaseDao;
import cn.edu.zucc.configcenter.pojo.BeanConfigBase;
import cn.edu.zucc.configcenter.service.ConfigBaseService;
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
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/13 22:29
 */
@Service("configBaseServiceImpl")
public class ConfigBaseServiceImpl extends ServiceImpl<ConfigBaseDao, BeanConfigBase> implements ConfigBaseService {

    @Resource(name = "configBaseDao")
    private ConfigBaseDao dao;

    @Override
    public void add(@Valid BeanConfigBase configBase) throws BaseException {
        QueryWrapper<BeanConfigBase> query = new QueryWrapper<>();
        query.eq("base_name",configBase.getBaseName());
        if(dao.selectOne(query)!=null){
            throw MU.exUtils.exBusinessfromMsg("当前数据库名已存在");
        }
        dao.insert(configBase);
    }

    @Override
    public void modify(@Valid BeanConfigBase configBase) throws BaseException {
        if(dao.selectById(configBase.getBaseId()) == null){
            throw MU.exUtils.exBusinessfromMsg("当前数据库不存在");
        }
        dao.updateById(configBase);
    }

    @Override
    public void delByIds(Collection<Serializable> ids) {
        dao.deleteBatchIds(ids);
    }

    @Override
    public IPage<BeanConfigBase> loadPage(String baseName,String type, String url, long curPage, long pageSize) {
        QueryWrapper<BeanConfigBase> query = new QueryWrapper<>();
        if (!StringUtils.isEmpty(baseName)) {
            query.like("base_name", baseName);
        }
        if (!StringUtils.isEmpty(url)) {
            query.like("url", url);
        }
        if (!StringUtils.isEmpty(type)) {
            query.eq("type", type);
        }
        return dao.selectPage(new Page<>(curPage, pageSize), query);
    }

    @Override
    public List<BeanConfigBase> load() {
        return list();
    }
}
