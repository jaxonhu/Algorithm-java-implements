package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/7/24
 * @Time: 5:24 PM
 * @Project: Algorithm-Java-implements
 */

public class FileCopyTest {

    private static int bufferSize = 1024 * 16;

    public static void main(String[] args) throws Exception {

        String sourcePath = "/Users/jaxon/Downloads/win764位安装版.zip";

        String destPath1 = "/Users/jaxon/tmp/movie1.avi";
        String destPath2 = "/Users/jaxon/tmp/movie2.avi";
        String destPath3 = "/Users/jaxon/tmp/movie3.avi";

        long t1 = System.currentTimeMillis();

        traditionalCopy(sourcePath, destPath1);
        long t2 = System.currentTimeMillis();
        System.out.println("传统IO方法耗时:" + (t2 - t1) + "ms");

        nioCopy(sourcePath, destPath2);
        long t3 = System.currentTimeMillis();
        System.out.println("NIO通道文件复制:" + (t3 - t2) + "ms");

        nioCopy2(sourcePath, destPath3);
        long t4 = System.currentTimeMillis();
        System.out.println("NIO内存映射文件复制:" + (t4 - t3) + "ms");
    }

    //内存映射文件复制
    private static void nioCopy2(String sourcePath, String destPath) throws Exception {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        FileInputStream fis = new FileInputStream(source);
//        FileOutputStream fos = new FileOutputStream(dest, true);
//        RandomAccessFile fos = new RandomAccessFile(dest, "rw");

//        FileChannel sourceCh = fis.getChannel();
//        FileChannel destCh = fos.getChannel();
        FileChannel sourceCh = FileChannel.open(Paths.get(sourcePath), StandardOpenOption.READ);
        FileChannel destCh = FileChannel.open(Paths.get(destPath), StandardOpenOption.WRITE, StandardOpenOption.READ);
        long fileSize = sourceCh.size();
        int blocks = (int) Math.ceil((double)fileSize / Integer.MAX_VALUE);
        MappedByteBuffer[] mbbs = new MappedByteBuffer[blocks];
        MappedByteBuffer[] mbbsSwap = new MappedByteBuffer[blocks];
        // 分块读取wq
        long headPtr = 0;
        long blockSize = Integer.MAX_VALUE;
        for(int i = 0 ; i < blocks ; i ++) {
            if(fileSize - headPtr < blockSize) {
                blockSize = fileSize - headPtr;
            }
            mbbs[i] = sourceCh.map(FileChannel.MapMode.READ_ONLY, headPtr, blockSize);
            mbbsSwap[i] = destCh.map(FileChannel.MapMode.READ_WRITE, headPtr, blockSize);
            headPtr += blockSize;
        }

        int bufferIndex = 0;
        byte[] byteBuffer;
        int realSize = bufferSize;
        while(realSize != -1) {
            if(bufferIndex >= blocks) {
                break;
            }
            int limit = mbbs[bufferIndex].limit();
            int position = mbbs[bufferIndex].position();
            realSize = bufferSize;
            if(limit - position < bufferSize) {
                realSize = limit - position;
            }
            byteBuffer = new byte[realSize];  //4k读取
            mbbs[bufferIndex].get(byteBuffer);
            mbbsSwap[bufferIndex].put(byteBuffer);
            if(realSize < bufferSize) {
                bufferIndex += 1;
            }
//            destCh.write();
//            fos.write(byteBuffer);
        }

//        destCh.write(mbb);
        sourceCh.close();
        destCh.close();
//        fos.close();
        fis.close();
    }
    //NIO文件通道文件复制
    private static void nioCopy(String sourcePath, String destPath) throws Exception {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(dest);
        FileChannel sourceCh = fis.getChannel();
        FileChannel destCh = fos.getChannel();
        destCh.transferFrom(sourceCh, 0, sourceCh.size());
        sourceCh.close();
        destCh.close();
        fos.close();
        fis.close();
    }

    //传统IO方法实现文件
    private static void traditionalCopy(String sourcePath, String destPath) throws Exception {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
    }
}
