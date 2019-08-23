package com.app.domain;

/**
 * @author Alvin
 **/

public enum Mnemonics {
    USD,
    EUR;

    public String getMnemonic() {
        return name();
    }
}
