package com.app.domain;

/**
 * @author Alvin
 *
 * Usage mnemonics in application for comfortable data manipulation
 **/
public enum Mnemonics {
    UAH,
    USD,
    EUR;

    public String getMnemonic() {
        return name();
    }
}
