package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.Players;

public interface IClientCallback {
    public Client getClient();
    public Players getPlayer();
}