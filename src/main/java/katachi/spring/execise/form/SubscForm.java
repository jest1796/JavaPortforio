package katachi.spring.execise.form;

import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SubscForm {

	private Integer id;			//	予定項目のID番号//
	private int userId;			//ユーザID//
	
	@NotBlank(message="サービス名を入力してください")
	@Size(max=50, message = "長すぎます")
	private String servName;		//サービス名//
	
	@NotNull
	@Digits(fraction = 0, integer = 10)
	@Min(value = 1,message = "　　　　　　　　　　　　　　　　　数値を入力してください")
	private int fee;				//料金//
	
	private int pay;				//支払い間隔　月額なら１，年額なら２//
	
	
	@Min(0)
	@Max(12)
	private int month;				//支払い月　月払いの場合は空白//
	
	@NotNull
	@Min(1)
	@Max(31)
	private int day;				//支払日//
	private int untilDays;			//次の支払日までの日数//
	private Date registDay;		//登録日//
}
