package sec02.p997.read;

import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample2 {

   public static void main(String[] args) throws Exception {
      InputStream is = new FileInputStream("C:/Temp2/test.txt");
      
      int readByteNo;
      
      byte[] readBytes = new byte[3];
      //byte[] readBytes = new byte[100];
      
      String data = "";
      
      while( true ) {
         readByteNo = is.read(readBytes);
         if(readByteNo == -1) {
            break;
         }
         data += new String(readBytes, 0, readByteNo, "UTF-8");//EUC-KR
      }
      System.out.println(data);
      is.close();
   }

}