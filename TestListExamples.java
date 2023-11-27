import static org.junit.Assert.*;
import org.junit.*;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

class TestStringChecker implements StringChecker {
    public boolean checkString(String s) {return true;}
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMerge() {
      List<String> input1 = Arrays.asList( "A" );
      List<String> input2 = Arrays.asList( "B", "C" );
      List<String> expect = Arrays.asList( "A", "B", "C" );
      List<String> output1 = ListExamples.merge(input1, input2);
      assertEquals(expect, output1);
  }

  @Test(timeout = 500)
  public void testMergeDuplicates() {
    List<String> left = Arrays.asList("a", "b");
    List<String> right = Arrays.asList("a", "b");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "b");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilter() {
    List<String> original = Arrays.asList("a", "a", "b", "b");
    ListExamples.filter(original, new IsMoon());
    List<String> expected = (List<String>) new ArrayList<String>();
    assertEquals(original,expected);
  }
}
