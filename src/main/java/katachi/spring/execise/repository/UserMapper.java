package katachi.spring.execise.repository;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.execise.domain.model.Users;

@Mapper
public interface UserMapper {
	
	//ユーザ登録
	public int signup(Users user) ;
	
	//ログインユーザ取得
	public Users findLoginUser(String loginUser); 
}
