package com.hcr.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ZKClient {
	private  ZooKeeper zk;
	
	@Before
	public void init(){
		String con="hadoop1:2181";
		int timeout=300000;
		Watcher watcher=System.out::println;
		
		 try {
			zk = new ZooKeeper(con, timeout, watcher);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ´´½¨ZNode
	 */
	@Test
	public void testCreate(){
		String cr = null;
		try {
			cr = zk.create("/com", "package com".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cr);
		
	}
	
	@Test
	public void testGet(){
		byte[] data = null;
		try {
			data = zk.getData("/com", true,null);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new String(data));
	}
	
	@Test
	public void testSet(){
		Stat setData = null;
		try {
			setData = zk.setData("/com", "hahah".getBytes(), 0);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(setData);
	}
	
	@After
	public void destory(){
		try {
			this.zk.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
