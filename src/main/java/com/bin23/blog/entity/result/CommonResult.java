package com.bin23.blog.entity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int resultCode;
    private String message;
    private T data;

    public CommonResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = null;
    }
}
