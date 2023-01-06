package com.rexcoinc.rexcoschool.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
public class Holiday extends BaseEntity {
    private String day;
    private String reason;
    private Type type;

    public enum Type{
        FESTIVAL, FEDERAL
    }
}
