package pe.com.orbis.tablayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.com.orbis.tablayout.R;
import pe.com.orbis.tablayout.intrface.FragmentInterface;

/**
 * Created by Ricardo Bravo on 29/04/16.
 */

public class ThreeFragment extends Fragment implements FragmentInterface{

    public ThreeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void updateSession() {
        Log.i("x- msge3", "abc");
    }
}