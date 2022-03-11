package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BillboardData implements Serializable {
	private ArrayList<Billboard> billboards;
	private ArrayList<Billboard> dangerReport;
	private final double DANGEROUS_AREA = 200000;

	public BillboardData() {
		billboards = new ArrayList<>();
		loadDataNormal();
	}
	
	@SuppressWarnings("unchecked")
	public void loadDataNormal() {
		try {
			File file = new File("data/BillboardsData.temp");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			billboards = (ArrayList<Billboard>) object;
			
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDataNormal() {
		try {
			File file = new File("data/BillboardsData.temp");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(billboards);
			
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadDatos(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		
		boolean firstLine = true;
		
		while(line != null) {
			if(!firstLine) {
				String[] parts = line.split("\\|");
				String billboardInfo = parts[0] + "++" + parts[1] + "++" + parts[2] + "++" + parts[3];
				
				addBillboard(billboardInfo);
			}
			line = br.readLine();
			firstLine = false;
		}
		
		br.close();
	}
	
	public void addBillboard(String billboardInfo) {
		String[] parts = billboardInfo.split("\\++");
		
		double width = Double.parseDouble(parts[0]);
		double height = Double.parseDouble(parts[1]);
		boolean inUse = Boolean.parseBoolean(parts[2]);
		String brand = parts[3];
		
		Billboard newBillboard = new Billboard(width, height, inUse, brand);
		
		billboards.add(newBillboard);
		saveDataNormal();
	}
	
	public String billboardsToString() {
		String info = "W	H	inUse	Brand\n";
		
		for (int i = 0; i < billboards.size(); i++) {
			info += billboards.get(i).getWidth() + "	" + billboards.get(i).getHeight() + "	" +
					billboards.get(i).isInUse() + "	" + billboards.get(i).getBrand() + "\n";
		}
		
		info += "Total: " + billboards.size();
		
		return info;
	}
	
	public void exportDangerousReport() throws IOException {
		dangerReport = new ArrayList<>();
		
		for (int i = 0; i < billboards.size(); i++) {
			if(billboards.get(i).getArea() >= DANGEROUS_AREA) {
				dangerReport.add(billboards.get(i));
			}
		}
		
		PrintWriter pw = new PrintWriter("data/report.txt");
		
		pw.println(dangerousBillboardsToString());
		
		pw.close();
	}
	
	public String dangerousBillboardsToString() {
		String info = "===========================\r\n"
				+ "DANGEROUS BILLBOARD REPORT\r\n"
				+ "===========================\r\n"
				+ "The dangerous billboard are:\r\n";
	
		for (int i = 0; i < dangerReport.size(); i++) {
			info += (i+1) + ". Billboard <" + dangerReport.get(i).getBrand() + "> with area <" + 
					dangerReport.get(i).getArea() + ">\n";
		}
		
		return info;
	}
}
