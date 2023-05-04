package io.github.arewena


import dev.minn.jda.ktx.intents
import dev.minn.jda.ktx.light
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin



class Main : JavaPlugin(), Listener {
    val jda = light("token", enableCoroutines = true) {
        intents += listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
    }

    override fun onEnable() {
        logger.info("Plugin Enabled")
        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onChat(event: PlayerChatEvent) {
        logger.info(event.player.name)
        logger.info(event.message)

    }
}