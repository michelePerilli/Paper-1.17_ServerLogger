package it.pixel.serverhandbook.model;

import lombok.Getter;

import java.io.Serializable;

/**
 * The type Player activity.
 */
public record PlayerActivity(@Getter String date, @Getter String playerName,
                             @Getter String activity) implements Serializable {

}
