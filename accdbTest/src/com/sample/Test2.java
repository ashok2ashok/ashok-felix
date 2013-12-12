package com.sample;

import java.io.File;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

public class Test2 {
	public static void main(String[] args) throws Exception {
		CryptCodecProvider c = new CryptCodecProvider("anilashdba");
		Database db = new DatabaseBuilder(new File("D:\\KTPL\\ktpldbs\\bdb.accdb")).setCodecProvider(c).open();
		  //.setCodecProvider(new CryptCodecProvider("MyDbPassword"))
		 // .open();
	}
}
