package com.chaching.constants;

/*
 * This class is not being used in any other class.
 * This is only for, How we can create ENUM class with some value.
 */
public enum UserEnum {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    HR("HR");

    private String value;

    UserEnum(String value){
        this.value = value;
    }

    public String getUserEnum(){
        return this.value;
    }
}
