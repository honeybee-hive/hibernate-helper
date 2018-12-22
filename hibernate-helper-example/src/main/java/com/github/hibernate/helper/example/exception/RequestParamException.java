/*
 * InputParamException  1.0  2018-12-04
 */
package com.github.hibernate.helper.example.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求参数错误
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-04 zhuyan 初版
 */
@Slf4j
public class RequestParamException extends RuntimeException {

    public RequestParamException() {
        super();
    }

    public RequestParamException(String message) {
        super(message);
    }

    public RequestParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestParamException(Throwable cause) {
        super(cause);
    }

}
