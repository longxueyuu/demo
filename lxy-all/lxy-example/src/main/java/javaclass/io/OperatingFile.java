package javaclass.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class OperatingFile {
	
	private static int tabNum = 0;
	
	public static void main(String[] args){
		String path = "E:/test_MES/java";
		String outputPath = "E:/test_MES/folderTree.txt";
		OperatingFile opFile = new OperatingFile();
		//File file = new File(path);
		//opFile.CreateFolderTree(file);
		opFile.CreateFolderTree(path,outputPath);
		//opFile.deleteFiles(path);
	}
	
	/**
	 * �Ը�����Ŀ¼��ӡ�ļ�Ŀ¼��,�����ָ����text�ļ���
	 * 
	 * @param inputPath
	 * @param outputFilePath
	 * @throws IOException 
	 */
	public void CreateFolderTree(String inputPath, String outputFilePath)
	{
		File file = new File(inputPath);
		File outputFile = new File(outputFilePath);
		CreateFolderTree(file, outputFile);
	}
	
	/**
	 * �Ը�����Ŀ¼��ӡ�ļ�Ŀ¼��,�����ָ����text�ļ���
	 * 
	 * @param file
	 * @param outputFile
	 * @throws IOException 
	 */
	public void CreateFolderTree(File file, File outputFile)
	{
		if(!file.exists())
		{
			return;
		}
		String str = tabsAndName(tabNum, file);
		System.out.println(str);
		writeFile(outputFile,str,tabNum);
		if(file.isFile() || file.listFiles().length == 0)
		{
			return;
		}
		File[] fileList = file.listFiles();	
		fileList = sortFile(fileList);
		
		for(File filePath : fileList)
		{
			tabNum++;
			CreateFolderTree(filePath, outputFile);
			tabNum--;
		}
	}
	
	/**
	 * ���ļ�Ŀ¼�ṹд��ָ�����ļ�
	 * 
	 * @param tabNum
	 * @param file
	 * @return
	 */
	private String tabsAndName(int tabNum, File file)
	{
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < tabNum; i++)
		{
			str.append("\t");
		}
		str.append(file.getName());
		return str.toString();
	}
	
	/**
	 * �ļ��б���������ʹĿ¼���б���ǰ�棬�ļ����б����
	 * 
	 * @param file
	 * @return
	 */
	private File[] sortFile(File[] file)
	{
		ArrayList<File> dir = new ArrayList<File>();
		ArrayList<File> fil = new ArrayList<File>();
		for(File filePath : file)
		{
			if(filePath.isDirectory())
			{
				dir.add(filePath);
			}else{
				fil.add(filePath);
			}
		}
		for(Iterator<File> iterator = fil.iterator(); iterator.hasNext();)
		{
			dir.add(iterator.next());
		}
		return dir.toArray(new File[0]);
	}
	
	/**
	 * 
	 * 
	 * @param file
	 * @param data
	 * @param tabNum
	 */
	public void writeFile(File file, String data, int tabNum)
	{
		Boolean append = false;
		if(tabNum > 0)
		{
			append = true;
		}
		FileWriter fw;
		try {
			fw = new FileWriter(file, append);
			fw.write(data);
			fw.write("\r\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ɾ��Ŀ¼�С�Ŀ¼�е��ļ�������Ŀ¼
	 * 
	 * @param path
	 */
	public void deleteFiles(String path)
	{
		File file = new File(path);
		deleteFiles(file);
	}
	
	/**
	 * ɾ��Ŀ¼�С�Ŀ¼�е��ļ�������Ŀ¼
	 * 
	 * @param file
	 */
	public void deleteFiles(File file)
	{
		if(!file.exists())
		{
			return;
		}
		if(file.isFile() || file.listFiles().length == 0)
		{
			file.delete();
			return;
		}
		File[] fileList = file.listFiles();
		for(File f : fileList)
		{
			deleteFiles(f);
		}
		file.delete(); // ���file��Ŀ¼����ɾ���������ļ��к��ļ���ɾ����Ŀ¼
	}
}































