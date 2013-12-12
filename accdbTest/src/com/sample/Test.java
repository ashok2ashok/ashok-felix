package com.sample;
import java.io.File;
import java.io.IOException;
import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.impl.CodecProvider;

public class Test {
	public static void main(String[] args) throws IOException {
		CryptCodecProvider cryptProvider = new CryptCodecProvider("anilashdba");
		Database db = new DatabaseBuilder(new File("D:\\KTPL\\ktpldbs\\bdb.accdb")).setCodecProvider((CodecProvider) cryptProvider).open();
		Table table = db.getTable("Checklist");
		for(Row row : table) {
		  System.out.println("Column 'Company' has value: " + row.get("Company"));
		}
	}
}
