package de.dfki.eliza.files.parsers;

import de.dfki.eliza.files.models.Info;
import de.dfki.eliza.files.models.Textable;
import de.dfki.eliza.files.parsers.dialog.*;
import de.dfki.eliza.files.utils.nameparser.DotParser;
import de.dfki.eliza.files.utils.nameparser.InfoNameParser;
import de.dfki.eliza.files.utils.nameparser.NameParserCollection;
import de.dfki.eliza.files.utils.nameparser.NameRegexFinder;
import de.dfki.eliza.renderer.Renderable;


/**
 * Created by alvaro on 3/13/17.
 */
public class InfoUserLineParser extends Dialog {

    private String agentName = "";

    private NameParserCollection nameParsers = new NameParserCollection();

    public InfoUserLineParser(){
        super();
        InfoNameParser regexFinder = new NameRegexFinder();
        InfoNameParser dotParser = new DotParser();
        nameParsers.add(regexFinder);
        nameParsers.add(dotParser);
    }

    public InfoUserLineParser(Renderable infoRender) {
        super(infoRender);
    }


    @Override
    public boolean parseLine(String line) {
        dialogLine = new DialogLine(line, INFO_LINE);
        valueLine = new NoValueLine();
        return isParseable(line);
    }

    @Override
    public void postParsed() {
        Textable info = new Info(dialogLine.getText(), render);
        conversationFactory.getConversation().addMessage(info);
        conversationFactory.getConversation().setSystemName(agentName);
    }


    public String getAgentName() {
        return agentName;
    }

    private boolean isParseable(String line) {
        boolean lineStartsWithInfoLine = line.startsWith(INFO_LINE);
        boolean isParseable = lineStartsWithInfoLine && line.contains(DotParser.NAME_START_SENTENCE);
        if(isParseable)
            agentName = nameParsers.getName(line);
        return isParseable;
    }


}
