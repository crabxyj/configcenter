package cn.edu.zucc.configcenter.utils.exception;

import lombok.Getter;

/**
 * @author crabxyj
 * @date 2020/5/14 18:33
 */
@Getter
public class BaseException extends Exception{
    private int code;
    private String msg;
    public BaseException(int code,String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
}
