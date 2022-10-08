package com.wei;

import java.io.Serializable;

public class FSTSeriazle {

	public static void main(String[] args) {
		User bean = new User();
		bean.setUsername("xxxxx");
		bean.setPassword("123456");
		bean.setAge(1000000);
		System.out.println("序列化 ， 反序列化 对比测试：");
		long size = 0;
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			byte[] jdkserialize = JRedisSerializationUtils.jdkserialize(bean);
			size += jdkserialize.length;
			JRedisSerializationUtils.jdkdeserialize(jdkserialize);
		}
		System.out.println("原生序列化方案[序列化10000次]耗时："
				+ (System.currentTimeMillis() - time1) + "ms size:=" + size);

		size = 0;
		long time2 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			byte[] serialize = JRedisSerializationUtils.serialize(bean);
			size += serialize.length;
			User u = (User) JRedisSerializationUtils.unserialize(serialize);
		}
		System.out.println("fst序列化方案[序列化10000次]耗时："
				+ (System.currentTimeMillis() - time2) + "ms size:=" + size);
		size = 0;
		long time3 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			byte[] serialize = JRedisSerializationUtils.kryoSerizlize(bean);
			size += serialize.length;
			User u = (User) JRedisSerializationUtils.kryoUnSerizlize(serialize);
		}
		System.out.println("kryo序列化方案[序列化10000次]耗时："
				+ (System.currentTimeMillis() - time3) + "ms size:=" + size);

	}

}

class User implements Serializable{

	private String username;
	private int age;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}