package com.gxsfdx.study.web.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 在线人数统计监听类
 * @author cjx
 *
 */
public class MySessionListener implements HttpSessionListener {

private ZooKeeper zk;

{
	try {
		zk=new ZooKeeper("hadoop1:2181,hadoop2:2181,hadoop3:2181",50000,new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				
				
			}
			
		});
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}	
	/**
	 * 人数增加
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		/*int counter=0;
		ServletContext sc=event.getSession().getServletContext();
		String strCounter=(String)sc.getAttribute("counter");
		if(strCounter!=null){		
			counter=Integer.valueOf(strCounter);//application.setAttribute("counter",counter);
			
		}
		
		sc.setAttribute("counter", ++counter);*/
		
		byte[] arr = null;
		try {
			arr = zk.getData("/y.l.x.com.numOfOnline", false, new Stat());
		} catch (KeeperException | InterruptedException e) {
			arr="1".getBytes();
			try {
				zk.create("/y.l.x.com.numOfOnline", arr, null, CreateMode.PERSISTENT);
			} catch (KeeperException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String str=new String(arr);
		int counter= Integer.valueOf(str);
		str=(counter++)+"";
		try {
			zk.setData("/y.l.x.com.numOfOnline", str.getBytes(), 0);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 人数减少
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		int counter=0;
		ServletContext sc=event.getSession().getServletContext();
		String strCounter=(String)sc.getAttribute("counter");
		if(strCounter==null){
			counter=Integer.valueOf(strCounter);//application.setAttribute("counter",counter);
		}
		
		sc.setAttribute("counter", --counter);
	}

}
