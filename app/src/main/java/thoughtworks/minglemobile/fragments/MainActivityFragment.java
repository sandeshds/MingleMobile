package thoughtworks.minglemobile.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import thoughtworks.minglemobile.Controller.ProjectsController;
import thoughtworks.minglemobile.FragmentChangeListener;
import thoughtworks.minglemobile.Model.Project;
import thoughtworks.minglemobile.R;
import thoughtworks.minglemobile.Utils.Mapper.ResponseMapper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ProjectsController projectsController = new ProjectsController();
        ResponseMapper responseMapper = new ResponseMapper();
        final Bundle bundle = new Bundle();

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        final List<Project> projects;

        try {
            projects = projectsController.loadProjects();
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.listview,
                    responseMapper.getProjectNames(projects));
            ListView listView = (ListView) rootView.findViewById(R.id.mobile_list);
            listView.setAdapter(itemsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Fragment fr = new CardFragment();
                    FragmentChangeListener fc=(FragmentChangeListener)getActivity();
                    bundle.putString("projectId", projects.get(position).getIdentifier());
                    fr.setArguments(bundle);
                    fc.replaceFragment(fr);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;

    }
}
