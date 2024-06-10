package com.whistle6.webfluxdemo.user.domain;

import lombok.Data;

@Data
public class RoleModel {
    Long id ;

    RoleName roleName ;
    
    public RoleModel(RoleName roleName) {this.roleName = roleName;}
    
    public String getRoleName() {
        return roleName.toString();
    }
}
