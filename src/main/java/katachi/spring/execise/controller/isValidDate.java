package katachi.spring.execise.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class isValidDate {
//	入力された月日の正誤判定
	 public boolean isValidDate(int month, int day) {
	        try {
	            LocalDate.of(2001, month, day); // 2月29日は登録できない無効な日付として扱いたいので、うるう年ではない2001年を基準として用いる
	            return true; // 有効な日付
	        } catch (Exception e) {
	            return false; // 無効な日付
	        }
	    }
}
