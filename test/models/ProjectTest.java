package models;

import helper.TestHelper;
import org.junit.Before;
import org.junit.Test;
import play.Logger;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: changhwa
 */
public class ProjectTest extends TestHelper {

    private Project project;
    public String projectName;
    public String projectDescription;
    public String projectVcsUrl;
    public String projectVcsId;
    public String projectVcsPw;


    @Before
    public void setUp() {
        project = new Project();
        projectName = "Naon CodeReview";
        projectDescription = "코드리뷰시스템";
        projectVcsUrl = "https://dev.naver.com/svn/codereview";
        projectVcsId = "changhwo";
        projectVcsPw = "test1!";

    }

    @Test
    public void testCreateProject() {

        Long id = Project.createProject(project);
        assertThat(id).isNotEqualTo(null);
    }

    @Test
    public void testFindByName(){

        List<Project> projects = Project.findByName("Review");
        Logger.info(projects.get(0).id+"");
        Logger.info(projects.get(0).projectName);
        assertThat(projects.size()).isEqualTo(1);
        assertThat(projects.get(0).projectName).isEqualTo("Code Review");
    }


}
