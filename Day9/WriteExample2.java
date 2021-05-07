package sec02.p1000.write;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteExample2 {

	public static void main(String[] args) throws Exception{
		OutputStream os = new FileOutputStream("C:/Temp2/test33.txt");
        byte[] data1 = "ABC\r\nDEF\\r\\n".getBytes();
        byte[] data2 = "자바를 열심히 복습하세요.".getBytes();        
        os.write(data1);
        os.write(data2);
        os.write(data2, 3, 6); //이렇게 중간에 몇 글자만 나오게 할 수도 있다.
        os.flush();
        os.close();
	}
}
