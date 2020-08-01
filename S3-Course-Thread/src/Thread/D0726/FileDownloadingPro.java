package Thread.D0726;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * 多线程下载文件 1.进度统计 2.合并的时机，要等到所有的块下载完成才能合并 3.时间的统计 隐患：对块数的限制 12M/2M = 6 1024m/2m
 * = 512个子线程 不能下载大文件 必须进行块数的限制，downNums 不能超过10
 */
public class FileDownloadingPro {
	/*
	 * 当前正在下载块
	 */
	private int downNums = 0;

	public static void main(String[] args) {

	}

	public void download() throws IOException, InterruptedException {
		URL url = new URL(
				"http://mirrors.bfsu.edu.cn/apache/tomcat/tomcat-7/v7.0.105/bin/apache-tomcat-7.0.105-windows-x64.zip");
		String filename = "d:/apache-tomcat-7.0.105-windows-x64.zip";
		long time = System.currentTimeMillis();
		URLConnection conn = url.openConnection();
		// 获取下载文件的大小
		int filesize = conn.getContentLength();
		// 定义下载块的大小(2M)
		int blocksize = 1024 * 1024 * 2;
		// 计算块数
		int blocknums = filesize / blocksize;
		if(	filesize % blocksize != 0) {
			blocknums++;
		}
		System.out.println("开始下载");
		//分块下载
		for (int i = 0; i < blocknums; i++) {
			/*开启线程下载*/
			downNums++;
			int index = i; //jdk会自动对其加final
			new Thread() {
				public void run() {
					try {
						System.out.println("第"+(index+1)+"块开始下载");
						URLConnection conn = url.openConnection();//在每次循环都要获取一个连接对象
						InputStream in = conn.getInputStream();
						FileOutputStream out = new FileOutputStream(filename+index);
						
						//开始的字节数
						int beginBytes = index*blocksize;
						//结束的字节数
						int endBytes = beginBytes+blocksize;
						//结束的字节数不能超过文件的大小
						if(endBytes > filesize) {
							endBytes = filesize;
						}
						//跳过开始的字节数
						in.skip(beginBytes);
						/*
						 * 下载当前块内的字节数
						 */
						int position = beginBytes; //当前下载到的位置
						byte [] buffer = new byte [1024];
						int count;
						while( (count = in.read(buffer)) > 0) {
							if(position + count > endBytes) {
								//计算超出部分
								int breakBlcok = position + count - endBytes;
								//减去超出部分
								count = count - breakBlcok;
							}
							out.write(buffer,0,count);
							//更新下载位置（向前推进）
							position += count;
							//如果下载位置已经到达该块结束位置
							if(position >= endBytes) {
								break;
							}
						}
						in.close();
						out.close();
						System.out.println("第"+(index+1)+"块下载完成");
						synchronized (FileDownloadingPro.this) {
							FileDownloadingPro.this.downNums --;
							FileDownloadingPro.this.notify();
						}
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
			/*在此等待*/
			synchronized(this) {
				while(downNums > 0) {
					wait();
				}
			}
			marge(filename,blocknums);
			System.out.println("共用时"+(System.currentTimeMillis()-time)/1000+"秒");
			System.out.println("下载完成");
	}
		
	// 合并文件
	public static void marge(String path, int filenums) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		for (int i = 0; i < filenums; i++) {
			FileInputStream fis = new FileInputStream(path + i);
			byte[] buffer = new byte[1024];
			int count;
			while ((count = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fis.close();
		}
		fos.close();
	}
}
