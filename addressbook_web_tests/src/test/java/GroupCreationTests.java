import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{
    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("groupName", "groupHeader", "groupFooter");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");

    }
}
