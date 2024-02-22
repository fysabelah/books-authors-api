package com.mongodb.booksauthors.util.exceptions;

import com.mongodb.booksauthors.util.MessageUtil;

public class ValidationsException extends Exception {

    public ValidationsException(String code) {
        super(MessageUtil.getMessage(code));
    }
}
