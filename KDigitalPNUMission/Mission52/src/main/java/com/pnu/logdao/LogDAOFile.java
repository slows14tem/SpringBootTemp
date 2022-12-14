package com.pnu.logdao;

import java.io.File;
import java.io.FileWriter;

import org.springframework.stereotype.Repository;

@Repository("LogDAOFile")
public class LogDAOFile implements LogDAO {

	@Override
	public void addlog(String method, String sql, boolean success) {
		
		try {
			File file = new File("log.txt");
			FileWriter fw = new FileWriter(file, true);
			fw.write(method + "," + sql + "," + success + "\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
