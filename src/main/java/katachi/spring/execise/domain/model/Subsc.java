package katachi.spring.execise.domain.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Subsc {
	
	private Integer id;			//	予定項目のID番号//
	private int userId;			//ユーザID//
	private String servName;		//サービス名//
	private int fee;				//料金//
	private int pay;				//支払い間隔　月額なら１，年額なら２//
	private int month;				//支払い月　月払いの場合は空白//
	private int day;				//支払日//
	private int untilDays;			//次の支払日までの日数//
	private Date registDay;		//登録日//
}
