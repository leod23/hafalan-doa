package uinbdg.id.doa.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uinbdg.id.doa.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SampleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SampleFragment() {
        // Required empty public constructor
    }

    public static SampleFragment newInstance(String title, String desc) {
        SampleFragment fragment = new SampleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, desc);
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
        View v = inflater.inflate(R.layout.fragment_sample, container, false);
        unbinder = ButterKnife.bind(this, v);
        tvTitle.setText(getArguments().getString(ARG_PARAM1));
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
