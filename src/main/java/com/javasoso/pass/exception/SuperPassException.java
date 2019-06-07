package com.javasoso.pass.exception;

/**
 * 业务异常
 */
public class SuperPassException extends RuntimeException {
    public SuperPassException() {
    }

    public SuperPassException(String message) {
        super(message);
    }

    public SuperPassException(String message, Throwable cause) {
        super(message, cause);
    }
}
