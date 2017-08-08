import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by Павел on 05.08.2017.
 */
public class InfoBot extends TelegramLongPollingBot {
    private final String BotToken = "394479438:AAGgxN1tqUZgrtJVqe619tTetgJh4awZO6w";
    private final String BotUsername = "ZinurovInfoPosting_bot";
    private long chatId;
    private ReplyKeyboard repKeyboard;
    public InfoBot() {
        super();
        repKeyboard = createKeyboard();
    }

//    TODO: сделать конфигуратор клавиатуры
    private ReplyKeyboard createKeyboard(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        ArrayList<KeyboardRow> keys = new ArrayList<KeyboardRow>();
        KeyboardRow keyRow = new KeyboardRow();
        keyRow.add(0,"Консультация");
        keyRow.add(1, "Тренинг");
        keyRow.add(2, "Свалить");
        keys.add(keyRow);

        keyboardMarkup.setKeyboard(keys);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            chatId = update.getMessage().getChatId();
            System.out.println("chatId: " + chatId);
            if (update.getMessage().hasText()) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(update.getMessage().getText())
                        .setReplyMarkup(repKeyboard);
                try {
                    this.sendApiMethod(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBotUsername() {
        return BotUsername;
    }

    public String getBotToken() {
        return BotToken;
    }
}
