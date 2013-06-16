package support;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 */
public class SVNSupport {

    private String svnUrl;
    private String svnId;
    private String svnPw;

    public SVNSupport(){}

    public SVNSupport(String svnUrl, String svnId, String svnPw){
        this.svnUrl = svnUrl;
        this.svnId = svnId;
        this.svnPw = svnPw;

    }

    private void setupLibrary() {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
    }

    public SVNRepository loadSvnRepo(){

        setupLibrary();

        SVNRepository repository = null;

        try {

            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnUrl));
            Logger.info(repository.toString());
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(svnId, svnPw);
            repository.setAuthenticationManager(authManager);

        } catch (SVNException svne) {

            return null;

        }

        return repository;
    }

    public boolean connectionStatus(SVNRepository svnRepository){
        try {
            svnRepository.testConnection();
        } catch (SVNException e) {
            return false;
        }
        return true;
    }


}
