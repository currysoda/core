package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.spel.CodeFlow;

import static org.assertj.core.api.Assertions.assertThat;


public class SingletonWithPrototypeTest1 {

	@Test
	@DisplayName("프로토 타입 빈 동작 확인")
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	@DisplayName("싱글톤 빈 + 프로토 타입 빈 함께 사용할 때")
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
		
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	@RequiredArgsConstructor
	static class ClientBean {
		
		// private final PrototypeBean prototypeBean; // 생성 시점에 주입

		// @Autowired
		// private ApplicationContext ac; // 스프링 컨테이너 사용

		// @Autowired
		// public ClientBean(PrototypeBean prototypeBean) {
		// 	this.prototypeBean = prototypeBean;
		// }

		// public int logic() {
		// 	PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class); // 스프링 컨테이너에서 타입으로 호출
		// 	prototypeBean.addCount();
		// 	int count = prototypeBean.getCount();
		// 	return count;
		// }
		
		// ObjectFactory, ObjectProvider 예제
		
		private final ObjectProvider<PrototypeBean> objectProvider;
		
		public int logic() {
			PrototypeBean prototypeBean = objectProvider.getObject(); // ObjectProvider 가 찾아서 넣어준다.
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init :: " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
