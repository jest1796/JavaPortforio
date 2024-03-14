package katachi.spring.execise.domain.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.execise.domain.model.Item;
import katachi.spring.execise.domain.service.ItemService;
import katachi.spring.execise.repository.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemMapper itemMapper;
	
//	一覧画面取得
	@Override
	public List<Item> getItem(int userId) {
		
		return itemMapper.getItem(userId);
	}
	
//	引落日までの日数更新
	@Override
	public void updateUntilDays(int userId) {
		
		itemMapper.updateUntilDays(userId);
	}
	
//	サービス項目を登録
	@Override
	public int registItem(Item item) {
		
		return itemMapper.registItem(item);
	}
	
//	編集・削除項目の取得
	@Override
	public Item findOne(int id) {
		
		return itemMapper.findItem(id);
	}
	
//	サービス項目の訂正
	@Override
	public void updateItem(Item item) {
		itemMapper.updateItem(item);
	}
	
//	サービス項目の削除
	@Override
	public void deleteItem(int id) {
		
		itemMapper.deleteItem(id);
	}

	

	

	
}
