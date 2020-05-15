package cn.edu.zucc.configcenter.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * @author crabxyj
 * @date 2020/5/13 22:26
 */
@Data
@Accessors(chain = true)
@TableName("config_database")
public class BeanConfigBase {
    private static final Set<String> TYPES;
    static {
        TYPES = new HashSet<>(8);
        TYPES.add("mysql");
        TYPES.add("redis");
    }
    @TableId
    private int baseId;

    private String type;
    @NotEmpty(message = "数据库名不能为空")
    private String baseName;
    @NotEmpty(message = "链接地址不能为空")
    private String url;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;

    @AssertTrue(message = "请确认数据库类型")
    private boolean validType(){
        return TYPES.contains(type);
    }
}
