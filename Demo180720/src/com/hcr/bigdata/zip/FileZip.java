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
			// ѹ���ļ�·��
			String str = "b/c/d/xiaogou.ser";
			// ����һ��ѹ���ļ�����
			ZipEntry zipEntry = new ZipEntry(str);
			zos.putNextEntry(zipEntry);
			// ��ȡҪѹ���ļ�������
			byte[] arr = Files.readAllBytes(Paths.get("D:", "Workspaces", "xaogou.ser"));
			// ���ļ�����д��
			zos.write(arr);
			// �ر�ѹ���ļ���
			zos.closeEntry();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * ѹ������ļ�
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
