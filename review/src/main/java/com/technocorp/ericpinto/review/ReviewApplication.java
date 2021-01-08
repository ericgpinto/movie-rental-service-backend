package com.technocorp.ericpinto.review;

import com.technocorp.ericpinto.review.builder.entity.BankAccount;
import com.technocorp.ericpinto.review.builder.entity.BankAccountBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);

//		BankAccount account = new BankAccount(123L, "Bart", "spring" , 100.00, 2.5);
//		BankAccount anotherAccount = new BankAccount(123L, "Homer",null , 2.5, 100.00);
//
//		System.out.println(account);
//		System.out.println(anotherAccount);

		BankAccountBuilder accountBuilder = new BankAccountBuilder.Builder(1234L)
				.withOwner("Marge")
				.atBranch("Springfield")
				.openingBalance(100.00)
				.atRate(2.5)
				.build();

		System.out.println(accountBuilder);
	}

}
