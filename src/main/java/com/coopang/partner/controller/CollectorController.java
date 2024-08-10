package com.coopang.partner.controller;

import com.coopang.partner.service.StrollerService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/collector")
public class CollectorController {
  private final StrollerService strollerService;

  @GetMapping("/deep-link")
  public void getStroller(String originalUrl) throws IOException, ParseException {
    strollerService.deepLink(originalUrl);
  }

  @GetMapping("/products/search")
  public void searchProduct(String keyword) throws IOException, ParseException {
    strollerService.searchProduct(keyword);
  }

  @GetMapping("/scraping")
  public void scrapingProduct() throws IOException, ParseException {
    strollerService.scrapingProduct();
  }
}
