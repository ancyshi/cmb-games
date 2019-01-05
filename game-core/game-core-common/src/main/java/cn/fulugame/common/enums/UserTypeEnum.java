package cn.fulugame.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  UserTypeEnum implements TypeEnum<Integer>{

    ADMIN_USER(1,"超级管理员"),
    GENERAL_USER(2,"普通用户");

    private Integer type;
    private String msg;


}
