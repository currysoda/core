package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ComponentFilterAppConfigTest {

	@Test
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ac.getBean("beanA", BeanA.class);
		Assertions.assertThat(beanA).isNotNull();

//		BeanB beanB = ac.getBean("beanB", BeanB.class); 하면안됨
		assertThrows(
				NoSuchBeanDefinitionException.class,
				() -> ac.getBean("beanB", BeanB.class)
		);
	}

	@Configuration
	@ComponentScan(
			includeFilters = @Filter(classes = MyIncludeComponent.class),
			excludeFilters = @Filter(classes = MyExcludeComponent.class)
	)
	static class ComponentFilterAppConfig {

	}
}
