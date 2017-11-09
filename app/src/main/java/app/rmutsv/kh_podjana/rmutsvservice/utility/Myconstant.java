package app.rmutsv.kh_podjana.rmutsvservice.utility;

/**
 * Created by lenovo on 8/11/2560.
 */

public class Myconstant {
    private String urlPostData = "http://androidthai.in.th/rmuts/addDataMaster.php";

    //  private String urlPostData = "http://androidthai.in.th/rmuts/addDataPodjana.php";

    private String urlGetAllUser = "http://androidthai.in.th/rmuts/getAllDataMaster.php";

    private String urlDeleteData = "http://androidthai.in.th/rmuts/deleteDataMaster.php";

    public String getUrlDeleteData(){
        return urlDeleteData;
    }

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String getUrlPostData() {
        return urlPostData;
    }
} //Main class



