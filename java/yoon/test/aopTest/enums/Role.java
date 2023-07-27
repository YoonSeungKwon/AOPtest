package yoon.test.aopTest.enums;

import lombok.Getter;

@Getter
public enum Role {

    GUEST("ROLE_ANONYMOUS"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;

    Role(String key){
        this.key = key;
    }

}
