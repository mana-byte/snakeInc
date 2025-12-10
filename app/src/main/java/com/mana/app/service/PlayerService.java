package com.mana.app.service;

import com.mana.app.params.PlayerParams;
import org.springframework.stereotype.Service;
import com.mana.app.entity.Player;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
  private Map<Integer, Player> players = new HashMap<>();
  AtomicInteger idGenerator = new AtomicInteger();
  public PlayerService() {
  }

  public Player getPlayer(Integer id) {
    return players.get(id);
  }

  public Integer addPlayer(PlayerParams player) {
    Player playerAdder = new Player(player);
    playerAdder.setCreatedAt(LocalDateTime.now());
    Integer id = this.generateId();
    players.put(id,playerAdder);
    return id;
  }
  private Integer generateId() {
    return idGenerator.addAndGet(1);
  }
}
