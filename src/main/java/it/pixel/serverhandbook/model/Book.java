package it.pixel.serverhandbook.model;

import java.io.Serializable;

public record Book(String playerName, String note) implements Serializable {
}
