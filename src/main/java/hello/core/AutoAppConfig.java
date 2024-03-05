package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 컴포넌트 스캔 공부용
//
@Configuration
@ComponentScan(
		// AppConfig 예제 유지를 위해 필터를 설정해 제외시켜둔다
		// 원래는 이렇게 하지 않지만 이전 예제 유지를 위해 넣은것
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
	// 중복 충돌 예제
//	@Bean(name = "memoryMemberRepository")
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//	}
}
