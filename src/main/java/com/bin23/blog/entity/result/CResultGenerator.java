package com.bin23.blog.entity.result;

import org.springframework.util.StringUtils;

public class CResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static CommonResult genSuccessResult() {
        CommonResult result = new CommonResult();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static CommonResult genSuccessResult(String message) {
        CommonResult result = new CommonResult();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        return result;
    }

    public static CommonResult genSuccessResult(Object data) {
        CommonResult result = new CommonResult();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static CommonResult genFailResult(String message) {
        CommonResult result = new CommonResult();
        result.setResultCode(RESULT_CODE_SERVER_ERROR);
        if (StringUtils.isEmpty(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static CommonResult genErrorResult(int code, String message) {
        CommonResult result = new CommonResult();
        result.setResultCode(code);
        result.setMessage(message);
        return result;
    }
}
