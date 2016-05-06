package pe.com.orbis.tablayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pe.com.orbis.tablayout.R;

/**
 * Created by Ricardo Bravo on 06/05/2016.
 */

public class SevenFragment extends Fragment implements View.OnClickListener{

    private Button btn;

    public SevenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seven, container, false);

        btn = (Button) view.findViewById(R.id.btn);

        btn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn:
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.fragment_six, new OneFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
                break;
        }

    }
}
