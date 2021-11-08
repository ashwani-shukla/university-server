package com.university.server.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Error response.
 */
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * The Code.
     */
    @Getter
    @Setter
    Integer code;

    /**
     * The Timestamp.
     */
    @Getter
    @Setter
    Date timestamp;

    /**
     * The Status.
     */
    @Getter
    @Setter
    String status;

    /**
     * The Message.
     */
    @Getter
    @Setter
    String message;

    /**
     * The Details.
     */
    @Getter
    @Setter
    String details;
}
