package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

// DiscountPolicy 인터페이스 두개 전부 @Component 선언시 오류 발생
// @Primary 지정 해주거나 @Qualified("") 로 지정해서 호출할것
@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if(member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		}
		else {
			return 0;
		}
	}
}
