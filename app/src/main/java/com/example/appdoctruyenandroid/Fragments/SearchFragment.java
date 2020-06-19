package com.example.appdoctruyenandroid.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyenandroid.Adapters.ComicAdapter;
import com.example.appdoctruyenandroid.Adapters.MultipleChooseAdapter;
import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Models.Category;
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
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String search;
    private EditText txtSearch;
    private RecyclerView recycler_filter;
    private IMyAPI iMyAPI;
    private Button btn_filter,btn_search;
    private CompositeDisposable compositeDisposable= new CompositeDisposable();
    private AlertDialog dialog ;
    private TextView searchText;
    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //Init API
        recycler_filter = (RecyclerView) view.findViewById(R.id.recycler_filter);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        searchText = (TextView)view.findViewById(R.id.searchText);
        btn_search =(Button)view.findViewById(R.id.btn_search);
        dialog= new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("Cứ từ từ rồi cháo cũng nhừ")
                .build();

        recycler_filter.setHasFixedSize(true);
        recycler_filter.setLayoutManager(new GridLayoutManager(getContext(),2));
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                fetchCategory();
            }
        });
        btn_filter=(Button)view.findViewById(R.id.btn_ctg);
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(searchText.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                fetchSearhTruyen(searchText.getText().toString());

            }
        });
        return view;
    }
    private void fetchSearhTruyen(String b){
        searchText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        Comic a = new Comic(b);
        compositeDisposable.add(iMyAPI.getSearchTruyen(a)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comic>>() {
                    @Override
                    public void accept(List<Comic> comics) throws Exception {
                        recycler_filter.setVisibility(View.VISIBLE);
                        recycler_filter.setAdapter(new ComicAdapter(getContext(),comics));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        recycler_filter.setVisibility(View.INVISIBLE);

                    }
                }));
    }
    private void fetchCategory(){
        compositeDisposable.add(iMyAPI.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        Common.categories = categories;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                }));
    }
    private void showOptionDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Chọn thể loại truyện");

        LayoutInflater inflater = this.getLayoutInflater();
        View filter_layout = inflater.inflate(R.layout.dialog_options,null);

        RecyclerView recycler_options = (RecyclerView)filter_layout.findViewById(R.id.recycler_options);
        recycler_options.setHasFixedSize(true);
        recycler_options.setLayoutManager(new LinearLayoutManager(getContext()));
        MultipleChooseAdapter adapter = new MultipleChooseAdapter(getContext(),Common.categories);
        recycler_options.setAdapter(adapter);

        alertDialog.setView(filter_layout);
        alertDialog.setNegativeButton("TRỞ VỀ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("LỌC", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fetchFilterCategory(adapter.getFilterArray());
            }
        });
        alertDialog.show();
    }
    private void fetchFilterCategory(List<Integer> danhSachCategory){
        compositeDisposable.add(iMyAPI.getFilterComic(danhSachCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comic>>() {
                    @Override
                    public void accept(List<Comic> comics) throws Exception {
                        recycler_filter.setVisibility(View.VISIBLE);
                        recycler_filter.setAdapter(new ComicAdapter(getContext(),comics));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        recycler_filter.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                }));
    }
}