import java.io.*;
import java.sql.*;
class BlobDemo{
	public static void main(String args[])
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			PreparedStatement stmt=con.prepareStatement("create table image(name varchar2(10),image blob)");
			stmt.execute();
			System.out.println("table created sucessfully");
			PreparedStatement stmt2=con.prepareStatement("insert into image values(?,?)");
			stmt2.setString(1,args[0]);
			
			FileInputStream fis=new FileInputStream(args[1]);
			stmt2.setBinaryStream(2,fis,fis.available());
			stmt2.executeUpdate();
			System.out.println("image inserted sucessfully");
			PreparedStatement stmt3=con.prepareStatement("drop table image");
			stmt3.execute();
			System.out.println("table droped successfully");
			
		}catch(Exception e){
			System.out.println(e);}
	}
}