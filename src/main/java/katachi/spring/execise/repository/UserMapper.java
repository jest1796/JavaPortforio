package katachi.spring.execise.repository;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.execise.domain.model.MUser;

@Mapper
public interface UserMapper {
	
//	ユーザ登録
	public int signup(MUser user) ;
	
//	ログインユーザ取得
	public MUser findLoginUser(String loginUser); 
}
