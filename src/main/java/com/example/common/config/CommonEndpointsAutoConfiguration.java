package com.example.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.common.endpoints.CommonEndpointController;
import com.example.common.endpoints.CustomizableEndpointService;

@Configuration
@EnableConfigurationProperties(CommonEndpointProperties.class)
@ConditionalOnProperty(prefix = "common.endpoints", name = "enabled", havingValue = "true",
    matchIfMissing = true)
public class CommonEndpointsAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public CustomizableEndpointService defaultEndpointService() {
    return new DefaultCustomizableEndpointService();
  }

  @Bean
  public CommonEndpointController commonEndpointController(
      CustomizableEndpointService endpointService) {
    return new CommonEndpointController(endpointService);
  }

  // Default implementation if none provided
  private static class DefaultCustomizableEndpointService implements CustomizableEndpointService {
    @Override
    public String getCustomStatus() {
      return "{\"status\":\"DEFAULT\",\"message\":\"No custom implementation provided\"}";
    }


  }
}
