package org.challenger.challenger.infrastructure.config;

import org.challenger.challenger.infrastructure.service.ChallengeService;
import org.challenger.challenger.telegrambot.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class TelegramConfig {
	@Value("${telegram-bot.webhook-path}")
	private String webhookPath;
	@Value("${telegram-bot.name}")
	private String botName;
	@Value("${telegram-bot.token}")
	private String botToken;

	@Bean
	public SetWebhook setWebhookInstance() {
		return SetWebhook.builder().url(webhookPath).build();
	}

	@Bean
	public MessageHandler messageHandler(ChallengeService challengeService, ChallengeSessionStorage challengeSessionStorage) {
		return new MessageHandler(challengeService, challengeSessionStorage);
	}

	@Bean
	public ChallengeSessionStorage sessionStorage() {
		return new ChallengeSessionStorage();
	}

	@Bean
	public CallbackQueryHandler callbackQueryHandler(ChallengeService challengeService, ChallengeSessionStorage challengeSessionStorage) {
		return new CallbackQueryHandler(challengeService, challengeSessionStorage);
	}

	@Bean
	public WriteReadBot springWebhookBot(SetWebhook setWebhook,
	                                     MessageHandler messageHandler,
	                                     CallbackQueryHandler callbackQueryHandler) {
		WriteReadBot bot = new WriteReadBot(setWebhook, messageHandler, callbackQueryHandler);

		bot.setBotPath(webhookPath);
		bot.setBotUsername(botName);
		bot.setBotToken(botToken);

		return bot;
	}
}