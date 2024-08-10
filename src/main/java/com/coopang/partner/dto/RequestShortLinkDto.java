package com.coopang.partner.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor()
@AllArgsConstructor
public class RequestShortLinkDto {
    List<String> coupangUrls;
    String subId;
}
