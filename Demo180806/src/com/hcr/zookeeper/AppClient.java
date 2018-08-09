package com.hcr.zookeeper;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class AppClient {
	private String groupNode = "sgroup";
	private ZooKeeper zk;
	private Stat stat = new Stat();
	//在多线程环境中共享变量
	private volatile List<String> serverList;

	/**
	 * 连接zookeeper
	 */
	public void connectZookeeper() throws Exception {
		zk = new ZooKeeper("hadoop1:2181,hadoop2:2181,hadoop3:2181", 5000, new Watcher() {
			public void process(WatchedEvent event) {
				// 如果发生了"/sgroup"节点下的子节点变化事件, 更新server列表, 并重新注册监听
				if (event.getType() == EventType.NodeChildrenChanged 
					&& ("/" + groupNode).equals(event.getPath())) {
					try {
						updateServerList();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		updateServerList();
	}

	/**
	 * 更新server列表
	 */
	private void updateServerList() throws Exception {
		List<String> newServerList = new ArrayList<String>();

		// 获取并监听groupNode的子节点变化
		// watch参数为true, 表示监听子节点变化事件. 
		// 每次都需要重新注册监听, 因为一次注册, 只能监听一次事件, 如果还想继续保持监听, 必须重新注册
		List<String> subList = zk.getChildren("/" + groupNode, true);
		for (String subNode : subList) {
			// 获取每个子节点下关联的server地址
			byte[] data = zk.getData("/" + groupNode + "/" + subNode, false, stat);
			newServerList.add(new String(data, "utf-8"));
		}

		// 替换server列表
		serverList = newServerList;

		System.out.println("server list updated: " + serverList);
	}

	/**
	 * client的工作逻辑写在这个方法中
	 * 此处不做任何处理, 只让client sleep
	 */
	public void handle() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}

	public static void main(String[] args) throws Exception {
		AppClient ac = new AppClient();
		ac.connectZookeeper();

		ac.handle();
	}
}



/*B、服务器端实现
public class AppServer {
	private String groupNode = "sgroup";
	private String subNode = "sub";

	*//**
	 * 连接zookeeper
	 * @param address server的地址
	 *//*
	public void connectZookeeper(String address) throws Exception {
		ZooKeeper zk = new ZooKeeper(
"localhost:4180,localhost:4181,localhost:4182", 
5000, new Watcher() {
			public void process(WatchedEvent event) {
				// 不做处理
			}
		});
		// 在"/sgroup"下创建子节点
		// 子节点的类型设置为EPHEMERAL_SEQUENTIAL, 表明这是一个临时节点, 且在子节点的名称后面加上一串数字后缀
		// 将server的地址数据关联到新创建的子节点上
		String createdPath = zk.create("/" + groupNode + "/" + subNode, address.getBytes("utf-8"), 
			Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("create: " + createdPath);
	}
	
	*//**
	 * server的工作逻辑写在这个方法中
	 * 此处不做任何处理, 只让server sleep
	 *//*
	public void handle() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}
	
	public static void main(String[] args) throws Exception {
		// 在参数中指定server的地址
		if (args.length == 0) {
			System.err.println("The first argument must be server address");
			System.exit(1);
		}
		
		AppServer as = new AppServer();
		as.connectZookeeper(args[0]);
		as.handle();
	}
}
*/