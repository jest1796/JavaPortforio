package katachi.spring.execise.repository;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.execise.domain.model.LoginUser;

@Mapper
public interface UserMapper {
	public int insertOne(LoginUser loginuser);
}
