package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.NoteItem;

public class LocalFiles{
	private static String filename = "notes";
	public static void serializeDataOut(List<NoteItem> obj){
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(obj);
		    oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<NoteItem> serializeDataIn(){
		List<NoteItem> res = new ArrayList<NoteItem>();
		File targetFile = new File(filename);
		if(targetFile.exists() && !targetFile.isDirectory()) { 
			try {
				FileInputStream fin;
				fin = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fin);
				res = (List<NoteItem>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
