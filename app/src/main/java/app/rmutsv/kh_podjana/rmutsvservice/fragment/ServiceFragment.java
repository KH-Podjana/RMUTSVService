package app.rmutsv.kh_podjana.rmutsvservice.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import app.rmutsv.kh_podjana.rmutsvservice.MyServiceActivity;
import app.rmutsv.kh_podjana.rmutsvservice.R;
import app.rmutsv.kh_podjana.rmutsvservice.utility.DeleteData;
import app.rmutsv.kh_podjana.rmutsvservice.utility.GetAllData;
import app.rmutsv.kh_podjana.rmutsvservice.utility.ListViewAdapter;
import app.rmutsv.kh_podjana.rmutsvservice.utility.Myconstant;

/**
 * Created by lenovo on 9/11/2560.
 */

public class ServiceFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        String[] strings = getArguments().getStringArray("Login");
        Log.d("9novV1", "Login(1) on ServiceFragment ==> " +strings[1]);

//      Create Toolbar
        createToolbar(strings[1]);

//        Create ListView
        createListView();


    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.livUser);
        Myconstant myconstant = new Myconstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myconstant.getUrlGetAllUser());
            String resultJSON = getAllData.get();
            Log.d("9novV1", "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);

            final String[] nameStrings = new String[jsonArray.length()];
            String[] catStrings = new String[jsonArray.length()];
            String[] userStrings = new String[jsonArray.length()];
            String[] passwordStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                nameStrings[i] = jsonObject.getString("Name");
                catStrings[i] = jsonObject.getString("Category");
                userStrings[i] = jsonObject.getString("User");
                passwordStrings[i] = jsonObject.getString("Password");


            } //FOR

            ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(),
                    nameStrings, catStrings, userStrings, passwordStrings);
            listView.setAdapter(listViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    confirmDialog(nameStrings[i]);
                }
            });


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void confirmDialog(final String nameString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle("You Choose" + nameString);
        builder.setMessage("What do you want ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteDataWhere(nameString);
                dialogInterface.dismiss();



            }
        });

        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();


    }

    private void deleteDataWhere(String nameString) {
        try{

            Myconstant myconstant = new Myconstant();
            DeleteData deleteData = new DeleteData(getActivity());
            deleteData.execute(nameString, myconstant.getUrlDeleteData());

            if (Boolean.parseBoolean(deleteData.get())){
                Toast.makeText(getActivity(), "Delete Sucess", Toast.LENGTH_SHORT).show();



            }else {
                Toast.makeText(getActivity(), "Delete Error", Toast.LENGTH_SHORT).show();

            }

            createListView();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void createToolbar(String strTitle) {
        Toolbar toolbar = getView().findViewById(R.id.toolbarService);
        ((MyServiceActivity)getActivity()).setSupportActionBar(toolbar);
        ((MyServiceActivity) getActivity()).getSupportActionBar().setSubtitle("ยินดีต้อนรับคุณ");
        ((MyServiceActivity) getActivity()).getSupportActionBar().setTitle(strTitle);

    }

    public  static ServiceFragment serviceInstane(String[] strings){
        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", strings);
        serviceFragment.setArguments(bundle);

        return serviceFragment;




    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;


    }
}
