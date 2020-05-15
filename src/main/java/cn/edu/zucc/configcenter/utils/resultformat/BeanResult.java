package cn.edu.zucc.configcenter.utils.resultformat;

import cn.edu.zucc.configcenter.utils.exception.BaseException;
import lombok.Getter;

import java.util.Collection;

/**
 * @author crabxyj
 * @date 2020/5/14 19:33
 */
@Getter
public class BeanResult {
    private int code;
    private String msg;
    private Object r;
    private Collection rs;

    public static BeanResult fromSuccess() {
        BeanResult result = new BeanResult();
        result.code = 0;
        return result;
    }

    public static BeanResult fromSuccess(Object r) {
        BeanResult result = fromSuccess();
        if (r instanceof Collection){
            result.rs = (Collection) r;
        }else{
            result.r = r;
        }
        return result;
    }

    public static BeanResult fromException(BaseException e) {
        BeanResult result = new BeanResult();
        result.code = e.getCode();
        result.msg = e.getMsg();
        return result;
    }

}
