package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: changhwa
 */
@Entity
public class ProjectMember {

    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    public User user;

    @ManyToOne
    public Project project;

    public int role;

    public String vcsId;
    public String vcsPw;







}
