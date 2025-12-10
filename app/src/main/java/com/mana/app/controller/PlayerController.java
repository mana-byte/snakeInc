package com.mana.app.controller;

import com.mana.app.entity.Player;
import com.mana.app.params.PlayerParams;
import com.mana.app.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v2/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public PlayerParams getPlayer(@RequestParam(defaultValue="2") Integer id) {
        Player player = playerService.getPlayer(id);
        return new PlayerParams(player.getName(), player.getAge());
    }

    @PostMapping
    public Map<String, Object> postPlayer(@Valid @RequestBody PlayerParams playerParams) {
        Integer id = playerService.addPlayer(playerParams);
        return Map.of("id", id, "player", playerService.getPlayer(id));
    }
}
