package com.coopang.partner.service;

import com.coopang.partner.config.ChromiumDriver;
import com.coopang.partner.dto.RequestShortLinkDto;
import com.coopang.partner.entity.Product;
import com.coopang.partner.util.HmacGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

@Service
@Slf4j
@RequiredArgsConstructor
public class StrollerService {

  @Value("${spring.coopang.access-key}")
  private String ACCESS_KEY;
  @Value("${spring.coopang.secret-key}")
  private String SECRET_KEY;

  private final ChromiumDriver driver;
  @Value("${spring.partner.id}")
  private String partnerId;

  @Value("${spring.partner.password}")
  private String partnerPassword;

  private final RestTemplate restTemplate;
  public void deepLink(String originalUrl) throws IOException {
    System.out.println("originalUrl = " + originalUrl);
    String REQUEST_METHOD = "POST";
    String DOMAIN = "https://api-gateway.coupang.com";
    String URL = "/v2/providers/affiliate_open_api/apis/openapi/v1/deeplink";
    // Generate HMAC string
    String authorization = HmacGenerator.generate(REQUEST_METHOD,URL,SECRET_KEY,ACCESS_KEY);

    // Create the headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", authorization);
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

    RequestShortLinkDto requestShortLinkDto = new RequestShortLinkDto();
    List<String> originalList = new ArrayList<>();
    originalList.add(originalUrl);
    requestShortLinkDto.setCoupangUrls(originalList);
    requestShortLinkDto.setSubId("String");
    HttpEntity<RequestShortLinkDto> request = new HttpEntity<>(requestShortLinkDto, headers);

    ResponseEntity<String> response = restTemplate.exchange(DOMAIN + URL, HttpMethod.POST, request, String.class);
    System.out.println("data = " + response.getBody());
  }

  public void searchProduct(String keyword) throws IOException {
    String REQUEST_METHOD = "GET";
    String DOMAIN = "https://api-gateway.coupang.com";
    String URL = "/v2/providers/affiliate_open_api/apis/openapi/products/search?keyword="+keyword+"&limit=100";
    System.out.println("URL = " + URL);
    // Generate HMAC string
    String authorization = HmacGenerator.generate(REQUEST_METHOD,URL,SECRET_KEY,ACCESS_KEY);
    log.info("auth={}",authorization);
    // Create the headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", authorization);
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

    HttpEntity<RequestShortLinkDto> request = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(DOMAIN + URL, HttpMethod.GET, request, String.class);
    System.out.println("data = " + response.getBody());
  }

  private List<Product> scrapingPage(){
    List<Product> products = new ArrayList<>();
    return products;
  }

  public static HttpHeaders getDefaultHeaders(String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Authorization", auth);
    return headers;
  }

  public void scrapingProduct() {
      try {
        driver.open("https://login.coupang.com/login/login.pang?rtnUrl=https%3A%2F%2Fpartners.coupang.com%2Fapi%2Fv1%2Fpostlogin");
        driver.wait(5000);
        WebElement usernameField = driver.findElementByName("email");
        WebElement passwordField = driver.findElementByName("password");
        usernameField.sendKeys(partnerId);
        passwordField.sendKeys(partnerPassword);

        //로그인 버튼 클릭
        WebElement loginButton = driver.findOneByXpath("//button[@type='submit']");
        loginButton.click();

        //로그인 후 대기
        driver.wait(5000);

        //파일 업로드 url을 이미지로 변경
        WebElement keywordInput = driver.findOneByXpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/div/div/div[2]/div/div/div/div/div/div/div/div[1]/div/div/div/div/span/input");
        keywordInput.sendKeys("부가부 비6");

        //검색 버튼 클릭
        List<WebElement> searchButton = driver.findAllByClassName("search-button");
        searchButton.get(0).click();

        List<WebElement> productList = driver.findAllByClassName("product-item");
        for (WebElement product : productList) {


        }


        driver.wait(2000);
        WebElement nextButton2 = driver.findOneByXpath("/html/body/div[6]/div[1]/div/div[3]/div/div/div/div/div/div/div/div[1]/div/div/div/div[3]/div/div");
        nextButton2.click();

        //캡션 입력
        WebElement captionField = driver.findOneByCss("div[aria-label='문구를 입력하세요...']");
        captionField.sendKeys("test");

        //공유 버튼 클릭
        WebElement shareButton = driver.findOneByXpath("/html/body/div[6]/div[1]/div/div[3]/div/div/div/div/div/div/div/div[1]/div/div/div/div[3]/div/div");
        //WebElement shareButton = driver.findOneByXpath("//div[contains(text(),'공유하기')]");
        shareButton.click();
      }finally {
        //driver.quit();
      }
  }
}
