package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.Players;

/**
 * Contains getters for client information.
 */
public interface IClientCallback {
    Client getClient();
    Players getPlayer();
}