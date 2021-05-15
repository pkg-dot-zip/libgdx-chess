package com.zimonishim.chess.util;

import java.awt.*;

/**
 * Contains methods for converting Java.awt.Color to com.badlogic.gdx.graphics.color, and the otherway around.
 */
public class ColorConverter {

    public static Color convertFromLibGdxToAwt(com.badlogic.gdx.graphics.Color color){
        int r = (int)color.r * 255;
        int g = (int)color.g * 255;
        int b = (int)color.b * 255;
        int a = (int)color.a * 255;

        return new Color(r, g, b, a);
    }

    public static com.badlogic.gdx.graphics.Color convertFromAwtToLibGdx(Color color){
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;
        float a = color.getAlpha() / 255f;

        return new com.badlogic.gdx.graphics.Color(r, g, b, a);
    }
}