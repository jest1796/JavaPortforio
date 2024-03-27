package katachi.spring.execise.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.execise.domain.model.Subscs;

@Mapper
public interface SubscMapper {

//	項目一覧取得
	public List<Subscs> getSubscs(int userId);
	
//	引落日までの日数更新
	public void updateUntilDays(int userId);
	
//	項目の登録
	public int registSubsc(Subscs subsc);
	
	
//	訂正・削除項目の取得
	public Subscs findSubsc(int id,int userId);
	
//	項目の訂正
	public void updateSubsc(Subscs subsc);
	
//	項目の削除
	public void deleteSubsc(int id,int userId);

	
}
