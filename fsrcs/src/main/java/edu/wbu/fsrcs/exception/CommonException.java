package edu.wbu.fsrcs.exception;

import edu.wbu.fsrcs.entity.ResultCode;

/**
 * 自定义异常
 */
public class CommonException  extends Exception {
    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
