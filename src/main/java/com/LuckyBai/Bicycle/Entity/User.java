package com.LuckyBai.Bicycle.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;
    /**
     * 电话
     */
    private String  phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private String salt;
}

