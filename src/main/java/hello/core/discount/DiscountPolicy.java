package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
	/*
	 * @return 할인 대상 금액 (직접 할인 하는 금액, 할인된 금액 X)
	 */
	int discount(Member member, int prince);
}
