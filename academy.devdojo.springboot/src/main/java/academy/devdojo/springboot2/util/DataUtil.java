package academy.devdojo.springboot2.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataUtil {
    private DateTimeFormatter DataTimeFormatter;

    public String formatLocalDataTimeToDatabasestyle(LocalDateTime localDateTime){
        return DataTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
