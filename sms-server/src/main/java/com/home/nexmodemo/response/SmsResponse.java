package com.home.nexmodemo.response;

public class SmsResponse {
    public static final Builder BUILDER = new Builder();

    private String message;
    private StatusCode status;

    public String getMessage() {
        return message;
    }

    public StatusCode getStatus() {
        return status;
    }

    public SmsResponse(String message, StatusCode status) {
        this.message = message;
        this.status = status;
    }

    public SmsResponse() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }

    public static class Builder {
        private String message;
        private StatusCode status;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withStatus(StatusCode status) {
            this.status = status;
            return this;
        }

        public SmsResponse build() {
            return new SmsResponse(message, status);
        }
    }

    public enum StatusCode {

        OK(0),
        ERROR(1);

        private int id;

        StatusCode(int id) {
            this.id = id;
        }
    }
}
