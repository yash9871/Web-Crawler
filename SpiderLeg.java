package com.stephen.crawler;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg
{
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36";
    private List<String> links = new LinkedList<String>();
    private Document htmlDoc;

    public boolean crawl(String url)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDoc = connection.get();
            this.htmlDoc = htmlDoc;
            if(connection.response().statusCode() == 200)
            {
                System.out.println("\nVisiting...\nReceived web page at " + url);
            }
            
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("Failure. Retrieved something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDoc.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
                
            }
            return true;
        }
        catch(IOException ioe)
        {
            return false;
        }
    }

    public boolean searchForWord(String searchWord)
    {
        if(this.htmlDoc == null)
        {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDoc.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }


    public List<String> getLinks()
    {
        return this.links;
    }

}