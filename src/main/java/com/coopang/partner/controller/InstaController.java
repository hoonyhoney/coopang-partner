package com.coopang.partner.controller;

import com.coopang.partner.service.InstaPostService;
import com.coopang.partner.service.StrollerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/insta")
public class InstaController {
  private final InstaPostService instaPostService;

  @GetMapping("/upload-post")
  public void uploadPost(){
    instaPostService.uploadPost();
  }
}
