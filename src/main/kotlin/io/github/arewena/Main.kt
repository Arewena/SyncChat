package io.github.arewena


import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin



class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        val builder = JDABuilder.createDefault("token").build()
        builder.presence.setStatus(OnlineStatus.ONLINE)
        logger.info("Plugin Enabled")
        this.server.pluginManager.registerEvents(this, this)
    }


}