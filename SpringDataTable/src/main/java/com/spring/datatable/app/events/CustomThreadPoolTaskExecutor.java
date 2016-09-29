package com.spring.datatable.app.events;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
	private static final long serialVersionUID = 1L;

	public CustomThreadPoolTaskExecutor() {
	}

	@Override
	public void execute(final Runnable task) {
		final Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
		super.execute(new Runnable() {
			public void run() {
				try {
					this.bindSecurityContext(authenticated);
					task.run();
				} finally {
					SecurityContextHolder.clearContext();
				}
			}

			private void bindSecurityContext(Authentication authenticated) {
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authenticated);
				SecurityContextHolder.setContext(securityContext);
			}
		});
	}
}
