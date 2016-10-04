package thoughtworks.minglemobile.Controller;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import thoughtworks.minglemobile.Model.Project;
import thoughtworks.minglemobile.Service.ProjectService;
import thoughtworks.minglemobile.Utils.Mapper.ResponseMapper;


public class ProjectsController {
    ProjectService projectService = new ProjectService();
    ResponseMapper responseMapper = new ResponseMapper();

    public List<Project> loadProjects() throws IOException, JSONException {
        List<Project> projects =  responseMapper.map(projectService.getAllProjects());
        return projects;
    }


}
