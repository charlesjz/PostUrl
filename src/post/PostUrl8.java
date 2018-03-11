package post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;


public class PostUrl8 {

	static int i=0;
	static int j=48;
	static int Thread_num=5;
	public static String urlString="http://www.dalian-jw.gov.cn:8080/lhsfc/querycx.asp"; 
	Lock lock= new ReentrantLock();
	Condition condition= lock.newCondition();
	
	public void loopA(){
		while(j<52){
			lock.lock();
			try{
				while( Thread_num <=0 ){
					condition.await();
				}
				Thread_num--;
				
				String cardString = getCardString(i,j);
		    	String a0=getHttpString(getString(cardString));
		    	if(a0.contains("姓名：</th>")|(!a0.contains("姓名"))){
		    		System.out.print("["+cardString+"]"+" X");
		    	}else{
		    		System.out.print("["+cardString+"]"+"   V");
		    	}
		    	if(j>51){
		    		j=48;
		    		i++;
		    	}
		    	j++;

		    	condition.signalAll();
		    	lock.unlock();
//		    	System.out.println(a0);
		    	Thread_num++;
	
			}catch(Exception e ){
				e.printStackTrace();
			}
			finally{
				System.out.println("");
			}
		}
	}
	public void loopB(){
		while(j<50){
			lock.lock();
			try{
				while( Thread_num <=0 ){
					condition.await();
				}
				Thread_num--;
				
				String cardString = getCardString(i,j);
		    	String a0=getHttpString(getString(cardString));
		    	if(a0.contains("姓名：</th>")|(!a0.contains("姓名"))){
		    		System.out.print("["+cardString+"]"+" X");
		    	}else{
		    		System.out.print("["+cardString+"]"+"   V");
		    	}
		    	if(j>45){
		    		j=0;
		    		i++;
		    	}
		    	j++;

		    	condition.signalAll();
		    	lock.unlock();
//		    	System.out.println(a0);
		    	Thread_num++;
	
			}catch(Exception e ){
				e.printStackTrace();
			}
			finally{
				System.out.println("");
			}
		}
	}
	public void loopC(){
		while(j<50){
			lock.lock();
			try{
				while( Thread_num <=0 ){
					condition.await();
				}
				Thread_num--;
				
				String cardString = getCardString(i,j);
		    	String a0=getHttpString(getString(cardString));
		    	if(a0.contains("姓名：</th>")|(!a0.contains("姓名"))){
		    		System.out.print("["+cardString+"]"+" X");
		    	}else{
		    		System.out.print("["+cardString+"]"+"   V");
		    	}
		    	if(j>45){
		    		j=0;
		    		i++;
		    	}
		    	j++;

		    	condition.signalAll();
		    	lock.unlock();
//		    	System.out.println(a0);
		    	Thread_num++;
	
			}catch(Exception e ){
				e.printStackTrace();
			}
			finally{
				System.out.println("");
			}
		}
	}
	public void loopD(){
		while(j<50){
			lock.lock();
			try{
				while( Thread_num <=0 ){
					condition.await();
				}
				Thread_num--;
				
				String cardString = getCardString(i,j);
		    	String a0=getHttpString(getString(cardString));
		    	if(a0.contains("姓名：</th>")|(!a0.contains("姓名"))){
		    		System.out.print("["+cardString+"]"+" X");
		    	}else{
		    		System.out.print("["+cardString+"]"+"   V");
		    	}
		    	if(j>45){
		    		j=0;
		    		i++;
		    	}
		    	j++;

		    	condition.signalAll();
		    	lock.unlock();
//		    	System.out.println(a0);
		    	Thread_num++;
	
			}catch(Exception e ){
				e.printStackTrace();
			}
			finally{
				System.out.println("");
			}
		}
	}
	public void loopE(){
		while(j<50){
			lock.lock();
			try{
				while( Thread_num <=0 ){
					condition.await();
				}
				Thread_num--;
				
				String cardString = getCardString(i,j);
		    	String a0=getHttpString(getString(cardString));
		    	if(a0.contains("姓名：</th>")|(!a0.contains("姓名"))){
		    		System.out.print("["+cardString+"]"+" X");
		    	}else{
		    		System.out.print("["+cardString+"]"+"   V");
		    	}
		    	if(j>45){
		    		j=0;
		    		i++;
		    	}
		    	j++;

		    	condition.signalAll();
		    	lock.unlock();
//		    	System.out.println(a0);
		    	Thread_num++;
	
			}catch(Exception e ){
				e.printStackTrace();
			}
			finally{
				System.out.println("");
			}
		}
	}
			
			

		@SuppressWarnings("resource")
		public static void main(String[] args) throws Exception {
			PostUrl8 post = new PostUrl8();
			
			
			new Thread(() -> post.loopA()).start();				
			new Thread(() -> post.loopB()).start();				
			new Thread(() -> post.loopC()).start();				
			new Thread(() -> post.loopD()).start();				
			new Thread(() -> post.loopE()).start();				

				
		}

	
    private static String getCardString(int i, int j) {
		return StringUtils.leftPad(""+i, 5,"0")+StringUtils.leftPad(""+j, 3,"0");
	}

	private static String getHttpString(String str) {
    	return sendPost(urlString, str);
		
	}

	private static String getString(String str) {
		
		return "lsbh="+str+"&verifycode=8107&backurl=";
	}

	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
//            System.out.print(",getResponseCode:"+((HttpURLConnection) conn).getResponseCode());
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
            		"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(),"GBK"));
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"GBK");
            in = new BufferedReader(isr);

            String line;
            
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
//                result += line;
            	sb.append(line);
            }
            	
            in.close();
            isr.close();
            out.close();
            result=sb.toString();
            
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
            return "";
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            
        }
        return result;
    }   
    
}
