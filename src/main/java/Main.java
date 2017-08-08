import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

/**
 * Created by Павел on 05.08.2017.
 */
public class Main {
    public static void main(String[] args){
        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try{
            botApi.registerBot(new InfoBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
