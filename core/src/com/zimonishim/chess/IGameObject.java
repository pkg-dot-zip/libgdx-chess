package com.zimonishim.chess;

public interface IGameObject {

    /**
     * Contains code related to values on the screen. Should be called every frame.
     * @param chessBoardCallback  callback containing the basic methods for updating the ChessBoard
     */
    void update(IChessBoardCallback chessBoardCallback);

    /**
     * Contains code directly related to drawing graphics on the screen. Should be called every frame.
     * @param drawCallback  callback containing the Batch and ShapeDrawer to draw with
     */
    void draw(IDrawCallback drawCallback);
}