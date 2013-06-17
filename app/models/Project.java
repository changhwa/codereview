package models;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: changhwa
 */

@Entity
public class Project extends  Model{

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public String projectName;
    public String projectDescription;
    public String projectVcsUrl;

    @Transient
    public String projectVcsId;

    @Transient
    public String projectVcsPw;

    public static Model.Finder<String, Project> find = new Model.Finder<String, Project>(String.class, Project.class);

    Project(){}

    public static Long createProject(Project project){
        project.save();
        return project.id;
    }

    public static List<Project> findByName(String projectName){

        List<Project> projects = find.where()
                .like("projectName","%"+projectName+"%")
                .findList();

        return projects;


    }







}
