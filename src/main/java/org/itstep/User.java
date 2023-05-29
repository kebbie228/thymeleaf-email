package org.itstep;

import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private String password;
    private String password2;
    private boolean enabled=false;

}