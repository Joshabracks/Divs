package com.josh.divs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DivsApplicationTests {

	@Test
	public void contextLoads() {
		NameGenerator gen = new NameGenerator();
		String div1 = gen.name();
		String div2 = gen.name();
		String div3 = gen.name(div1);
		String div4 = gen.name(div2);
		String div5 = gen.name(div3, div4);
		String div6 = gen.name(div3, div4);
		String div7 = gen.name(div3, div4);
		
		System.out.println("First gen: " + div1 + "|  Child: " + div3);
		System.out.println("First gen: " + div2 + "|  Child: " + div4);
		System.out.println(div3 + " & " + div4 + " are Parents of...");
		System.out.println("Siblings: " + div5 + ", " + div6 + ", " + div7);
		
	}
		
}
