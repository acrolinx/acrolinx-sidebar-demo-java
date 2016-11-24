import com.acrolinx.sidebar.AcrolinxSidebarPlugin;
import junit.framework.TestCase;
import org.junit.Test;

public class AcrolinxSidebarPluginTest extends TestCase
{

    public AcrolinxSidebarPluginTest(String name)
    {
        super(name);
    }

    @Test public void testInitPluginWithNull()
    {
        Throwable e = null;

        try {
            new AcrolinxSidebarPlugin(null, null);
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof NullPointerException);
    }

}
