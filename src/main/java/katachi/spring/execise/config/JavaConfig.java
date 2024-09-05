package katachi.spring.execise.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

	/**
	 * マッピング
	 * @return Model
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
}
