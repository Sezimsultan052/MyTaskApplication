package com.example.mytaskapplication.ui.profile;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytaskapplication.App;
import com.example.mytaskapplication.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private static final int GALLERY_REQUEST = 1;
    //String imageView;
    private FragmentProfileBinding binding;
    private ActivityResultLauncher<String> mGetContent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Uri  uri = (Uri) App.prefs.getPic();
        //binding.imageViewGalery.setImageURI(uri);
        binding.imageViewGalery.setImageURI(App.prefs.getPic());
        binding.userNameEt.setText(App.prefs.getInfo());
        return root;

        }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        binding.deleteBtn.setOnClickListener(v -> {
            App.prefs.clearPic();
            App.prefs.clearInfo();
            binding.imageViewGalery.setImageIcon(null);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initProfileListener();
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Glide.with(getContext()).load(result).apply(RequestOptions.circleCropTransform()).into(binding.imageViewGalery);
                binding.imageViewGalery.setImageURI(result);
                App.prefs.savePic(result.toString());

                binding.userNameEt.setText(result.toString());
                App.prefs.saveInfo(result.toString());
            }
        });

        //saveUsername();



    }

    private void saveUsername() {
        String text = binding.userNameEt.getText().toString();
        App.prefs.saveInfo(text);
    }

    private void initProfileListener() {
        binding.imageViewGalery.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });

    }
}