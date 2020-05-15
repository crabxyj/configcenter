package cn.edu.zucc.configcenter.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/15 18:15
 */
@Data
@Accessors(chain = true)
@TableName("config_module_base")
public class BeanConfigModuleBase {
    @TableId(type = IdType.AUTO)
    private int id;
    @NotEmpty(message = "模块名称不能为空")
    private String moduleName;
    @NotEmpty(message = "版本号不能为空")
    private String version;

    private String baseIds;

    @TableField(exist = false)
    private List<BeanConfigBase> moduleBases;
}
