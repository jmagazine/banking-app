package com.example.banking;

import com.example.banking.account.AccountController;
import com.example.banking.account.investment.InvestmentController;
import com.example.banking.user.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankingApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private AccountController accountController;

	@Autowired
	private InvestmentController investmentController;

	@Test
	void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
		assertThat(accountController).isNotNull();
		assertThat(investmentController).isNotNull();
	}
}
