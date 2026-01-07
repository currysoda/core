package hello.core.scope;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
	
	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		
		//1. 조회: 호출할 때 마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();
		//2. 조회: 호출할 때 마다 객체를 생성
		MemberService memberService2 = appConfig.memberService();
		
		//참조값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		//memberService1 != memberService2
		assertThat(memberService1).isNotSameAs(memberService2);
	}
	
	@Test
	@DisplayName("싱글톤 컨테이너 사용")
	void singletonBeanFind() {
		
		// 스프링 컨테이너 생성
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
		
		// 싱글톤 확인
		MemberService memberservice1 = ac.getBean(MemberService.class);
		MemberService memberservice2 = ac.getBean(MemberService.class);
		System.out.println("memberservice1 = " + memberservice1);
		System.out.println("memberservice2 = " + memberservice2);
		assertThat(memberservice1).isSameAs(memberservice2);
		
		// 이거 교제 예제인데 위에 예제랑 다루는 빈이 달라서 이거 외에 예제 하나 더해봄
		SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);
		assertThat(singletonBean1).isSameAs(singletonBean2);

		// 종료
		ac.close();
	}

	@Scope("singleton")
	static class SingletonBean {
		@PostConstruct
		public void init() {
			System.out.println("---SingletonBean.init---");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("---SingletonBean.destroy---");
		}
		
		// 예제용 빈
		@Bean(name = "memberService")
		public MemberService memberService() {
			return new MemberServiceImpl(memberRepository());
		}
		
		@Bean(name = "memberRepository")
		public MemberRepository memberRepository() {
			return new MemoryMemberRepository();
		}
	}
}
