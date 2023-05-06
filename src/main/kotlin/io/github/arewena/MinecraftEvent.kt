package io.github.arewena


import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin



class MinecraftEvent : JavaPlugin(), Listener {
    // TODO: 메시지 입력 감지, 디코에서 온 메시지 보내는 함수 만들기


    override fun onEnable() {
        val jda = JDABuilder.createDefault("OTE1MTQyNzEyNDAyMDAxOTIw.GHkucr.do5Nmyak2CFL2tBvtVrFaUB0D_XdloklkB3E7Y").build()
        jda.presence.setStatus(OnlineStatus.ONLINE)
        logger.info("Plugin Enabled")
        this.server.pluginManager.registerEvents(this, this)
    }


}