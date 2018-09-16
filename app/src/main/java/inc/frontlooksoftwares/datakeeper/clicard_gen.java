package inc.frontlooksoftwares.datakeeper;

public class clicard_gen {
    private String fname;
    private String lname;
    private String mname;
    private String id;
    private String client_name;
    private String mobile_no;
    private String emailid;

    String x = "";

    public clicard_gen() {

        //empty constructor is needed.

    }

    public clicard_gen(String fname, String lname, String mname, String id, String mobile_no, String dob, String emailid) {
        this.fname = fname;
        this.lname = lname;
        this.mname = mname;
        this.id = id;
//        this.client_name = fname+mname+lname;
        this.mobile_no = mobile_no;
        this.emailid = emailid;
    }

    public String getFname() {
//        if (fname != null)
//        {
        return fname;
//        }
//        else {
//            return x;
//        }
    }

    public String getLname() {
//        if (lname != null)
//        {
        return lname;
//        }
//        else {
//            return x;
//        }
    }

    public String getMname() {
//        if (mname != null)
//        {
        return mname;
//        }
//        else {
//            return x;
//        }
    }

    public String getId() {
        id = "Client Id. " + id;
        return id;
    }

    public String getClient_name() {
        if (fname != null) {
            if (lname != null) {
                if (mname != null) {
                    client_name = "Name: " + fname + " " + mname + " " + lname;
                } else {
                    client_name = "Name: " + fname + " " + lname;
                }
            } else {
                client_name = "Name: " + fname + " ";
            }
        }
        return client_name;
    }

    public String getMobile_no() {
        mobile_no = "Mobile No. " + mobile_no;
        return mobile_no;
    }

    public String getEmailid() {
        return emailid;
    }
}
