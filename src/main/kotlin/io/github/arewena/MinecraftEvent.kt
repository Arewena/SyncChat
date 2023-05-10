package io.github.arewena


import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin



class MinecraftEvent : JavaPlugin(), Listener {
    // TODO: 메시지 입력 감지, 디코에서 온 메시지 보내는 함수 만들기


    override fun onEnable() {
        val start = BotEvent().set("")
        logger.info("Plugin Enabled")
        this.server.pluginManager.registerEvents(this, this)
    }


}