package com.sample;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class Test {
	public static void main(String[] args) throws IOException {
		Table table = DatabaseBuilder.open(new File("C:\\Users\\agoli\\Documents\\Northwind.accdb")).getTable("Products");
		for(Row row : table) {
		  System.out.println("Column 'a' has value: " + row.get("ID"));
		}
	}
}
