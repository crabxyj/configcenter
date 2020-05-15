package cn.edu.zucc.configcenter.dao;

import cn.edu.zucc.configcenter.pojo.BeanConfigModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/13 22:06
 */
@Repository("configModuleDao")
public interface ConfigModuleDao extends BaseMapper<BeanConfigModule> {
    @Select(" select distinct module_name from configmoduleinfo ")
    List<String> loadModuleNames();
}
