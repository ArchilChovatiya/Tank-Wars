import java.util.Date;

public class Testing {

	public Testing() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		 Date now = new Date();
		 // TODO Auto-generated method stub
		 Long longTime = new Long(now.getTime())/1000;
		 while (true) {
			 System.out.println(new Long(new Date().getTime()/1000)-longTime);
		 }
	}

}
