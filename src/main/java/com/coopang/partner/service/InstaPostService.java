package com.coopang.partner.service;


import com.coopang.partner.config.ChromiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstaPostService {
  private final ChromiumDriver driver;
  @Value("${spring.insta.username}")
  private String username;

  @Value("${spring.insta.password}")
  private String password;


   public void uploadPost(){
     try {
       driver.open("https://www.instagram.com/accounts/login/");
       driver.wait(5000);
       WebElement usernameField = driver.findElementByName("username");
       WebElement passwordField = driver.findElementByName("password");
       usernameField.sendKeys(username);

       //로그인 버튼 클릭
       WebElement loginButton = driver.findOneByXpath("//button[@type='submit']");
       loginButton.click();

       //로그인 후 대기
       driver.wait(5000);

       //"+" 버튼 클릭하여 게시물 작성 페이지로 이동
       WebElement newPostButton = driver.findOneByCss("svg[aria-label='New Post']");
       newPostButton.click();

       //파일 업로드 url을 이미지로 변경
       WebElement fileInput = driver.findOneByXpath("//input[@type='file']");
       fileInput.sendKeys("/path/to/your/image.jpg");

       //다음 버튼 클릭
       WebElement nextButton = driver.findOneByXpath("//button[contains(text(),'Next')]");
       nextButton.click();

       //캡션 입력
       WebElement captionField = driver.findOneByTag("textarea");
       captionField.sendKeys("your caption here");

       //공유 버튼 클릭
       WebElement shareButton = driver.findOneByXpath("//button[contains(text(),'Share')]");
       shareButton.click();
     }finally {
       driver.quit();
     }
   }
}
