package katachi.spring.execise.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
	
//	@Bean
//	public isValidDate isValidDate() {
//		return new isValidDate();
//	}
}
