package com.example.screenforselectingplayer;

//import android.app.Fragment;
import com.example.prototype1.*;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ScreenSlidePageFragment extends Fragment {
   
    public static final String ARG_PAGE = "page";
    private int mPageNumber;
    private Context context;
    private ArrayList<Player> playerArr;
    private ArrayAdapter<Player> adapter;
    private ImageView iv;

	@SuppressLint("ValidFragment")
	public static ScreenSlidePageFragment create(Context context, ArrayList<Player> playerArr, ArrayAdapter<Player> adapter, int pageNumber, ImageView iv) {
		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment(context, playerArr, adapter, iv);
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment(Context context, ArrayList<Player> playerArr, ArrayAdapter<Player> adapter, ImageView iv) {
    	this.context = context;
    	this.playerArr = playerArr;
    	this.adapter = adapter;
    	this.iv = iv;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }
    
	private void registerClickCallback(GridView gv) {
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
				Player player = playerArr.get(position);
				String message = "you tapped " + player.getplayerName();
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
				iv.setImageResource(player.getPlayerImageId());
			}			
		});
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);
       
        GridView gv = (GridView) rootView.findViewById(R.id.gridView1);
        gv.setAdapter(adapter);
        registerClickCallback(gv);
        
        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}
