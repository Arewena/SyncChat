package io.github.arewena

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands

var selctedChannel: String? = ""

class BotEvent : EventListener, ListenerAdapter() {
    // TODO: 메시지 보내는 함수 하나 만들기, selectedChannel 변수 어떻게 써먹을지 구상하기



    fun set(token: String) {
        val jda = JDABuilder.createLight(token)
            .addEventListeners(BotEvent())
            .setStatus(OnlineStatus.ONLINE)
            .build()

        jda.updateCommands().addCommands(
            Commands.slash("select-channel", "Minecraft 메시지를 출력할 채널을 설정하는 명령어 입니다.")
        ).queue()

    }


    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.member?.isOwner!!) {
            val name = event.name
            val channelName = event.channel.name
            when(name) {
                "select-channel" -> event.reply("Minecraft 인게임 채팅과 $channelName 채널이 성공적으로 연결되었습니다.").setEphemeral(true).queue()

            }
        }
        else {
            event.reply("이 명령어를 사용할 권한이 없어요.").setEphemeral(true).queue()
        }


    }





}