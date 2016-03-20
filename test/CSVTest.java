import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.io.CSV;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Red on 2016/3/21.
 */
public class CSVTest {
    private CSV _csv;
    @Before
    public void before(){
        _csv = new CSV(',','`');
    }

    @After
    public void after(){
    }

    @Test
    public void testEmptyDataForMethodReadCategoryDataset() throws IOException {
        Reader reader = new StringReader("");
        CategoryDataset result = _csv.readCategoryDataset(reader);
        assertEquals(0, result.getColumnCount());
        assertEquals(0, result.getRowCount());
    }

    @Test
    public void testNormalDataForMethodReadCategoryDataset() throws IOException {
        Reader reader = new StringReader("`1`,`2`,`3`\n" + "`1`,`2`,`3`\n" +
                "`4`,`5`,`6`");
        CategoryDataset result = _csv.readCategoryDataset(reader);
        assertEquals(2, result.getColumnCount());
        assertEquals(2, result.getRowCount());
        reader = new StringReader("`1`,`2`");
        result = _csv.readCategoryDataset(reader);
        assertEquals(0, result.getColumnCount());
        assertEquals(0, result.getRowCount());
    }


}
