package katachi.spring.execise.form;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscData {

	private Integer id;			//	予定項目のID番号//
	private int userId;			//ユーザID//
	
	@NotBlank(message="サービス名を入力してください")
	private String servName;		//サービス名//
	
	@NotNull
	@Digits(fraction = 0, integer = 10)
	@Min(1)
	private int fee;				//料金//
	
	private int pay;				//支払い間隔　月額なら１，年額なら２//
	
	
	@Min(0)
	@Max(12)
	private int month;				//支払い月　月払いの場合は空白//
	
	@NotNull
	@Min(1)
	@Max(31)
	private int day;				//支払日//
	
	private int untilDays;			//次回引き落としまでの日数//
}
