package io.github.arewena

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        logger.info("Plugin Enabled")
        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onChat(event: PlayerChatEvent) {
        logger.info(event.player.toString())

    }
}