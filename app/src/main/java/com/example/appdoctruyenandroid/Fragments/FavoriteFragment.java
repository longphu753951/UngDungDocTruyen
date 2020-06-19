package com.example.appdoctruyenandroid.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appdoctruyenandroid.Adapters.ComicAdapter;
import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Models.Comic;
import com.example.appdoctruyenandroid.R;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.Slider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
    private RecyclerView recyclerViewFavo;
    private IMyAPI iMyAPI;
    private CompositeDisposable compositeDisposable= new CompositeDisposable();
    private AlertDialog dialog ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        //Init API
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        //View
        dialog= new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("Cứ từ từ rồi cháo cũng nhừ")
                .build();
        //Lay Truyen Theo Ngay
        recyclerViewFavo = (RecyclerView) view.findViewById(R.id.recycler_view_favorite);
        recyclerViewFavo.setHasFixedSize(true);
        recyclerViewFavo.setLayoutManager(new GridLayoutManager(getContext(),2));
        fetch_comic();
        return view;
    }
    private void fetch_comic() {
        compositeDisposable.add(iMyAPI.getLikeTruyen(Common.signin_TaiKhoan.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comic>>() {
                    @Override
                    public void accept(List<Comic> comics) throws Exception {
                        recyclerViewFavo.setAdapter(new ComicAdapter(getContext(), comics));
                        dialog.dismiss();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                }));
    }
}