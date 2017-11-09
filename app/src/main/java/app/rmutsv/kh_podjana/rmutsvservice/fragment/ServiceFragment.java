package app.rmutsv.kh_podjana.rmutsvservice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.rmutsv.kh_podjana.rmutsvservice.MyServiceActivity;
import app.rmutsv.kh_podjana.rmutsvservice.R;

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
