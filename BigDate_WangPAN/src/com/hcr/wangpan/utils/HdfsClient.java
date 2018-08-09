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

		// 构造一个配置参数对象，设置一个参数：我们要访问的hdfs的URI
		// 从而FileSystem.get()方法就知道应该是去构造一个访问hdfs文件系统的客户端，以及hdfs的访问地址
		// new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
		// 然后再加载classpath下的hdfs-site.xml
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.145.130:8020");
		/**
		 * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
		 */
		conf.set("dfs.replication", "2");

		// 获取一个hdfs的访问客户端，根据参数，这个实例应该是DistributedFileSystem的实例
		// fs = FileSystem.get(conf);

		// 如果这样去获取，那conf里面就可以不要配"fs.defaultFS"参数，而且，这个客户端的身份标识已经是hadoop用户
		fs = FileSystem.get(new URI("hdfs://192.168.145.130:8020"), conf, "hadoop");

	}

	/**
	 * 往hdfs上传文件
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFileToHdfs() throws Exception {

		// 要上传的文件所在的本地路径
		Path src = new Path("D:/Workspaces/vmware.log");
		// 要上传到hdfs的目标路径
		Path dst = new Path("/wordcount/input");
		fs.copyFromLocalFile(src, dst);
		//fs.close();
	}
	
	/**
	 * 从hdfs下载文件到本地
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testDownToLocal() throws IllegalArgumentException, IOException{
		//下载下来的文件会附带一个.crc校验文件
		//Hadoop系统为了保证数据的一致性，会对文件生成相应的校验文件，并在读写的时候进行校验，确保数据的准确性。
		fs.copyToLocalFile(new Path("/myDir/hello.txt"), new Path("D:/Workspaces/"));
	}
	
	/**
	 * 创建目录文件
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testMkdirFile() throws IllegalArgumentException, IOException{
		fs.mkdirs(new Path("/myDir/output"));
		
	}
	
	/**
	 * 删除目录
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testDeleteFile() throws IllegalArgumentException, IOException{
		
		fs.delete(new Path("/myDir/output"),true);
	}
	
	/***
	 * 重命名文件或文件夹
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testReName() throws IllegalArgumentException, IOException{
		//fs.rename(new Path("/myDir/hello2.txt"), new Path("/myDir/myHello.txt"));
		fs.rename(new Path("/workcount"), new Path("/wordcount"));
	}
	
	/**
	 * 显示文件
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testListFile() throws FileNotFoundException, IllegalArgumentException, IOException{
		
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"),true);
		
		while(listFiles.hasNext()){
			LocatedFileStatus lfs = listFiles.next();
			//文件名
			System.out.println(lfs.getPath().getName());
			System.out.println(lfs.getBlockSize());
			System.out.println(lfs.getLen());
			System.out.println(lfs.getPermission());
			System.out.println(lfs.getOwner());
			BlockLocation[] blockLocations = lfs.getBlockLocations();
			for (BlockLocation bl : blockLocations) {
				//getOffset()偏移量
				System.out.println(bl.getLength()+"------"+bl.getOffset());
				//机子名
				String[] hosts = bl.getHosts();
				for (String str : hosts) {
					System.out.println("str------"+str);
				}
				//机子ip地址
				String[] names = bl.getNames();
				for (String name : names) {
					System.out.println("name----"+name);
				}
			}
		}
	}
	
	
	/**
	 * 查看文件
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

		// 要上传的文件所在的本地路径
		Path src = new Path(args[0]);
		// 要上传到hdfs的目标路径
		Path dst = new Path(args[1]);
		fs.copyFromLocalFile(src, dst);
		//fs.close();
	}

}
