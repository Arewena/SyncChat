package io.github.arewena


import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin



class MinecraftEvent : JavaPlugin(), Listener {
    // TODO: 플레이어 머리 스킨 이미지 보낼 방법 찾기
    // 토큰 지우는거 잊어먹지 말기!!


    override fun onEnable() {
        logger.info("Plugin Enabled")
        val jda = jda
        BotEvent().set()
        this.server.pluginManager.registerEvents(this, this)

    }

    override fun onDisable() {
        logger.info("Plugin Disabled")
        jda.shutdown()
    }

    @EventHandler
    fun onChat(event: PlayerChatEvent) {
        logger.info(selectedChannel.toString())
        BotEvent().message(event.player.name, event.message, 1)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) { BotEvent().message(event.player.name, "https://mc-heads.net/avatar/${event.player.name}", 2) }

    @EventHandler
    fun onLeft(event: PlayerQuitEvent) { BotEvent().message(event.player.name, "https://mc-heads.net/avatar/${event.player.name}", 3) }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {BotEvent().message(event.player.name, "https://mc-heads.net/avatar/${event.player.name}", 4)}



}