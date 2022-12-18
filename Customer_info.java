package projects;
import java.sql.*;
public class Customer_info extends Product_info {
        static int cid,noprodpurch,cpoint;
	static long mno;
	static String fname,lname,cemail,caddress;
	public static boolean insertCustomerData(int cid,int noprodpurch,int cpoint,long mno,String fname,String lname,String cemail,String caddress)throws Exception {
		String q="insert into customer_info values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps=c.prepareStatement(q);
		ps.setInt(1,cid);
		ps.setString(2,fname);
		ps.setString(3, lname);
		ps.setLong(4, mno);
		ps.setString(5, cemail);
		ps.setString(6, caddress);
		ps.setInt(7, noprodpurch);
		ps.setInt(8,cpoint);
		int out=ps.executeUpdate();
		boolean out1=(out==1)? true:false;
                return out1;
	}
	public static boolean deleteCustomerData(int cid)throws Exception {
		String q="delete from customer_info where Customer_ID=?";
		PreparedStatement ps=c.prepareStatement(q);
		ps.setInt(1, cid);
		int out=ps.executeUpdate();
		boolean out1=(out==1)? true:false;
                return out1;
	}
	public static void printCustomerData(int pid1)throws Exception {
		String q="select *from customer_info where customer_id=?";
		PreparedStatement ps=c.prepareStatement(q);
		ps.setInt(1, pid1);
		ResultSet rs=ps.executeQuery();
		rs.next();
		cid=rs.getInt(1);
		fname=rs.getString(2);
		lname=rs.getString(3);
		mno=rs.getLong(4);
		cemail=rs.getString(5);
		caddress=rs.getString(6);
		noprodpurch=rs.getInt(7);
		cpoint=rs.getInt(8);
	}
	public static boolean updateCustomerData(int no,int cid,String cn)throws Exception {
		String q="update customer_info set #=? where customer_id=?";
		String a1="";
		switch(no) {
			case 1:
				a1="First_Name";
				break;
			case 2:
				a1="Last_Name";
				break;
			case 3:
				a1="Mobile_no";
				break;
			case 4:
				a1="Email_id";
				break;
			case 5:
				a1="Address";
				break;
			case 6:
				a1="No_product_Purchased";
				break;
			case 7:
				a1="Customer_Points";
				break;
		}
		String q1=q.replaceAll("#",a1);
		PreparedStatement ps=c.prepareStatement(q1);
		if(no==6 || no==7) {
			int cn1=Integer.parseInt(cn);
			ps.setInt(1, cn1);
		}
		else if(no==3) {
			long cn1=Long.parseLong(cn);
			ps.setLong(1, cn1);
		}
		else {
			ps.setString(1, cn);
		}
		ps.setInt(2, cid);
		int out=ps.executeUpdate();
		boolean out1=(out==1)? true:false;
                return out1;
	}
	
}

