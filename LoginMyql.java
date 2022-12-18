package projects;
import java.sql.*;
public class LoginMyql extends Customer_info {
    private static String username;
    public static boolean login(String uname,String upass)throws Exception {
		String q="select User_name,Password from admin";
		PreparedStatement ps=c.prepareStatement(q);
		ResultSet rs=ps.executeQuery();
		int count=0;
		while(rs.next()){
			String uname1=rs.getString(1);
			String upass1=rs.getString(2);
			if(uname.equals(uname1) && upass.equals(upass1)) {
				count=1;
				username=uname;
			}
		}
		boolean out=(count==1)? true:false;
		return out;
	}
	public static String getuname() {
		String uname=username;
		return uname;
	}
	public static String createAccount(String uname,String upass,long mno)throws Exception {
		if(uname.length()<=8 && upass.length()<=8) {
			return "8";
		}
		else {
			String q="insert into admin values(?,?,?)";
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, uname);
			ps.setString(2, upass);
			ps.setLong(3, mno);
			int out=ps.executeUpdate();
			String out1=(out==1)? "Created":"Not";
			return out1;
		}
	}
	public static String deleteAccount(long mno)throws Exception {
		String q="select Mobile_Number from admin where User_name=?";
		String uname=LoginMyql.getuname();
		PreparedStatement ps=c.prepareStatement(q);
		ps.setString(1, uname);
		ResultSet rs=ps.executeQuery();
		rs.next();
		long mno1=rs.getLong(1);
		String q1="delete from admin where Mobile_Number=?";
		if(mno1==mno) {
			PreparedStatement ps1=c.prepareStatement(q1);
			ps1.setLong(1, mno1);
			int out=ps1.executeUpdate();
			String out1=(out==1)? "Deleted":"Not";
                        return out1;
		}
		else {
			return "wrong";
		}
	}
        static int gpid,gpprice;
        public static void getProductDetails(String str)throws Exception{
            String q="select Product_Id,Product_Price from product_info where Product_Name=?";
            PreparedStatement ps=c.prepareStatement(q);
            ps.setString(1, str);
            ResultSet rs=ps.executeQuery();
            rs.next();
            gpid=rs.getInt(1);
            gpprice=rs.getInt(2);   
        }
    
}
