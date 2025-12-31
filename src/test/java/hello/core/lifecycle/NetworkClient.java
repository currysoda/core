package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

// 빈 생명주기 콜백
// 인터페이스 예제 implements InitializingBean, DisposableBean
// 설정 정보 예제
// 어노테이션 예제
// 이렇게 예제가 있다.
@Component
public class NetworkClient  {

	private String url;

	public NetworkClient() {
		System.out.println("생성자 호출, url = " + url);

	}

	public void setUrl(String url) {
		this.url = url;
	}

	// 서비스 시작 호출
	public void connect() {
		System.out.println("connect : " + url);
	}

	public void call(String message) {
		System.out.println("call : " + url + " message = " + message);
	}

	// 서비스 종료 호출
	public void disconnect() {
		System.out.println("close : " + url);
	}

	// 의존관계 주입이 끝나면
	@PostConstruct
	public void init() throws Exception {
		System.out.println("NetworkClient.afterPropertiesSet");
		connect();
		call("초기화 연결 메시지");
	}

	// 빈 소멸시
	@PreDestroy
	public void close() throws Exception {
		System.out.println("NetworkClient.destroy");
		disconnect();
	}
	
	
 }
