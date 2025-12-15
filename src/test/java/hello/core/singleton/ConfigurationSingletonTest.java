package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigurationSingletonTest {

	@Test
	@DisplayName("스프링 컨테이너의 싱글톤 관리 확인")
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		
		//모두 같은 인스턴스를 참고하고 있다.
		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		MemberRepository memberRepository3 = memberRepository;
		System.out.println("memberService -> memberRepository1 = " + memberRepository1);
		System.out.println("orderService -> memberRepository2 = " + memberRepository2);
		System.out.println("memberRepository -> memberRepository3 = " + memberRepository3);
//		System.out.println(memberRepository.getClass());

		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository3);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository3);
	}

	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);

		System.out.println("Bean = " + bean.getClass());
	}
}
