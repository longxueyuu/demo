package javaclass.net.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CustomizeClassLoader extends ClassLoader{
	
	private String path;
	private String name;
	public CustomizeClassLoader(String name) {
		super();
		this.name = name;
	}
	
	public CustomizeClassLoader(ClassLoader parent, String name) {
		super(parent);
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return this.name.toString();
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.getClassdata(name);
		return this.defineClass(name, data, 0, data.length );
	}
	
	private byte[] getClassdata(String name)
	{
		InputStream is = null;
		byte[] data = null;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    try {
	    	//System.out.println(name);
	    	name = name.replaceAll("\\.", "\\\\");
	    	
			is = new FileInputStream(path + name + ".class");
			int byt = 0;
		    while(-1 != (byt = is.read()))
		    {
		    	bos.write(byt);
		    }
		    data = bos.toByteArray();
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				is.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return data;	
		
	}

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String path1 = "E:\\classloader\\server\\";
		String path2 = "E:\\classloader\\client\\";
		String name = "javaclass.net.classloader.Person";
		
		CustomizeClassLoader loader1 = new CustomizeClassLoader("loader1");
		//System.out.println(loader1.getParent());
		CustomizeClassLoader loader2 = new CustomizeClassLoader(null,"loader2");
		System.out.println("loader2 parent classloader: " + loader2.getParent());
		
		
		
		loader1.setPath(path1);
		toLoad(loader1, name);
		
		loader2.setPath(path2);
		toLoad(loader2, name);
		System.out.println("aa");
	}
	
	public static void toLoad(ClassLoader classloader, String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class clazz = classloader.loadClass(name);
		Object obj = clazz.newInstance();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}



















