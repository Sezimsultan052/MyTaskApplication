package com.example.mytaskapplication.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytaskapplication.App;
import com.example.mytaskapplication.R;
import com.example.mytaskapplication.databinding.FragmentFormBinding;
import com.example.mytaskapplication.ui.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    private FragmentFormBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isEdited();

        //initListeners();

    }

    private void isEdited() {
        if (getArguments() != null) {
            binding.nameEt.setText(getArguments().getString("editName"));
            binding.surnameEt.setText(getArguments().getString("editSurname"));
            binding.saveBtn.setOnClickListener(view -> {
                editUser();
                close();
            });
        } else {
            binding.saveBtn.setOnClickListener(view -> {
                initListeners();
            });
        }
    }

    private void editUser() {
        Bundle bundleEDited = new Bundle();
      String name = binding.nameEt.getText().toString();
      String surname = binding.surnameEt.getText().toString();
      int id = getArguments().getInt("position");
      User userEdit = new User(id, name, surname);
        App.dataBase.userDao().updateUser(userEdit);
        bundleEDited.putString("editName", name);
        bundleEDited.putString("editSurname", surname);
        bundleEDited.putSerializable("editedUser", userEdit);
        getParentFragmentManager().setFragmentResult("UserEdited", bundleEDited);
        getParentFragmentManager().popBackStack();
    }

    private void initListeners() {
        binding.saveBtn.setOnClickListener(v -> {
            save();
            close();
        });

    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    private void save() {
//        String text = binding.taskEt.getText().toString();
//        Bundle bundle = new Bundle();
//        bundle.putString("text",  text);
//        getParentFragmentManager().setFragmentResult("key", bundle);

        String name = binding.nameEt.getText().toString();
        String surname = binding.surnameEt.getText().toString();
        User user = new User(name, surname);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        getParentFragmentManager().setFragmentResult("key", bundle);

    }
}