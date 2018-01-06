package com.example.loylogic.hackathon;

import android.app.Fragment;;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.loylogic.hackathon.Model.Department;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();
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
        View view =  inflater.inflate(R.layout.fragment_dash_board, container, false);

        TextView textView = view.findViewById(R.id.viewAllShareFeedback);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).goTOMenu(3);
            }
        });
        RecyclerView depatrmentRCView = (RecyclerView) view.findViewById(R.id.feedbackList);
        RecyclerView initiativeRCView = (RecyclerView) view.findViewById(R.id.initiativeList);
        RecyclerView statsRCView = (RecyclerView) view.findViewById(R.id.statsList);

        List<Department> departmentList = new ArrayList<>();
         departmentList.add(new Department("Education","#7E52D4","ic_education"));
        departmentList.add(new Department("HealthCare","#303F9F","ic_healthcare_1"));
        departmentList.add(new Department("Enviornment","#BEE422","ic_envirnment"));
        departmentList.add(new Department("Energy","#CB0F39","ic_energy"));

        DepartmentAdapter mAdapter = new DepartmentAdapter(departmentList,getActivity(), R.layout.department_list_row);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        depatrmentRCView.setLayoutManager(mLayoutManager);
        depatrmentRCView.setItemAnimator(new DefaultItemAnimator());
        SeparatorDecoration decoration = new SeparatorDecoration(getActivity(), Color.WHITE, 1.5f);
        depatrmentRCView.addItemDecoration(decoration);
        depatrmentRCView.setAdapter(mAdapter);




        List<Department> initiativetList = new ArrayList<>();
        initiativetList.add(new Department("Welfare","#FFFFFF","welfare"));
        initiativetList.add(new Department("Donation","#FFFFFF","dsc_1369"));
        initiativetList.add(new Department("Welfare","#FFFFFF","sample3"));
        initiativetList.add(new Department("Donation","#FFFFFF","dsc_1369"));

        DepartmentAdapter initiativeAdapter = new DepartmentAdapter(initiativetList,getActivity(), R.layout.initiative_row);
        RecyclerView.LayoutManager initiativemLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        initiativeRCView.setLayoutManager(initiativemLayoutManager);
        initiativeRCView.setItemAnimator(new DefaultItemAnimator());
        initiativeRCView.addItemDecoration(decoration);
        initiativeRCView.setAdapter(initiativeAdapter);


        List<Department> statList = new ArrayList<>();
        statList.add(new Department("","#FFFFFF","sample_1"));
        statList.add(new Department("","#FFFFFF","sample_2"));
        statList.add(new Department("","#FFFFFF","sample_1"));
        statList.add(new Department("","#FFFFFF","sample_2"));

        DepartmentAdapter statsAdapter = new DepartmentAdapter(initiativetList,getActivity(), R.layout.stats_row_item);
        RecyclerView.LayoutManager statsLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        statsRCView.setLayoutManager(statsLayoutManager);
        statsRCView.setItemAnimator(new DefaultItemAnimator());
        statsRCView.addItemDecoration(decoration);
        statsRCView.setAdapter(statsAdapter);

        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public static class SeparatorDecoration extends RecyclerView.ItemDecoration {
        private final Paint mPaint;

        public SeparatorDecoration(Context context, int color, float heightDp) {
            mPaint = new Paint();
            mPaint.setColor(color);
            final float thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    heightDp, context.getResources().getDisplayMetrics());
            mPaint.setStrokeWidth(thickness);
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

            // we want to retrieve the position in the list
            final int position = params.getViewAdapterPosition();

            // and add a separator to any view but the last one
            if (position < state.getItemCount()) {
                outRect.set(0, 0, (int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth()); // left, top, right, bottom
            } else {
                outRect.setEmpty(); // 0, 0, 0, 0
            }
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            // we set the stroke width before, so as to correctly draw the line we have to offset by width / 2
            final int offset = (int) (mPaint.getStrokeWidth() / 2);

            // this will iterate over every visible view
            for (int i = 0; i < parent.getChildCount(); i++) {
                // get the view
                final View view = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

                // get the position
                final int position = params.getViewAdapterPosition();

                // and finally draw the separator
                if (position < state.getItemCount()) {
                    c.drawLine(view.getLeft(), view.getBottom() + offset, view.getRight(), view.getBottom() + offset, mPaint);
                }
            }
        }
    }
}
