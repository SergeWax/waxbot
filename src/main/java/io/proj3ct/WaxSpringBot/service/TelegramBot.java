package io.proj3ct.WaxSpringBot.service;

import io.proj3ct.WaxSpringBot.config.BotConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    StartCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;

                default: sendMessage(chatId, "Sorry. Command was not recognized");
            }
        }
    }
    private void StartCommandReceived(long chatId, String name) {
        String answer = "Hi, " + name + "!. " + "You have cool name!";
        log.error("Replied to user" + name);

        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend)  {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);

        } catch (TelegramApiException e) {
            log.info("Error" + e.getMessage());
        }

    }
}
