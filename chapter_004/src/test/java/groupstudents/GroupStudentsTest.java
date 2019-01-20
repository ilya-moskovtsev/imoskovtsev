package groupstudents;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class GroupStudentsTest {

    @Test
    public void levelOf() {
        GroupStudents groupStudents = new GroupStudents();
        List<Student> input = Arrays.asList(
                new Student("name1", 1),
                null,
                new Student("name3", 3),
                null,
                new Student("name6", 4),
                null,
                new Student("name2", 2),
                null,
                new Student("name5", 5),
                null,
                new Student("name4", 4),
                null
        );

        Assert.assertThat(
                groupStudents.levelOf(input, 0),
                is(Arrays.asList(
                        new Student("name5", 5),
                        new Student("name4", 4),
                        new Student("name6", 4),
                        new Student("name3", 3),
                        new Student("name2", 2),
                        new Student("name1", 1)
                ))
        );

        Assert.assertThat(
                groupStudents.levelOf(input, 1),
                is(Arrays.asList(
                        new Student("name5", 5),
                        new Student("name4", 4),
                        new Student("name6", 4),
                        new Student("name3", 3),
                        new Student("name2", 2)
                ))
        );

        Assert.assertThat(
                groupStudents.levelOf(input, 2),
                is(Arrays.asList(
                        new Student("name5", 5),
                        new Student("name4", 4),
                        new Student("name6", 4),
                        new Student("name3", 3)
                ))
        );

        Assert.assertThat(
                groupStudents.levelOf(input, 3),
                is(Arrays.asList(
                        new Student("name5", 5),
                        new Student("name4", 4),
                        new Student("name6", 4)
                ))
        );

        Assert.assertThat(
                groupStudents.levelOf(input, 4),
                is(Collections.singletonList(
                        new Student("name5", 5)
                ))
        );

        Assert.assertThat(
                groupStudents.levelOf(input, 5),
                is(Collections.emptyList())
        );
    }
}