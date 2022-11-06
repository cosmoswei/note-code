package com.wei.mmap;

import org.springframework.util.DigestUtils;

import javax.print.attribute.standard.Destination;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class MappedByteBuffer基本应用 {
    static int length = 0x8000000; // 128 Mb 一个bit占1B，0x8000000换成十进制为：134217728
    public static void main(String[] args) throws Exception {
        // 为了以可读可写的方式打开文件，我们使用RandomAccessFile来创建文件
        FileChannel fc = new RandomAccessFile("test3.txt", "rw").getChannel();
        //文件通道的可读可写要建立在文件流本身可读写的基础之上
        ByteBuffer buf = ByteBuffer.allocate(65535);
        fc.read(buf);
        System.out.println(new String(buf.array()));
        byte[] bytes1 = DigestUtils.md5Digest(new FileInputStream("test3.txt"));
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);
        //写128M的内容*（a）
//        for (int i = 0; i < length; i++) {
//            mbb.put((byte) 'a');
//        }
        System.out.println("writing end");
        //读取文件中间20个字节内容
        for (int i = length / 2; i < length / 2 + 20; i++) {
            System.out.print((char) mbb.get(i));
        }
        mbb.flip();
        int length = mbb.limit();

        byte[] bytes = new byte[length];

        System.out.println("mbb.get(bytes) = " + new String(bytes));
//        System.out.println(mbb.array());
        fc.close();

    }

}