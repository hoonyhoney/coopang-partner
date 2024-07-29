package com.coopang.partner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Product extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pid;
	@Column(length = 1000, nullable = false)
	private String title;
	private Long price;
	@Column(columnDefinition = "text")
	private String link;

	@Column(columnDefinition = "text")
	private String saleLink;

	@Column(columnDefinition = "text")
	private String imgSrc;

	@Column(columnDefinition = "text")
	private String content;

	@Builder
	public Product(Long id, String pid, String title, Long price, String link, String saleLink,
			String imgSrc, String content) {
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.price = price;
		this.link = link;
		this.saleLink = saleLink;
		this.imgSrc = imgSrc;
		this.content = content;
	}
}
