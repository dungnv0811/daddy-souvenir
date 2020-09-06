package com.dvnguyen.daddysouvenir;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginUseCaseIntegrationTest {

	private static final String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
	public static final String LOGIN_PATH = "/login";
	public static final String X_FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
	public static final String LOGIN_POST_CONTENT = "username=%s&password=%s";
	public static final String SECURITY_DEFAULT_USERNAME_CONFIG_KEY = "security.user.name";
	public static final String SECURITY_DEFAULT_PASSWORD_CONFIG_KEY = "security.user.password";
	public static final int LOGIN_SUCCESS_RESPONSE_CODE = 302;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Environment env;

	@Test
	void loginWithDefaultAccount() throws Exception {
		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

		String defaultUsername = env.getProperty(SECURITY_DEFAULT_USERNAME_CONFIG_KEY);
		String defaultPassword = env.getProperty(SECURITY_DEFAULT_PASSWORD_CONFIG_KEY);
		String postContent = String.format(LOGIN_POST_CONTENT, defaultUsername, defaultPassword);
		this.mockMvc.perform(
				post(LOGIN_PATH)
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken())
						.contentType(X_FORM_CONTENT_TYPE)
						.content(postContent))
				.andExpect(status().is(LOGIN_SUCCESS_RESPONSE_CODE));
	}

}
