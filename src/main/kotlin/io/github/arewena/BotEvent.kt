package io.github.arewena

import dev.minn.jda.ktx.EmbedBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands
import java.awt.Color


val jda = JDABuilder.createLight("token")
    .addEventListeners(BotEvent())
    .setStatus(OnlineStatus.ONLINE)
    .build()

var selectedChannel: Long? = null



class BotEvent : EventListener, ListenerAdapter() {
    // TODO: 디스코드 -> 마인크래프트로 채팅을 보낼 방법 구상하기, 임베드 수정

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
                    author("$user (이)가 서버에 입장하셨습니다.", null, null)
                    color = Color.GREEN.rgb
                }.build())?.queue()
                3 -> jda.getTextChannelById(selectedChannel!!)?.sendMessageEmbeds(EmbedBuilder().apply {
                    author("$user (이)가 서버에서 퇴장하셨습니다.", null, null)
                    color = Color.RED.rgb
                }.build())?.queue()
                4 -> jda.getTextChannelById(selectedChannel!!)?.sendMessageEmbeds(EmbedBuilder().apply {
                    author("$user (이)가 사망하셨습니다!", null, null)
                    color = Color.GRAY.rgb
                }.build())?.queue()
                5 -> jda.getTextChannelById(selectedChannel!!)?.sendMessageEmbeds(EmbedBuilder().apply {
                    author("$user (이)가 $message 발전과제를 달성하셨습니다!", null, null)
                    color = Color.YELLOW.rgb
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