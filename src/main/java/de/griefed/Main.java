package de.griefed;

import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.apache.commons.io.FileUtils;

public class Main {

  public static void main(String[] args) throws IOException {
    MutableDataSet options = new MutableDataSet();
    options.setFrom(ParserEmulationProfile.MULTI_MARKDOWN);
    options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");
    options.set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true);
    options.set(Parser.EXTENSIONS, Arrays.asList(
        AbbreviationExtension.create(),
        DefinitionExtension.create(),
        FootnoteExtension.create(),
        TablesExtension.create(),
        TypographicExtension.create()
    ));

    Parser parser = Parser.builder(options).build();
    Node document = parser.parse(
        FileUtils.readFileToString(new File("HELP.md"), StandardCharsets.UTF_8));
    HtmlRenderer renderer = HtmlRenderer.builder(options).build();
    String ABOUTWINDOWTEXT = "<html>" + renderer.render(document) + "</html>";



    Dimension ABOUTDIMENSION = new Dimension(1080, 520);
    JLabel label = new JLabel(ABOUTWINDOWTEXT);
    JScrollPane ABOUTWINDOWSCROLLPANE =
        new JScrollPane(
            label,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    ABOUTWINDOWSCROLLPANE.setMinimumSize(ABOUTDIMENSION);
    ABOUTWINDOWSCROLLPANE.setPreferredSize(ABOUTDIMENSION);
    ABOUTWINDOWSCROLLPANE.setMaximumSize(ABOUTDIMENSION);
    JOptionPane.showMessageDialog(
        null,
        ABOUTWINDOWSCROLLPANE,
        "HELP",
        JOptionPane.INFORMATION_MESSAGE);
  }
}
