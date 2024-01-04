package kr.co.maple.common.component;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import kr.co.maple.common.util.DateUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MapleApiTimeCheckComponent {
    private final DateUtil dateUtil;

    public String timeCheck(Date date, int data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if (currentHour > 8 || (currentHour == 8 && currentMinute >= 30)) {
            return processTimeCheck(0, 1, data);

        } else {
            return processTimeCheck(1, 2, data);
        }
    }

    private String processTimeCheck(int day1, int day2, int data) {
        String formatDate = dateUtil.get(new Date(), 10);
        String previousDate = dateUtil.getDateFormatFrom(2, formatDate, data == 0 ? -day1 : -day2);
        return previousDate;
    }
}