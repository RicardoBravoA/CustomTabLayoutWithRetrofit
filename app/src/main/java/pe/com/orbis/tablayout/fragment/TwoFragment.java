package pe.com.orbis.tablayout.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.com.orbis.tablayout.R;

/**
 * Created by Ricardo Bravo on 29/04/16.
 */

public class TwoFragment extends BaseFragment {

    private TextView txt;

    public TwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("x- onCreate", "hola");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        txt = (TextView) view.findViewById(R.id.txt);
        Log.i("x- onCreateView", "hola");
        return  view;
    }

    @Override
    public void verifySession(String data) {
        super.verifySession(data);
        Log.i("x- msge", data);
    }
}