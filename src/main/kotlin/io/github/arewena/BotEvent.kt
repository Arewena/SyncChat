package io.github.arewena

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands


class BotEvent : EventListener, ListenerAdapter() {
    // TODO: 디스코드 -> 마인크래프트로 채팅을 보낼 방법 구상하기, 서버 입/퇴장 메시지, 사망 메시지, 발전과제 달성 메시지 임베드화
    lateinit var selectedChannel: MessageChannelUnion


    fun set(token: String) {
        val jda = JDABuilder.createLight(token)
            .addEventListeners(BotEvent())
            .setStatus(OnlineStatus.ONLINE)
            .build()

        jda.updateCommands().addCommands(
            Commands.slash("select-channel", "Minecraft 메시지를 출력할 채널을 설정하는 명령어 입니다."),
        ).queue()

    }

    fun message(user: String, message: String, type: Int) {
        when (type) {
            1 -> selectedChannel.sendMessage("<$user> $message")
            2 -> selectedChannel.sendMessage("$user (이)가 서버에 입장하셨습나다.")
            3 -> selectedChannel.sendMessage("$user (이)가 서버에서 퇴장하셨습니다.")
            4 -> selectedChannel.sendMessage("$user (이)가 사망혔습니다!")
            5 -> selectedChannel.sendMessage("$user (이)가 $message 발전과제를 달성하셨습니다!")
        }

    }


    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.member?.isOwner!!) {
            val name = event.name
            val channelName = "https://discord.com/channels/${event.guild?.id}/${event.channel.id}"
            when(name) {
                "select-channel" -> {
                    event.reply("Minecraft 인게임 채팅과 $channelName 채널이 성공적으로 연결되었습니다.").setEphemeral(true).queue()
                    selectedChannel = event.channel
                }


            }

        }
        else {
            event.reply("이 명령어를 사용할 권한이 없어요.").setEphemeral(true).queue()
        }
    }
}