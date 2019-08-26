package com.app.domain;

/**
 * @author Alvin
 **/
public enum Mnemonics {
    UAH,
    USD,
    EUR;

    public String getMnemonic() {
        return name();
    }
}
