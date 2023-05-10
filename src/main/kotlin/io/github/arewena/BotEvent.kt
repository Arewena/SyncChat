package io.github.arewena

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands


class BotEvent : EventListener, ListenerAdapter() {
    // TODO: 슬래시 커맨드로 메시지 보낼 채널 설정 여부 묻는 커맨드 만들기, 메시지 보내는 함수 하나 만들기
    val selctedChannel = ""

    fun set(token: String) {
        val jda = JDABuilder.createLight(token)
            .addEventListeners(BotEvent())
            .setStatus(OnlineStatus.ONLINE)
            .build()

        jda.updateCommands().addCommands(
            Commands.slash("asdf", "asdf")
        ).queue()

    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {


    }





}