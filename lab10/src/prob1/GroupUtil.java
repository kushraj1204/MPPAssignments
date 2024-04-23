package prob1;
import java.util.*;

public class GroupUtil {
	//Fix this code
	public static <T> Group<?> copy(Group<? extends T> group) {
		List<?> elements = group.getElements();
		Group<T> grp2 = new Group<T>(group.getSpecialElement(), elements);
		//Group<T> grp = new Group<T>(group.getSpecialElement(), elements);
		return grp2;
	}

		//Test using this main method
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(0,1,2,3,4);
		Group<Integer> group = new Group<>(0, list);
		System.out.println(group);
		System.out.println(GroupUtil.copy(group));
	}
}
