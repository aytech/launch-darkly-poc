package com.demo.launchdarkly;

import com.demo.launchdarkly.configuration.LaunchDarklyClient;
import com.demo.launchdarkly.rest.LaunchDarklyController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.launchdarkly.configuration.Global.REACT_DEMO_FLAG;
import static com.demo.launchdarkly.configuration.Global.SPRING_DEMO_FLAG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class LaunchDarklyApplicationTests {

	@Mock
	private LaunchDarklyClient launchDarklyClient;

	@InjectMocks
	private LaunchDarklyController launchDarklyController;

	@Test
	void shouldReturnFlagsValues() {
		when(launchDarklyClient.getCurrentValue(REACT_DEMO_FLAG)).thenReturn(true);
		when(launchDarklyClient.getCurrentValue(SPRING_DEMO_FLAG)).thenReturn(true);
		assertThat(launchDarklyController.getFlags().isReactDemoFlag()).isEqualTo(true);
		assertThat(launchDarklyController.getFlags().isSpringDemoFlag()).isEqualTo(true);
		when(launchDarklyClient.getCurrentValue(REACT_DEMO_FLAG)).thenReturn(true);
		when(launchDarklyClient.getCurrentValue(SPRING_DEMO_FLAG)).thenReturn(true);
		assertThat(launchDarklyController.getFlags().isReactDemoFlag()).isEqualTo(false);
		assertThat(launchDarklyController.getFlags().isSpringDemoFlag()).isEqualTo(false);
	}

}
