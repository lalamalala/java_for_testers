package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase{
    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static Stream<GroupData> randomGroup() throws IOException {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30));

            return Stream.generate(randomGroup).limit(1);
    }

    @ParameterizedTest
    @MethodSource("randomGroup")
    public void canCreateGroups(GroupData group) {
//        var oldGroups = app.groups().getList();
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();

        var maxID = newGroups.get(newGroups.size() - 1).id();
        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withID(newId));

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "sfsd","dsfsf")));
        return result;
    }

    //  @ParameterizedTest
    //  @ValueSource(strings = {"group name", "group name'"})
    //  public void canCreateGroup(String name) {
    //    int groupCount = app.groups().getCount();
    //  app.groups().createGroup(new GroupData(name, "header", "footer"));
    //    int newGroupCount = app.groups().getCount();
    //     Assertions.assertEquals(groupCount + 1, newGroupCount);
    //  }
}
