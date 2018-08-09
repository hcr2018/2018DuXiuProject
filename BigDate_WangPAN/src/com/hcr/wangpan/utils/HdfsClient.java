package com.hcr.wangpan.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import com.opensymphony.xwork2.interceptor.annotations.Before;


public class HdfsClient {

	FileSystem fs = null;

	@Before
	public void init() throws Exception {

		// ����һ�����ò�����������һ������������Ҫ���ʵ�hdfs��URI
		// �Ӷ�FileSystem.get()������֪��Ӧ����ȥ����һ������hdfs�ļ�ϵͳ�Ŀͻ��ˣ��Լ�hdfs�ķ��ʵ�ַ
		// new Configuration();��ʱ�����ͻ�ȥ����jar���е�hdfs-default.xml
		// Ȼ���ټ���classpath�µ�hdfs-site.xml
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.145.130:8020");
		/**
		 * �������ȼ��� 1���ͻ��˴��������õ�ֵ 2��classpath�µ��û��Զ��������ļ� 3��Ȼ���Ƿ�������Ĭ������
		 */
		conf.set("dfs.replication", "2");

		// ��ȡһ��hdfs�ķ��ʿͻ��ˣ����ݲ��������ʵ��Ӧ����DistributedFileSystem��ʵ��
		// fs = FileSystem.get(conf);

		// �������ȥ��ȡ����conf����Ϳ��Բ�Ҫ��"fs.defaultFS"���������ң�����ͻ��˵���ݱ�ʶ�Ѿ���hadoop�û�
		fs = FileSystem.get(new URI("hdfs://192.168.145.130:8020"), conf, "hadoop");

	}

	/**
	 * ��hdfs�ϴ��ļ�
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFileToHdfs() throws Exception {

		// Ҫ�ϴ����ļ����ڵı���·��
		Path src = new Path("D:/Workspaces/vmware.log");
		// Ҫ�ϴ���hdfs��Ŀ��·��
		Path dst = new Path("/wordcount/input");
		fs.copyFromLocalFile(src, dst);
		//fs.close();
	}
	
	/**
	 * ��hdfs�����ļ�������
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testDownToLocal() throws IllegalArgumentException, IOException{
		//�����������ļ��ḽ��һ��.crcУ���ļ�
		//HadoopϵͳΪ�˱�֤���ݵ�һ���ԣ�����ļ�������Ӧ��У���ļ������ڶ�д��ʱ�����У�飬ȷ�����ݵ�׼ȷ�ԡ�
		fs.copyToLocalFile(new Path("/myDir/hello.txt"), new Path("D:/Workspaces/"));
	}
	
	/**
	 * ����Ŀ¼�ļ�
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testMkdirFile() throws IllegalArgumentException, IOException{
		fs.mkdirs(new Path("/myDir/output"));
		
	}
	
	/**
	 * ɾ��Ŀ¼
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testDeleteFile() throws IllegalArgumentException, IOException{
		
		fs.delete(new Path("/myDir/output"),true);
	}
	
	/***
	 * �������ļ����ļ���
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testReName() throws IllegalArgumentException, IOException{
		//fs.rename(new Path("/myDir/hello2.txt"), new Path("/myDir/myHello.txt"));
		fs.rename(new Path("/workcount"), new Path("/wordcount"));
	}
	
	/**
	 * ��ʾ�ļ�
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testListFile() throws FileNotFoundException, IllegalArgumentException, IOException{
		
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"),true);
		
		while(listFiles.hasNext()){
			LocatedFileStatus lfs = listFiles.next();
			//�ļ���
			System.out.println(lfs.getPath().getName());
			System.out.println(lfs.getBlockSize());
			System.out.println(lfs.getLen());
			System.out.println(lfs.getPermission());
			System.out.println(lfs.getOwner());
			BlockLocation[] blockLocations = lfs.getBlockLocations();
			for (BlockLocation bl : blockLocations) {
				//getOffset()ƫ����
				System.out.println(bl.getLength()+"------"+bl.getOffset());
				//������
				String[] hosts = bl.getHosts();
				for (String str : hosts) {
					System.out.println("str------"+str);
				}
				//����ip��ַ
				String[] names = bl.getNames();
				for (String name : names) {
					System.out.println("name----"+name);
				}
			}
		}
	}
	
	
	/**
	 * �鿴�ļ�
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testListAll() throws FileNotFoundException, IllegalArgumentException, IOException{
		FileStatus[] listStatus = fs.listStatus(new Path("/myDir"));
		for (FileStatus fs : listStatus) {
			if(fs.isFile()){
				System.out.println(fs.getPath().getName());
			}
		}
	}
	
	
	
	
	@After
	public void close(){
		try {
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void testAddToHdfs(String[] args) throws Exception {

		// Ҫ�ϴ����ļ����ڵı���·��
		Path src = new Path(args[0]);
		// Ҫ�ϴ���hdfs��Ŀ��·��
		Path dst = new Path(args[1]);
		fs.copyFromLocalFile(src, dst);
		//fs.close();
	}

}
