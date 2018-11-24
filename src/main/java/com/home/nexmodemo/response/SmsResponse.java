package com.home.nexmodemo.response;

public class SmsResponse {
    public static final Builder BUILDER = new Builder();

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public SmsResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {
        private String message;
        private String status;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public SmsResponse build() {
            return new SmsResponse(message, status);
        }
    }
}
