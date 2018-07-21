package com.hcr.bigdata.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class FileZip {
	@Test
	public void testZipFileInputStream() {
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\Workspaces\\xiaogou.zip"));

		) {
			// 压缩文件路径
			String str = "b/c/d/xiaogou.ser";
			// 创建一个压缩文件对象
			ZipEntry zipEntry = new ZipEntry(str);
			zos.putNextEntry(zipEntry);
			// 读取要压缩文件的内容
			byte[] arr = Files.readAllBytes(Paths.get("D:", "Workspaces", "xaogou.ser"));
			// 将文件内容写进
			zos.write(arr);
			// 关闭压缩文件流
			zos.closeEntry();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * 压缩多个文件
	 */
	@Test
	public void testZipManyFile() {
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\Workspaces\\java.zip"));) {
			String str = "E:\\MobileFile";
			File file = new File(str);
			File[] fileArr = file.listFiles(f -> f.isFile());
			Stream.of(fileArr).forEach(System.out::println);
			String[] entryName = new String[fileArr.length];

			Pattern pattern = Pattern.compile(".*\\\\(MobileFile\\\\.*)");
			int i = 0;
			for (File fi : fileArr) {
				String strArr = fi.toString();
				Matcher matcher = pattern.matcher(strArr);
				if (matcher.find()) {
					String group = matcher.group(1);
					System.out.println(group);
					entryName[i] = group;
				}
				i++;

			}
			Stream.of(entryName).forEach(System.out::println);
			for (int j = 0; j < entryName.length; j++) {
				ZipEntry zipEntry = new ZipEntry(entryName[j]);
				zos.putNextEntry(zipEntry);
				byte[] arr = Files.readAllBytes(fileArr[j].toPath());
				zos.write(arr);
				zos.closeEntry();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		try 
		//ZipInputStream zis = new ZipInputStream(new FileInputStream("D:\\Workspaces\\java"));

		{
			ZipFile zipFile = new ZipFile("D:\\Workspaces\\java.zip");
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while(entries.hasMoreElements()){
				ZipEntry entry = entries.nextElement();
				System.out.println(entry.getName());
				if(entry.isDirectory()){
					
				}
				
				InputStream is = zipFile.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream("D:\\Workspaces\\java");
				//FileInputStream fos = new FileInputStream("D:\\Workspaces\\java");
				  int count;

	                byte[] buf = new byte[8192];

	                while ((count = is.read(buf)) != -1) {

	                    fos.write(buf, 0, count);

	                }

	                is.close();

	                fos.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
