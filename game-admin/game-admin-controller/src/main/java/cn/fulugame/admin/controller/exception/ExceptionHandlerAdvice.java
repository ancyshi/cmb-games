package cn.fulugame.admin.controller.exception;

import cn.fulugame.common.Result;
import cn.fulugame.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("数据库中已存在该记录");
    }

    @ExceptionHandler(TypeMismatchException.class)
    public Result handleTypeMismatchException(TypeMismatchException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("参数类型不匹配!");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("数据库插入或更新异常，请检查字段设置!");
    }

    @ExceptionHandler(DataAccessException.class)
    public Result handleDataAccessException(DataAccessException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("SQL执行错误:" + e.getCause().getMessage());
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public Result noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("未找到该页面");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("必填参数空:" + e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Result BizException(BizException e) {
        log.error(e.getMessage(), e);
        return Result.error().msg(e.getMessage());
    }

    /**
     * 处理@Valid绑定注解的异常抛出
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result bindException(BindException e) {
        List<ObjectError> errors = e.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return Result.error().msg(sb.toString());
    }

    /**
     * 上传文件过大异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public Result handleException(MultipartException e) {
        log.error("文件上传错误", e);
        return Result.error().msg("上传文件不能超过2M");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error().msg("服务器错误");
    }
}
