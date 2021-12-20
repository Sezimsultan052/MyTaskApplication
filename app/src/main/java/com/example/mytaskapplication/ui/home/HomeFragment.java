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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mytaskapplication.R;
import com.example.mytaskapplication.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements TaskAdapter.OnItemClick {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TaskAdapter adapter = new TaskAdapter();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        initRv();
    }

    private void initRv() {
        adapter.setListener(this);
        binding.taskRv.setAdapter(adapter);

    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String text  = result.getString("text");
                //Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show();
                adapter.setText(text);
            }
        });
    }

    private void initListeners() {
        binding.actionBtn.setOnClickListener(v -> {
            openFragment();
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.formFragment);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(String txt) {
        Toast.makeText(requireActivity(), txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position) {
        Log.e("TAG", "pos"  + position);
        new AlertDialog.Builder(requireContext())
                .setMessage(" !!!!!!")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle("Do you remove item?")
                .setNegativeButton("No", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);
                        binding.taskRv.setAdapter(adapter);
                    }
                }).show();


    }
}