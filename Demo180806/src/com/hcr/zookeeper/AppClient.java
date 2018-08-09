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
	//�ڶ��̻߳����й������
	private volatile List<String> serverList;

	/**
	 * ����zookeeper
	 */
	public void connectZookeeper() throws Exception {
		zk = new ZooKeeper("hadoop1:2181,hadoop2:2181,hadoop3:2181", 5000, new Watcher() {
			public void process(WatchedEvent event) {
				// ���������"/sgroup"�ڵ��µ��ӽڵ�仯�¼�, ����server�б�, ������ע�����
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
	 * ����server�б�
	 */
	private void updateServerList() throws Exception {
		List<String> newServerList = new ArrayList<String>();

		// ��ȡ������groupNode���ӽڵ�仯
		// watch����Ϊtrue, ��ʾ�����ӽڵ�仯�¼�. 
		// ÿ�ζ���Ҫ����ע�����, ��Ϊһ��ע��, ֻ�ܼ���һ���¼�, �������������ּ���, ��������ע��
		List<String> subList = zk.getChildren("/" + groupNode, true);
		for (String subNode : subList) {
			// ��ȡÿ���ӽڵ��¹�����server��ַ
			byte[] data = zk.getData("/" + groupNode + "/" + subNode, false, stat);
			newServerList.add(new String(data, "utf-8"));
		}

		// �滻server�б�
		serverList = newServerList;

		System.out.println("server list updated: " + serverList);
	}

	/**
	 * client�Ĺ����߼�д�����������
	 * �˴������κδ���, ֻ��client sleep
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



/*B����������ʵ��
public class AppServer {
	private String groupNode = "sgroup";
	private String subNode = "sub";

	*//**
	 * ����zookeeper
	 * @param address server�ĵ�ַ
	 *//*
	public void connectZookeeper(String address) throws Exception {
		ZooKeeper zk = new ZooKeeper(
"localhost:4180,localhost:4181,localhost:4182", 
5000, new Watcher() {
			public void process(WatchedEvent event) {
				// ��������
			}
		});
		// ��"/sgroup"�´����ӽڵ�
		// �ӽڵ����������ΪEPHEMERAL_SEQUENTIAL, ��������һ����ʱ�ڵ�, �����ӽڵ�����ƺ������һ�����ֺ�׺
		// ��server�ĵ�ַ���ݹ������´������ӽڵ���
		String createdPath = zk.create("/" + groupNode + "/" + subNode, address.getBytes("utf-8"), 
			Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("create: " + createdPath);
	}
	
	*//**
	 * server�Ĺ����߼�д�����������
	 * �˴������κδ���, ֻ��server sleep
	 *//*
	public void handle() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}
	
	public static void main(String[] args) throws Exception {
		// �ڲ�����ָ��server�ĵ�ַ
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