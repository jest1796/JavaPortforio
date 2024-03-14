package katachi.spring.execise.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.execise.domain.model.Item;

@Mapper
public interface ItemMapper {

//	項目一覧取得
	public List<Item> getItem(int userId);
	
//	引落日までの日数更新
	public void updateUntilDays(int userId);
	
//	項目の登録
	public int registItem(Item item);
	
	
//	訂正・削除項目の取得
	public Item findItem(int id);
	
//	項目の訂正
	public void updateItem(Item item);
	
//	項目の削除
	public void deleteItem(int id);

	
}
