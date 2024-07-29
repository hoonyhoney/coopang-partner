package com.coopang.partner.controller;

import com.coopang.partner.service.StrollerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/collector")
public class CollectorController {
  private final StrollerService strollerService;

  @GetMapping("/stroller")
  public void getStroller(){
    strollerService.collectStroller();
  }
}
