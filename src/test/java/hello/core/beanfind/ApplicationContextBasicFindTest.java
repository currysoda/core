package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService", MemberService.class);
//		System.out.println("memberService = " + memberService);
//		System.out.println("memberService.getClass() = " + memberService.getClass());
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class);
//		System.out.println("memberService = " + memberService);
//		System.out.println("memberService.getClass() = " + memberService.getClass());
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	// 구현에 의존하면 안돼기 때문에 interface로 조회하도록 유도 이거는 그냥 보여주는용도
	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2() {
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
//		System.out.println("memberService = " + memberService);
//		System.out.println("memberService.getClass() = " + memberService.getClass());
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 이름으로 조회X")
	void findBeanByNameX() {
//		MemberService memberService = ac.getBean("tempfailname", MemberService.class);
//		System.out.println("memberService = " + memberService);
//		System.out.println("memberService.getClass() = " + memberService.getClass());
		org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
				() -> ac.getBean("xxxxx", MemberService.class));
	}
}
