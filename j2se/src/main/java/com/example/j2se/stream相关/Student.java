package com.example.j2se.stream相关;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxi created on 2020/6/10 12:03 AM
 * @version v1.0
 *
 */
@Data
public class Student {
	private String name;
	private Set<String> book;
	public void addBook(String book) {
		if (this.book == null) {
			this.book = new HashSet<>();
		}
		this.book.add(book);
	}
}
