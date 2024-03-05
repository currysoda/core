package hello.core;

// Lombok 예제

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HelloLombok {

	private String name;
	private int age;

	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok();
		helloLombok.setName("asdf");

		String name = helloLombok.getName();
		System.out.println("name = " + name);
	}
}
