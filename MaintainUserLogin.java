package nonGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MaintainUserLogin {
	ArrayList<String> firstnamelist = new ArrayList<String>();
	ArrayList<String> lastnamelist = new ArrayList<String>();
	ArrayList<String> doblist = new ArrayList<String>();
	ArrayList<String> emaillist = new ArrayList<String>();
	ArrayList<String> passwordlist = new ArrayList<String>();
	ArrayList<String> sexlist = new ArrayList<String>();
	ArrayList<String> occupationlist = new ArrayList<String>();
	ArrayList<String> educationlist = new ArrayList<String>();
	ArrayList<String> postlist = new ArrayList<String>();
	
	ArrayList<String> firstnamelist2 = new ArrayList<String>();
	ArrayList<String> lastnamelist2 = new ArrayList<String>();
	ArrayList<String> doblist2 = new ArrayList<String>();
	ArrayList<String> emaillist2 = new ArrayList<String>();
	ArrayList<String> passwordlist2 = new ArrayList<String>();
	ArrayList<String> sexlist2 = new ArrayList<String>();
	ArrayList<String> occupationlist2 = new ArrayList<String>();
	ArrayList<String> educationlist2 = new ArrayList<String>();
	ArrayList<String> postlist2 = new ArrayList<String>();
	
	public String firstname;
	public String lastname;
	public String dob;
	public String email;
	public String password;
	public String sex;
	public String occupation;
	public String education;
	public String post;
	public Boolean signIn = false;
	public Boolean duplicate = false;
	
	public MaintainUserLogin(String firstname2, String lastname2, String dob2, String email2, String password2, String sex2, String occupation2, String education2, String post2) {
		this.firstname = firstname2;
		this.lastname = lastname2;
		this.dob = dob2;
		this.email = email2;
		this.password = password2;
		this.sex = sex2;
		this.occupation = occupation2;
		this.education = education2;
		this.post = post2;
		readDB();
	}
	
	public MaintainUserLogin(String email2, String password2) {
		this.email = email2;
		this.password = password2;
		readDBLogin();
	}
	
	private void readDB() {
		firstnamelist = new ArrayList<String>();
		lastnamelist = new ArrayList<String>();
		doblist = new ArrayList<String>();
		emaillist = new ArrayList<String>();
		passwordlist = new ArrayList<String>();
		sexlist = new ArrayList<String>();
		occupationlist = new ArrayList<String>();
		educationlist = new ArrayList<String>();
		postlist = new ArrayList<String>();
		
		String path = System.getProperty("user.dir");
	    String path2 = path + "/src/nonGUI/UserLoginInfo";
		File login = new File(path2);
		try {
			BufferedReader br = new BufferedReader(new FileReader(login));
			String str;
			while((str = br.readLine()) != null) {
				String[] words = str.split(",");
				for(int i = 0; i < words.length; i++) {
					if(i == 0) {
						firstnamelist.add(words[i]);
					}
					else if(i == 1) {
						lastnamelist.add(words[i]);
					}
					else if(i == 2) {
						doblist.add(words[i]);
					}
					else if(i == 3) {
						emaillist.add(words[i]);
					}
					else if(i == 4) {
						passwordlist.add(words[i]);
					}
					else if(i == 5) {
						sexlist.add(words[i]);
					}
					else if(i == 6) {
						occupationlist.add(words[i]);
					}
					else if(i == 7) {
						educationlist.add(words[i]);
					}
					else if(i == 8) {
						postlist.add(words[i]);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeDB();
	}
	
	private void writeDB() {
		String path = System.getProperty("user.dir");
	    String path2 = path + "/src/nonGUI/UserLoginInfo";
		File login = new File(path2);
		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter(login));
			if(this.firstname != null && this.lastname != null && this.dob != null && this.email != null && this.password != null && this.sex != null && this.occupation != null && this.education != null && this.post != null) {
				if(!firstnamelist.isEmpty()) {
					for(int i = 0; i < firstnamelist.size(); i++) {
						bw.write(firstnamelist.get(i) + "," + lastnamelist.get(i) + "," + doblist.get(i) + "," + emaillist.get(i) + "," + passwordlist.get(i) + "," + sexlist.get(i) + "," + occupationlist.get(i) + "," + educationlist.get(i) + "," + postlist.get(i) + "\n");
					}
					if(this.emaillist.contains(this.email)) {
						this.duplicate = true;
					}
					else {
						bw.write(this.firstname + "," + this.lastname + "," + this.dob + "," + this.email + "," + this.password + "," + this.sex + "," + this.occupation + "," + this.education + "," + this.post + "\n");
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readDBLogin() {
		firstnamelist2 = new ArrayList<String>();
		lastnamelist2 = new ArrayList<String>();
		doblist2 = new ArrayList<String>();
		emaillist2 = new ArrayList<String>();
		passwordlist2 = new ArrayList<String>();
		sexlist2 = new ArrayList<String>();
		occupationlist2 = new ArrayList<String>();
		educationlist2 = new ArrayList<String>();
		postlist2 = new ArrayList<String>();
		
		String path = System.getProperty("user.dir");
	    String path2 = path + "/src/nonGUI/UserLoginInfo";
		File login = new File(path2);
		try {
			BufferedReader br = new BufferedReader(new FileReader(login));
			String str;
			while((str = br.readLine()) != null) {
				String[] words = str.split(",");
				for(int i = 0; i < words.length; i++) {
					if(i == 0) {
						firstnamelist2.add(words[i]);
					}
					else if(i == 1) {
						lastnamelist2.add(words[i]);
					}
					else if(i == 2) {
						doblist2.add(words[i]);
					}
					else if(i == 3) {
						emaillist2.add(words[i]);
					}
					else if(i == 4) {
						passwordlist2.add(words[i]);
					}
					else if(i == 5) {
						sexlist2.add(words[i]);
					}
					else if(i == 6) {
						occupationlist2.add(words[i]);
					}
					else if(i == 7) {
						educationlist2.add(words[i]);
					}
					else if(i == 8) {
						postlist2.add(words[i]);
					}
				}
			}
			br.close();
			for(int j = 0; j < firstnamelist2.size(); j++) {
//				System.out.println(this.email + " " + this.password);
//				System.out.println(this.emaillist2.get(j) + " " + this.passwordlist2.get(j));
				if(emaillist2.get(j).equals(this.email) && passwordlist2.get(j).equals(this.password)) {
					this.signIn = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
