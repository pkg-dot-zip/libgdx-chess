package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.Players;

public interface IClientCallback {
    Client getClient();
    Players getPlayer();
}