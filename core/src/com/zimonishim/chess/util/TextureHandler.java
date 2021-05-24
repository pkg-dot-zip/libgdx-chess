package com.zimonishim.chess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class TextureHandler {

    private static HashMap<String, Texture> textures = new HashMap<>();

    public static void initTextures(){
        addTexture(chessPieceTexturesPath + "/white_bishop.png");
        addTexture(chessPieceTexturesPath + "/black_bishop.png");
        addTexture(chessPieceTexturesPath + "/white_king.png");
        addTexture(chessPieceTexturesPath + "/black_king.png");
        addTexture( chessPieceTexturesPath + "/white_knight.png");
        addTexture( chessPieceTexturesPath + "/black_knight.png");
        addTexture(chessPieceTexturesPath + "/white_pawn.png");
        addTexture(chessPieceTexturesPath + "/black_pawn.png");
        addTexture(chessPieceTexturesPath + "/white_queen.png");
        addTexture(chessPieceTexturesPath + "/black_queen.png");
        addTexture(chessPieceTexturesPath + "/white_rook.png");
        addTexture(chessPieceTexturesPath + "/black_rook.png");

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
