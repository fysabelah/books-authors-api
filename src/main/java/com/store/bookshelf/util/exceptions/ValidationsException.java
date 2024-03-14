package com.store.bookshelf.util.exceptions;

import com.store.bookshelf.util.MessageUtil;

public class ValidationsException extends Exception {

    public ValidationsException(String code) {
        super(MessageUtil.getMessage(code));
    }
}
