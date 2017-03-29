package de.dfki.eliza.files.utils.nameparser.system;

import de.dfki.eliza.files.utils.nameparser.InfoNameParser;
import de.dfki.eliza.files.utils.nameparser.NameRegexFinder;

/**
 * Created by alvaro on 3/29/17.
 */
public class BracketsNameParser implements InfoNameParser {

    public static final String NAME_PREFIX = "{";
    public static final String NAME_SUFFIX = "}";
    private String name = "";
    private boolean parsed = false;

    @Override
    public boolean parse(String line) {
        checkSystemName(line);
        return parsed;
    }

    private void checkSystemName(String line) {
        if(line.startsWith(NAME_PREFIX)){
            int closingTag = line.indexOf(NAME_SUFFIX);
            String beginingName = line.substring(0, closingTag + 1);
            findNameInsideBrackets(beginingName);
        }
    }

    private void findNameInsideBrackets(String beginingName) {
        NameRegexFinder finder = new NameRegexFinder();
        parsed = finder.parse(beginingName);
        name = finder.getName();

    }

    @Override
    public String getName() {
        return name;
    }
}
