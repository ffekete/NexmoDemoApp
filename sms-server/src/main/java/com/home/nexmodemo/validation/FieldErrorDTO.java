package com.home.nexmodemo.validation;

/**
 * Simple DTO for field validation.
 */
public class FieldErrorDTO {
    private String fieldName;
    private String message;

    public FieldErrorDTO(String fieldName, String message) {
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
