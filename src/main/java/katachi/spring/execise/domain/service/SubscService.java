package katachi.spring.execise.domain.service;

import java.util.List;

import katachi.spring.execise.domain.model.Subsc;

public interface SubscService {

//	登録サービス一覧取得
	public List<Subsc> getSubscs(int userId) ;
	
	
//	サービス項目の登録
	public int registSubsc(Subsc subsc);
	
	
//	訂正・削除項目の取得
	public Subsc findOne(int id,int userId);
	
//	サービス項目の訂正
	public void updateSubsc(Subsc subsc);
	
	
//	サービス項目の削除
	public void deleteSubsc(int id,int userId);

	
}
