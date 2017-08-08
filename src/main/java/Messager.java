import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by Павел on 05.08.2017.
 */
public class Messager extends TimerTask {
    private long chatId;
    private ReplyKeyboard repKeyboard;

    public Messager(long chatId) {
        this.chatId = chatId;
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


    public void run() {
        //TODO:
        //chatId и текст_сообщения нужно юрать из хранилища (DB?)
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("Очередное сообщение")
                .setReplyMarkup(repKeyboard);

    }
}
