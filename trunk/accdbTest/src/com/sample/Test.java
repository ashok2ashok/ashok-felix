package com.sample;


import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class Test {
	public static void main(String[] args) throws IOException {
		CryptCodecProvider cryptProvider = new CryptCodecProvider("MyDbPassword");
		Database db = new DatabaseBuilder(new File("D:\\KTPL\\ktpldbs\\Karlo Transport  Software.accdb")).setCodecProvider((CodecProvider) cryptProvider).open();
		Table table = db.getTable("Products");
//		Table table = DatabaseBuilder.open(new File("D:\\KTPL\\ktpldbs\\Karlo Transport  Software.accdb")).getTable("Products");
		for(Row row : table) {
		  System.out.println("Column 'a' has value: " + row.get("ID"));
		}
	}
}
