package support;

import helper.TestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 */
public class SVNSupportTest extends TestHelper{

    private SVNSupport svnSupport;

    private String svnUrl;
    private String svnId;
    private String svnPw;

    @Before
    public void setUp(){
        svnUrl = "https://dev.naver.com/svn/codereview";
        svnId = "changhwo";
        svnPw = "test1!";
        svnSupport = new SVNSupport(svnUrl, svnId, svnPw);
    }

    @Test
    public void testConnectionStatus(){
        boolean flag = svnSupport.connectionStatus(svnSupport.loadSvnRepo());
        assertThat(flag).isEqualTo(true);
    }
}
