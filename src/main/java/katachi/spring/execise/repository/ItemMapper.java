package katachi.spring.execise.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.execise.domain.model.Item;

@Mapper
public interface ItemMapper {

//	項目一覧取得
	public List<Item> getItem(int userId);

}
