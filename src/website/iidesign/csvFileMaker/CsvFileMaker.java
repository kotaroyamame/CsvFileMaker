package website.iidesign.csvFileMaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileMaker {

	private String filepath;
	private String filename;

	public CsvFileMaker(String path, String filename) {
		this.setFilepath(path);
		this.setFilename(filename);
	}

	public void filepathSet(String filepath) {
		this.filepath = filepath;
	}

	public String filepathGet() {
		return this.filepath;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void writeFile(String value) throws IOException {
		this.writeFile(value, true);
	}

	public void writeFile(String value, Boolean bool) throws IOException {
		File file = new File(filepath + "/" + this.filename + ".csv");
		FileWriter filewriter = new FileWriter(file, bool);
		filewriter.write(value);
		filewriter.close();
	}

	public String readFile() {
		String str = null;
		StringBuffer stbf = new StringBuffer();
		try {
			File file = new File(filepath + "/" + this.filename + ".csv");
			if (checkBeforeReadfile(file)) {
				FileReader filereader = new FileReader(file);
				BufferedReader br = new BufferedReader(filereader);
				str = br.readLine();
				while (str != null) {
					stbf.append(str);
				}

				filereader.close();
				return stbf.toString();
			} else {
				System.out.println("File is not there");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return stbf.toString();
	}

	public ArrayList<String> getTextList() {
		int i = 0;
		// 行数チェック
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.getFilepath());
		buffer.append("/");
		buffer.append(this.getFilename());
		String fullpath = buffer.toString();

		int line = this.getLineLength(this.getFilename());
		ArrayList<String> lineList = new ArrayList<String>();
		// 行ごと配列に収納
		try {
			File file = new File(fullpath);
			if (checkBeforeReadfile(file)) {
				FileReader filereader = new FileReader(file);
				BufferedReader br = new BufferedReader(filereader);
				String str;
				while (i < line) {
					str = br.readLine();
					lineList.add(str);
					i++;
				}
				filereader.close();
			} else {
				System.out.println("File is not there");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return lineList;
	}

	public String[] getFileList() {
		File dir = new File(this.getFilepath());
		return dir.list();
	}

	private boolean checkBeforeReadfile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}
		return false;
	}

	public int getLineLength(String filename) {
		int lineCount = 0;

		BufferedReader bra = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(getFilepath());
		buffer.append("/");
		buffer.append(filename);
		try {
			FileReader fr = new FileReader(buffer.toString());
			bra = new BufferedReader(fr);

			String line;
			line = bra.readLine();

			while (line != null) {
				lineCount++;
				line = bra.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (bra != null) {
					bra.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return lineCount;
	}

	public boolean accept(String name) {

		if (name.matches(".*\\.csv$")) {
			return true;
		}

		return false;
	}

	public String getFilename() {
		return this.filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
