package katachi.spring.execise.domain.service;

import java.util.List;

import katachi.spring.execise.domain.model.Item;

public interface ItemService {

//	登録サービス一覧取得
	public List<Item> getItem(int userId) ;
}
