package com.bin23.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
}
