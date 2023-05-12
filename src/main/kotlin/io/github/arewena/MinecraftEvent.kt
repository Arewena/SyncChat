package io.github.arewena


import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin



class MinecraftEvent : JavaPlugin(), Listener {
    // TODO: 디스코드 -> 마인크래프트로 채팅 보내는 함수 만들기, shutdown 어떻게 할지 구상하기, 발전과제 달성 메시지랑 데스 메시지 번역(가능하면)
    // 토큰 지우는거 잊어먹지 말기!!


    override fun onEnable() {
        val start = BotEvent().set("")
        logger.info("Plugin Enabled")
        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onChat(event: PlayerChatEvent) { BotEvent().message(event.player.name, event.message, 1) }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) { BotEvent().message(event.player.name, "", 2) }

    @EventHandler
    fun onLeft(event: PlayerQuitEvent) { BotEvent().message(event.player.name, "", 3) }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {BotEvent().message(event.player.name, "", 4)}

    @EventHandler
    fun onDone(event: PlayerAdvancementDoneEvent) {BotEvent().message(event.player.name, event.eventName, 5)}


}