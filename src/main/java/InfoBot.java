import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.*;
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
    //private ReplyKeyboard repKeyboard;
    private Connection connection;
    public InfoBot() {
        super();
        //repKeyboard = createKeyboard();

        // установка соединения с базой
        connection = DAO.getConnection();
/*
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        //Имя пользователя БД
        String name = "user1";
        //Пароль
        String password = "1";
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер PostgreSQL подключен");
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    private void addSubscriber(long charId){
        PreparedStatement prepStatment = null;
        try {
            prepStatment = connection.prepareStatement("select exists(select true from  subscribers  where chat_id = ?) res");
            prepStatment.setLong(1, chatId);
            ResultSet rs = prepStatment.executeQuery();
            while(rs.next()){
                if(!(rs.getBoolean("res"))){
                    prepStatment = connection.prepareStatement("INSERT INTO subscribers (chat_id, reg_date) VALUES (?, current_timestamp)");
                    prepStatment.setLong(1, chatId);
                    int res = prepStatment.executeUpdate();
                    System.out.println("Добавлено " + res + " записей");
                }
                else {
                    System.out.println("Собеседник " + chatId + " уже есть в базе");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            chatId = update.getMessage().getChatId();
            System.out.println("chatId: " + chatId);
            addSubscriber(chatId);
            /*
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
            */
        }
    }

    public String getBotUsername() {
        return BotUsername;
    }

    public String getBotToken() {
        return BotToken;
    }
}
