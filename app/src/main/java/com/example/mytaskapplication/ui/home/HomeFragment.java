package com.example.mytaskapplication.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mytaskapplication.App;
import com.example.mytaskapplication.R;
import com.example.mytaskapplication.databinding.FragmentHomeBinding;
import com.example.mytaskapplication.ui.models.User;

import java.text.Normalizer;
import java.util.List;

public class HomeFragment extends Fragment implements TaskAdapter.OnItemClick {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TaskAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adapter = new TaskAdapter();
        adapter.setListener(getContext(), this);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        setFragmentListener();
        App.dataBase.userDao().getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setList(users);
                Log.e("TAG", "LiveDATA worked ");
            }
        });
        initRv();
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.setList(App.dataBase.userDao().getAllUsers());
//    }

    private void initRv() {

        // adapter.setList(getList());
        binding.taskRv.setAdapter(adapter);

    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                User user = (User) result.getSerializable("user");
                App.dataBase.userDao().addUser(user);
                //Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show();
                //adapter.setUser(user);
            }
        });
        getParentFragmentManager().setFragmentResultListener("UserEdited", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                User user = (User) result.getSerializable("editedUser");

            }
        });
    }

    private void initListeners() {
        binding.actionBtn.setOnClickListener(v -> {
            openFragment();
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.formFragment);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(int position) {
        User userEdit = adapter.getList().get(position);
        Bundle bundleEdit = new Bundle();
        bundleEdit.putString("editName", userEdit.getName());
        bundleEdit.putString("editSurname", userEdit.getSurname());
        bundleEdit.putInt("position", userEdit.getId());
        FormFragment formFragment = new FormFragment();
        formFragment.setArguments(bundleEdit);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, formFragment);
        transaction.addToBackStack("FormFragment");
        transaction.commit();


        //binding.

        //Toast.makeText(requireActivity(), txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position) {
        //Log.e("TAG", "pos"  + position);
        new AlertDialog.Builder(requireContext())
                .setMessage(" !!!!!!")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle("Do you remove item?")
                .setNegativeButton("No", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);
//                        App.dataBase.userDao().deleteUser(user);
//                        adapter.removeItem(position);
//                        binding.taskRv.setAdapter(adapter);

                    }
                }).show();


    }
}