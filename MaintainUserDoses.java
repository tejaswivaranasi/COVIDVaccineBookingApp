package nonGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MaintainUserDoses {
	ArrayList<String> emaillist = new ArrayList<String>();
	ArrayList<String> doselist = new ArrayList<String>();
	ArrayList<String> dosenamelist = new ArrayList<String>();
	ArrayList<String> datelist = new ArrayList<String>();
	ArrayList<String> timelist = new ArrayList<String>();
	
	ArrayList<String> emaillist2 = new ArrayList<String>();
	ArrayList<String> doselist2 = new ArrayList<String>();
	ArrayList<String> dosenamelist2 = new ArrayList<String>();
	ArrayList<String> datelist2 = new ArrayList<String>();
	ArrayList<String> timelist2 = new ArrayList<String>();
	
	public String email;
	public String dose;
	public String dosename;
	public String date;
	public String time;
	public Boolean signIn = false;
	public Boolean duplicate = false;
	public int situation = 0;
	public Boolean incorrectName = false;
	public Boolean incorrectNumber = false;
	public Boolean enter = false;
	public Boolean check = false;
	
	public MaintainUserDoses(String email2, String dose2, String dosename2) {
		this.email = email2;
		this.dose = dose2;
		this.dosename = dosename2;
		readDBCheck();
	}
	
	public MaintainUserDoses(String email2, String dose2, String dosename2, String date2, String time2) {
		this.email = email2;
		this.dose = dose2;
		this.dosename = dosename2;
		this.date = date2;
		this.time = time2;
		readDB();
	}
	
	private void readDBCheck() {
		emaillist = new ArrayList<String>();
		doselist = new ArrayList<String>();
		dosenamelist = new ArrayList<String>();
		
		String path = System.getProperty("user.dir");
	    String path2 = path + "/src/nonGUI/DosesInfo";
		File login = new File(path2);
		try {
			BufferedReader br = new BufferedReader(new FileReader(login));
			String str;
			while((str = br.readLine()) != null) {
				String[] words = str.split(",");
				for(int i = 0; i < words.length; i++) {
					if(i == 0) {
						emaillist.add(words[i]);
					}
					else if(i == 1) {
						dosenamelist.add(words[i]);
					}
					else if(i == 2) {
						doselist.add(words[i]);
					}
				}
			}
			br.close();
			for(int i = 0; i < emaillist.size(); i++) {
				if(emaillist.get(i).equals(this.email)) {
					this.check = true;
					if(dosenamelist.get(i).equals(this.dosename)) {
						if(doselist.get(i).equals("1") && this.dose.equals("1")) {
							this.situation = 2;
						}
						else if(doselist.get(i).equals("2")) {
							this.situation = 4;
						}
					}
					else {
						this.incorrectName = true;
					}
				}
			}
			if(!this.check) {
				if(this.dose.equals("1")) {

				}
				else {
					this.incorrectNumber = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readDB() {
		emaillist = new ArrayList<String>();
		doselist = new ArrayList<String>();
		dosenamelist = new ArrayList<String>();
		datelist = new ArrayList<String>();
		timelist = new ArrayList<String>();
		
		String path = System.getProperty("user.dir");
	    String path2 = path + "/src/nonGUI/DosesInfo";
		File login = new File(path2);
		try {
			BufferedReader br = new BufferedReader(new FileReader(login));
			String str;
			while((str = br.readLine()) != null) {
				String[] words = str.split(",");
				for(int i = 0; i < words.length; i++) {
					if(i == 0) {
						emaillist.add(words[i]);
					}
					else if(i == 1) {
						dosenamelist.add(words[i]);
					}
					else if(i == 2) {
						doselist.add(words[i]);
					}
					else if(i == 3) {
						datelist.add(words[i]);
					}
					else if(i == 4) {
						timelist.add(words[i]);
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
	    String path2 = path + "/src/nonGUI/DosesInfo";
		File login = new File(path2);
		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter(login));
			if(true){//this.email != null && this.dosename != null && this.dose != null) {
				if(!emaillist.isEmpty()) {
					for(int i = 0; i < emaillist.size(); i++) {
						if(emaillist.get(i).equals(this.email)) {
							this.enter = true;
							if(dosenamelist.get(i).equals(this.dosename)) {
								if(doselist.get(i).equals("0") && this.dose.equals("1")) {
									bw.write(this.email + "," + this.dosename + "," + this.dose + "," + this.date + "," + this.time + "\n");
								}
								else if(doselist.get(i).equals("0") && this.dose.equals("2")) {
									this.situation = 1;
									bw.write(emaillist.get(i) + "," + dosenamelist.get(i) + "," + doselist.get(i) + "," + datelist.get(i) + "," + timelist.get(i) + "\n");
								}
								else if(doselist.get(i).equals("1") && this.dose.equals("1")) {
									this.situation = 2;
									bw.write(emaillist.get(i) + "," + dosenamelist.get(i) + "," + doselist.get(i) + "," + datelist.get(i) + "," + timelist.get(i) + "\n");
								}
								else if(doselist.get(i).equals("1") && this.dose.equals("2")) {
									bw.write(this.email + "," + this.dosename + "," + this.dose + "," + this.date + "," + this.time + "\n");
								}
								else if(doselist.get(i).equals("2")) {
									this.situation = 4;
									bw.write(emaillist.get(i) + "," + dosenamelist.get(i) + "," + doselist.get(i) + "," + datelist.get(i) + "," + timelist.get(i) + "\n");
								}
							}
							else {
								this.incorrectName = true;
								bw.write(emaillist.get(i) + "," + dosenamelist.get(i) + "," + doselist.get(i) + "," + datelist.get(i) + "," + timelist.get(i) + "\n");
							}
						}
						else {
							bw.write(emaillist.get(i) + "," + dosenamelist.get(i) + "," + doselist.get(i) + "," + datelist.get(i) + "," + timelist.get(i) + "\n");
						}
					}
					if(!this.enter) {
						if(this.dose.equals("1")) {
							bw.write(this.email + "," + this.dosename + "," + this.dose + "," + this.date + "," + this.time + "\n");
						}
						else {
							this.incorrectNumber = true;
						}
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
