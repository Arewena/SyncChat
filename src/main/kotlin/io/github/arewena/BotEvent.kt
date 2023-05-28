package io.github.arewena

import dev.minn.jda.ktx.EmbedBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.requests.GatewayIntent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import java.awt.Color


val jda = JDABuilder.createLight("token")
    .addEventListeners(BotEvent())
    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
    .setStatus(OnlineStatus.ONLINE)
    .build()

var selectedChannel: Long? = null



class BotEvent : EventListener, ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.author.isBot) {
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendMessage(
                    Component.text("[DISCORD]").color(NamedTextColor.BLUE).append(Component.text("<${event.author.name}> ${event.message.contentRaw}").color(NamedTextColor.WHITE)))
            }
        }
    }


    fun set() {
        jda.updateCommands().addCommands(
            Commands.slash("select-channel", "Minecraft 메시지를 출력할 채널을 설정하는 명령어 입니다."),
            Commands.slash("info", ".")).queue()
    }

    fun message(user: String, message: String, type: Int) {

        if (selectedChannel != null) {
            val selectedChannel = selectedChannel
            when (type) {
                1 -> jda.getTextChannelById(selectedChannel!!)?.sendMessage("<$user> $message")?.queue()
                2 -> jda.getTextChannelById(selectedChannel!!)?.sendMessageEmbeds(EmbedBuilder().apply {
                    author("$user (이)가 서버에 입장하셨습니다.", null, message)
                    color = Color.GREEN.rgb
                }.build())?.queue()
                3 -> jda.getTextChannelById(selectedChannel!!)?.sendMessageEmbeds(EmbedBuilder().apply {
                    author("$user (이)가 서버에서 퇴장하셨습니다.", null, message)
                    color = Color.RED.rgb
                }.build())?.queue()
                4 -> jda.getTextChannelById(selectedChannel!!)?.sendMessageEmbeds(EmbedBuilder().apply {
                    author("$user (이)가 사망하셨습니다!", null, message)
                    color = Color.GRAY.rgb
                }.build())?.queue()

            }
        }
    }


    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.member?.isOwner!!) {
            val name = event.name
            val channelName = "https://discord.com/channels/${event.guild?.id}/${event.channel.id}"
            when(name) {
                "select-channel" -> {
                    selectedChannel = event.channel.idLong
                    event.reply("Minecraft 인게임 채팅과 $channelName 채널이 성공적으로 연결되었습니다.").setEphemeral(true).queue()
                }
                "info" -> {
                    event.reply(selectedChannel.toString()).queue()
                }



            }

        }
        else {
            event.reply("이 명령어를 사용할 권한이 없습니다.").setEphemeral(true).queue()
        }
    }
}