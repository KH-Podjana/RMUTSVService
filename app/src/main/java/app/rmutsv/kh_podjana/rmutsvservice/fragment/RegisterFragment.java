package app.rmutsv.kh_podjana.rmutsvservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;


import app.rmutsv.kh_podjana.rmutsvservice.MainActivity;
import app.rmutsv.kh_podjana.rmutsvservice.R;
import app.rmutsv.kh_podjana.rmutsvservice.utility.MyAlert;

/**
 * Created by lenovo on 7/11/2560.
 */

public class RegisterFragment extends Fragment {

  // Explicit
    private String nameString, userString, passwordString, categoryString;
    private boolean aBoolean = true;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Toolbar Controller
        toolbarController();

//        Save Controller
        saveController();

//      Category Controller
        categoryController();




    } // Main Method

    private void categoryController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragCategory);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                aBoolean = false;
                switch (i) {
                    case R.id.radBuyer:
                        categoryString = "Buyer";
                        break;
                    case R.id.radSeller:
                        categoryString = "Seller";
                        break;

                }

            }
        });
    }

    private void saveController() {
        ImageView imageView = getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

   //        Initial View
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView() .findViewById(R.id.edtUser);
                EditText passwordEditText = getView() .findViewById(R.id.edtPassword);

//           change Data type
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

 //               Check Space การเช็คพื้นที่ว่างในฟอร์ม
                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
 //                   Have Space  ถ้ามีพื้นที่ว่างต้องแจ้งเตือน Alert Dialog
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("กรอกข้อมูลให้ครบสิค่ะ", "กรุณาอย่าเว้นช่องว่าง");
                } else if (aBoolean) {
//                      Non Choose Choice
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Non Choose Category",
                            "Please Choose Category");

                } else {
//                   Choosed Choice

                }

            }// onclick
        });
    }

    private void toolbarController() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity())
                .getSupportActionBar()
                .setTitle(getResources().getString(R.string.register));

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
} //Main Class
