/**
 * 
 */
package cn.salesuite.saf.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Tony Shen
 * 
 * 
 */
public class IOUtil {
	
	private final static int BUFFER_SIZE = 0x400; // 1024
	
	/**
	 * 从输入流读取数据
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws IOException{
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int len = 0;
		while( (len = inStream.read(buffer)) !=-1 ){
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/**
	 * 从输入流读取数据
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static String inputStream2String(InputStream inStream) throws IOException{
		
		return new String(readInputStream(inStream));
	}
	
	/**
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	public static void copyStream(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		for (;;) {
			int count = is.read(bytes, 0, BUFFER_SIZE);
			if (count == -1)
				break;
			os.write(bytes, 0, count);
		}
	}
	
	/**
     * 文件拷贝
     * @param src source {@link File}
     * @param dst destination {@link File}
     * @throws IOException
     */
    public static void copyFile(File src, File dst) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dst);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }

        in.close();
        out.close();
    }
}
