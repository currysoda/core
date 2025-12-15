package hello.core.singleton;

import lombok.Getter;

public class SingletonService {
	
	// getInstance로 이미 만들어진 한 개를 계속 반환하는 식으로 운용한다.
	@Getter
	private static final SingletonService instance = new SingletonService();
	
	// 생성자를 private로 막아서 생성되는 것 자체를 막는다
	private SingletonService() {

	}

	public void logic() {
		System.out.println("싱글톤 객체 사용 로직");
	}
}
