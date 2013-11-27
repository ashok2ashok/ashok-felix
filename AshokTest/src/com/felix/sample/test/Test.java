package com.felix.sample.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
	}
}
