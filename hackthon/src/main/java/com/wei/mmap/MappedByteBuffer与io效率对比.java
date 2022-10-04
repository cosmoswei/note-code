package com.wei.mmap;
 
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
 
/**
 * 测试MappedByte和lO的效率对比
 */
public class MappedByteBuffer与io效率对比 {
    static int length = 0x8000000; // 128 Mb 一个bit占1B，0x8000000换成十进制为：134217728
 
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        // 调用普通io
        testIO();
        // 调用MappedByteBuffer
        testMappedByteBuffer();
        // 调用 testFileChannel()
        testFileChannel();
        // 调用 testFileChannelByteBuffer()
        testFileChannelByteBuffer();
        long end = System.currentTimeMillis();
        System.out.println("耗时=" + (end - start) + "ms");
        /**
         * 调用 testIO()打印内容：耗时=6218ms;
         * 调用 testMappedByteBuffer()打印内容：耗时=2132ms
         * 调用 testFileChannel()打印内容：耗时=703ms
         * 调用 testFileChannelByteBuffer()打印内容：耗时=819ms
         */
    }
 
    /**
     * 测试0.9G文件，IO的效率
     *
     * @throws IOException
     */
    private static void testIO() throws IOException {
        File sourceFile = new File("D:/TEST/testFile0.9G文件.zip");
        byte[] bytes = new byte[1024];  // 和下面方式创建byte[]效率基本一样
//        byte[] bytes = new byte[(int) sourceFile.length()];
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream("D:/TEST/0.9G文件.zip");
        int len = -1;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len); // 写入数据
        }
        fis.close();
        fos.close();
    }
 
    /**
     * 测试0.9G文件，MappedByteBuffer的效率
     *
     * @throws IOException
     */
    private static void testMappedByteBuffer() throws IOException {
        File sourceFile = new File("D:/TEST/testFile0.9G文件.zip");
//        byte[] bytes = new byte[1024];  // 和下面方式创建byte[]效率基本一样
        byte[] bytes = new byte[(int) sourceFile.length()];
        RandomAccessFile ra_read = new RandomAccessFile(sourceFile, "r");
        FileChannel fc = new RandomAccessFile("D:/TEST/0.9G文件.zip", "rw").getChannel();
        MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_WRITE, 0, sourceFile.length());
        int len = -1;
        while ((len = ra_read.read(bytes)) != -1) {
            map.put(bytes, 0, len); // 写入数据
        }
        ra_read.close();
        fc.close();
    }
 
    /**
     * 测试0.9G文件，FileChannel的效率
     *
     * @throws IOException
     */
    private static void testFileChannel() throws IOException {
        File sourceFile = new File("D:/TEST/testFile0.9G文件.zip");
        FileInputStream fis = new FileInputStream(sourceFile);
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("D:/TEST/0.9G文件.zip");
        FileChannel fosChannel = fos.getChannel();
        fisChannel.transferTo(0, fisChannel.size(), fosChannel);
        fis.close();
        fos.close();
    }
 
    /**
     * 测试0.9G文件，FileChannel的效率
     *
     * @throws IOException
     */
    private static void testFileChannelByteBuffer() throws IOException {
        try (FileChannel from = new FileInputStream("D:/TEST/testFile0.9G文件.zip").getChannel();
             FileChannel to = new FileOutputStream("D:/TEST/0.9G文件.zip").getChannel();
        ) {
            ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024);
            while (true) {
                int len = from.read(bb);
                if (len == -1) {
                    break;
                }
                bb.flip();  // 调用flip之后，读写指针指到缓存头部，切换成读模式
                to.write(bb);
                bb.clear();  // 切换成写模式
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}