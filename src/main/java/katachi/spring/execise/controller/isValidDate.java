package katachi.spring.execise.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class isValidDate {
//	入力された月日の正誤判定
	 public boolean isValidDate(int month, int day) {
	        try {
	            LocalDate.of(2001, month, day); // 2001年はうるう年ではないので、2月29日は無効な日付として扱います
	            return true; // 有効な日付
	        } catch (Exception e) {
	            return false; // 無効な日付
	        }
	    }
}
