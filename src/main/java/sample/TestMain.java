package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class TestMain {

	public static void main(String[] args) throws Exception {

		// Load EXCEL
		LoadFile test = new LoadFile();

		// 1. Configure FreeMarker
		//
		// You should do this ONLY ONCE, when your application starts,
		// then reuse the same Configuration object elsewhere.

		Configuration cfg = new Configuration();

		// Where do we load the templates from:
		cfg.setClassForTemplateLoading(TestMain.class, "templates");

		// Some other recommended settings:
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// 2. Proccess template(s)
		//
		// You will do this for several times in typical applications.

		// 2.1. Prepare the template input:

		Map<String, Object> input = new HashMap<String, Object>();

		input.put("title", "Report example");

		List<ValueExampleObject> systems = new ArrayList<ValueExampleObject>();
		systems.add(new ValueExampleObject("dir1/dir2/TestScript1.xlsx", "case:001", "3"));
		systems.add(new ValueExampleObject("dir1/dir2/TestScript1.xlsx", "case:002", "6"));
		systems.add(new ValueExampleObject("dir1/dir2/TestScript2.xlsx", "case:001", "10"));
		input.put("systems", systems);

		// 2.2. Get the template

		Template template = cfg.getTemplate("OutputTemplate.ftl");

		// 2.3. Generate the output

		// Write output to the console
		Writer consoleWriter = new OutputStreamWriter(System.out);
		template.process(input, consoleWriter);

		// For the sake of example, also write output into a file:
		Writer fileWriter = new FileWriter(new File("output.html"));
		try {
			template.process(input, fileWriter);
		} finally {
			fileWriter.close();
		}

	}
}