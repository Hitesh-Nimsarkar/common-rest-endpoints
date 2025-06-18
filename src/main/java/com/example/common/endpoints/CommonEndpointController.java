package com.example.common.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${common.endpoints.base-path:/api/common}")
public class CommonEndpointController {

  private final CustomizableEndpointService endpointService;

  public CommonEndpointController(CustomizableEndpointService endpointService) {
    this.endpointService = endpointService;
  }

  @GetMapping("/status")
  public String getStatus() {
    return "{\"status\":\"OK\",\"service\":\"common-library\"}";
  }

  @GetMapping("/version")
  public String getVersion() {
    return "{\"version\":\"1.0.0\"}";
  }

  @GetMapping("/status2")
  public String getStatus2() {
    return endpointService.getCustomStatus();
  }
}
