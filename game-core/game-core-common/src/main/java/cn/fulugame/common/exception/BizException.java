package cn.fulugame.common.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException{

    protected Integer code;
    protected String message;
}
