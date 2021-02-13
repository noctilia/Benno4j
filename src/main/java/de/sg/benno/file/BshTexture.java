/*
 * This file is part of the Benno4j project.
 *
 * Copyright (c) 2021. stwe <https://github.com/stwe/Benno4j>
 *
 * License: GPLv2
 */

package de.sg.benno.file;

import de.sg.ogl.resource.Texture;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class BshTexture {

    private final BufferedImage bufferedImage;
    private Texture texture;

    //-------------------------------------------------
    // Ctors.
    //-------------------------------------------------

    BshTexture(BufferedImage bufferedImage) {
        this.bufferedImage = Objects.requireNonNull(bufferedImage, "bufferedImage must not be null");
    }

    //-------------------------------------------------
    // Getter
    //-------------------------------------------------

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Texture getTexture() {
        return texture;
    }

    //-------------------------------------------------
    // Setter
    //-------------------------------------------------

    void setTexture(Texture texture) {
        this.texture = texture;
    }
}