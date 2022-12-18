package projects;
import java.sql.*;
import java.util.*;
public class Product_info {
	static Connection c;
	static Scanner in=new Scanner(System.in);
        static int ppid,ppprice,ppoffer,ppgstno,ppstockno;
	static long pdcontactno;
	static String ppname,pcname,pcdetails,pdname,pddetails,pdemail,pppaydetails;
	public static Boolean insertProductData(int pid,int pprice,int poffer,long dcontactno,int pgstno,int pstockno,String pname,String cname,String cdetails,String dname,String ddetails,String demail,String ppaydetails)throws Exception {
		/*Product -- p , Dealer -- d ,Company -- c*/
		String q="insert into product_info values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=c.prepareStatement(q);
		ps.setInt(1, pid);
		ps.setString(2, pname);
		ps.setInt(3, pprice);
		ps.setInt(4, poffer);
		ps.setString(5, cname);
		ps.setString(6, cdetails);
		ps.setString(7, dname);
		ps.setString(8, ddetails);
		ps.setLong(9,dcontactno);
		ps.setString(10, demail);
		ps.setInt(11, pgstno);
		ps.setString(12, ppaydetails);
		ps.setInt(13,pstockno);
		int out=ps.executeUpdate();
		boolean out1=(out==1)? true:false;
		return out1;
		}
	public static void printProductData(int pid1)throws Exception {
		String q="select *from product_info where product_id=?";
		PreparedStatement ps=c.prepareStatement(q);
		ps.setInt(1, pid1);
		ResultSet rs=ps.executeQuery();
		rs.next();
		ppid=rs.getInt(1);
		ppname=rs.getString(2);
		ppprice=rs.getInt(3);
		ppoffer=rs.getInt(4);
		pcname=rs.getString(5);
		pcdetails=rs.getString(6);
		pdname=rs.getString(7);
		pddetails=rs.getString(8);
		pdcontactno=rs.getLong(9);
		pdemail=rs.getString(10);
		ppgstno=rs.getInt(11);
		pppaydetails=rs.getString(12);
		ppstockno=rs.getInt(13);
	}
	public static boolean deleteData(int pid)throws Exception {
		String q="delete from product_info where product_id=?";
		PreparedStatement ps=c.prepareStatement(q);
		ps.setInt(1, pid);
		int out=ps.executeUpdate();
		boolean out1=(out==1)? true:false;
                return out1;
	}
	public static boolean updateData(int no,int pid,String cn) throws Exception{
		String q="update product_info set #=? where product_id=?";
		String a1="";
		switch(no) {
			case 1:
				a1="Product_Name";
				break;
			case 2:
				a1="Product_Price";
				break;
			case 3:
				a1="Product_offer";
				break;
			case 4:
				a1="Company_Name";
				break;
			case 5:
				a1="Company_Details";
				break;
			case 6:
				a1="Dealer_Name";
				break;
			case 7:
				a1="Dealer_Deatails";
				break;
			case 8:
				a1="Dealer_Contact_no";
				break;
			case 9:
				a1="Dealer_Email";
				break;
			case 10:
				a1="Gst_no";
				break;
			case 11:
				a1="product_payment_details";
				break;
			case 12:
				a1="Stock_number";
				break;
		}
		String q1=q.replaceAll("#",a1);
		PreparedStatement ps=c.prepareStatement(q1);
		if(no==2 || no==3 || no==10 || no==12) {
                        int cn1=Integer.parseInt(cn);
			ps.setInt(1, cn1);
		}
		else if(no==8) {
                        long cn1=Long.parseLong(cn);
			ps.setLong(1, cn1);
		}
		else {
			ps.setString(1, cn);
		}
		ps.setInt(2, pid);
		int out=ps.executeUpdate();
		boolean out1=(out==1)? true:false;
                return out1;
	}
	public static void getConnection()throws Exception {
		String url="jdbc:mysql://localhost:3306/product_manage_system";
		String uname="root";
		String upass="";
		c=DriverManager.getConnection(url,uname,upass);
		System.out.println("Database Connected");
	}
}

