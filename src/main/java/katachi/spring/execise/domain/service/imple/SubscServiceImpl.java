package katachi.spring.execise.domain.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.execise.domain.model.Subsc;
import katachi.spring.execise.domain.service.SubscService;
import katachi.spring.execise.repository.SubscMapper;

@Service
public class SubscServiceImpl implements SubscService {

	@Autowired
	SubscMapper subscMapper;
	
//	一覧画面取得
	@Override
	public List<Subsc> getSubscs(int userId) {
		
		return subscMapper.getSubscs(userId);
	}
		
	
//	サービス項目を登録
	@Override
	public int registSubsc(Subsc subsc) {
		
		return subscMapper.registSubsc(subsc);
	}
	
//	編集・削除項目の取得
	@Override
	public Subsc findOne(int id,int userId) {
		
		return subscMapper.findSubsc(id, userId);
	}
	
//	サービス項目の訂正
	@Override
	public void updateSubsc(Subsc subsc) {
		subscMapper.updateSubsc(subsc);
	}
	
//	サービス項目の削除
	@Override
	public void deleteSubsc(int id,int userId) {
		
		subscMapper.deleteSubsc(id,userId);
	}

	
}
