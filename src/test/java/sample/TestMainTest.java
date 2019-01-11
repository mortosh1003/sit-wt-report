package sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TestMainTest {

	@Test
	public void test() {
		List<String> actual;
		List<String> expected;
		try {
			//project直下パス
			actual = Files.lines(Paths.get("output.html"), StandardCharsets.UTF_8).collect(Collectors.toList());
			expected = Files.lines(Paths.get("expected.html"), StandardCharsets.UTF_8).collect(Collectors.toList());
			for (int i = 0; i < expected.size(); i++) {
				assertThat("line:" + i, actual.get(i), is(expected.get(i)));
			} 
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
