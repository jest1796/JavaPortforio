package katachi.spring.execise.domain.service;

import java.util.List;

import katachi.spring.execise.domain.model.Subscs;

public interface SubscService {

//	登録サービス一覧取得
	public List<Subscs> getSubscs(int userId) ;
	
	
//	サービス項目の登録
	public int registSubsc(Subscs subsc);
	
	
//	訂正・削除項目の取得
	public Subscs findOne(int id,int userId);
	
//	サービス項目の訂正
	public void updateSubsc(Subscs subsc);
	
	
//	サービス項目の削除
	public void deleteSubsc(int id,int userId);

	
}
