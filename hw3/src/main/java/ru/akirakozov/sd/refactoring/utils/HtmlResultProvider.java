package ru.akirakozov.sd.refactoring.utils;

/**
 * @author ilyakirpichev
 */
public class HtmlResultProvider {
    public static String prepare(String resultHeader, String answerBody) {
        return "<html><body>\n" +
                resultHeader + "\n" +
                answerBody + '\n' +
                "</body></html>\n";
    }
}
