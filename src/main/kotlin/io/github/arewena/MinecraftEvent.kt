package io.github.arewena

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent

class MinecraftEvent : Listener {
    @EventHandler
    fun onChat(event: PlayerChatEvent) {
    }
}