package com.zimonishim.chess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import static com.zimonishim.chess.util.FilePathHandler.*;

public class TextureHandler {

    private static final Map<String, Texture> textures = new HashMap<>();

    public static void initTextures(){
        addTexture(whiteBishop);
        addTexture(blackBishop);
        addTexture(whiteKing);
        addTexture(blackKing);
        addTexture(whiteKnight);
        addTexture(blackKnight);
        addTexture(whitePawn);
        addTexture(blackPawn);
        addTexture(whiteQueen);
        addTexture(blackQueen);
        addTexture(whiteRook);
        addTexture(blackRook);
    }

    private static void addTexture(String filePath){
        textures.put(filePath, new Texture(Gdx.files.internal(filePath)));
    }

    public static Texture getTexture(String filePath){
        return textures.get(filePath);
    }

    public static void dispose(){
        for (Texture texture : textures.values()){
            texture.dispose();
        }
    }
}
