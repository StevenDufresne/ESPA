package application;

import java.io.*;
import java.security.*;
import java.sql.*;

public class LoginManager {
	public String getHashedKey(String key) {
		String res = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(key.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			res = (hexString.toString());

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return res;
	}
	public Boolean validator(String username, String passwd) {
		boolean tf = false;
		try {
			String key ="::HEADER::HEHE" + passwd + "::TAIL::HEHE";
			String hashedKey1 = getHashedKey(key);
			String hashedKey2 = null;
			Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/espa?useSSL=false", "root", "sudo");
			Statement statement = dbConnection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM userinfokeystore WHERE username='" + username + "'");
			while(resultset.next()) {
				hashedKey2 = resultset.getString("somekindofletters");
			}
			if(hashedKey1.equals(hashedKey2)) {
				tf=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tf;
	}
}
