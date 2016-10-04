package thoughtworks.minglemobile.Utils.Mapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import thoughtworks.minglemobile.Model.Project;

@NoArgsConstructor
public class ResponseMapper {

    public List<Project> map (JSONArray projectsArray) throws JSONException {
        ArrayList<Project> projectsList = new ArrayList<Project>();

            for (int i=0;i<projectsArray.length();i++){
                projectsList.add(new Project(projectsArray.getJSONObject(i).getString("name"), projectsArray.getJSONObject(i).getString("identifier")));
            }

        return projectsList;
    }

    public List<String> getProjectNames(List<Project> projects) {
        List<String> projectNames = new ArrayList<>();
        for (int i=0;i<projects.size() ;i++) {
            projectNames.add(projects.get(i).getName());
        }
        return projectNames;
    }
}
