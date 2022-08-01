package it.pixel.serverhandbook.model;

import lombok.Getter;

/**
 * Project: ServerHandbook
 * Author: Michele
 * File: Commands
 * Creation: 01/08/2022
 */
@Getter
public enum Commands {
    COORDS("coords"),
    ACTIVITY("activity"),
    HERE("here");

    private final String cmd;

    Commands(String cmd) {
        this.cmd = cmd;
    }
}
