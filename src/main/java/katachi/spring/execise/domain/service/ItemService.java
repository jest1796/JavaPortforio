package katachi.spring.execise.domain.service;

import java.util.List;

import katachi.spring.execise.domain.model.Item;

public interface ItemService {

//	登録サービス一覧取得
	public List<Item> getItem(int userId) ;
	
//	引落日までの日数更新
	public void updateUntilDays(int userId);
	
//	サービス項目の登録
	public int registItem(Item item);
	
	
//	訂正・削除項目の取得
	public Item findOne(int id);
	
//	サービス項目の訂正
	public void updateItem(Item item);
	
	
//	サービス項目の削除
	public void deleteItem(int id);

	
}
