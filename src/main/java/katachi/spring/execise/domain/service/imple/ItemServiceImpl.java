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
	
	@Override
	public List<Item> getItem(int userId) {
		
		return itemMapper.getItem(userId);
	}

}
